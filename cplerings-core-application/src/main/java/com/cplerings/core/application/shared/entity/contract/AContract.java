package com.cplerings.core.application.shared.entity.contract;

import com.cplerings.core.application.shared.entity.file.ADocument;
import com.cplerings.core.application.shared.entity.file.AImage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AContract implements Serializable {

    private Long id;
    private AImage signature;
    private Instant signedDate;
    private ADocument document;
    private Instant createdAt;
}
