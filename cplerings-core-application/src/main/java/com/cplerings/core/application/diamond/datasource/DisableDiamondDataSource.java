package com.cplerings.core.application.diamond.datasource;

import java.util.Optional;

import com.cplerings.core.domain.diamond.Diamond;

public interface DisableDiamondDataSource {

    Optional<Diamond> getDiamondById(Long diamondId);

    Diamond save(Diamond diamond);
}
