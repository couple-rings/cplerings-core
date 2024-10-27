package com.cplerings.core.domain.design.session;

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
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_design_session")
public class DesignSession extends AbstractEntity {

    private static final String DESIGN_SESSION_SEQUENCE = "design_session_seq";

    @Id
    @GeneratedValue(generator = DESIGN_SESSION_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DESIGN_SESSION_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "design_session_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Account customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private DesignSessionStatus status;

    @Column(name = "session_id", nullable = false)
    private UUID sessionId;
}
