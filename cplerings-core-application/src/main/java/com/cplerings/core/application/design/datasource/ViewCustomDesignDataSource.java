package com.cplerings.core.application.design.datasource;

import java.util.Optional;

import com.cplerings.core.domain.design.CustomDesign;

public interface ViewCustomDesignDataSource {

   Optional<CustomDesign> getCustomDesignByCustomDesignId(Long customDesignId);
}
