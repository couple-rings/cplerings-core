package com.cplerings.core.application.crafting.datasource.result;

import java.util.List;

import com.cplerings.core.domain.crafting.CraftingStage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CraftingStages {

    private List<CraftingStage> craftingStages;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
