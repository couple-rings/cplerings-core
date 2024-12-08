package com.cplerings.core.application.shared.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.shared.entity.order.ARefund;
import com.cplerings.core.application.shared.entity.order.ARefundInfo;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.refund.Refund;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                ARefundMapper.class,
                MoneyMapper.class,
                AImageMapper.class,
                AAccountMapper.class,
                AStandardOrderMapper.class,
                AOrderMapper.class,
        }
)
public interface ARefundMapper {

    ARefund toRefund(Refund refund);

    ARefundInfo toRefundInfo(Refund refund);
}
