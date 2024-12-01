package com.cplerings.core.application.shared.entity.account;

import java.io.Serializable;
import java.time.Instant;

import com.cplerings.core.application.shared.entity.branch.ABranch;
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
public class ADesignStaff implements Serializable {

    private Long id;
    private String email;
    private String username;
    private String phone;
    private AImage avatar;
    private ARole role;
    private Instant createdAt;
    private ABranch branch;
    private Long numberOfHandledCustomRequest;
}
