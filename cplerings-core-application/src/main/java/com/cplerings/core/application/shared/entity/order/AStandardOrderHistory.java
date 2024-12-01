package com.cplerings.core.application.shared.entity.order;

import java.io.Serializable;
import java.time.Instant;

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
public class AStandardOrderHistory implements Serializable {

    private Long id;
    private AStandardOrderStatus status;
    private Instant createdAt;
}
