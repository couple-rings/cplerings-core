package com.cplerings.core.api.crafting.request;

import com.cplerings.core.application.crafting.input.data.RingMaintenance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompleteCraftingStageRequest {

    @Schema(hidden = true)
    private Long craftingStageId;

    private Long imageId;

    private Set<RingMaintenance> ringMaintenances;
}
