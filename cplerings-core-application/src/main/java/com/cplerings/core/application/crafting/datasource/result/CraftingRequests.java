package com.cplerings.core.application.crafting.datasource.result;

import java.util.List;

import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.request.CustomRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CraftingRequests {

    private List<CraftingRequest> craftingRequests;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
