package com.cplerings.core.application.shared.entity.design;

import com.cplerings.core.application.shared.entity.file.AImage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

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
}