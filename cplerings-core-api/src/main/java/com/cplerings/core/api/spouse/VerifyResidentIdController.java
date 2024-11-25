package com.cplerings.core.api.spouse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.SpouseTag;
import com.cplerings.core.api.spouse.data.SpouseData;
import com.cplerings.core.api.spouse.mapper.APIVerifyResidentIdMapper;
import com.cplerings.core.api.spouse.request.VerifyResidentIdRequest;
import com.cplerings.core.api.spouse.request.data.VerifyResidentIdRequestData;
import com.cplerings.core.api.spouse.response.VerifyResidentIdResponse;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.application.spouse.VerifyResidentIdUseCase;
import com.cplerings.core.application.spouse.input.VerifyResidentIdInput;
import com.cplerings.core.application.spouse.output.VerifyResidentIdOutput;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class VerifyResidentIdController extends AbstractController<VerifyResidentIdInput, VerifyResidentIdOutput, SpouseData, VerifyResidentIdRequest, VerifyResidentIdResponse> {

    private final APIVerifyResidentIdMapper apiVerifyResidentIdMapper;
    private final VerifyResidentIdUseCase verifyResidentIdUseCase;

    @GetMapping(APIConstant.VERIFY_SPOUSE_PATH)
    @SpouseTag
    @Operation(summary = "Verify spouse")
    @ApiResponse(
            description = "Spouse data",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = VerifyResidentIdResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> verifySpouse(@PathVariable("citizenId") String citizenId, VerifyResidentIdRequestData verifyResidentIdRequestData) {
        VerifyResidentIdRequest request = VerifyResidentIdRequest.builder()
                .citizenId(citizenId)
                .customerId(verifyResidentIdRequestData.customerId())
                .build();
        return handleRequest(request);
    }

    @Override
    protected UseCase<VerifyResidentIdInput, VerifyResidentIdOutput> getUseCase() {
        return verifyResidentIdUseCase;
    }

    @Override
    protected APIMapper<VerifyResidentIdInput, VerifyResidentIdOutput, SpouseData, VerifyResidentIdRequest, VerifyResidentIdResponse> getMapper() {
        return apiVerifyResidentIdMapper;
    }
}
