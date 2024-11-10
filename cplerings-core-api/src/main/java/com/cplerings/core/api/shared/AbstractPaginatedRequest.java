package com.cplerings.core.api.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.Min;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractPaginatedRequest {

    @Min(value = 0, message = "Page must be larger or equal to 0")
    private int page;

    @Min(value = 1, message = "The page size must be larger or equal to 1")
    private int pageSize;
}
