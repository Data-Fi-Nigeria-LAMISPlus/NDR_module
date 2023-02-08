package org.lamisplus.modules.ndr.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.lamisplus.modules.base.domain.dto.ApplicationCodesetDTO;
import org.lamisplus.modules.base.service.ApplicationCodesetService;
import org.lamisplus.modules.hiv.domain.dto.HIVStatusTrackerDto;
import org.lamisplus.modules.hiv.domain.entity.ARTClinical;
import org.lamisplus.modules.hiv.domain.entity.Regimen;
import org.lamisplus.modules.hiv.repositories.ARTClinicalRepository;
import org.lamisplus.modules.hiv.repositories.HivEnrollmentRepository;
import org.lamisplus.modules.hiv.repositories.RegimenRepository;
import org.lamisplus.modules.hiv.service.HIVStatusTrackerService;
import org.lamisplus.modules.ndr.domain.PatientDemographics;
import org.lamisplus.modules.ndr.schema.CodedSimpleType;
import org.lamisplus.modules.ndr.schema.ConditionSpecificQuestionsType;
import org.lamisplus.modules.ndr.schema.HIVQuestionsType;
import org.lamisplus.modules.ndr.service.NDRCodeSetResolverService;
import org.lamisplus.modules.patient.repository.PersonRepository;
import org.lamisplus.modules.triage.domain.entity.VitalSign;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.lamisplus.modules.ndr.utility.DateUtil.getXmlDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConditionSpecificQuestionsTypeMapper {

    private final NDRCodeSetResolverService ndrCodeSetResolverService;
    private final HivEnrollmentRepository hivEnrollmentRepository;

    private final ApplicationCodesetService applicationCodesetService;

    private final ARTClinicalRepository artClinicalRepository;
    private final PersonRepository personRepository;

    private final RegimenRepository regimenRepository;

    private final HIVStatusTrackerService hivStatusTrackerService;
// improve performance

    public ConditionSpecificQuestionsType getConditionSpecificQuestionsType(PatientDemographics demographics) {
        try {
            ConditionSpecificQuestionsType hivQuestions = new ConditionSpecificQuestionsType ();
            HIVQuestionsType hiv = new HIVQuestionsType ();
            processAndSetDateOfRegistration (hiv, demographics.getDateOfRegistration(), demographics.getStatusAtRegistration());
            processAndSetCareEntryPoint (hiv, demographics.getCareEntryPoint());
            if (demographics.getDateOfRegistration() != null) {
                                String enrollmentStatus = demographics.getStatusAtRegistration();
                                processAndHandleARTStatus (hiv, demographics.getId (), enrollmentStatus);
            }
                
                List<ARTClinical> artCommencement = artClinicalRepository.findByArchivedAndIsCommencementIsTrue (0);
                if (artCommencement.size () == 1) {
                    ARTClinical firstArtClinical = artCommencement.get (0);
                    processAndSetArtStartDate (hiv, firstArtClinical);
                    VitalSign vitalSign = firstArtClinical.getVitalSign ();
                    if (vitalSign != null) {
                        processAndSetHeightAndWeight (hiv, vitalSign);
                    }
                    processAndSetWHOStagingAndFunctionalStatus (hiv, firstArtClinical);
                    long regimenId = firstArtClinical.getRegimenId ();
                    Optional<Regimen> regimenOptional = regimenRepository.findById (regimenId);
                    regimenOptional.ifPresent (regimen -> {
                        String ndrRegimen = StringUtils.trim (regimen.getComposition ()) + "_" + regimen.getRegimenType ().getId ();
                        System.out.println("ndrRegimen: " + ndrRegimen);
                        Optional<CodedSimpleType> simpleCodeSet = ndrCodeSetResolverService.getSimpleCodeSet (ndrRegimen);
                        simpleCodeSet.ifPresent (hiv::setFirstARTRegimen);
                    });
                    processAndSetCD4 (hiv, demographics.getAge(), firstArtClinical);
                }
                hivQuestions.setHIVQuestions (hiv);
            return hivQuestions;
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return null;

    }

    private void processAndSetHeightAndWeight(HIVQuestionsType hiv, VitalSign vitalSign) {
        Double bodyWeight = vitalSign.getBodyWeight ();
        if (bodyWeight > 0) {
            hiv.setWeightAtARTStart (bodyWeight.intValue ());
        }
        if (bodyWeight.intValue () > 200) {
            int weight = bodyWeight.intValue () / 10;
            hiv.setWeightAtARTStart (weight);
        }
        Double height = vitalSign.getHeight ();
        if (height > 0) {
            int heightInCm = (int) (height * 100);
            if (heightInCm > 200) {
                heightInCm = heightInCm / 10;
            }
            hiv.setChildHeightAtARTStart (heightInCm);
        }
    }

    private void processAndSetWHOStagingAndFunctionalStatus(HIVQuestionsType hiv, ARTClinical firstArtClinical) {
        Long whoStagingId = firstArtClinical.getWhoStagingId ();
        Long functionalStatusId = firstArtClinical.getFunctionalStatusId ();
        ApplicationCodesetDTO WHOStageCode = applicationCodesetService.getApplicationCodeset (whoStagingId);
        if (WHOStageCode != null) {
            Optional<String> whoStageCodeSet =
                    ndrCodeSetResolverService.getNDRCodeSetCode ("WHO_STAGE", WHOStageCode.getDisplay ());
            whoStageCodeSet.ifPresent (hiv::setWHOClinicalStageARTStart);
        }
        ApplicationCodesetDTO functionalStatusCode = applicationCodesetService.getApplicationCodeset (functionalStatusId);
        if (functionalStatusCode != null) {
            Optional<String> functionalStatusCodeSet =
                    ndrCodeSetResolverService.getNDRCodeSetCode ("FUNCTIONAL_STATUS", functionalStatusCode.getDisplay ());
            functionalStatusCodeSet.ifPresent (hiv::setFunctionalStatusStartART);
        }
    }

    private void processAndSetDateOfRegistration(HIVQuestionsType hiv, LocalDate dateOfRegistration, String statusAtRepresentation) {
        try {
            if (dateOfRegistration != null) {
                hiv.setEnrolledInHIVCareDate (getXmlDate (Date.valueOf (dateOfRegistration)));
                if (statusAtRepresentation != null) {
                    if (statusAtRepresentation.equalsIgnoreCase ("HIV+ non ART")) {
                        hiv.setFirstConfirmedHIVTestDate (getXmlDate (Date.valueOf (dateOfRegistration)));
                    }
                    if (statusAtRepresentation.equalsIgnoreCase ("ART Transfer In")) {
                        hiv.setTransferredInDate (getXmlDate (Date.valueOf (dateOfRegistration)));
                    }
                }
            }
        } catch (Exception ignore) {
        
        }
    }

    private void processAndSetCareEntryPoint(HIVQuestionsType hiv, String careEntryPoint) {
       if(careEntryPoint != null) {
           Optional<String> careEntryPointNdrCodeSet = ndrCodeSetResolverService.getNDRCodeSetCode("CARE_ENTRY_POINT", careEntryPoint);
           careEntryPointNdrCodeSet.ifPresent(hiv::setCareEntryPoint);
       }
    }

    private void processAndSetArtStartDate(HIVQuestionsType hiv, ARTClinical firstArtClinical) {
        try {
            LocalDate visitDate = firstArtClinical.getVisitDate ();
            if (visitDate != null) {
                hiv.setARTStartDate (getXmlDate (Date.valueOf (visitDate)));
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    private void processAndHandleARTStatus(HIVQuestionsType hiv, Long personId, String enrollmentStatus) {
        try {
            String ndrARTStatus = enrollmentStatus == null ? "Pre-ART" : "ART";
            String status = hivStatusTrackerService.getPersonCurrentHIVStatusByPersonId (personId).getStatus();
            handlePatientTransferOut (hiv, personId, ndrARTStatus, status);
            handlePatientDeathStatus (hiv, personId, ndrARTStatus, status);
        } catch (Exception e) {
            e.printStackTrace ();
        }

    }

    private void handlePatientDeathStatus(HIVQuestionsType hiv, Long personId, String ndrARTStatus, String status) {
        if (status.contains ("Died")) {
            Optional<HIVStatusTrackerDto> patientStatus = getPatientStatus (personId, status);
            patientStatus.ifPresent (currentStatus -> {
                Optional<String> artStatus = ndrCodeSetResolverService.getNDRCodeSetCode ("ART_STATUS", ndrARTStatus);
                artStatus.ifPresent (hiv::setStatusAtDeath);
                try {
                    hiv.setDeathDate (getXmlDate (Date.valueOf (currentStatus.getStatusDate ())));
                } catch (DatatypeConfigurationException e) {
                    throw new RuntimeException (e);
                }
                hiv.setPatientHasDied (true);
            });

        }
    }

    private void handlePatientTransferOut(HIVQuestionsType hiv, Long personId, String ndrARTStatus, String status) {
        if (status.contains ("Out")) {
            Optional<HIVStatusTrackerDto> patientStatus = getPatientStatus (personId, status);
            patientStatus.ifPresent (currentStatus -> {
                Optional<String> artStatus = ndrCodeSetResolverService.getNDRCodeSetCode ("ART_STATUS", ndrARTStatus);
                artStatus.ifPresent (hiv::setTransferredOutStatus);
                try {
                    hiv.setTransferredOutDate (getXmlDate (Date.valueOf (currentStatus.getStatusDate ())));
                } catch (DatatypeConfigurationException e) {
                    throw new RuntimeException (e);
                }
                hiv.setPatientTransferredOut (true);
            });
        } else {
            hiv.setPatientTransferredOut (false);
        }
    }

    @NotNull
    private Optional<HIVStatusTrackerDto> getPatientStatus(Long personId, String status) {
        return hivStatusTrackerService.getPersonHIVStatusByPersonId (personId)
                .stream ()
                .filter (s -> s.getHivStatus ().equals (status))
                .findFirst ();
    }

    private void processAndSetCD4(HIVQuestionsType hiv, int age, ARTClinical artCommence) {
        Long cd4 = artCommence.getCd4 ();
        Long cd4p = artCommence.getCd4Percentage ();
        String clinicalStage = null;
        String eligible = null;
        if (cd4 == null) {
            cd4 = 0l;
        }
        if (cd4 == null) {
            cd4p = 0l;
        }
        String whyEligible = "WHY_ELIGIBLE";
        if (age >= 15) {
            if (cd4 < 350) {
                Optional<String> ndrCodeSet = ndrCodeSetResolverService.getNDRCodeSetCode (whyEligible, "CD4");
                if (ndrCodeSet.isPresent ()) eligible = ndrCodeSet.get ();
            } else {
                ApplicationCodesetDTO WHOStageCode = applicationCodesetService.getApplicationCodeset (artCommence.getWhoStagingId ());
                if (WHOStageCode != null) {
                    clinicalStage = WHOStageCode.getDisplay ();
                    if (clinicalStage.equalsIgnoreCase ("Stage III") ||
                            clinicalStage.equalsIgnoreCase ("Stage IV")) {
                        Optional<String> ndrCodeSet = ndrCodeSetResolverService.getNDRCodeSetCode (whyEligible, "Staging");
                        if (ndrCodeSet.isPresent ()) eligible = ndrCodeSet.get ();
                    }
                }
            }
        } else {
            if (cd4 < 750 || cd4p < 25) {
                Optional<String> ndrCodeSet;
                if (cd4 < 25) {
                    ndrCodeSet = ndrCodeSetResolverService.getNDRCodeSetCode (whyEligible, "CD4p");
                } else {
                    ndrCodeSet = ndrCodeSetResolverService.getNDRCodeSetCode (whyEligible, "CD4");
                }
                if (ndrCodeSet.isPresent ()) eligible = ndrCodeSet.get ();
            } else {
                if (clinicalStage.equalsIgnoreCase ("Stage III") ||
                        clinicalStage.equalsIgnoreCase ("Stage IV")) {
                    Optional<String> ndrCodeSet = ndrCodeSetResolverService.getNDRCodeSetCode (whyEligible, "Staging");
                    if (ndrCodeSet.isPresent ()) eligible = ndrCodeSet.get ();
                }
            }
        }
        try {
            hiv.setARTStartDate (getXmlDate (Date.valueOf (artCommence.getVisitDate ())));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace ();
        }
        try {
            hiv.setMedicallyEligibleDate (getXmlDate (Date.valueOf (artCommence.getVisitDate ())));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace ();
        }
        if (eligible != null && ! eligible.isEmpty ()) hiv.setReasonMedicallyEligible (eligible);
    }


}