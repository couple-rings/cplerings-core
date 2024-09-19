package com.cplerings.core.domain.metal;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
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
@Table(name = "tbl_metal", schema = DatabaseConstant.SCHEME_CORE)
public class Metal extends AbstractEntity {

    private static final String METAL_SEQUENCE = "metal_seq";

    @Id
    @GeneratedValue(generator = METAL_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = METAL_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "metal_id")
    private Long id;
}
