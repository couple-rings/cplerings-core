package com.cplerings.core.application.dashboard.input;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewTotalTypeOfPaymentInput {

    private Instant startDate;
    private Instant endDate;
}
