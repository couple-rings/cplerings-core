package com.cplerings.core.application.shared.entity.jewelry;

import java.io.Serializable;
import java.util.Collection;

import com.cplerings.core.application.shared.entity.design.ADesign;

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
public class AJewelryCategory implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Collection<ADesign> designs;
}
