package com.cplerings.core.test.integration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(
        basePackages = {
                "com.cplerings.core.api",
                "com.cplerings.core.application",
                "com.cplerings.core.domain",
                "com.cplerings.core.infrastructure",
                "com.cplerings.core.test.integration"
        }
)
public class IntegrationTestConfiguration {

}
