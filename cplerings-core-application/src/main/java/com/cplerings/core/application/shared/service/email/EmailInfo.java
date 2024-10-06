package com.cplerings.core.application.shared.service.email;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailInfo {

    private String recipient;
    private String body;
    private String subject;
}
