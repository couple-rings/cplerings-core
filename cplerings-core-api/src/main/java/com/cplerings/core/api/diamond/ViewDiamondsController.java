package com.cplerings.core.api.diamond;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.diamond.data.DiamondsData;
import com.cplerings.core.api.diamond.mapper.APIViewDiamondsMapper;
import com.cplerings.core.api.diamond.request.ViewDiamondsRequest;
import com.cplerings.core.api.diamond.response.ViewDiamondsResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DiamondTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.diamond.ViewDiamondsUseCase;
import com.cplerings.core.application.diamond.input.ViewDiamondsInput;
import com.cplerings.core.application.diamond.output.ViewDiamondsOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewDiamondsController extends AbstractController<ViewDiamondsInput, ViewDiamondsOutput, DiamondsData, ViewDiamondsRequest, ViewDiamondsResponse> {

    private final APIViewDiamondsMapper apiViewDiamondsMapper;
    private final ViewDiamondsUseCase viewDiamondsUseCase;

    @Override
    protected UseCase<ViewDiamondsInput, ViewDiamondsOutput> getUseCase() {
        return viewDiamondsUseCase;
    }

    @Override
    protected APIMapper<ViewDiamondsInput, ViewDiamondsOutput, DiamondsData, ViewDiamondsRequest, ViewDiamondsResponse> getMapper() {
        return apiViewDiamondsMapper;
    }

    @GetMapping(APIConstant.DIAMONDS_PATH)
    @DiamondTag
    @Operation(summary = "View diamonds")
    @ApiResponse(
            description = "View diamonds",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewDiamondsResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewDiamondsRequest request) {
        return handleRequest(request);
    }
}
