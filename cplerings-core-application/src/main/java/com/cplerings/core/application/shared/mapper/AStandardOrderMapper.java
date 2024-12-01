package com.cplerings.core.application.shared.mapper;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.cplerings.core.application.shared.entity.jewelry.AJewelry;
import com.cplerings.core.application.shared.entity.order.AStandardOrder;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderItem;

@Mapper(config = SpringMapperConfiguration.class,
uses = {
        WeightMapper.class,
        DesignSizeMapper.class,
        MoneyMapper.class,
})
public interface AStandardOrderMapper {

    @Mapping(source = "standardOrderItems", target = "jewelries", qualifiedByName = "mapJewelries")
    AStandardOrder toAStandardOrder(StandardOrder standardOrder);

    @Named("mapJewelries")
    default Collection<AJewelry> mapJewelries(Set<StandardOrderItem> items) {
        return items.stream()
                .map(item -> toAJewelry(item.getJewelry()))
                .collect(Collectors.toList());
    }

    AJewelry toAJewelry(Jewelry jewelry);
}
