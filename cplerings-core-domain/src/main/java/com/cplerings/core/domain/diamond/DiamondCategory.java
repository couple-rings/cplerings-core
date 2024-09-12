package com.cplerings.core.domain.diamond;

import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;

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
@Table(name = "DIAMOND_CATEGORY")
public class DiamondCategory extends AbstractEntity {

    private static final String DIAMOND_CATEGORY_SEQUENCE = "DIAMOND_CATEGORY_SEQ";

    @Id
    @GeneratedValue(generator = DIAMOND_CATEGORY_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DIAMOND_CATEGORY_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "DIAMOND_CATEGORY_ID")
    private Long id;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Diamond> diamonds;
}
