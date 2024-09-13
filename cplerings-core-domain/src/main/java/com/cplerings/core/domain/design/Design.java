package com.cplerings.core.domain.design;

import java.util.Set;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;
import com.cplerings.core.domain.collection.Collection;
import com.cplerings.core.domain.custom.Contract;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "tbl_design", schema = DatabaseConstant.SCHEME_CORE)
public class Design extends AbstractEntity {

    private static final String DESIGN_SEQUENCE = "design_seq";

    @Id
    @GeneratedValue(generator = DESIGN_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DESIGN_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "design_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @OneToMany(mappedBy = "design", fetch = FetchType.LAZY)
    private Set<DesignFingerSize> designFingerSizes;

    @OneToMany(mappedBy = "design", fetch = FetchType.LAZY)
    private Set<Contract> contracts;
}
