package com.cplerings.core.application.shared.entity.agreement;

import java.io.Serializable;
import java.time.Instant;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.file.AImage;

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
public class AAgreement implements Serializable {

    private Long id;
    private AAccount customer;
    private String mainName;
    private AImage mainSignature;
    private String partnerName;
    private AImage partnerSignature;
    private Instant signedDate;
}
