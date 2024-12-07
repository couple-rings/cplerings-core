package com.cplerings.core.test.component.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cplerings.core.test.shared.AbstractCT;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.entity.product.DummyProduct;

class AbstractProductEntityCT extends AbstractCT {

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenAbstractProductEntity_whenInsertNew() {
        final AtomicReference<DummyProduct> dummyProductRef = new AtomicReference<>();
        assertThatNoException().isThrownBy(() -> dummyProductRef.set(testDataSource.save(DummyProduct.builder()
                .build())));

        final DummyProduct dummyProduct = dummyProductRef.get();
        thenOrderNoIsPopulated(dummyProduct);
    }

    private void thenOrderNoIsPopulated(DummyProduct dummyProduct) {
        assertThat(dummyProduct.getProductNo()).isEqualTo("00000001");
    }
}
