package com.cplerings.core.application.shared.entity.configuration;

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
public class AConfiguration {

    private Long id;
    private String key;
    private String value;
    private Instant createdAt;
}
