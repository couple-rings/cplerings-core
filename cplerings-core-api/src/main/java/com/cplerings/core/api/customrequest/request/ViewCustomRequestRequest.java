package com.cplerings.core.api.customrequest.request;

import jakarta.validation.constraints.NotBlank;

public record ViewCustomRequestRequest(@NotBlank Long customRequestId) {
}
