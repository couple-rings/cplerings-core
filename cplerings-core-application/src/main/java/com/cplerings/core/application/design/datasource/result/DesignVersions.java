package com.cplerings.core.application.design.datasource.result;

import com.cplerings.core.domain.design.DesignVersion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DesignVersions {

    private List<DesignVersion> designVersions;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
