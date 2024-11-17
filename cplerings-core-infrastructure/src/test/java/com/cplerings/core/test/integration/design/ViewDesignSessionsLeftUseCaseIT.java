package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.design.data.DesignSessionLeftData;
import com.cplerings.core.api.design.request.ViewDesignSessionsLeftRequest;
import com.cplerings.core.api.design.response.ViewDesignSessionsLeftResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.design.session.DesignSessionStatus;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

class ViewDesignSessionsLeftUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenStaff_whenViewNumberOfSessionsLeftOfCustomer() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        DesignSession designSession = DesignSession.builder()
                .customer(accountRepository.getReferenceById(1L))
                .status(DesignSessionStatus.UNUSED)
                .sessionId(UUID.randomUUID())
                .build();
        testDataSource.save(designSession);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_DESIGN_SESSIONS_LEFT_PATH.replace("{customerId}", Long.toString(1L)))
                .authorizationHeader(token)
                .method(RequestBuilder.Method.GET)
                .send();

        thenResponseIsOk(response);
        thenReturnNumOfSessionsLeft(response);
    }

    private void thenReturnNumOfSessionsLeft(final WebTestClient.ResponseSpec response) {
        final ViewDesignSessionsLeftResponse responseBody = response.expectBody(ViewDesignSessionsLeftResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final DesignSessionLeftData designSessionLeftData = responseBody.getData();
        assertThat(designSessionLeftData).isNotNull();
        assertThat(designSessionLeftData.numOfSessions()).isEqualTo(1);
    }
}
