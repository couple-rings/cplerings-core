package com.cplerings.core.application.design.datasource.result;

import com.cplerings.core.domain.design.DesignCouple;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DesignCouples {

    private List<DesignCouple> designCouples;
    private long count;
    private int page;
    private int pageSize;
}
