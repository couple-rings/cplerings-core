package com.cplerings.core.test.shared.entity.product;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.shared.AbstractProductEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tbl_dummy_product")
public class DummyProduct extends AbstractProductEntity {

    private static final String DUMMY_PRODUCT_SEQUENCE = "dummy_product_seq";

    @Id
    @GeneratedValue(generator = DUMMY_PRODUCT_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DUMMY_PRODUCT_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "dummy_product_id")
    private Long id;
}
