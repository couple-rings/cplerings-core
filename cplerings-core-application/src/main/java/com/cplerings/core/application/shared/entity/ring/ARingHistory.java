package com.cplerings.core.application.shared.entity.ring;

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
public class ARingHistory implements Serializable {

    private Long id;
    private ARingStatus status;
    private Instant createdAt;
}
