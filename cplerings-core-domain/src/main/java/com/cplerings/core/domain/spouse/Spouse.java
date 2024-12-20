package com.cplerings.core.domain.spouse;

import java.time.Instant;
import java.util.UUID;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.ring.Ring;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_spouse")
public class Spouse extends AbstractEntity {

    private static final String SPOUSE_SEQUENCE = "spouse_seq";

    @Id
    @GeneratedValue(generator = SPOUSE_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SPOUSE_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "spouse_id")
    private Long id;

    @Column(name = "citizen_id", length = 12, nullable = false, unique = true)
    private String citizenId;

    @Column(name = "date_of_birth", nullable = false)
    private Instant dateOfBirth;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "couple_id", nullable = false)
    private UUID coupleId;

    @OneToOne(mappedBy = "spouse")
    private SpouseAccount spouseAccount;

    @OneToOne(mappedBy = "spouse")
    private Ring ring;
}
