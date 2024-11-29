package com.cplerings.core.application.design.datasource.result;

import java.util.List;

import com.cplerings.core.domain.design.DesignCollection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DesignCollections {

    private List<DesignCollection> designCollections;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
