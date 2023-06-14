package org.lamisplus.modules.ndr.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import com.google.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.lamisplus.modules.ndr.domain.dto.*;
import org.lamisplus.modules.ndr.domain.entities.NdrMessageLog;
import org.lamisplus.modules.ndr.domain.entities.NdrXmlStatus;
import org.lamisplus.modules.ndr.mapper.ConditionTypeMapper;
import org.lamisplus.modules.ndr.mapper.MessageHeaderTypeMapper;
import org.lamisplus.modules.ndr.mapper.PatientDemographicsMapper;
import org.lamisplus.modules.ndr.repositories.*;
import org.lamisplus.modules.ndr.schema.*;
import org.lamisplus.modules.ndr.utility.ZipUtility;
import org.springframework.stereotype.Service;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Service
@Slf4j
@RequiredArgsConstructor
public class NdrOptimizationService {
	
	private final NdrMessageLogRepository data;
	
	private final NDRService ndrService;
	
	private final MessageHeaderTypeMapper messageHeaderTypeMapper;
	private final PatientDemographicsMapper patientDemographicsMapper;
	private final ConditionTypeMapper conditionTypeMapper;
	
	private final NdrXmlStatusRepository ndrXmlStatusRepository;
	
	public static final String BASE_DIR = "runtime/ndr/transfer/";
	
	public static final String USER_DIR = "user.dir";
	
