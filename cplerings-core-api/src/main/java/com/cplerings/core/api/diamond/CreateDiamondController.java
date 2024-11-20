package com.cplerings.core.api.diamond;

import com.cplerings.core.api.diamond.mapper.APICreateDiamondMapper;
import com.cplerings.core.api.diamond.request.CreateDiamondRequest;
import com.cplerings.core.api.diamond.response.CreateDiamondResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DiamondTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.diamond.CreateDiamondUseCase;
import com.cplerings.core.application.diamond.input.CreateDiamondInput;
import com.cplerings.core.application.diamond.output.CreateDiamondOutput;
import com.cplerings.core.application.shared.entity.design.ADiamond;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
public class CreateDiamondController extends AbstractDataController<CreateDiamondInput, CreateDiamondOutput, ADiamond, CreateDiamondRequest, CreateDiamondResponse> {

    private final CreateDiamondUseCase createDiamondUseCase;
    private final APICreateDiamondMapper apiCreateDiamondMapper;

    @Override
    protected UseCase<CreateDiamondInput, CreateDiamondOutput> getUseCase() {
        return createDiamondUseCase;
    }

    @Override
    protected APIMapper<CreateDiamondInput, CreateDiamondOutput, ADiamond, CreateDiamondRequest, CreateDiamondResponse> getMapper() {
        return apiCreateDiamondMapper;
    }

    @PostMapping(APIConstant.DIAMONDS_PATH)
    @DiamondTag
    @Operation(summary = "Create diamond")
    @ApiResponse(
            description = "Diamond created",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateDiamondResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(CreateDiamondRequest request) {
        return handleRequest(request);
    }
}
