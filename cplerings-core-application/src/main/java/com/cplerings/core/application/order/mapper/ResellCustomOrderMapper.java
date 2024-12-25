package com.cplerings.core.application.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.order.output.ResellCustomOrderOutput;
import com.cplerings.core.application.shared.mapper.AResellOrderMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.resell.ResellOrder;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                AResellOrderMapper.class
        })
public interface ResellCustomOrderMapper {

    ResellCustomOrderOutput toOutput(ResellOrder resellOrder);
}
