package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.dashboard.data.ViewPaymentWithDateData;
import com.cplerings.core.api.dashboard.mapper.APIViewPaymentWithDateMapper;
import com.cplerings.core.api.dashboard.request.ViewPaymentWithDateRequest;
import com.cplerings.core.api.dashboard.request.ViewRefundOrdersWithDateRequest;
import com.cplerings.core.api.dashboard.response.ViewPaymentWithDateResponse;
import com.cplerings.core.api.dashboard.response.ViewRefundOrdersWithDateResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewPaymentWithDateUseCase;
import com.cplerings.core.application.dashboard.input.ViewPaymentWithDateInput;
import com.cplerings.core.application.dashboard.output.ViewPaymentWithDateOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewPaymentWithDateController extends AbstractController<ViewPaymentWithDateInput, ViewPaymentWithDateOutput, ViewPaymentWithDateData, ViewPaymentWithDateRequest, ViewPaymentWithDateResponse> {

    private final APIViewPaymentWithDateMapper mapper;
    private final ViewPaymentWithDateUseCase useCase;

    @Override
    protected UseCase<ViewPaymentWithDateInput, ViewPaymentWithDateOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<ViewPaymentWithDateInput, ViewPaymentWithDateOutput, ViewPaymentWithDateData, ViewPaymentWithDateRequest, ViewPaymentWithDateResponse> getMapper() {
        return mapper;
    }

    @GetMapping(APIConstant.PAYMENTS_STATISTIC_PAGINATION_PATH)
    @DashboardTag
    @Operation(summary = "View payments")
    @ApiResponse(
            description = "The payments",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewPaymentWithDateResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewPaymentWithDateRequest request) {
        return handleRequest(request);
    }
}
