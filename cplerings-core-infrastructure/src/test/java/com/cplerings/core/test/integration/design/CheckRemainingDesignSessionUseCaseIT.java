package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.design.data.RemainingDesignSessionData;
import com.cplerings.core.api.design.response.CheckRemainingDesignSessionResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.design.session.DesignSessionStatus;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

class CheckRemainingDesignSessionUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUpDesignSessions() {
        final Account account = accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL)
                .orElse(null);
        assertThat(account).isNotNull();

        final UUID designSessionId = UUID.randomUUID();
        for (int i = 0; i < 3; i++) {
            final DesignSession designSession = DesignSession.builder()
                    .sessionId(designSessionId)
                    .customer(account)
                    .build();
            if (i % 2 == 0) {
                designSession.setStatus(DesignSessionStatus.UNUSED);
            } else {
                designSession.setStatus(DesignSessionStatus.USED);
            }
            testDataSource.save(designSession);
        }
    }

    @Test
    void givenCustomer_whenCheckRemainingDesignSessionsCount() {
        final String customerToken = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.DESIGN_SESSION_PATH)
                .authorizationHeader(customerToken)
                .method(RequestBuilder.Method.GET)
                .send();

        thenResponseIsOk(response);
        thenRemainingDesignSessionsCountIsCorrect(response);
    }

    private void thenRemainingDesignSessionsCountIsCorrect(WebTestClient.ResponseSpec response) {
        final CheckRemainingDesignSessionResponse responseBody = response.expectBody(CheckRemainingDesignSessionResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(RemainingDesignSessionData.class);
        assertThat(responseBody.getData().remainingCount()).isEqualTo(2);
    }
}
