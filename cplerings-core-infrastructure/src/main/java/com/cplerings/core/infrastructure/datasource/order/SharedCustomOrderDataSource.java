package com.cplerings.core.infrastructure.datasource.order;

import java.util.List;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.order.datasource.ViewCustomOrdersDataSource;
import com.cplerings.core.application.order.datasource.result.CustomOrders;
import com.cplerings.core.application.order.input.ViewCustomOrdersInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.order.QCustomOrder;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DataSource
public class SharedCustomOrderDataSource extends AbstractDataSource implements ViewCustomOrdersDataSource {

    private static final QCustomOrder Q_CUSTOM_ORDER = QCustomOrder.customOrder;

    @Override
    public CustomOrders getCustomOrders(ViewCustomOrdersInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<CustomOrder> query = createQuery()
                .select(Q_CUSTOM_ORDER)
                .from(Q_CUSTOM_ORDER);

        final BooleanExpressionBuilder booleanExpressionBuilder = createBooleanExpressionBuilder();
        if (input.getStatus() != null) {
            switch (input.getStatus()) {
                case PENDING -> booleanExpressionBuilder.and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.PENDING));
                case WAITING -> booleanExpressionBuilder.and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.WAITING));
                case IN_PROGRESS -> booleanExpressionBuilder.and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.IN_PROGRESS));
                case DELIVERING -> booleanExpressionBuilder.and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.DELIVERING));
                case DONE -> booleanExpressionBuilder.and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.DONE));
                case CANCELED -> booleanExpressionBuilder.and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.CANCELED));
                case COMPLETED -> booleanExpressionBuilder.and(Q_CUSTOM_ORDER.status.eq(CustomOrderStatus.COMPLETED));
            }
        }
        if (input.getCustomerId() != null) {
            booleanExpressionBuilder.and(Q_CUSTOM_ORDER.customer.id.eq(input.getCustomerId()));
        }

        if (input.getJewelerId() != null) {
            booleanExpressionBuilder.and(Q_CUSTOM_ORDER.jeweler.id.eq(input.getJewelerId()));
        }

        final BooleanExpression predicate = booleanExpressionBuilder.build();
        query.where(predicate);

        long count = query.distinct().fetchCount();
        List<CustomOrder> customOrders = query.limit(input.getPageSize()).offset(offset).fetch();
        return CustomOrders.builder()
                .customOrders(customOrders)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }
}
