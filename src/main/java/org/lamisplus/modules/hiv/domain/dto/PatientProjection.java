package org.lamisplus.modules.hiv.domain.dto;


import java.time.LocalDate;

public interface PatientProjection {
	String getHospitalNumber();
	String getFirstName();
	String getSurname();
	String getOtherName();
	String getGender();
	String getUniqueId();
	
	String getBiometric();
	String getEnrollmentStatus();
	String getPersonUuid();
	Boolean getIsEnrolled();
	Boolean getIsCommenced();
	Boolean getIsDobEstimated();
	Integer getAge();
	Long getId();
	Long getFacility();
	Long getTargetGroupId();
	Long getEnrollmentId();
	LocalDate getDateOfBirth();
	
	
}