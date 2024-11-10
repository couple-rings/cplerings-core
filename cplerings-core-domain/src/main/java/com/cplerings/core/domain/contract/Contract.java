package com.cplerings.core.domain.contract;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.shared.AbstractEntity;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.Instant;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_contract")
public class Contract extends AbstractEntity {

    private static final String CONTRACT_SEQUENCE = "contract_seq";

    @Id
    @GeneratedValue(generator = CONTRACT_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = CONTRACT_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "contract_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "signature")
    private Image signature;

    @Column(name = "signed_date")
    private Instant signedDate;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "document_id", unique = true)
    private Document document;
}
