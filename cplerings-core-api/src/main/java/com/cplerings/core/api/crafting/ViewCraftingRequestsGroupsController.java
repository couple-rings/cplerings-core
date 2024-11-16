package com.cplerings.core.api.crafting;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.crafting.data.ViewCraftingRequestsGroupsData;
import com.cplerings.core.api.crafting.mapper.APIViewCraftingRequestsGroupsMapper;
import com.cplerings.core.api.crafting.request.ViewCraftingRequestsGroupsRequest;
import com.cplerings.core.api.crafting.response.ViewCraftingRequestsGroupsResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.CraftingRequestTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.crafting.ViewCraftingRequestsGroupsUseCase;
import com.cplerings.core.application.crafting.input.ViewCraftingRequestsGroupsInput;
import com.cplerings.core.application.crafting.output.ViewCraftingRequestsGroupsOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewCraftingRequestsGroupsController extends AbstractController<ViewCraftingRequestsGroupsInput, ViewCraftingRequestsGroupsOutput, ViewCraftingRequestsGroupsData, ViewCraftingRequestsGroupsRequest, ViewCraftingRequestsGroupsResponse> {

    private final APIViewCraftingRequestsGroupsMapper apiViewCraftingRequestsGroupsMapper;
    private final ViewCraftingRequestsGroupsUseCase viewCraftingRequestsGroupsUseCase;

    @Override
    protected UseCase<ViewCraftingRequestsGroupsInput, ViewCraftingRequestsGroupsOutput> getUseCase() {
        return viewCraftingRequestsGroupsUseCase;
    }

    @Override
    protected APIMapper<ViewCraftingRequestsGroupsInput, ViewCraftingRequestsGroupsOutput, ViewCraftingRequestsGroupsData, ViewCraftingRequestsGroupsRequest, ViewCraftingRequestsGroupsResponse> getMapper() {
        return apiViewCraftingRequestsGroupsMapper;
    }

    @GetMapping(APIConstant.VIEW_CRAFTING_REQUEST_GROUPS_PATH)
    @CraftingRequestTag
    @Operation(summary = "View crafting requests groups")
    @ApiResponse(
            description = "View crafting requests groups",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewCraftingRequestsGroupsResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewCraftingRequestsGroupsRequest request) {
        return handleRequest(request);
    }
}
