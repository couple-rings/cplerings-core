package com.cplerings.core.application.shared.entity.account;

import com.cplerings.core.application.shared.entity.branch.ABranch;

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
public class AAccount implements Serializable {

    private Long id;
    private String email;
    private String username;
    private String phone;
    private String avatar;
    private ARole role;
    private Instant createdAt;
    private ABranch branch;
}
