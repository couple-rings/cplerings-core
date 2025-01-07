package com.cplerings.core.domain.shared.generator;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.payment.Payment;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

import java.util.EnumSet;

public final class PaymentNoGenerator implements BeforeExecutionGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
        if (owner instanceof Payment entity && entity.getId() != null) {
            return String.format("PA%0" + DatabaseConstant.DEFAULT_ORDER_NO_NUMBER_PART_LENGTH + "d", entity.getId());
        }
        throw new IllegalStateException("Entity should be " + Payment.class.getSimpleName() + " and with non-null ID");
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(EventType.INSERT);
    }
}
