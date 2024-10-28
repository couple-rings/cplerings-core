package com.cplerings.core.domain.design;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.design.request.DesignCustomRequest;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.jewelry.JewelryCategory;
import com.cplerings.core.domain.shared.AbstractEntity;
import com.cplerings.core.domain.shared.valueobject.DesignSize;
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
@Table(name = "tbl_design")
public class Design extends AbstractEntity {

    private static final String DESIGN_SEQUENCE = "design_seq";

    @Id
    @GeneratedValue(generator = DESIGN_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DESIGN_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "design_id")
    private Long id;

    @Embedded
    @AttributeOverride(
            name = "weight",
            column = @Column(name = "metal_weight", nullable = false, precision = 10, scale = 2)
    )
    private Weight metalWeight;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = DatabaseConstant.DEFAULT_DESCRIPTION_LENGTH, nullable = false)
    private String description;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "blueprint_id", nullable = false)
    private Document blueprint;

    @Enumerated(EnumType.STRING)
    @Column(name = "characteristic", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private DesignCharacteristic characteristic;

    @Embedded
    private DesignSize size;

    @Column(name = "side_diamonds_count", nullable = false)
    private Integer sideDiamondsCount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "design_collection_id")
    private DesignCollection designCollection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_couple_id")
    private DesignCouple designCouple;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jewelry_category_id")
    private JewelryCategory jewelryCategory;

    @OneToMany(mappedBy = "design", fetch = FetchType.LAZY)
    private Set<DesignMetalSpecification> designMetalSpecifications;

    @OneToMany(mappedBy = "design", fetch = FetchType.LAZY)
    private Set<DesignDiamondSpecification> designDiamondSpecifications;

    @OneToMany(mappedBy = "design", fetch = FetchType.LAZY)
    private Set<DesignCustomRequest> designCustomRequests;
}
