package com.cplerings.core.domain.jewelry;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.design.Design;
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
@Table(name = "tbl_jewelry_category")
public class JewelryCategory extends AbstractEntity {

    private static final String JEWELRY_CATEGORY_SEQUENCE = "jewelry_category_seq";

    @Id
    @GeneratedValue(generator = JEWELRY_CATEGORY_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = JEWELRY_CATEGORY_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "jewelry_category_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = DatabaseConstant.DEFAULT_DESCRIPTION_LENGTH, nullable = false)
    private String description;

    @OneToMany(mappedBy = "jewelryCategory", fetch = FetchType.LAZY)
    private Set<Design> designs;
}
