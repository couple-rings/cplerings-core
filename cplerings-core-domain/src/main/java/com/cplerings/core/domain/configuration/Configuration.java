package com.cplerings.core.domain.configuration;

import com.cplerings.core.common.database.DatabaseConstant;
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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_configuration")
public class Configuration extends AbstractEntity {

    private static final String CONFIGURATION_SEQUENCE = "configuration_seq";

    @Id
    @GeneratedValue(generator = CONFIGURATION_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = CONFIGURATION_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "configuration_id")
    private Long id;

    @Column(name = "key", length = DatabaseConstant.DEFAULT_CONFIGURATION_KEY_LENGTH, nullable = false, updatable = false)
    private String key;

    @Column(name = "value", nullable = false, updatable = false)
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private ConfigurationStatus status;
}
