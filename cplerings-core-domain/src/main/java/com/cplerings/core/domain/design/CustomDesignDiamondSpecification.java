package com.cplerings.core.domain.design;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.shared.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tbl_custom_design_diamond_specification")
public class CustomDesignDiamondSpecification extends AbstractEntity {

    private static final String CUSTOM_DESIGN_DIAMOND_SPECIFICATION_SEQUENCE = "custom_design_diamond_specification_sequence";

    @Id
    @GeneratedValue(generator = CUSTOM_DESIGN_DIAMOND_SPECIFICATION_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = CUSTOM_DESIGN_DIAMOND_SPECIFICATION_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "custom_design_diamond_specification_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "custom_design_id")
    private CustomDesign customDesign;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "diamond_specification_id")
    private DiamondSpecification diamondSpecification;
}
