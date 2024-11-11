package com.cplerings.core.domain.design;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.shared.AbstractEntity;
import com.cplerings.core.domain.shared.valueobject.Weight;
import com.cplerings.core.domain.spouse.Spouse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@Table(name = "tbl_custom_design")
public class CustomDesign extends AbstractEntity {

    private static final String CUSTOM_DESIGN_SEQUENCE = "custom_design_sequence";

    @Id
    @GeneratedValue(generator = CUSTOM_DESIGN_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = CUSTOM_DESIGN_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "custom_design_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "design_version_id")
    private DesignVersion designVersion;

    @OneToMany(mappedBy = "customDesign", fetch = FetchType.LAZY)
    private Set<CustomDesignDiamondSpecification> customDesignDiamondSpecifications;

    @OneToMany(mappedBy = "customDesign", fetch = FetchType.LAZY)
    private Set<CustomDesignMetalSpecification> customDesignMetalSpecifications;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "spouse_id")
    private Spouse spouse;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Account account;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "blue_print_id")
    private Document blueprint;

    @Embedded
    @AttributeOverride(
            name = "weight",
            column = @Column(name = "metal_weight", nullable = false, precision = 10, scale = 2)
    )
    private Weight metalWeight;

    @Column(name = "side_diamonds_count", nullable = false)
    private Integer sideDiamondsCount;
}