	public static final String JAXB_ENCODING = "UTF-8";
	public static final String XML_WAS_GENERATED_FROM_LAMISPLUS_APPLICATION = "\n<!-- This XML was generated from LAMISPlus application -->";
	public static final String HEADER_BIND_COMMENT = "com.sun.xml.bind.xmlHeaders";
	public final AtomicLong messageId = new AtomicLong(0);
	
	
	public void generatePatientsNDRXml(long facilityId, boolean initial) {
		final String pathname = BASE_DIR + "temp/" + facilityId + "/";
		log.info("folder -> "+ pathname);
		ndrService.cleanupFacility(facilityId, pathname);
		AtomicInteger generatedCount = new AtomicInteger();
		AtomicInteger errorCount = new AtomicInteger();
		LocalDateTime start = LocalDateTime.of(1984, 1, 1, 0, 0);
		List<String> patientIds = new ArrayList<String>();
		List<NDRErrorDTO> ndrErrors = new ArrayList<NDRErrorDTO>();
		if (initial) {
			patientIds = data.getPatientIdsEligibleForNDR(start, facilityId);
		} else {
			Optional<Timestamp> lastGenerateDateTimeByFacilityId =
					ndrXmlStatusRepository.getLastGenerateDateTimeByFacilityId(facilityId);
			if (lastGenerateDateTimeByFacilityId.isPresent()) {
				LocalDateTime lastModified =
						lastGenerateDateTimeByFacilityId.get().toLocalDateTime();
				patientIds = data.getPatientIdsEligibleForNDR(lastModified, facilityId);
			}
		}
		PatientDemographicDTO[] patientDemographicDTO = new PatientDemographicDTO[1];
		
		log.info("patient size -> "+ patientIds.size());
		patientIds.parallelStream()
				.forEach(id -> {
			if (getPatientNDRXml(id, facilityId, initial, ndrErrors)) {
				generatedCount.getAndIncrement();
				patientDemographicDTO[0] = data.getPatientDemographics(id, facilityId).get();
			} else {
				errorCount.getAndIncrement();
			}
		});
		log.info("generated  {}/{}", generatedCount.get(), patientIds.size());
		log.info("files not generated  {}/{}", errorCount.get(), patientIds.size());

		File folder = new File(BASE_DIR + "temp/" + facilityId + "/");
		log.info("fileSize {} bytes ", ZipUtility.getFolderSize(folder));
		if (ZipUtility.getFolderSize(folder) >= 15_000_000) {
			log.info(BASE_DIR + "temp/" + facilityId + "/" + " will be split into two");
		}
		if (generatedCount.get() > 0) {
			zipAndSaveTheFilesforDownload(
					facilityId,
					pathname,
					generatedCount,
					patientDemographicDTO[0],
					ndrErrors
					);
		}
		log.error("error list {}", ndrErrors);
	}
	
	
	
	
	private boolean getPatientNDRXml(String patientId, long facilityId, boolean initial, List<NDRErrorDTO> ndrErrors) {
		ObjectMapper objectMapper = new ObjectMapper();
		log.info("starting process patient xml file information");
		log.info("facilityId {}, patientId {}", facilityId, patientId);
		LocalDate start = LocalDate.of(1985, Month.JANUARY, 1);
		LocalDate end = LocalDate.now().plusDays(1);
		log.info("start {}, end {}", start, end);
		
		PatientDemographicDTO patientDemographic =
				getPatientDemographic(patientId, facilityId, ndrErrors);
		
		if (!initial && patientDemographic != null) {
			Optional<NdrMessageLog> messageLog =
					data.findFirstByIdentifierAndFileType(patientDemographic.getPatientIdentifier(), "treatment");
			if (messageLog.isPresent()) {
				start = messageLog.get().getLastUpdated().toLocalDate();
			}
		}
		List<EncounterDTO> patientEncounters =
				getPatientEncounters(patientId, facilityId, objectMapper, start, end, ndrErrors);
		
		List<RegimenDTO> patientRegimens =
				getPatientRegimens(patientId, facilityId, objectMapper, start, end, ndrErrors);
		if (patientDemographic == null) return false;
		String fileName = generatePatientNDRXml(facilityId, patientDemographic, patientEncounters, patientRegimens, initial, ndrErrors);
		if (fileName != null) {
			saveTheXmlFile(patientDemographic.getPatientIdentifier(), fileName);
		}
		return fileName != null;
	}
	
	
	public String generatePatientNDRXml(long facilityId, PatientDemographicDTO patientDemographic,
	                                    List<EncounterDTO> patientEncounters,
	                                    List<RegimenDTO> patientRegimens, boolean initial, List<NDRErrorDTO> ndrErrors) {
		log.info("generating ndr xml of patient with uuid {}", patientDemographic.getPatientIdentifier());
		try {
			log.info("fetching patient demographics....");
			long id = messageId.incrementAndGet();
			Container container = new Container();
			JAXBContext jaxbContext = JAXBContext.newInstance(Container.class);
			//caching this because is static
			PatientDemographicsType patientDemographics =
					patientDemographicsMapper.getPatientDemographics(patientDemographic);
			if (patientDemographics != null) {
				log.info("fetching treatment details... ");
				IndividualReportType individualReportType = new IndividualReportType();
				ConditionType conditionType =
						conditionTypeMapper.getConditionType(patientDemographic, patientEncounters, patientRegimens);
				individualReportType.setPatientDemographics(patientDemographics);
				MessageHeaderType messageHeader = messageHeaderTypeMapper.getMessageHeader(patientDemographic);
				String messageStatusCode = "INITIAL";
				if (!initial) {
					messageStatusCode = "UPDATED";
				}
				messageHeader.setMessageStatusCode(messageStatusCode);
				messageHeader.setMessageUniqueID(Long.toString(id));
				container.setMessageHeader(messageHeader);
				container.setIndividualReport(individualReportType);
				log.info("done fetching treatment details ");
				Marshaller jaxbMarshaller = ndrService.getMarshaller(jaxbContext);
				jaxbMarshaller.setProperty(HEADER_BIND_COMMENT, XML_WAS_GENERATED_FROM_LAMISPLUS_APPLICATION);
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, JAXB_ENCODING);
				SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
				Schema schema = sf.newSchema(getClass().getClassLoader().getResource("NDR 1.6.2.xsd"));
				jaxbMarshaller.setSchema(schema);
				String identifier = patientDemographics.getPatientIdentifier();
				if (conditionType != null) {
					individualReportType.getCondition().add(conditionType);
				}
				log.info("converting treatment details to xml... ");
				String fileName = ndrService.processAndGenerateNDRFile(facilityId, jaxbMarshaller, container, patientDemographic, id, ndrErrors);
				if (fileName != null) {
					log.info("NDR XML was successfully generated for patient with hospital number " + patientDemographic.getHospitalNumber());
				}
				return fileName;
			}
		} catch (Exception e) {
			log.error("An error occur when generating person with hospital number {}",
					patientDemographic.getHospitalNumber());
			log.error("error: " + e.getMessage());
			ndrErrors.add(new NDRErrorDTO(patientDemographic.getPersonUuid(),
					patientDemographic.getHospitalNumber(), e.getMessage()));
		}
		return null;
	}
	
	
	private List<RegimenDTO> getPatientRegimens(String patientId, long facilityId,
	                                            ObjectMapper objectMapper, LocalDate start, LocalDate end, List<NDRErrorDTO> ndrErrors) {
		try {
			PatientPharmacyEncounterDTO patientPharmacyEncounterDTO;
			Optional<PatientPharmacyEncounterDTO> patientPharmacyEncounter =
					data.getPatientPharmacyEncounter(patientId, facilityId, start, end);
			if (patientPharmacyEncounter.isPresent()) {
				patientPharmacyEncounterDTO = patientPharmacyEncounter.get();
				//log.info("patient pharmacy data {}", patientPharmacyEncounterDTO.getRegimens());
				List<RegimenDTO> patientRegimenList =
						getPatientRegimenList(patientPharmacyEncounterDTO, objectMapper, ndrErrors);
				//log.info("patientRegimenList {}", patientRegimenList);
				return patientRegimenList;
			}
		} catch (Exception e) {
			log.error("An error occurred while getting patient regimen list error {}", e.getMessage());
			ndrErrors.add(new NDRErrorDTO(patientId, "", e.getMessage()));
		}
		return null;
	}
	
	private List<EncounterDTO> getPatientEncounters(String patientId, long facilityId, ObjectMapper objectMapper, LocalDate start, LocalDate end, List<NDRErrorDTO> ndrErrors) {
		PatientEncounterDTO patientEncounterDTO;
		Optional<PatientEncounterDTO> patientEncounter =
				data.getPatientEncounter(patientId, facilityId, start, end);
		if (patientEncounter.isPresent()) {
			patientEncounterDTO = patientEncounter.get();
			List<EncounterDTO> patientEncounterDTOList =
					getPatientEncounterDTOList(patientEncounterDTO, objectMapper, ndrErrors);
			return patientEncounterDTOList;
		}
		
		return null;
	}
	
	
	
	private PatientDemographicDTO getPatientDemographic(String patientId, long facilityId, List<NDRErrorDTO> ndrErrors) {
		try {
			PatientDemographicDTO patientDemographicDTO;
			Optional<PatientDemographicDTO> patientDemographicDTOOptional =
					data.getPatientDemographics(patientId, facilityId);
			if (patientDemographicDTOOptional.isPresent()) {
				log.info("patient demographic information were retrieved successfully");
				patientDemographicDTO = patientDemographicDTOOptional.get();
				return patientDemographicDTO;
			}
		} catch (Exception e) {
			log.error("An error occur while fetching patient with uuid {} information error {}", patientId, e.getMessage());
			ndrErrors.add(new NDRErrorDTO(patientId, "", e.getMessage()));
		}
		return null;
	}
	
	private List<EncounterDTO> getPatientEncounterDTOList(
			PatientEncounterDTO patientEncounterDTO,
			ObjectMapper objectMapper,
			List<NDRErrorDTO> ndrErrors) {
		try {
			TypeFactory typeFactory = objectMapper.getTypeFactory();
			return objectMapper.readValue(patientEncounterDTO.getEncounters(), typeFactory.constructCollectionType(List.class, EncounterDTO.class));
		} catch (Exception e) {
			log.error("Error reading encounters of patient with uuid {} errorMsg {}",
					patientEncounterDTO.getPatientUuid(), e.getMessage());
			ndrErrors.add(new NDRErrorDTO(patientEncounterDTO.getPatientUuid(),
					"", e.getMessage()));
			
		}
		return new ArrayList<>();
	}
	
	private List<RegimenDTO> getPatientRegimenList(PatientPharmacyEncounterDTO pharmacyEncounterDTO, ObjectMapper objectMapper, List<NDRErrorDTO> ndrErrors) {
		try {
			TypeFactory typeFactory = objectMapper.getTypeFactory();
			return objectMapper.readValue(pharmacyEncounterDTO.getRegimens(), typeFactory.constructCollectionType(List.class, RegimenDTO.class));
		} catch (Exception e) {
			log.error("Error reading regimens of patient with uuid {}  errorMsg {}",
					pharmacyEncounterDTO.getPatientUuid(), e.getMessage());
			ndrErrors.add(new NDRErrorDTO(pharmacyEncounterDTO.getPatientUuid(), "", e.getMessage()));
		}
		return new ArrayList<>();
	}
	
	private void saveTheXmlFile(String identifier, String fileName) {
		NdrMessageLog ndrMessageLog = new NdrMessageLog();
		ndrMessageLog.setIdentifier(identifier);
		ndrMessageLog.setFile(fileName);
		ndrMessageLog.setLastUpdated(LocalDateTime.now());
		ndrMessageLog.setFileType("treatment");
		data.save(ndrMessageLog);
	}
	
	private void zipAndSaveTheFilesforDownload(
			Long facilityId,
			String pathname,
			AtomicInteger count,
			PatientDemographicDTO patient, List<NDRErrorDTO> ndrErrors) {
		try {
			String zipFileName = zipFiles(patient, facilityId, pathname, ndrErrors);
			NdrXmlStatus ndrXmlStatus = new NdrXmlStatus();
			if(ndrErrors.size() > 0){
				JsonNode node = getNode(ndrErrors);
				ndrXmlStatus.setError(node);
			}
			ndrXmlStatus.setFacilityId(facilityId);
			ndrXmlStatus.setFiles(count.get());
			ndrXmlStatus.setFileName(zipFileName);
			ndrXmlStatus.setLastModified(LocalDateTime.now());
			ndrXmlStatus.setPushIdentifier(patient.getFacilityId().concat("_").concat(patient.getPersonUuid()));
			ndrXmlStatus.setCompletelyPushed(Boolean.FALSE);
			ndrXmlStatus.setPercentagePushed(0L);
			ndrXmlStatusRepository.save(ndrXmlStatus);
		} catch (Exception e) {
			log.error("An error occurred while zipping files error {}", e.getMessage());
			ndrErrors.add(new NDRErrorDTO(patient.getPersonUuid(), patient.getHospitalNumber(), e.getMessage()));
		}
	}
	
	
	private JsonNode getNode(List<NDRErrorDTO> values) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return  mapper.valueToTree(values);
		} catch (Exception e) {
			log.error("An error occurred while converting error list to JsonB");
		}
		return null;
	}
	
	

	
	public String zipFiles(PatientDemographicDTO demographic,
	                       long facilityId,
	                       String sourceFolder,
	                       List<NDRErrorDTO> ndrErrors) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		String sCode = demographic.getStateCode();
		String lCode = demographic.getLgaCode();
		String fileName = StringUtils.leftPad(sCode, 2, "0") + "_" +
				StringUtils.leftPad(lCode, 3, "0") + "_" + demographic.getFacilityId() +
				"_" + demographic.getFacilityName() + "_" + dateFormat.format(new Date());
		fileName = RegExUtils.replaceAll(fileName, "/", "-");
		log.info("file name for download {}", fileName);
		String finalFileName = fileName.replace(" ", "").replace(",", "")
				.replace(".", "");
		String outputZipFile = null;
		try {
			outputZipFile = BASE_DIR + "ndr/" + finalFileName;
			new File(BASE_DIR + "ndr").mkdirs();
			new File(Paths.get(outputZipFile).toAbsolutePath().toString()).createNewFile();
			List<File> files = new ArrayList<>();
			files = ndrService.getFiles(sourceFolder, files);
			//log.info("Files: {}", files);
			long fifteenMB = FileUtils.ONE_MB * 15;
			File folder = new File(BASE_DIR + "temp/" + facilityId + "/");
			if (ZipUtility.getFolderSize(folder) > fifteenMB) {
				List<List<File>> splitFiles = split(files, fifteenMB);
				for (int i = 0; i < splitFiles.size(); i++) {
					String splitFileName = finalFileName + "_" + (i + 1);
					String splitOutputZipFile = BASE_DIR + "ndr/" + splitFileName;
					new File(Paths.get(splitOutputZipFile).toAbsolutePath().toString()).createNewFile();
					zip(splitFiles.get(i), Paths.get(splitOutputZipFile).toAbsolutePath().toString());
				}
				return finalFileName + "_1";
			} else {
				ZipUtility.zip(files, Paths.get(outputZipFile).toAbsolutePath().toString(), fifteenMB);
				return finalFileName;
			}
		} catch (Exception exception) {
			ndrErrors.add(new NDRErrorDTO(demographic.getPersonUuid(), demographic.getHospitalNumber(), exception.getMessage()));
			log.error("An error occurred while creating temporary file " + outputZipFile);
		}
		return null;
	}
	
	public static List<List<File>> split(List<File> files, long sizeLimit) {
		List<List<File>> splitFiles = new ArrayList<>();
		List<File> currentSplit = new ArrayList<>();
		long currentSize = 0;
		for (File file : files) {
			long fileSize = file.length();
			if (currentSize + fileSize > sizeLimit) {
				splitFiles.add(currentSplit);
				currentSplit = new ArrayList<>();
				currentSize = 0;
			}
			currentSplit.add(file);
			currentSize += fileSize;
		}
		splitFiles.add(currentSplit);
		return splitFiles;
	}
	
	public static void zip(List<File> files, String outputZipFile) throws IOException {
		try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(outputZipFile))) {
			for (File file : files) {
				try (FileInputStream fileIn = new FileInputStream(file)) {
					ZipEntry zipEntry = new ZipEntry(file.getName());
					zipOut.putNextEntry(zipEntry);
					byte[] bytes = new byte[1024];
					int length;
					while ((length = fileIn.read(bytes)) >= 0) {
						zipOut.write(bytes, 0, length);
					}
				}
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
	

