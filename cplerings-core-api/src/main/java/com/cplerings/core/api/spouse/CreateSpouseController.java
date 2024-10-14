package com.cplerings.core.api.spouse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.mapper.APIMapper;
import com.cplerings.core.api.openapi.ErrorAPIResponse;
import com.cplerings.core.api.openapi.SpouseTag;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.NoData;
import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.api.spouse.mapper.APICreateSpouseMapper;
import com.cplerings.core.api.spouse.request.CreateSpouseRequest;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.application.spouse.CreateSpouseUseCase;
import com.cplerings.core.application.spouse.input.CreateSpouseInput;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CreateSpouseController extends AbstractController<CreateSpouseInput, NoOutput, NoData, CreateSpouseRequest, NoResponse> {

    private final CreateSpouseUseCase createSpouseUseCase;
    private final APICreateSpouseMapper apiCreateSpouseMapper;

    @PostMapping(APIConstant.SPOUSES_PATH)
    @SpouseTag
    @Operation(summary = "Create spouse")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Create spouse",
            required = true,
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateSpouseRequest.class)
            )
    )
    @ApiResponse(
            description = "No data response",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = NoResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> createSpouse(@RequestBody CreateSpouseRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<CreateSpouseInput, NoOutput> getUseCase() {
        return createSpouseUseCase;
    }

    @Override
    protected APIMapper<CreateSpouseInput, NoOutput, NoData, CreateSpouseRequest, NoResponse> getMapper() {
        return apiCreateSpouseMapper;
    }
}
