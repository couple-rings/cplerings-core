package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.configuration.Configuration;
import com.cplerings.core.domain.configuration.ConfigurationStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {

    Set<Configuration> findAllByStatus(ConfigurationStatus status);
}