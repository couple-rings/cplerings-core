package com.cplerings.core.application.design.datasource.result;

import java.util.List;

import com.cplerings.core.domain.design.request.CustomRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CustomRequests {

    private List<CustomRequest> customRequests;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
