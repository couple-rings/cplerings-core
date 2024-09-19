package com.cplerings.core.domain.custom;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;

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

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_custom_request_tracking", schema = DatabaseConstant.SCHEME_CORE)
public class CustomRequestTracing extends AbstractEntity {

    private static final String CUSTOM_REQUEST_TRACING_SEQUENCE = "custom_request_tracing_seq";

    @Id
    @GeneratedValue(generator = CUSTOM_REQUEST_TRACING_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = CUSTOM_REQUEST_TRACING_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "custom_request_tracing_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10, nullable = false)
    private CustomRequestStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "custom_request_id")
    private CustomRequest customRequest;
}
