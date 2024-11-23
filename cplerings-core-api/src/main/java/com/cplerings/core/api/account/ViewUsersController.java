package com.cplerings.core.api.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.account.data.UsersData;
import com.cplerings.core.api.account.mapper.APIViewUsersMapper;
import com.cplerings.core.api.account.request.ViewUsersRequest;
import com.cplerings.core.api.account.response.ViewUsersResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.AccountTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.account.ViewUsersUseCase;
import com.cplerings.core.application.account.input.ViewUsersInput;
import com.cplerings.core.application.account.output.ViewUsersOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewUsersController extends AbstractController<ViewUsersInput, ViewUsersOutput, UsersData, ViewUsersRequest, ViewUsersResponse> {

    private final APIViewUsersMapper apiViewUsersMapper;
    private final ViewUsersUseCase viewUsersUseCase;

    @Override
    protected UseCase<ViewUsersInput, ViewUsersOutput> getUseCase() {
        return viewUsersUseCase;
    }

    @Override
    protected APIMapper<ViewUsersInput, ViewUsersOutput, UsersData, ViewUsersRequest, ViewUsersResponse> getMapper() {
        return apiViewUsersMapper;
    }

    @GetMapping(APIConstant.USERS_PATH)
    @AccountTag
    @Operation(summary = "View users")
    @ApiResponse(
            description = "The users",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewUsersResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> view(@RequestParam List<Long> userIds) {
        ViewUsersRequest request = ViewUsersRequest.builder().userIds(userIds).build();
        return handleRequest(request);
    }
}
