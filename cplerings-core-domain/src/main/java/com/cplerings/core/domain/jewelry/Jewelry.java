package com.cplerings.core.domain.jewelry;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.order.StandardOrderItem;
import com.cplerings.core.domain.shared.AbstractEntity;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_jewelry")
public class Jewelry extends AbstractEntity {

    private static final String JEWELRY_SEQUENCE = "jewelry_seq";

    @Id
    @GeneratedValue(generator = JEWELRY_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = JEWELRY_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "jewelry_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "metal_specification_id")
    private MetalSpecification metalSpecification;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "design_id")
    private Design design;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Column(name = "purchase_date")
    private Instant purchaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private JewelryStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_document_id")
    private Document maintenanceDocument;

    @Column(name = "maintenance_expired_date")
    private Instant maintenanceExpiredDate;

    @OneToMany(mappedBy = "jewelry", fetch = FetchType.LAZY)
    private Set<StandardOrderItem> standardOrderItems;
}
