package com.cplerings.core.application.agreement.datasource.result;

import java.util.List;

import com.cplerings.core.domain.spouse.Agreement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Agreements {

    private List<Agreement> agreements;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
