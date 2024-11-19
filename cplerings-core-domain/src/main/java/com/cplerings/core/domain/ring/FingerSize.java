package com.cplerings.core.domain.ring;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.shared.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tbl_finger_size")
public class FingerSize extends AbstractEntity {

    private static final String FINGER_SIZE_SEQUENCE = "finger_size_seq";

    @Id
    @GeneratedValue(generator = FINGER_SIZE_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = FINGER_SIZE_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "finger_size_id")
    private Long id;

    @Column(name = "size", nullable = false)
    private Integer size;

    @Column(name = "diameter", nullable = false)
    private Double diameter;
}
