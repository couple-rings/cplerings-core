package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.address.TransportationAddress;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportationAddressRepository extends JpaRepository<TransportationAddress, Long> {

}