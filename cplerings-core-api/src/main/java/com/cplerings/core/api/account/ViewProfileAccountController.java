package com.cplerings.core.api.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.account.data.AccountProfile;
import com.cplerings.core.api.account.mapper.APIViewProfileAccountMapper;
import com.cplerings.core.api.account.request.ViewProfileAccountRequest;
import com.cplerings.core.api.account.response.AccountProfileResponse;
import com.cplerings.core.api.authentication.response.AuthenticationTokenResponse;
import com.cplerings.core.api.mapper.APIMapper;
import com.cplerings.core.api.openapi.AccountTag;
import com.cplerings.core.api.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.application.account.ViewProfileUseCase;
import com.cplerings.core.application.account.input.ViewProfileInput;
import com.cplerings.core.application.account.output.AccountProfileOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ViewProfileAccountController extends AbstractDataController<ViewProfileInput, AccountProfileOutput, AccountProfile, ViewProfileAccountRequest, AccountProfileResponse> {

    private final ViewProfileUseCase viewProfileUseCase;
    private final APIViewProfileAccountMapper apiViewProfileAccountMapper;

    @GetMapping(APIConstant.PROFILE_PATH)
    @AccountTag
    @Operation(summary = "Account profile")
    @ApiResponse(
            description = "The authentication token",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = AuthenticationTokenResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> viewProfile(@PathVariable("id") Long id) {
        ViewProfileAccountRequest viewProfileAccountRequest = new ViewProfileAccountRequest(id);
        return handleRequest(viewProfileAccountRequest);
    }

    @Override
    protected UseCase getUseCase() {
        return viewProfileUseCase;
    }

    @Override
    protected APIMapper getMapper() {
        return apiViewProfileAccountMapper;
    }
}
