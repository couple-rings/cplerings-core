package com.cplerings.core.application.shared.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.shared.entity.order.AStandardOrder;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.order.StandardOrder;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class,
        })
public interface AStandardOrderMapper {

    //    @Mapping(source = "standardOrderItems", target = "jewelries", qualifiedByName = "mapJewelries")
    AStandardOrder toAStandardOrder(StandardOrder standardOrder);

//    @Named("mapJewelries")
//    default Collection<AJewelry> mapJewelries(Set<StandardOrderItem> items) {
//        return items.stream()
//                .map(item -> toAJewelry(item.getJewelry()))
//                .collect(Collectors.toList());
//    }
//
//    AJewelry toAJewelry(Jewelry jewelry);
}
