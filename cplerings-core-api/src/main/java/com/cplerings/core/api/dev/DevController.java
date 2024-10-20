package com.cplerings.core.api.dev;

import com.cplerings.core.api.shared.AbstractRestController;
import com.cplerings.core.application.shared.entity.account.ARole;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.jwt.input.JWTGenerationInput;
import com.cplerings.core.application.shared.service.payment.PaymentInfo;
import com.cplerings.core.application.shared.service.payment.PaymentRequest;
import com.cplerings.core.application.shared.service.payment.PaymentRequestService;
import com.cplerings.core.common.payment.PaymentConstant;
import com.cplerings.core.common.profile.LocalDevelopmentProfileConstant;
import com.cplerings.core.common.profile.ProfileConstant;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@RestController
@Profile({ ProfileConstant.DEVELOPMENT, ProfileConstant.LOCAL })
@Tag(name = "DEVELOPMENT_ONLY", description = "APIs for development only")
public class DevController extends AbstractRestController {

    private static final String BASE_PATH = "/dev";
    private static final String HELLO_PATH = BASE_PATH + "/hello";
    private static final String TOKEN_PATH = BASE_PATH + "/token";
    private static final String PAYMENT_PATH = BASE_PATH + "/payments";
    private static final String VNPAY_PATH = PAYMENT_PATH + "/vnpay";

    private final JWTGenerationService jwtGenerationService;

    private PaymentRequestService paymentRequestService;

    @GetMapping(HELLO_PATH)
    public ResponseEntity<String> sayHello() {
        log.info("Request to say hello initiated");
        return ResponseEntity.ok(LocalDevelopmentProfileConstant.HELLO_MESSAGE);
    }

    @GetMapping(TOKEN_PATH)
    public ResponseEntity<String> getToken(@RequestParam("email") String email) {
        return ResponseEntity.ok(jwtGenerationService.generateToken(JWTGenerationInput.builder()
                .email(email)
                .role(ARole.CUSTOMER)
                .accountId(1L)
                .build()));
    }

    @GetMapping(VNPAY_PATH)
    public ResponseEntity<String> getVNPayPaymentLink() {
        final PaymentInfo paymentInfo = new PaymentInfo() {

            @Override
            public Money getAmount() {
                return Money.create(BigDecimal.valueOf(123123));
            }

            @Override
            public String getDescription() {
                return "Amount for 123123";
            }
        };
        final PaymentRequest paymentRequest = paymentRequestService.requestPayment(paymentInfo);
        return ResponseEntity.ok(paymentRequest.getPaymentLink());
    }

    @Autowired
    @Qualifier(PaymentConstant.VNPAY_PAYMENT_SERVICE_NAME)
    public void setPaymentRequestService(PaymentRequestService paymentRequestService) {
        this.paymentRequestService = paymentRequestService;
    }
}
