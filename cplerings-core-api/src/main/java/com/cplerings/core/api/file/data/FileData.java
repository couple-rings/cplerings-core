package com.cplerings.core.api.file.data;

import com.cplerings.core.application.shared.service.file.FileType;

import lombok.Builder;

@Builder
public record FileData(Long id, String url, FileType type) {

}
