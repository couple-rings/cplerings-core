package com.cplerings.core.test.shared;

import com.cplerings.core.common.profile.ProfileConstant;
import com.cplerings.core.infrastructure.CplringsCoreApplication;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(
        classes = {
                CplringsCoreApplication.class
        }
)
@Import({
        CTConfiguration.class,
        AWSTestConfiguration.class
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles(ProfileConstant.TEST)
public abstract class AbstractCT {

    @Autowired
    private Flyway flyway;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    protected final void populateDatabase() {
        flyway.migrate();
    }

    protected final void thenResponseIsOk(WebTestClient.ResponseSpec response) {
        response.expectStatus().is2xxSuccessful();
    }

    protected final void thenResponseIsRedirection(WebTestClient.ResponseSpec response) {
        response.expectStatus().is3xxRedirection();
    }

    protected final TestDataLoader getTestDataLoader(String folder) {
        return TestDataLoader.builder()
                .folder(folder)
                .objectMapper(objectMapper)
                .build();
    }
}
