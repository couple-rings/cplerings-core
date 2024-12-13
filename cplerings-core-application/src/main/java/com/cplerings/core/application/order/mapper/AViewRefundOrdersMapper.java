package com.cplerings.core.application.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.order.datasource.result.Refunds;
import com.cplerings.core.application.order.output.ViewRefundOrdersOutput;
import com.cplerings.core.application.shared.mapper.ARefundMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                ARefundMapper.class,
        })
public interface AViewRefundOrdersMapper {

    @Mapping(target = "items", source = "refunds")
    ViewRefundOrdersOutput toOutput(Refunds refunds);
}
