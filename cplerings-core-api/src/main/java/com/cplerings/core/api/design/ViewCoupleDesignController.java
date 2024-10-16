package com.cplerings.core.api.design;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.design.data.CoupleDesignInformation;
import com.cplerings.core.api.design.request.ViewDesignCouplesRequest;
import com.cplerings.core.api.design.response.ViewCoupleDesignResponse;
import com.cplerings.core.api.shared.AbstractPaginatedController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.input.ViewCoupleDesignInput;
import com.cplerings.core.application.design.output.ViewCoupleDesignOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ViewCoupleDesignController extends AbstractPaginatedController<ViewCoupleDesignInput, ViewCoupleDesignOutput, CoupleDesignInformation, ViewDesignCouplesRequest, ViewCoupleDesignResponse> {

    @GetMapping(APIConstant.DESIGN_COUPLE_PATH)
    @DesignTag
    @Operation(summary = "View couple designs")
    @ApiResponse(
            description = "View couple designs",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewCoupleDesignResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> viewDesign(ViewDesignCouplesRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<ViewCoupleDesignInput, ViewCoupleDesignOutput> getUseCase() {
        return null;
    }

    @Override
    protected APIMapper<ViewCoupleDesignInput, ViewCoupleDesignOutput, CoupleDesignInformation, ViewDesignCouplesRequest, ViewCoupleDesignResponse> getMapper() {
        return null;
    }
}
