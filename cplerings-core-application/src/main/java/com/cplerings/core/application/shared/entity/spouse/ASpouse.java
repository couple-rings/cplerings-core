package com.cplerings.core.application.shared.entity.spouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ASpouse implements Serializable {

    private Long id;
    private Long customerId;
    private UUID coupleId;
    private Instant createdAt;
}
