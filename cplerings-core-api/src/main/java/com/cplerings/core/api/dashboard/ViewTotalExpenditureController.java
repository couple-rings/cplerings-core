package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.dashboard.data.ViewTotalExpenditureData;
import com.cplerings.core.api.dashboard.mapper.APIViewTotalExpenditureMapper;
import com.cplerings.core.api.dashboard.request.ViewTotalExpenditureRequest;
import com.cplerings.core.api.dashboard.response.ViewTotalExpenditureResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewTotalExpenditureUseCase;
import com.cplerings.core.application.dashboard.input.ViewTotalExpenditureInput;
import com.cplerings.core.application.dashboard.output.ViewTotalExpenditureOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewTotalExpenditureController extends AbstractController<ViewTotalExpenditureInput, ViewTotalExpenditureOutput, ViewTotalExpenditureData, ViewTotalExpenditureRequest, ViewTotalExpenditureResponse> {

    private final APIViewTotalExpenditureMapper mapper;
    private final ViewTotalExpenditureUseCase useCase;

    @Override
    protected UseCase<ViewTotalExpenditureInput, ViewTotalExpenditureOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<ViewTotalExpenditureInput, ViewTotalExpenditureOutput, ViewTotalExpenditureData, ViewTotalExpenditureRequest, ViewTotalExpenditureResponse> getMapper() {
        return mapper;
    }

    @GetMapping(APIConstant.TOTAL_EXPENDITURE_PATH_WITH_DATE)
    @DashboardTag
    @Operation(summary = "View total expenditure")
    @ApiResponse(
            description = "The total expenditure",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewTotalExpenditureResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewTotalExpenditureRequest request) {
        return handleRequest(request);
    }
}
