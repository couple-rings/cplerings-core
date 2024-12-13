package com.cplerings.core.test.integration.diamond;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.diamond.response.DisableDiamondResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.design.ADiamond;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.infrastructure.repository.DiamondRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.diamond.DiamondTestHelper;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

class DisableDiamondUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private DiamondTestHelper diamondTestHelper;

    @Autowired
    private DiamondRepository diamondRepository;

    private Diamond diamond;

    @Test
    void givenManager_whenDisableDiamond() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.MANAGER_EMAIL);
        this.diamond = diamondTestHelper.createDiamond();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.SINGLE_DIAMOND_PATH, 1L)
                .method(RequestBuilder.Method.DELETE)
                .authorizationHeader(token)
                .send();

        thenResponseIsOk(response);
        thenDiamondIsCreated(response);
    }

    private void thenDiamondIsCreated(WebTestClient.ResponseSpec response) {
        final DisableDiamondResponse responseBody = response.expectBody(DisableDiamondResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final ADiamond diamond = responseBody.getData();
        assertThat(diamond).isNotNull();
        assertThat(diamond.getId()).isNotNull();
        Diamond diamond1 = diamondRepository.findById(this.diamond.getId())
                .orElse(null);
        assertThat(diamond1.getState()).isEqualByComparingTo(State.INACTIVE);
    }
}
