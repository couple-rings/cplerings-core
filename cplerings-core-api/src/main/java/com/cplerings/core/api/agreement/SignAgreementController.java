package com.cplerings.core.api.agreement;

import com.cplerings.core.api.agreement.mapper.APISignAgreementMapper;
import com.cplerings.core.api.agreement.request.SignAgreementRequest;
import com.cplerings.core.api.agreement.response.SignAgreementResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.AgreementTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.agreement.SignAgreementUseCase;
import com.cplerings.core.application.agreement.input.SignAgreementInput;
import com.cplerings.core.application.agreement.output.SignAgreementOutput;
import com.cplerings.core.application.shared.entity.agreement.AAgreement;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
public class SignAgreementController extends AbstractDataController<SignAgreementInput, SignAgreementOutput, AAgreement, SignAgreementRequest, SignAgreementResponse> {

    private final SignAgreementUseCase signAgreementUseCase;
    private final APISignAgreementMapper apisignAgreementMapper;

    @Override
    protected UseCase<SignAgreementInput, SignAgreementOutput> getUseCase() {
        return signAgreementUseCase;
    }

    @Override
    protected APIMapper<SignAgreementInput, SignAgreementOutput, AAgreement, SignAgreementRequest, SignAgreementResponse> getMapper() {
        return apisignAgreementMapper;
    }

    @PutMapping(APIConstant.SIGN_AGREEMENT_PATH)
    @AgreementTag
    @Operation(summary = "Sign agreement")
    @ApiResponse(
            description = "Agreement data",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = SignAgreementResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> sign(@PathVariable(name = "agreementId") Long agreementId,
                                       @RequestBody SignAgreementRequest request) {
        request.setAgreementId(agreementId);
        return handleRequest(request);
    }
}
