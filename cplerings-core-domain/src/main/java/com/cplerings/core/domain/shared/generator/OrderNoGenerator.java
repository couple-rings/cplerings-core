package com.cplerings.core.domain.shared.generator;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.refund.Refund;
import com.cplerings.core.domain.resell.ResellOrder;
import com.cplerings.core.domain.shared.AbstractOrderEntity;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

import java.util.EnumSet;

public final class OrderNoGenerator implements BeforeExecutionGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
        if (!(owner instanceof AbstractOrderEntity entity) || entity.getId() == null) {
            throw new IllegalStateException("Entity should be " + AbstractOrderEntity.class.getSimpleName() + " and with non-null ID");
        }
        return switch (entity) {
            case CustomOrder customOrder ->
                    String.format("CO%0" + DatabaseConstant.DEFAULT_ORDER_NO_NUMBER_PART_LENGTH + "d", customOrder.getId());
            case StandardOrder standardOrder ->
                    String.format("SO%0" + DatabaseConstant.DEFAULT_ORDER_NO_NUMBER_PART_LENGTH + "d", standardOrder.getId());
            case TransportationOrder transportationOrder ->
                    String.format("TO%0" + DatabaseConstant.DEFAULT_ORDER_NO_NUMBER_PART_LENGTH + "d", transportationOrder.getId());
            case ResellOrder resellOrder ->
                    String.format("RS%0" + DatabaseConstant.DEFAULT_ORDER_NO_NUMBER_PART_LENGTH + "d", resellOrder.getId());
            case Refund refund ->
                    String.format("RF%0" + DatabaseConstant.DEFAULT_ORDER_NO_NUMBER_PART_LENGTH + "d", refund.getId());
            default ->
                    throw new IllegalStateException("Unknown OrderEntity type: " + entity.getClass().getSimpleName());
        };
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(EventType.INSERT);
    }
}
