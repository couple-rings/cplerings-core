package com.cplerings.core.application.design.datasource.result;

import java.util.List;

import com.cplerings.core.domain.design.DesignCouple;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DesignVersions {

    private List<DesignVerion> designVerions;
    private long count;
    private int page;
    private int pageSize;
}
