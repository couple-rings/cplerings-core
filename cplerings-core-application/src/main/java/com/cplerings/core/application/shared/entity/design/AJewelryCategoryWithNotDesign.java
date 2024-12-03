package com.cplerings.core.application.shared.entity.design;

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
public class AJewelryCategoryWithNotDesign implements Serializable {

    private Long id;
    private String name;
    private String description;
}
