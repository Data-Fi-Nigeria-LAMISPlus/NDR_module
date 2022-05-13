package org.lamisplus.modules.hiv.domain.entity;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "art_clinical")
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class ARTClinical extends HivAuditEntity implements Serializable, Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @PastOrPresent
    @Column(name = "visit_date")
    private LocalDate visitDate;

    @Column(name = "cd_4")
    private String cd4;

    @Column(name = "cd_4_percentage")
    private Long cd4Percentage;

    @Column(name = "is_commencement")
    private Boolean isCommencement;

    @Column(name = "functional_status_id")
    private Long functionalStatusId;

    @NotNull
    private Long clinicalStageId;

    @Column(name = "clinical_note")
    private String clinicalNote;

    @Column(name = "uuid", nullable = false, unique = true)
    private String uuid;
    @Column(name = "hiv_enrollment_id")
    @NotNull
    private Long hivEnrollmentId;

    private long  regimenId;

    private long  regimenTypeId;

    @Column(name = "art_status_id", nullable = false)
    private Long artStatusId;

    @Column(name = "archived")
    private Integer archived;

    @NotNull
    @Column(name = "vital_sign_id")
    private Long vitalSignId;

    @Column(name = "who_staging_id")
    private Long whoStagingId;


    @Column(name = "facility_id")
    @NotNull
    private Long facilityId;


    @Column(name = "person_id")
    @NotNull
    Long personId;

    @Size(max = 5)
    @Column(name = "oi_screened")
    private String oiScreened;

    @Size(max = 50)
    @Column(name = "sti_ids")
    private String stiIds;

    @Size(max = 5)
    @Column(name = "sti_treated")
    private String stiTreated;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    JsonNode opportunisticInfections;

    @Size(max = 5)
    @Column(name = "adr_screened")
    private String adrScreened;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    JsonNode  adverseDrugReactions;

    @Size(max = 15)
    @Column(name = "adherence_level")
    private String adherenceLevel;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    JsonNode adheres;

    @PastOrPresent
    @Column(name = "next_appointment")
    private LocalDate nextAppointment;

    @PastOrPresent
    @Column(name = "lmp_date")
    private LocalDate lmpDate;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "tb_screen")
    private JsonNode TBScreen;

    @Override
    public boolean isNew() {
        return id == null;
    }


}
