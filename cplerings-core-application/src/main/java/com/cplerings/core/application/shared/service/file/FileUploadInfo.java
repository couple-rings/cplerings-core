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
public class FileUploadInfo {

    private String fileBase64;
    private Type type;
    public enum Type {

        STATIC, DYNAMIC
    }
}
