package com.cplerings.core.domain.spouse;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.image.Image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_verification_request_detail")
public class VerificationRequestDetail extends AbstractEntity {

    private static final String VERIFICATION_REQUEST_DETAIL_SEQUENCE = "verification_request_detail_seq";

    @Id
    @GeneratedValue(generator = VERIFICATION_REQUEST_DETAIL_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = VERIFICATION_REQUEST_DETAIL_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "verification_request_detail_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "face_photo_id")
    private Image faceImage;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "citizen_id_image_id")
    private Image citizenIdImage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "verification_request_id")
    private VerificationRequest request;
}
