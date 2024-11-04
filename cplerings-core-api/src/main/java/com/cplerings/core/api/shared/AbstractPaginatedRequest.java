package com.cplerings.core.api.shared;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class AbstractPaginatedRequest {

    @Min(value = 0, message = "Page must be larger or equal to 0")
    private int page;

    @Min(value = 1, message = "The page size must be larger or equal to 1")
    private int pageSize;
}
