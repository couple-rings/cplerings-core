package com.cplerings.core.application.branch.datasource.result;

import java.util.List;

import com.cplerings.core.domain.branch.Branch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Branches {

    private List<Branch> branches;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
