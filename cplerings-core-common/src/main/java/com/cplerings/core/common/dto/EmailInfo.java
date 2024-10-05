package com.cplerings.core.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailInfo {

    private String recipient;
    private String body;
    private String subject;
}
