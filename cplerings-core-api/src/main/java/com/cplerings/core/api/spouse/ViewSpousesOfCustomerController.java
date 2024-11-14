package com.cplerings.core.api.spouse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.SpouseTag;
import com.cplerings.core.api.spouse.data.SpouseList;
import com.cplerings.core.api.spouse.mapper.APIViewSpousesOfCustomerMapper;
import com.cplerings.core.api.spouse.request.ViewSpousesOfCustomerRequest;
import com.cplerings.core.api.spouse.response.ViewSpousesOfCustomerResponse;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.application.spouse.ViewSpousesOfCustomerUseCase;
import com.cplerings.core.application.spouse.input.ViewSpousesOfCustomerInput;
import com.cplerings.core.application.spouse.output.ViewSpousesOfCustomerOutput;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewSpousesOfCustomerController extends AbstractController<ViewSpousesOfCustomerInput, ViewSpousesOfCustomerOutput, SpouseList, ViewSpousesOfCustomerRequest, ViewSpousesOfCustomerResponse> {

    private final APIViewSpousesOfCustomerMapper apiViewSpousesOfCustomerMapper;
    private final ViewSpousesOfCustomerUseCase viewSpousesOfCustomerUseCase;

    @Override
    protected UseCase<ViewSpousesOfCustomerInput, ViewSpousesOfCustomerOutput> getUseCase() {
        return viewSpousesOfCustomerUseCase;
    }

    @Override
    protected APIMapper<ViewSpousesOfCustomerInput, ViewSpousesOfCustomerOutput, SpouseList, ViewSpousesOfCustomerRequest, ViewSpousesOfCustomerResponse> getMapper() {
        return apiViewSpousesOfCustomerMapper;
    }

    @GetMapping(APIConstant.SPOUSES_PATH)
    @SpouseTag
    @Operation(summary = "View spouses")
    @ApiResponse(
            description = "2 spouses data",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewSpousesOfCustomerResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> createSpouse(ViewSpousesOfCustomerRequest request) {
        return handleRequest(request);
    }
}
