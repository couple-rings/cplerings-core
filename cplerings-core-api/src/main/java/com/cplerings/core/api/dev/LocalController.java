package com.cplerings.core.api.dev;

import com.cplerings.core.api.shared.AbstractRestController;
import com.cplerings.core.api.security.IsAnyone;
import com.cplerings.core.api.security.IsCustomer;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.common.profile.LocalDevelopmentProfileConstant;
import com.cplerings.core.common.profile.ProfileConstant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile(ProfileConstant.LOCAL)
@RequiredArgsConstructor
@Slf4j
public class LocalController extends AbstractRestController {

    private final JWTGenerationService jwtGenerationService;

    @IsCustomer
    @GetMapping("/local/hello")
    public ResponseEntity<String> sayHello() {
        log.info("Request to say hello initiated");
        return ResponseEntity.ok(LocalDevelopmentProfileConstant.HELLO_MESSAGE);
    }

    @IsAnyone
    @GetMapping("/local/token")
    public ResponseEntity<String> getToken(@RequestParam("email") String email) {
        return ResponseEntity.ok(jwtGenerationService.generateToken(email));
    }
}
