package com.cplerings.core.domain.ring;

import java.time.Instant;
import java.util.Set;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.shared.AbstractProductEntity;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.domain.spouse.Spouse;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "tbl_ring")
public class Ring extends AbstractProductEntity {

    private static final String RING_SEQUENCE = "ring_seq";

    @Id
    @GeneratedValue(generator = RING_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = RING_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "ring_id")
    private Long id;

    @Column(name = "purchase_date")
    private Instant purchaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private RingStatus status;

    @Column(name = "maintenance_expired_date")
    private Instant maintenanceExpiredDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_document_id")
    private Document maintenanceDocument;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToOne(mappedBy = "firstRing")
    private CustomOrder customOrderAsFirst;

    @OneToOne(mappedBy = "secondRing")
    private CustomOrder customOrderAsSecond;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "spouse_id")
    private Spouse spouse;

    @Column(name = "engraving", length = DatabaseConstant.DEFAULT_ENGRAVING_LENGTH)
    private String engraving;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "total_price", precision = 12, scale = 3, nullable = false))
    private Money price;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "custom_design_id", nullable = false)
    private CustomDesign customDesign;

    @Column(name = "finger_size", nullable = false)
    private Integer fingerSize;

    @OneToMany(mappedBy = "ring", fetch = FetchType.LAZY)
    private Set<RingDiamond> ringDiamonds;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "metal_specification_id", nullable = false)
    private MetalSpecification metalSpecification;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ring")
    private Set<RingHistory> ringHistories;
}
