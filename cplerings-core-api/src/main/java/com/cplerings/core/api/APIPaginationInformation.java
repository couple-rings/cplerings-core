package com.cplerings.core.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIPaginationInformation {

    private int page;
    private int pageSize;
    private int totalCount;
}
