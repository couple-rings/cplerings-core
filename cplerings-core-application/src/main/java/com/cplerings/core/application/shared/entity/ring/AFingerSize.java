package com.cplerings.core.application.shared.entity.ring;

import java.io.Serializable;

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
public class AFingerSize implements Serializable {

    private Long id;
    private Integer size;
    private Double diameter;
}
