package com.cplerings.core.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(
        scanBasePackages = {
                "com.cplerings.core.api",
                "com.cplerings.core.application",
                "com.cplerings.core.infrastructure",
        },
        exclude = {
                UserDetailsServiceAutoConfiguration.class
        }
)
@EntityScan(basePackages = "com.cplerings.core.domain")
public class CplringsCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CplringsCoreApplication.class, args);
    }
}
