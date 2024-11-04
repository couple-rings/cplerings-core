package com.cplerings.core.application.design.output;

import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;

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
public class CreateCustomRequestOutput {

    private ACustomRequest request;
}
