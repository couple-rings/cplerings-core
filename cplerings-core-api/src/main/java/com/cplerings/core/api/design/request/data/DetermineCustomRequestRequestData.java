package com.cplerings.core.api.design.request.data;

import com.cplerings.core.application.shared.entity.design.request.ACustomRequestStatus;

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
public class DetermineCustomRequestRequestData {

    private ACustomRequestStatus customRequestStatus;
    private Long staffId;
}
