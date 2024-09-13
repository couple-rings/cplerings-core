package com.cplerings.core.domain.design;

import java.util.Set;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.DomainConstant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tbl_finger_size", schema = DatabaseConstant.SCHEME_CORE)
public class FingerSize extends AbstractEntity {

    private static final String FINGER_SIZE_SEQUENCE = "finger_size_seq";

    @Id
    @GeneratedValue(generator = FINGER_SIZE_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = FINGER_SIZE_SEQUENCE, allocationSize = DomainConstant.DEFAULT_ALLOCATION_SIZE)
    @Column(name = "fincer_size_id")
    private Long id;

    @OneToMany(mappedBy = "fingerSize", fetch = FetchType.LAZY)
    private Set<DesignFingerSize> designFingerSizes;
}
