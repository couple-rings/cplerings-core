package com.cplerings.core.domain.design.crafting;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.shared.AbstractEntity;

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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_crafting_request")
public class CraftingRequest extends AbstractEntity {

    private static final String CRAFTING_REQUEST_SEQUENCE = "crafting_request_sequence";

    @Id
    @GeneratedValue(generator = CRAFTING_REQUEST_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = CRAFTING_REQUEST_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "crafting_request_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Account customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "metal_specification_id")
    private MetalSpecification metalSpecification;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "diamond_specification_id")
    private DiamondSpecification diamondSpecification;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "custom_design_id")
    private CustomDesign customDesign;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id")
    private Account reviewer;

    @Column(name = "engraving")
    private String engraving;

    @Column(name = "finger_size", nullable = false)
    private Integer fingerSize;

    @Column(name = "comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "crafting_request_status", nullable = false)
    private CraftingRequestStatus craftingRequestStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "branch_id")
    private Branch branch;
}
