package com.cplerings.core.test.shared.entity.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyProductRepository extends JpaRepository<DummyProduct, Long> {

}