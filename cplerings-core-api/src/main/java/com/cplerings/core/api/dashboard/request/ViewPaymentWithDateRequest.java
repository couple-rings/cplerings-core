package com.cplerings.core.api.dashboard.request;

import java.time.Instant;

import com.cplerings.core.api.shared.AbstractPaginatedRequest;

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
public class ViewPaymentWithDateRequest extends AbstractPaginatedRequest {

    private Instant startDate;
    private Instant endDate;
}
