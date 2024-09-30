package com.cplerings.core.domain.spouse;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_spouse_verification_request")
public class VerificationRequest extends AbstractEntity {

    private static final String VERIFICATION_REQUEST_SEQUENCE = "verification_request_seq";

    @Id
    @GeneratedValue(generator = VERIFICATION_REQUEST_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = VERIFICATION_REQUEST_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "verification_request_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10, nullable = false)
    private VerificationRequestStatus status;

    @Column(name = "comment", length = DatabaseConstant.DEFAULT_COMMENT_LENGTH)
    private String comment;
}
