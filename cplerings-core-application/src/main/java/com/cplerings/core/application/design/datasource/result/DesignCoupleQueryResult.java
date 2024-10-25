package com.cplerings.core.application.design.datasource.result;

import com.cplerings.core.domain.design.DesignCollection;
import com.cplerings.core.domain.file.Image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DesignCoupleQueryResult {

    private Long id;
    private Image previewImage;
    private String name;
    private String description;
    private DesignCollection designCollection;
}
