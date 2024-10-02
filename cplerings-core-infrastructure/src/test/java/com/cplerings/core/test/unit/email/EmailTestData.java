package com.cplerings.core.test.unit.email;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter(AccessLevel.PACKAGE)
@Builder
public class EmailTestData {

    private String recipient;
    private String subject;
    private String body;
}
