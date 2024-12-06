package com.cplerings.core.application.shared.entity.order;

import java.io.Serializable;
import java.util.Collection;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.file.AImage;
import com.cplerings.core.application.shared.entity.transport.ATransportationNote;
import com.cplerings.core.application.shared.entity.transport.ATransportationOrderStatus;

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
public class ATransportOrderWithoutStandard implements Serializable {

    private Long id;
    private ATransportationOrderStatus status;
    private String receiverName;
    private String receiverPhone;
    private String deliveryAddress;
    private AAccount transporter;
    private String orderNo;
    private Collection<ATransportationNote> transportationNotes;
    private AImage image;
    private Collection<ATransportOrderHistory> transportOrderHistories;
}
