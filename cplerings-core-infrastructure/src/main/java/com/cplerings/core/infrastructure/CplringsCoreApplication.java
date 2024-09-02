package com.cplerings.core.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.cplerings.core.api",
                "com.cplerings.core.application",
                "com.cplerings.core.domain",
                "com.cplerings.core.infrastructure",
        }
)
public class CplringsCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CplringsCoreApplication.class, args);
    }
}
