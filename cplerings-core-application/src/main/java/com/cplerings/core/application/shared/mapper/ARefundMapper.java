package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.order.ARefund;
import com.cplerings.core.application.shared.entity.order.ARefundInfo;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.refund.Refund;

import org.mapstruct.Mapper;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                AEnumMapper.class,
                MoneyMapper.class,
                AImageMapper.class,
                ARefundMapper.class,
                AAccountMapper.class,
                AStandardOrderMapper.class,
                AOrderMapper.class,
        }
)
public interface ARefundMapper {

    ARefund toRefund(Refund refund);
}
