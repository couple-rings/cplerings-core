package com.cplerings.core.api.jewelry;

import com.cplerings.core.api.jewelry.mapper.APICreateJewelryMapper;
import com.cplerings.core.api.jewelry.request.CreateJewelryRequest;
import com.cplerings.core.api.jewelry.response.CreateJewelryResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.JewelryTag;
import com.cplerings.core.application.jewelry.CreateJewelryUseCase;
import com.cplerings.core.application.jewelry.input.CreateJewelryInput;
import com.cplerings.core.application.jewelry.output.CreateJewelryOutput;
import com.cplerings.core.application.shared.entity.jewelry.AJewelry;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequiredArgsConstructor
@RestController
public class CreateJewelryController extends AbstractController<CreateJewelryInput, CreateJewelryOutput, AJewelry, CreateJewelryRequest, CreateJewelryResponse> {

    private final APICreateJewelryMapper apiCreateJewelryMapper;
    private final CreateJewelryUseCase createJewelryUseCase;

    @Override
    protected UseCase<CreateJewelryInput, CreateJewelryOutput> getUseCase() {
        return createJewelryUseCase;
    }

    @Override
    protected APIMapper<CreateJewelryInput, CreateJewelryOutput, AJewelry, CreateJewelryRequest, CreateJewelryResponse> getMapper() {
        return apiCreateJewelryMapper;
    }

    @PostMapping(APIConstant.JEWELRIES_PATH)
    @JewelryTag
    @Operation(summary = "Create jewelry")
    @ApiResponse(
            description = "jewelry information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateJewelryResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create(@RequestBody CreateJewelryRequest request) {
        return handleRequest(request);
    }
}
