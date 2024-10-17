package com.cplerings.core.api.account;

import com.cplerings.core.api.account.data.Profile;
import com.cplerings.core.api.account.mapper.APIViewCurrentProfileMapper;
import com.cplerings.core.api.account.response.ProfileResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.NoRequest;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.AccountTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.account.ViewCurrentProfileUseCase;
import com.cplerings.core.application.account.output.ProfileOutput;
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
public class ViewCurrentProfileController extends AbstractDataController<NoInput, ProfileOutput, Profile, NoRequest, ProfileResponse> {

    private final ViewCurrentProfileUseCase viewCurrentProfileUseCase;
    private final APIViewCurrentProfileMapper apiViewCurrentProfileMapper;

    @GetMapping(APIConstant.CURRENT_PROFILE_PATH)
    @AccountTag
    @Operation(summary = "View current account's profile")
    @ApiResponse(
            description = "The profile",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ProfileResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view() {
        return handleRequest();
    }

    @Override
    protected UseCase<NoInput, ProfileOutput> getUseCase() {
        return viewCurrentProfileUseCase;
    }

    @Override
    protected APIMapper<NoInput, ProfileOutput, Profile, NoRequest, ProfileResponse> getMapper() {
        return apiViewCurrentProfileMapper;
    }
}
