package com.cplerings.core.api.design.request;

import jakarta.validation.constraints.NotBlank;

public record ViewCustomRequestRequest(@NotBlank Long customRequestId) {
}
