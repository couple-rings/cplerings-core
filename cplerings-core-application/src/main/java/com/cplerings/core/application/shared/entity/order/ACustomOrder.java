package com.cplerings.core.application.shared.entity.order;

import java.io.Serializable;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.contract.AContract;
import com.cplerings.core.application.shared.ring.ARing;
import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.ring.Ring;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
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
public class ACustomOrder implements Serializable {

    private Long id;
    private ARing firstRing;
    private ARing secondRing;
    private AAccount customer;
    private AAccount jeweler;
    private AContract contract;
}
