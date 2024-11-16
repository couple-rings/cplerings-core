package com.cplerings.core.test.component.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import com.cplerings.core.test.shared.AbstractCT;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.entity.order.DummyOrder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicReference;

class AbstractOrderEntityCT extends AbstractCT {

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenAbstractOrderEntity_whenInsertNew() {
        final AtomicReference<DummyOrder> dummyOrderRef = new AtomicReference<>();
        assertThatNoException().isThrownBy(() -> dummyOrderRef.set(testDataSource.save(DummyOrder.builder().build())));

        final DummyOrder dummyOrder = dummyOrderRef.get();
        thenOrderNoIsPopulated(dummyOrder);
    }

    private void thenOrderNoIsPopulated(DummyOrder dummyOrder) {
        assertThat(dummyOrder.getOrderNo()).isEqualTo("00000001");
    }
}
