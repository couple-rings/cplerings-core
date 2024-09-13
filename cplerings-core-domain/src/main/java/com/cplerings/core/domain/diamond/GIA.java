package com.cplerings.core.domain.diamond;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
@Table(name = "tbl_gia", schema = DatabaseConstant.SCHEME_CORE)
public class GIA extends AbstractEntity {

    private static final String GIA_SEQUENCE = "gia_seq";

    @Id
    @GeneratedValue(generator = GIA_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = GIA_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "gia_id")
    private Long id;

    @Column(name = "gia_document_url", nullable = false, unique = true)
    private String giaDocumentURL;

    @OneToOne(mappedBy = "gia")
    private Diamond diamond;
}
