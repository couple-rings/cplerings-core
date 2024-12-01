package com.cplerings.core.api.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.account.data.DesignStaffsData;
import com.cplerings.core.api.account.mapper.APIGetDesignStaffsMapper;
import com.cplerings.core.api.account.request.GetDesignStaffsRequest;
import com.cplerings.core.api.account.request.ViewTransportersRequest;
import com.cplerings.core.api.account.response.GetDesignStaffsResponse;
import com.cplerings.core.api.account.response.ViewTransportersResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.AccountTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.account.GetDesignStaffsUseCase;
import com.cplerings.core.application.account.input.GetDesignStaffsInput;
import com.cplerings.core.application.account.output.GetDesignStaffsOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class GetDesignStaffsController extends AbstractController<GetDesignStaffsInput, GetDesignStaffsOutput, DesignStaffsData, GetDesignStaffsRequest, GetDesignStaffsResponse> {

    private final APIGetDesignStaffsMapper apiGetDesignStaffsMapper;
    private final GetDesignStaffsUseCase getDesignStaffsUseCase;

    @Override
    protected UseCase<GetDesignStaffsInput, GetDesignStaffsOutput> getUseCase() {
        return getDesignStaffsUseCase;
    }

    @Override
    protected APIMapper<GetDesignStaffsInput, GetDesignStaffsOutput, DesignStaffsData, GetDesignStaffsRequest, GetDesignStaffsResponse> getMapper() {
        return apiGetDesignStaffsMapper;
    }

    @GetMapping(APIConstant.GET_DESIGN_STAFFS_PATH)
    @AccountTag
    @Operation(summary = "View design staffs")
    @ApiResponse(
            description = "The design staffs",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = GetDesignStaffsResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(GetDesignStaffsRequest request) {
        return handleRequest(request);
    }
}
