package com.cplerings.core.application.dashboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.dashboard.datasource.data.Payments;
import com.cplerings.core.application.dashboard.output.ViewPaymentWithDateOutput;
import com.cplerings.core.application.shared.mapper.ACraftingMapper;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
    uses = {
            WeightMapper.class,
            MoneyMapper.class,
            DesignSizeMapper.class,
            ACraftingMapper.class,
    }
)
public interface AViewPaymentWithDateMapper {

    @Mapping(target = "items", source = "payments")
    ViewPaymentWithDateOutput toOutput(Payments payments);
}
