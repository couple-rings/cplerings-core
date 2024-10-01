package com.cplerings.core.domain.diamond;

import java.util.Set;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "clarity", nullable = false)
    private String clarity;

    @Column(name = "cut", nullable = false)
    private String cut;

    @Column(name = "shape", nullable = false)
    private String shape;

    @Column(name = "price", nullable = false)
    private double price;

    @OneToMany(mappedBy = "diamondSpecification", fetch = FetchType.LAZY)
    private Set<Diamond> diamonds;
}
