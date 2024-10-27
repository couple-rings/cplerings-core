package com.cplerings.core.api.design;

import com.cplerings.core.api.design.data.RemainingDesignSessionData;
import com.cplerings.core.api.design.mapper.APICheckRemainingDesignSessionMapper;
import com.cplerings.core.api.design.response.CheckRemainingDesignSessionResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.NoRequest;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.application.design.CheckRemainingDesignSessionUseCase;
import com.cplerings.core.application.design.output.CheckRemainingDesignSessionOutput;
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
public class CheckRemainingDesignSessionController extends AbstractDataController<NoInput, CheckRemainingDesignSessionOutput, RemainingDesignSessionData, NoRequest, CheckRemainingDesignSessionResponse> {

    private final CheckRemainingDesignSessionUseCase checkRemainingDesignSessionUseCase;
    private final APICheckRemainingDesignSessionMapper apiCheckRemainingDesignSessionMapper;

    @GetMapping(APIConstant.DESIGN_SESSION_PATH)
    @DesignTag
    @Operation(summary = "Get remaining design sessions count")
    @ApiResponse(
            description = "Remaining count",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = CheckRemainingDesignSessionResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> create() {
        return handleRequest();
    }

    @Override
    protected UseCase<NoInput, CheckRemainingDesignSessionOutput> getUseCase() {
        return checkRemainingDesignSessionUseCase;
    }

    @Override
    protected APIMapper<NoInput, CheckRemainingDesignSessionOutput, RemainingDesignSessionData, NoRequest, CheckRemainingDesignSessionResponse> getMapper() {
        return apiCheckRemainingDesignSessionMapper;
    }
}
