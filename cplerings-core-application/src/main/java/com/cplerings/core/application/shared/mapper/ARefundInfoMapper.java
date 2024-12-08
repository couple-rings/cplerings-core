package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.order.ARefundInfo;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.refund.Refund;

import org.mapstruct.Mapper;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                AEnumMapper.class,
                MoneyMapper.class,
                AAccountMapper.class,
                AImageMapper.class,
        }
)
public interface ARefundInfoMapper {

    ARefundInfo toRefundInfo(Refund refund);
}
