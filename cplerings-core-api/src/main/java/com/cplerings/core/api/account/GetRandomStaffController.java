package com.cplerings.core.api.account;

import com.cplerings.core.api.account.mapper.APIGetRandomStaffMapper;
import com.cplerings.core.api.account.response.GetRandomStaffResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.NoRequest;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.AccountTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.account.GetRandomStaffUseCase;
import com.cplerings.core.application.account.output.GetRandomStaffOutput;
import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
public class GetRandomStaffController extends AbstractDataController<NoInput, GetRandomStaffOutput, AAccount, NoRequest, GetRandomStaffResponse> {

    private final GetRandomStaffUseCase getRandomStaffUseCase;
    private final APIGetRandomStaffMapper apiGetRandomStaffMapper;

    @Override
    protected UseCase<NoInput, GetRandomStaffOutput> getUseCase() {
        return getRandomStaffUseCase;
    }

    @Override
    protected APIMapper<NoInput, GetRandomStaffOutput, AAccount, NoRequest, GetRandomStaffResponse> getMapper() {
        return apiGetRandomStaffMapper;
    }

    @GetMapping(APIConstant.GET_RANDOM_STAFF_PATH)
    @AccountTag
    @Operation(summary = "Get random staff")
    @ApiResponse(
            description = "The staff",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = GetRandomStaffResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> random() {
        return handleRequest();
    }
}
