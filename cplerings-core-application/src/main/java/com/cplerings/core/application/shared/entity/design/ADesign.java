package com.cplerings.core.application.shared.entity.design;

import com.cplerings.core.application.shared.entity.file.ADocument;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ADesign implements Serializable {

    private Long id;
    private BigDecimal metalWeight;
    private String name;
    private String description;
    private ADocument blueprint;
    private ADesignCharacteristic characteristic;
    private Integer size;
    private Integer sideDiamondsCount;
    private Collection<ADesignMetalSpecification> designMetalSpecifications;
    private Collection<ADesignDiamondSpecification> designDiamondSpecifications;
    private ADesignCollection designCollection;
}