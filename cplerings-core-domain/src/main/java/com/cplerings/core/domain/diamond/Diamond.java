package com.cplerings.core.domain.diamond;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.ring.RingDiamond;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_diamond")
public class Diamond extends AbstractEntity {

    private static final String DIAMOND_SEQUENCE = "diamond_seq";

    @Id
    @GeneratedValue(generator = DIAMOND_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DIAMOND_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "diamond_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gia_document_id", nullable = false)
    private Document giaDocument;

    @Column(name = "gia_report_number", nullable = false)
    private String giaReportNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "diamond_specification_id")
    private DiamondSpecification diamondSpecification;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "diamond", fetch = FetchType.LAZY)
    private Set<RingDiamond> ringDiamonds;
}
