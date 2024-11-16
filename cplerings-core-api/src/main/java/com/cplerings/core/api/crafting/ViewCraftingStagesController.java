package com.cplerings.core.api.crafting;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.crafting.data.CraftingStagesData;
import com.cplerings.core.api.crafting.mapper.APIViewCraftingStagesMapper;
import com.cplerings.core.api.crafting.request.ViewCraftingStagesRequest;
import com.cplerings.core.api.crafting.response.ViewCraftingStagesResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CraftingStageTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.crafting.ViewCraftingStagesUseCase;
import com.cplerings.core.application.crafting.input.ViewCraftingStagesInput;
import com.cplerings.core.application.crafting.output.ViewCraftingStagesOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewCraftingStagesController extends AbstractController<ViewCraftingStagesInput, ViewCraftingStagesOutput, CraftingStagesData, ViewCraftingStagesRequest, ViewCraftingStagesResponse> {

    private final ViewCraftingStagesUseCase viewCraftingStagesUseCase;
    private final APIViewCraftingStagesMapper apiViewCraftingStagesMapper;

    @Override
    protected UseCase<ViewCraftingStagesInput, ViewCraftingStagesOutput> getUseCase() {
        return viewCraftingStagesUseCase;
    }

    @Override
    protected APIMapper<ViewCraftingStagesInput, ViewCraftingStagesOutput, CraftingStagesData, ViewCraftingStagesRequest, ViewCraftingStagesResponse> getMapper() {
        return apiViewCraftingStagesMapper;
    }

    @GetMapping(APIConstant.CRAFTING_STAGE_PATH)
    @CraftingStageTag
    @Operation(summary = "View crafting stages")
    @ApiResponse(
            description = "View crafting stages",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewCraftingStagesResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewCraftingStagesRequest request) {
        return handleRequest(request);
    }
}
