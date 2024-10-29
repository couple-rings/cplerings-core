package com.cplerings.core.application.file.input;

import lombok.Builder;

@Builder
public record UploadFileInput(String fileBase64) {

}
