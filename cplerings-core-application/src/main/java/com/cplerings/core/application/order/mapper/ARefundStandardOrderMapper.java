package com.cplerings.core.application.order.mapper;

import com.cplerings.core.application.order.output.RefundStandardOrderOutput;
import com.cplerings.core.application.shared.mapper.ARefundMapper;
import com.cplerings.core.application.shared.mapper.ARingMapper;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.refund.Refund;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class,
                ARingMapper.class,
                ARefundMapper.class,
        })
public interface ARefundStandardOrderMapper {

    RefundStandardOrderOutput toOutput(Refund refund);
}
