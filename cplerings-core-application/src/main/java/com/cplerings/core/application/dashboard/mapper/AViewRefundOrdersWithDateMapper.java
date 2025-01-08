package com.cplerings.core.application.dashboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.dashboard.output.ViewRefundOrdersWithDateOutput;
import com.cplerings.core.application.order.datasource.result.Refunds;
import com.cplerings.core.application.order.mapper.AViewRefundOrdersMapper;
import com.cplerings.core.application.shared.mapper.ARefundMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                ARefundMapper.class,
        })
public interface AViewRefundOrdersWithDateMapper {

    @Mapping(target = "items", source = "refunds")
    ViewRefundOrdersWithDateOutput toOutput(Refunds orders);
}
