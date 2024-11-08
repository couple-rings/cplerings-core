package com.cplerings.core.application.shared.entity.contract;

import java.io.Serializable;
import java.time.Instant;

import com.cplerings.core.application.shared.entity.design.ADocument;

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
public class AContract implements Serializable {

    private Long id;
    private String signature;
    private Instant signedDate;
    private ADocument document;
}
