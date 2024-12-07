package com.cplerings.core.domain.shared;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.shared.generator.OrderNoGeneratorType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractOrderEntity extends AbstractEntity {

    @OrderNoGeneratorType
    @Column(name = "order_no", length = DatabaseConstant.DEFAULT_ENTITY_NO_LENGTH, nullable = false, updatable = false)
    private String orderNo;
}
