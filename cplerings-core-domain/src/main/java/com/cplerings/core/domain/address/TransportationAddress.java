package com.cplerings.core.domain.address;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.shared.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_transportation_address")
public class TransportationAddress extends AbstractEntity {

    private static final String TRANSPORTATION_ADDRESS_SEQUENCE = "transportation_seq";

    @Id
    @GeneratedValue(generator = TRANSPORTATION_ADDRESS_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = TRANSPORTATION_ADDRESS_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "transportation_address_id")
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "district_code", nullable = false)
    private String districtCode;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "ward_code", nullable = false)
    private String wardCode;

    @Column(name = "ward", nullable = false)
    private String ward;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(name = "receiver_phone", nullable = false)
    private String receiverPhone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Account customer;

    @Transient
    public String getAddressAsString() {
        return address
                + ", "
                + ward
                + ", "
                + district;
    }
}
