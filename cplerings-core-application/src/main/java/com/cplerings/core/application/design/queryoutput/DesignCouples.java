package com.cplerings.core.application.design.queryoutput;

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
public class DesignCouples {

    private List<DesignCoupleQueryOutput> designCouples;
    private long count;
    private int page;
    private int pageSize;
    private int totalPages;
}
