package com.cplerings.core.api.dev;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.shared.AbstractRestController;
import com.cplerings.core.application.shared.entity.account.ARole;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.jwt.input.JWTGenerationInput;
import com.cplerings.core.common.profile.LocalDevelopmentProfileConstant;
import com.cplerings.core.common.profile.ProfileConstant;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@Profile({ProfileConstant.DEVELOPMENT, ProfileConstant.LOCAL})
@Tag(name = "DEVELOPMENT_ONLY", description = "APIs for development only")
public class DevController extends AbstractRestController {

    private static final String BASE_PATH = "/dev";
    private static final String HELLO_PATH = BASE_PATH + "/hello";
    private static final String TOKEN_PATH = BASE_PATH + "/token";

    private final JWTGenerationService jwtGenerationService;

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
}
