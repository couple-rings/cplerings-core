package com.cplerings.core.application.shared.entity.order;

import java.io.Serializable;
import java.time.Instant;

import com.cplerings.core.application.shared.entity.transport.ATransportationOrderStatus;

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
public class ATransportOrderHistory implements Serializable {

    private Long id;
    private ATransportationOrderStatus status;
    private Instant createdAt;
}
