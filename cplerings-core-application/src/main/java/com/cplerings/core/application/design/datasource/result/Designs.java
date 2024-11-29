package com.cplerings.core.application.design.datasource.result;

import java.util.List;

import com.cplerings.core.domain.design.Design;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Designs {

    private List<Design> designs;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
