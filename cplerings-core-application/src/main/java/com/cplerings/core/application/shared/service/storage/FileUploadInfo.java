package com.cplerings.core.application.shared.service.storage;

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

    public enum Type {

        STATIC, DYNAMIC
    }

    private String fileBase64;
    private Type type;
}
