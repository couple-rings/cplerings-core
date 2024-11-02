package com.cplerings.core.domain.diamond;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.design.CustomDesignDiamondSpecification;
import com.cplerings.core.domain.shared.AbstractEntity;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.domain.shared.valueobject.Weight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_diamond_specification")
public class DiamondSpecification extends AbstractEntity {

    private static final String DIAMOND_SPECIFICATION_SEQUENCE = "diamond_specification_seq";

    @Id
    @GeneratedValue(generator = DIAMOND_SPECIFICATION_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DIAMOND_SPECIFICATION_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "diamond_specification_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Weight weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private DiamondColor color;

    @Enumerated(EnumType.STRING)
    @Column(name = "clarity", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private DiamondClarity clarity;

    @Enumerated(EnumType.STRING)
    @Column(name = "shape", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private DiamondShape shape;

    @Embedded
    @AttributeOverride(
            name = "amount",
            column = @Column(name = "price", nullable = false)
    )
    private Money price;

    @OneToMany(mappedBy = "diamondSpecification", fetch = FetchType.LAZY)
    private Set<Diamond> diamonds;

    @OneToMany(mappedBy = "diamondSpecification", fetch = FetchType.LAZY)
    private Set<CustomDesignDiamondSpecification> customDesignDiamondSpecifications;
}
