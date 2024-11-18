package com.cplerings.core.api.agreement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.agreement.data.AgreementsData;
import com.cplerings.core.api.agreement.mapper.APIViewAgreementsMapper;
import com.cplerings.core.api.agreement.request.ViewAgreementsRequest;
import com.cplerings.core.api.agreement.response.ViewAgreementsResponse;
import com.cplerings.core.api.diamond.request.ViewDiamondSpecificationRequest;
import com.cplerings.core.api.diamond.response.ViewDiamondSpecificationResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.AgreementTag;
import com.cplerings.core.api.shared.openapi.DiamondSpecificationTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.agreement.ViewAgreementsUseCase;
import com.cplerings.core.application.agreement.datasource.result.Agreements;
import com.cplerings.core.application.agreement.input.ViewAgreementsInput;
import com.cplerings.core.application.agreement.output.ViewAgreementsOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewAgreementsController extends AbstractController<ViewAgreementsInput, ViewAgreementsOutput, AgreementsData, ViewAgreementsRequest, ViewAgreementsResponse> {

    private final ViewAgreementsUseCase viewAgreementsUseCase;
    private final APIViewAgreementsMapper apiViewAgreementsMapper;

    @Override
    protected UseCase<ViewAgreementsInput, ViewAgreementsOutput> getUseCase() {
        return viewAgreementsUseCase;
    }

    @Override
    protected APIMapper<ViewAgreementsInput, ViewAgreementsOutput, AgreementsData, ViewAgreementsRequest, ViewAgreementsResponse> getMapper() {
        return apiViewAgreementsMapper;
    }

    @GetMapping(APIConstant.AGREEMENTS_PATH)
    @AgreementTag
    @Operation(summary = "View agreements")
    @ApiResponse(
            description = "Agreements data",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewAgreementsResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewAgreementsRequest request) {
        return handleRequest(request);
    }
}
