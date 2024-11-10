package com.cplerings.core.application.metal.datasource.result;

import com.cplerings.core.domain.metal.MetalSpecification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MetalSpecifications {

    private List<MetalSpecification> metalSpecifications;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
