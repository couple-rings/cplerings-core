package com.cplerings.core.api.file.request;

import lombok.Builder;

@Builder
public record FileUploadRequest(String fileBase64) {

}
