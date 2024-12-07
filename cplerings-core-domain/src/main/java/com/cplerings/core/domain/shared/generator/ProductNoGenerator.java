package com.cplerings.core.domain.shared.generator;


import java.util.EnumSet;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.shared.AbstractOrderEntity;
import com.cplerings.core.domain.shared.AbstractProductEntity;

public class ProductNoGenerator implements BeforeExecutionGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object owner, Object currentValue, EventType eventType) {
        if (owner instanceof AbstractOrderEntity entity && entity.getId() != null) {
            return String.format("%0" + DatabaseConstant.DEFAULT_ENTITY_NO_LENGTH + "d", entity.getId());
        }
        throw new IllegalStateException("Entity should be " + AbstractProductEntity.class.getSimpleName() + " and with non-null ID");
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(EventType.INSERT);
    }
}
