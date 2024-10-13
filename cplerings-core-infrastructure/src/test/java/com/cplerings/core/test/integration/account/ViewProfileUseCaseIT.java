package com.cplerings.core.test.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.account.data.AccountProfile;
import com.cplerings.core.api.account.response.AccountProfileResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.State;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.helper.EmailHelper;

class ViewProfileUseCaseIT extends AbstractIT {

    @Autowired
    private AccountRepository accountRepository;

    private Long id;

    @BeforeEach
    public void startEmailService() {
        Account account = Account.builder()
                .email(EmailHelper.TEST_EMAIL)
                .status(AccountStatus.ACTIVE)
                .createdBy("test")
                .modifiedBy("test")
                .password("test")
                .phone("test")
                .state(State.ACTIVE)
                .username("test")
                .role(Role.CUSTOMER)
                .build();
        var accountCreated = accountRepository.save(account);
        id = accountCreated.getId();
    }

    @Test
    void givenAnyUser_whenViewProfile () {
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.PROFILE_PATH.replace("{id}", id.toString()))
                .method(RequestBuilder.Method.GET)
                .send();

        thenReturnProfileOfTheAccount(response);
    }

    private void thenReturnProfileOfTheAccount(WebTestClient.ResponseSpec response) {
        final AccountProfileResponse responseBody = response.expectBody(AccountProfileResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(AccountProfile.class);
        AccountProfile accountProfile = (AccountProfile) responseBody.getData();
        assertThat(accountProfile.email())
                .isNotNull()
                .isEqualTo(EmailHelper.TEST_EMAIL);
        assertThat(accountProfile.username())
                .isNotNull()
                .isEqualTo("test");
    }
}
