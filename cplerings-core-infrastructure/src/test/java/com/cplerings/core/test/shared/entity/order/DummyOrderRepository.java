package com.cplerings.core.test.shared.entity.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyOrderRepository extends JpaRepository<DummyOrder, Long> {

}