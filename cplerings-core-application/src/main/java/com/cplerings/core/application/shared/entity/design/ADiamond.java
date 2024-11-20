package com.cplerings.core.application.shared.entity.design;

import com.cplerings.core.application.shared.entity.branch.ABranch;
import com.cplerings.core.application.shared.entity.file.ADocument;

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
public class ADiamond implements Serializable {

    private Long id;
    private ADocument giaDocument;
    private String giaReportNumber;
    private ADiamondSpecification diamondSpecification;
    private ABranch branch;
    private Instant createdAt;
}