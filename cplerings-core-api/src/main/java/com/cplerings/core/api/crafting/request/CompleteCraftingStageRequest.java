package com.cplerings.core.api.crafting.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompleteCraftingStageRequest {

    @Schema(hidden = true)
    private Long craftingStageId;

    private Long imageId;
}
