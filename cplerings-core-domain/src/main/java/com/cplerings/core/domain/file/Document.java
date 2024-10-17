package com.cplerings.core.domain.file;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.shared.AbstractEntity;

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
@Table(name = "tbl_document")
public class Document extends AbstractEntity {

    private static final String DOCUMENT_SEQUENCE = "document_seq";

    @Id
    @GeneratedValue(generator = DOCUMENT_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DOCUMENT_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "document_id")
    private Long id;

    @Column(name = "url", nullable = false, unique = true)
    private String url;
}
