package com.cplerings.core.application.shared.entity.address;

import java.io.Serializable;

import com.cplerings.core.application.shared.entity.account.AAccount;

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
public class ATransportationAddress implements Serializable {

    private Long id;
    private String address;
    private Long districtCode;
    private String district;
    private Long wardCode;
    private String ward;
    private String receiverName;
    private String receiverPhone;
    private AAccount customer;
}
