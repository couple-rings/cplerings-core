package com.cplerings.core.application.jewelry.datasource.result;

import java.util.List;

import com.cplerings.core.domain.jewelry.JewelryCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class JewelryCategories {

    private List<JewelryCategory> jewelryCategories;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
