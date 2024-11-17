package com.cplerings.core.application.shared.service.configuration;

import com.cplerings.core.domain.shared.valueobject.Money;

public interface ConfigurationService {

    Money getDesignFee();

    Money getSideDiamondPrice();

    Integer getCraftingStageProgress1();

    Integer getCraftingStageProgress2();

    Integer getCraftingStageProgress3();

    Integer getMaximumMaintenanceDuration();
}
