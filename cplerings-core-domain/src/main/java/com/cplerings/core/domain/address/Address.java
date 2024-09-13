package com.cplerings.core.domain.address;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_address", schema = DatabaseConstant.SCHEME_CORE)
public class Address extends AbstractEntity {

    private static final String ADDRESS_SEQUENCE = "address_seq";

    @Id
    @GeneratedValue(generator = ADDRESS_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = ADDRESS_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    @Column(name = "district")
    private String district;

    @Column(name = "province")
    private String province;
}
