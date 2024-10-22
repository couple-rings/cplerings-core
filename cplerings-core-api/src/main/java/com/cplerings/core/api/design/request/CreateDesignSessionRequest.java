package com.cplerings.core.api.design.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateDesignSessionRequest(@NotBlank Long customerId) {

}
