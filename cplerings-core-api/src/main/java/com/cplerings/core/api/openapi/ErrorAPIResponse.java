package com.cplerings.core.api.openapi;

import com.cplerings.core.api.APIConstant;
import com.cplerings.core.api.ErrorCodesResponse;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiResponse(
        description = "Bad Request due to either VALIDATION or BUSINESS errors.",
        responseCode = "400",
        content = @Content(
                mediaType = APIConstant.APPLICATION_JSON,
                schema = @Schema(implementation = ErrorCodesResponse.class)
        )
)
@ApiResponse(
        description = "Unauthorized access. The resource needs to have Authentication JWT along the request.",
        responseCode = "401",
        content = @Content(
                mediaType = APIConstant.APPLICATION_JSON,
                schema = @Schema(implementation = ErrorCodesResponse.class)
        )
)
@ApiResponse(
        description = "Forbidden. The current Authentication JWT does not have the privilege to access the resource.",
        responseCode = "403",
        content = @Content(
                mediaType = APIConstant.APPLICATION_JSON,
                schema = @Schema(implementation = ErrorCodesResponse.class)
        )
)
@ApiResponse(
        description = "The resource is not found.",
        responseCode = "404",
        content = @Content(
                mediaType = APIConstant.APPLICATION_JSON,
                schema = @Schema(implementation = ErrorCodesResponse.class)
        )
)
@ApiResponse(
        description = "Server error. Please contact Admin about this.",
        responseCode = "500",
        content = @Content(
                mediaType = APIConstant.APPLICATION_JSON,
                schema = @Schema(implementation = ErrorCodesResponse.class)
        )
)
public @interface ErrorAPIResponse {

}
