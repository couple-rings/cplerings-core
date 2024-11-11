package com.cplerings.core.application.file.output;

import com.cplerings.core.application.shared.service.file.FileType;

import lombok.Builder;

@Builder
public record UploadFileOutput(Long id, String url, FileType type) {

}
