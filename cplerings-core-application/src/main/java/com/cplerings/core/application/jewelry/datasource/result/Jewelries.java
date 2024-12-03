package com.cplerings.core.application.jewelry.datasource.result;

import java.util.List;

import com.cplerings.core.domain.jewelry.Jewelry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Jewelries {

    private List<Jewelry> jewelries;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
