package com.cplerings.core.api.dev;

import com.cplerings.core.api.shared.AbstractRestController;
import com.cplerings.core.api.shared.openapi.DevTag;
import com.cplerings.core.application.shared.entity.account.ARole;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.jwt.input.JWTGenerationInput;
import com.cplerings.core.application.shared.service.payment.PaymentRequestService;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.common.profile.LocalDevelopmentProfileConstant;
import com.cplerings.core.common.profile.ProfileConstant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@Profile({ ProfileConstant.DEVELOPMENT, ProfileConstant.LOCAL })
@DevTag
public class DevController extends AbstractRestController {

    private final JWTGenerationService jwtGenerationService;

    private PaymentRequestService paymentRequestService;

    @GetMapping(APIConstant.DEV_HELLO_PATH)
    public ResponseEntity<String> sayHello() {
        log.info("Request to say hello initiated");
        return ResponseEntity.ok(LocalDevelopmentProfileConstant.HELLO_MESSAGE);
    }

    @GetMapping(APIConstant.DEV_TOKEN_PATH)
    public ResponseEntity<String> getToken(@RequestParam("email") String email) {
        return ResponseEntity.ok(jwtGenerationService.generateToken(JWTGenerationInput.builder()
                .email(email)
                .role(ARole.CUSTOMER)
                .accountId(1L)
                .build()));
    }
}
