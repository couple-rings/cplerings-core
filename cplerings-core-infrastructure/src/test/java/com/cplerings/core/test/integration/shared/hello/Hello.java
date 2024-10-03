package com.cplerings.core.test.integration.shared.hello;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "tbl_hello")
public class Hello extends AbstractEntity {

    private static final String HELLO_SEQUENCE = "hello_seq";

    @Id
    @GeneratedValue(generator = HELLO_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = HELLO_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "hello_id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;
}
