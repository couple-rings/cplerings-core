package com.cplerings.core.application.shared.mapper;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.shared.entity.design.ADiamond;
import com.cplerings.core.application.shared.entity.ring.ARing;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingDiamond;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                AEnumMapper.class,
                ADocumentMapper.class,
                ASpouseMapper.class,
                ADesignMapper.class,
                MoneyMapper.class,
                WeightMapper.class
        }
)
public interface ARingMapper {

    @Mapping(target = "diamonds", expression = "java(mapDiamonds(ring.getRingDiamonds()))")
    ARing toRing(Ring ring);

    default Collection<ADiamond> mapDiamonds(Set<RingDiamond> ringDiamonds) {
        return ringDiamonds.stream()
                .map(RingDiamond::getDiamond)
                .map(this::toDiamond)
                .collect(Collectors.toSet());
    }

    ADiamond toDiamond(Diamond diamond);
}
