package com.cplerings.core.application.design.queryoutput;

import com.cplerings.core.domain.design.DesignCollection;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.metal.MetalSpecification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DesignCoupleQueryOutput {

    private Long id;
    private Image previewImage;
    private String name;
    private String description;
    private MetalSpecification metalSpecification;
    private DesignCollection designCollection;
}
