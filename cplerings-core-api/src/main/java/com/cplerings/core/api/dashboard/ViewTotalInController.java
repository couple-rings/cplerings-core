package com.cplerings.core.api.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.dashboard.data.ViewTotalInData;
import com.cplerings.core.api.dashboard.mapper.APIViewTotalInMapper;
import com.cplerings.core.api.dashboard.request.ViewTotalInRequest;
import com.cplerings.core.api.dashboard.response.ViewTotalInResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DashboardTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.dashboard.ViewTotalInUseCase;
import com.cplerings.core.application.dashboard.input.ViewTotalInInput;
import com.cplerings.core.application.dashboard.output.ViewTotalInOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewTotalInController extends AbstractController<ViewTotalInInput, ViewTotalInOutput, ViewTotalInData, ViewTotalInRequest, ViewTotalInResponse> {

    private final APIViewTotalInMapper mapper;
    private final ViewTotalInUseCase useCase;

    @Override
    protected UseCase<ViewTotalInInput, ViewTotalInOutput> getUseCase() {
        return useCase;
    }

    @Override
    protected APIMapper<ViewTotalInInput, ViewTotalInOutput, ViewTotalInData, ViewTotalInRequest, ViewTotalInResponse> getMapper() {
        return mapper;
    }

    @GetMapping(APIConstant.TOTAL_INCOME_PATH_WITH_DATE)
    @DashboardTag
    @Operation(summary = "View total Orders")
    @ApiResponse(
            description = "The total Orders",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewTotalInResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(ViewTotalInRequest request) {
        return handleRequest(request);
    }
}
