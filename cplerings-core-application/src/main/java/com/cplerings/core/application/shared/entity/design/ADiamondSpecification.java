package com.cplerings.core.application.shared.entity.design;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ADiamondSpecification implements Serializable {

    private Long id;
    private String name;
    private BigDecimal weight;
    private ADiamondColor color;
    private ADiamondClarity clarity;
    private ADiamondShape shape;
    private BigDecimal price;
}