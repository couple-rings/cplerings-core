package com.cplerings.core.application.shared.entity.design.request;

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
public class ACustomRequestHistory implements Serializable {

    private Long id;
    private ACustomRequestStatus status;
    private Instant createdAt;
}
