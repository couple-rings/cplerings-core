package com.cplerings.core.domain.shared;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.shared.generator.ProductNoGeneratorType;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
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
@MappedSuperclass
public abstract class AbstractProductEntity extends AbstractEntity {

    @ProductNoGeneratorType
    @Column(name = "product_no", length = DatabaseConstant.DEFAULT_ENTITY_NO_LENGTH, nullable = false, updatable = false)
    private String productNo;
}
