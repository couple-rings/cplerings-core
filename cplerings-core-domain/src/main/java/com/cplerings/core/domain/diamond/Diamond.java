package com.cplerings.core.domain.diamond;

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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_diamond", schema = DatabaseConstant.SCHEME_CORE)
public class Diamond extends AbstractEntity {

    private static final String DIAMOND_SEQUENCE = "diamond_seq";

    @Id
    @GeneratedValue(generator = DIAMOND_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DIAMOND_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "diamond_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gia_id")
    private GIA gia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private DiamondCategory category;
}
