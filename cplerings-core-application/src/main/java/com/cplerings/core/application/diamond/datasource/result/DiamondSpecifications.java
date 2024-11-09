package com.cplerings.core.application.diamond.datasource.result;

import java.util.List;

import com.cplerings.core.domain.diamond.DiamondSpecification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DiamondSpecifications {

    private List<DiamondSpecification> diamondSpecifications;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
