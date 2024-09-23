package com.cplerings.core.test.integration.shared;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TestConfiguration
@ComponentScan(
        basePackages = {
                "com.cplerings.core.api",
                "com.cplerings.core.application",
                "com.cplerings.core.infrastructure",
                "com.cplerings.core.test.integration.shared"
        }
)
@EntityScan(
        basePackages = {
                "com.cplerings.core.domain",
                "com.cplerings.core.test.integration.internal"
        }
)
@EnableJpaRepositories(
        basePackages = {
                "com.cplerings.core.test.integration.shared"
        }
)
public class IntegrationTestConfiguration {

}
