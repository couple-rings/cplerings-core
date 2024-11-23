package com.cplerings.core.application.design.datasource;

import java.util.Optional;

import com.cplerings.core.domain.design.Design;

public interface ViewDesignDataSource {

    Optional<Design> getDesign(Long designId);
}
