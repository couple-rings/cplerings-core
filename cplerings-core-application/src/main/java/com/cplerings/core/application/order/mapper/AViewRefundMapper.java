package com.cplerings.core.application.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.order.output.ViewRefundOutput;
import com.cplerings.core.application.shared.mapper.ARefundMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.refund.Refund;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
            ARefundMapper.class,

})
public interface AViewRefundMapper {

    ViewRefundOutput toOutput(Refund refund);
}
