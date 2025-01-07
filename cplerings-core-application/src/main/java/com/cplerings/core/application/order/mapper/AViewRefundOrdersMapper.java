package com.cplerings.core.application.order.mapper;

import com.cplerings.core.application.order.datasource.result.Refunds;
import com.cplerings.core.application.order.output.ViewRefundOrdersOutput;
import com.cplerings.core.application.shared.mapper.ARefundMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                ARefundMapper.class,
        })
public interface AViewRefundOrdersMapper {

    @Mapping(target = "items", source = "refunds")
    @Mapping(target = "totalPages", ignore = true)
    ViewRefundOrdersOutput toOutput(Refunds refunds);
}
