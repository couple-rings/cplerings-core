package com.cplerings.core.application.configuration.datasource.data;

import java.util.List;

import com.cplerings.core.domain.configuration.Configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Configurations {

    private List<Configuration> configurations;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
