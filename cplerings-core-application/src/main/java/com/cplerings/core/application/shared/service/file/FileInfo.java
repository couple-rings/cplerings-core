package com.cplerings.core.application.shared.service.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class FileInfo {

    private Long id;
    private String url;
    private FileType type;
}
