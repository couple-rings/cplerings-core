package com.cplerings.core.api.design.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomRequestRequest {

    private Long customerId;

    @Builder.Default
    private Set<Long> designIds = new HashSet<>();
}
