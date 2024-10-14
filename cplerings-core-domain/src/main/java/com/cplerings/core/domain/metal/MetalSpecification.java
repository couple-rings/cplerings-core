package com.cplerings.core.domain.metal;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.shared.AbstractEntity;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
@Table(name = "tbl_metal_specification")
public class MetalSpecification extends AbstractEntity {

    private static final String METAL_SPECIFICATION_SEQUENCE = "metal_specification_seq";

    @Id
    @GeneratedValue(generator = METAL_SPECIFICATION_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = METAL_SPECIFICATION_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "metal_specification_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    @AttributeOverride(
            name = "amount",
            column = @Column(name = "price_per_unit", nullable = false, precision = 12, scale = 3)
    )
    private Money pricePerUnit;

    @Column(name = "color", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private MetalColor color;
}
