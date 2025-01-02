package com.cplerings.core.api.dashboard.request;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ViewTotalTypeOfPaymentRequest {

    private Instant startDate;
    private Instant endDate;
}
