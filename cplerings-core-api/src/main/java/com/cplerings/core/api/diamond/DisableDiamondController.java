package com.cplerings.core.api.diamond;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.diamond.mapper.APIDisableDiamondMapper;
import com.cplerings.core.api.diamond.request.DisableDiamondRequest;
import com.cplerings.core.api.diamond.response.DisableDiamondResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DiamondTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.diamond.DisableDiamondUseCase;
import com.cplerings.core.application.diamond.input.DisableDiamondInput;
import com.cplerings.core.application.diamond.output.DisableDiamondOutput;
import com.cplerings.core.application.shared.entity.design.ADiamond;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class DisableDiamondController extends AbstractController<DisableDiamondInput, DisableDiamondOutput, ADiamond, DisableDiamondRequest, DisableDiamondResponse> {

    private final APIDisableDiamondMapper apiDisableDiamondMapper;
    private final DisableDiamondUseCase disableDiamondUseCase;

    @Override
    protected UseCase<DisableDiamondInput, DisableDiamondOutput> getUseCase() {
        return disableDiamondUseCase;
    }

    @Override
    protected APIMapper<DisableDiamondInput, DisableDiamondOutput, ADiamond, DisableDiamondRequest, DisableDiamondResponse> getMapper() {
        return apiDisableDiamondMapper;
    }

    @DeleteMapping(APIConstant.SINGLE_DIAMOND_PATH)
    @DiamondTag
    @Operation(summary = "disable diamond")
    @ApiResponse(
            description = "Diamond infor",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = DisableDiamondResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> disable(DisableDiamondRequest request) {
        return handleRequest(request);
    }
}

