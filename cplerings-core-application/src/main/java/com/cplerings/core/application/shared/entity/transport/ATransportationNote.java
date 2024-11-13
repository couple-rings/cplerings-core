package com.cplerings.core.application.shared.entity.transport;

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
public class ATransportationNote implements Serializable {

    private Long id;
    private Instant date;
    private String note;
}
