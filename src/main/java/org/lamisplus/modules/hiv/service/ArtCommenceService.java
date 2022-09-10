package org.lamisplus.modules.hiv.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.audit4j.core.util.Log;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lamisplus.modules.base.controller.apierror.EntityNotFoundException;
import org.lamisplus.modules.base.controller.apierror.IllegalTypeException;
import org.lamisplus.modules.base.controller.apierror.RecordExistException;
import org.lamisplus.modules.base.domain.entities.ApplicationCodeSet;
import org.lamisplus.modules.base.domain.repositories.ApplicationCodesetRepository;
import org.lamisplus.modules.hiv.domain.dto.ARTClinicalCommenceDto;
import org.lamisplus.modules.hiv.domain.dto.HIVStatusTrackerDto;
import org.lamisplus.modules.hiv.domain.entity.ARTClinical;
import org.lamisplus.modules.hiv.domain.entity.HIVStatusTracker;
import org.lamisplus.modules.hiv.domain.entity.HivEnrollment;
import org.lamisplus.modules.hiv.repositories.ARTClinicalRepository;
import org.lamisplus.modules.hiv.repositories.HivEnrollmentRepository;
import org.lamisplus.modules.patient.domain.entity.Person;
import org.lamisplus.modules.patient.domain.entity.Visit;
import org.lamisplus.modules.patient.service.PersonService;
import org.lamisplus.modules.triage.domain.dto.VitalSignDto;
import org.lamisplus.modules.triage.domain.entity.VitalSign;
import org.lamisplus.modules.triage.repository.VitalSignRepository;
import org.lamisplus.modules.triage.service.VitalSignService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtCommenceService {
	
	private final ARTClinicalRepository artClinicalRepository;
	private final HivEnrollmentRepository hivEnrollmentRepository;
	
	private final HIVStatusTrackerService hivStatusTrackerService;
	
	
	private final VitalSignService vitalSignService;
	
	private final ApplicationCodesetRepository applicationCodesetRepository;
	private final CurrentUserOrganizationService organizationUtil;
	
	
	private final VitalSignRepository vitalSignRepository;
	
	
	private final HandleHIVVisitEncounter handleHIVisitEncounter;
	
	public ARTClinicalCommenceDto createArtCommence(ARTClinicalCommenceDto artClinicalCommenceDto) {
		Long personId = artClinicalCommenceDto.getPersonId();
		Long hivEnrollmentId = artClinicalCommenceDto.getHivEnrollmentId();
		Optional<ApplicationCodeSet> hivStatus =
				applicationCodesetRepository.findByDisplayAndCodesetGroup("ART Start", "HIV_STATUS");
		StringBuilder statusDisplay = new StringBuilder();
		HivEnrollment hivEnrollment =
				hivEnrollmentRepository
						.findById(hivEnrollmentId)
						.orElseThrow(() -> new EntityNotFoundException(HivEnrollment.class, "id", "" + hivEnrollmentId));
		boolean isEnrollmentDateAfterArtCommence = hivEnrollment.getDateOfRegistration().isAfter(artClinicalCommenceDto.getVisitDate());
		
		if (isEnrollmentDateAfterArtCommence) {
			final String message = artClinicalCommenceDto.getVisitDate() + " is below enrollment date " + hivEnrollment.getDateOfRegistration();
			throw new IllegalTypeException(ARTClinical.class, "Art commence date", message);
		}
		Person person = hivEnrollment.getPerson();
		boolean artCommenceExist = artClinicalRepository.findByPersonAndIsCommencementIsTrueAndArchived(person, 0).isPresent();
		if (artCommenceExist) throw new RecordExistException(ARTClinical.class, "personId", "" + personId);
		Visit visit = handleHIVisitEncounter.processAndCreateVisit(personId, artClinicalCommenceDto.getVisitDate());
		if (visit != null) {
			artClinicalCommenceDto.setVisitId(visit.getId());
			artClinicalCommenceDto.getVitalSignDto().setVisitId(visit.getId());
		}
		Long vitalSignId = artClinicalCommenceDto.getVitalSignId();
		vitalSignId = getVitalSignId(artClinicalCommenceDto, visit, vitalSignId);
		ARTClinical artClinical = convertDtoToART(artClinicalCommenceDto, vitalSignId);
		artClinical.setUuid(UUID.randomUUID().toString());
		artClinical.setIsCommencement(true);
		if (hivStatus.isPresent()) {
			artClinical.setArtStatusId(hivStatus.get().getId());
			statusDisplay.append(hivStatus.get().getDisplay());
		}
		artClinical.setArchived(0);
		artClinical.setHivEnrollment(hivEnrollment);
		artClinical.setPerson(person);
		artClinical.setVisit(visit);
		ARTClinical saveArtClinical = artClinicalRepository.save(artClinical);
		processAndSaveHIVStatus(saveArtClinical, statusDisplay.toString());
		return convertArtToResponseDto(saveArtClinical);
	}
	
	@Nullable
	private Long getVitalSignId(ARTClinicalCommenceDto artClinicalCommenceDto, Visit visit, Long vitalSignId) {
		if (vitalSignId == null) {
			Optional<VitalSign> existingVitalSign = vitalSignRepository.getVitalSignByVisitAndArchived(visit, 0);
			if (!existingVitalSign.isPresent()) {
				vitalSignId = processAndSaveVitalSign(artClinicalCommenceDto);
			} else {
				vitalSignId = existingVitalSign.get().getId();
			}
		}
		return vitalSignId;
	}
	
	
	public Long processAndSaveVitalSign(ARTClinicalCommenceDto artClinicalCommenceDto) {
		VitalSignDto vitalSignDto = artClinicalCommenceDto.getVitalSignDto();
		vitalSignDto.setVisitId(artClinicalCommenceDto.getVisitId());
		vitalSignDto.setFacilityId(organizationUtil.getCurrentUserOrganization());
		vitalSignDto.setEncounterDate(artClinicalCommenceDto.getVisitDate());
		Log.info("vitalSign dto {}", vitalSignDto);
		VitalSignDto saveVitalSignDto = vitalSignService.registerVitalSign(vitalSignDto);
		return saveVitalSignDto.getId();
		
	}
	
	
	public ARTClinicalCommenceDto updateArtCommence(Long id, ARTClinicalCommenceDto artClinicalCommenceDto) {
		ARTClinical existArtClinical = getExistArt(id);
		ARTClinical artClinical = convertDtoToART(artClinicalCommenceDto, artClinicalCommenceDto.getVitalSignId());
		artClinical.setId(existArtClinical.getId());
		artClinical.setCreatedBy(existArtClinical.getCreatedBy());
		artClinical.setCreatedDate(existArtClinical.getCreatedDate());
		artClinical.setHivEnrollment(existArtClinical.getHivEnrollment());
		artClinical.setVisit(existArtClinical.getVisit());
		artClinical.setPerson(existArtClinical.getPerson());
		artClinical.setVitalSign(existArtClinical.getVitalSign());
		return convertArtToResponseDto(artClinicalRepository.save(artClinical));
	}
	
	
	public void archivedArtCommenceClinical(Long id) {
		ARTClinical artClinical = getExistArt(id);
		HIVStatusTracker hivStatusTracker = hivStatusTrackerService
				.findDistinctFirstByPersonAndStatusDate(artClinical.getPerson(), artClinical.getVisitDate());
		if (hivStatusTracker != null) {
			hivStatusTrackerService.archivedHIVStatusTracker(hivStatusTracker.getId());
		}
		artClinical.setArchived(1);
		artClinicalRepository.save(artClinical);
	}
	
	public ARTClinicalCommenceDto getArtById(Long id) {
		return convertArtToResponseDto(getExistArt(id));
	}
	
	public List<ARTClinicalCommenceDto> getAll() {
		return artClinicalRepository
				.findByArchivedAndIsCommencementIsTrue(0)
				.stream()
				.map(this::convertArtToResponseDto)
				.collect(Collectors.toList());
	}
	
	
	private ARTClinical getExistArt(Long id) {
		return artClinicalRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(ARTClinical.class, "id", "" + id));
	}
	
	private void processAndSaveHIVStatus(ARTClinical artClinical, String hivStatus) {
		HIVStatusTrackerDto statusTracker = new HIVStatusTrackerDto();
		statusTracker.setHivStatus(hivStatus);
		statusTracker.setStatusDate(artClinical.getVisitDate());
		statusTracker.setVisitId(artClinical.getVisit().getId());
		statusTracker.setPersonId(artClinical.getPerson().getId());
		hivStatusTrackerService.registerHIVStatusTracker(statusTracker);
	}
	
	
	@NotNull
	public ARTClinicalCommenceDto convertArtToResponseDto(ARTClinical artClinical) {
		VitalSignDto vitalSignDto = vitalSignService.getVitalSignById(artClinical.getVitalSign().getId());
		ARTClinicalCommenceDto artClinicalCommenceDto = new ARTClinicalCommenceDto();
		BeanUtils.copyProperties(artClinical, artClinicalCommenceDto);
		artClinicalCommenceDto.setVitalSignId(vitalSignDto.getId());
		artClinicalCommenceDto.setVitalSignDto(vitalSignDto);
		artClinicalCommenceDto.setPersonId(artClinical.getPerson().getId());
		artClinicalCommenceDto.setVisitId(artClinical.getVisit().getId());
		return artClinicalCommenceDto;
	}
	
	
	@NotNull
	public ARTClinical convertDtoToART(ARTClinicalCommenceDto artClinicalCommenceDto, Long vitalSignId) {
		ARTClinical artClinical = new ARTClinical();
		BeanUtils.copyProperties(artClinicalCommenceDto, artClinical);
		VitalSign vitalSign = getVitalSign(vitalSignId);
		artClinical.setVitalSign(vitalSign);
		artClinical.setFacilityId(organizationUtil.getCurrentUserOrganization());
		return artClinical;
	}
	
	
	private VitalSign getVitalSign(Long vitalSignId) {
		return vitalSignRepository.findById(vitalSignId).orElseThrow(() -> new EntityNotFoundException(VitalSign.class, "id", String.valueOf(vitalSignId)));
		
	}
	
}
