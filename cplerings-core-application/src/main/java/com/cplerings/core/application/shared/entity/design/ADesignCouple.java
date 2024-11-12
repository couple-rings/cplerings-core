package com.cplerings.core.application.shared.entity.design;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

import com.cplerings.core.application.shared.entity.file.AImage;
import com.cplerings.core.domain.shared.State;

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
public class ADesignCouple implements Serializable {

    private Long id;
    private AImage previewImage;
    private String name;
    private String description;
    private Set<ADesign> designs;
    private Instant createdAt;
    private State state;
}