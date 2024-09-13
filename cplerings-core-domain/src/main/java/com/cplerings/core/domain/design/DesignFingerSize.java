package com.cplerings.core.domain.design;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tbl_design_finger_size", schema = DatabaseConstant.SCHEME_CORE)
public class DesignFingerSize extends AbstractEntity {

    private static final String DESIGN_FINGER_SIZE_SEQUENCE = "design_finder_size_seq";

    @Id
    @GeneratedValue(generator = DESIGN_FINGER_SIZE_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DESIGN_FINGER_SIZE_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "design_finger_size_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "design_id")
    private Design design;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "finger_size_id")
    private FingerSize fingerSize;
}
