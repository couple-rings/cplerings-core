package com.cplerings.core.api.crafting;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.crafting.data.CraftingRequestData;
import com.cplerings.core.api.crafting.mapper.APIViewCraftingRequestsMapper;
import com.cplerings.core.api.crafting.request.ViewCraftingRequestsRequest;
import com.cplerings.core.api.crafting.response.ViewCraftingRequestsResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CraftingRequestTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.crafting.ViewCraftingRequestsUseCase;
import com.cplerings.core.application.crafting.input.ViewCraftingRequestsInput;
import com.cplerings.core.application.crafting.output.ViewCraftingRequestsOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewCraftingRequestsController extends AbstractController<ViewCraftingRequestsInput, ViewCraftingRequestsOutput, CraftingRequestData, ViewCraftingRequestsRequest, ViewCraftingRequestsResponse> {

    private final APIViewCraftingRequestsMapper apiViewCraftingRequestsMapper;
    private final ViewCraftingRequestsUseCase viewCraftingRequestsUseCase;

    @Override
    protected UseCase<ViewCraftingRequestsInput, ViewCraftingRequestsOutput> getUseCase() {
        return viewCraftingRequestsUseCase;
    }

    @Override
    protected APIMapper<ViewCraftingRequestsInput, ViewCraftingRequestsOutput, CraftingRequestData, ViewCraftingRequestsRequest, ViewCraftingRequestsResponse> getMapper() {
        return apiViewCraftingRequestsMapper;
    }

    @GetMapping(APIConstant.VIEW_CRAFTING_REQUESTS_PATH)
    @CraftingRequestTag
    @Operation(summary = "View crafting requests requests")
    @ApiResponse(
            description = "View crafting requests requests",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewCraftingRequestsResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewCraftingRequestsRequest request) {
        return handleRequest(request);
    }

}
