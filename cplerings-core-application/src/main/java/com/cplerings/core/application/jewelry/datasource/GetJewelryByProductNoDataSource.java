package com.cplerings.core.application.jewelry.datasource;

import java.util.Optional;

import com.cplerings.core.domain.jewelry.Jewelry;

public interface GetJewelryByProductNoDataSource {

    Optional<Jewelry> getJewelryByProductNo(String productNo);
}
