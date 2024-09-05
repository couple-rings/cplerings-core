package com.cplerings.core.test.integration.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.AbstractRestController;
import com.cplerings.core.api.security.IsCustomer;

@RestController
public class TestController extends AbstractRestController {

    public static final String TEST_HELLO_PATH = "/test/hello";
    public static final String DEFAULT_HELLO_MESSAGE = "Hello, World!";

    @IsCustomer
    @GetMapping(TEST_HELLO_PATH)
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok(DEFAULT_HELLO_MESSAGE);
    }
}
