package com.cplerings.core.test.shared.hello;

import com.cplerings.core.api.shared.AbstractRestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController extends AbstractRestController {

    public static final String TEST_PATH = "/test";
    public static final String TEST_HELLO_PATH = TEST_PATH + "/hello";
    public static final String TEST_EXCEPTION_PATH = TEST_PATH + "/exception";

    public static final String DEFAULT_HELLO_MESSAGE = "Hello, World!";

    @GetMapping(TEST_HELLO_PATH)
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok(DEFAULT_HELLO_MESSAGE);
    }

    @GetMapping(TEST_EXCEPTION_PATH)
    public ResponseEntity<Object> throwException() {
        throw new RuntimeException("Hello Exception!");
    }
}
