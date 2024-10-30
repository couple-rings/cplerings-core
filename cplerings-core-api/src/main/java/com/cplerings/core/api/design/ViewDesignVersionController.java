package com.cplerings.core.api.design;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.design.request.ViewDesignVersionRequest;
import com.cplerings.core.api.design.response.ViewDesignVersionResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.openapi.DesignTag;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ViewDesignVersionController extends AbstractController<> {

    @GetMapping(APIConstant.)
    @DesignTag
    @Operation(summary = "View design version")
    @ApiResponse(
            description = "Design version information",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = ViewDesignVersionResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> viewDesign(@PathVariable("designVersionId") Long designVersionId) {
        ViewDesignVersionRequest request = ViewDesignVersionRequest.builder().designVersionId(designVersionId).build();
        return handleRequest(request);
    }
}
