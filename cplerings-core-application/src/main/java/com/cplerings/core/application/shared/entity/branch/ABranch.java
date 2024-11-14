package com.cplerings.core.application.shared.entity.branch;

import java.io.Serializable;

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
public class ABranch implements Serializable {

    private Long id;
    private String address;
    private String storeName;
    private String phone;
    private AImage coverImage;
}
