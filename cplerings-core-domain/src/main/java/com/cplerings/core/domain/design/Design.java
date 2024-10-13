package com.cplerings.core.domain.design;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.shared.AbstractEntity;
import com.cplerings.core.domain.shared.valueobject.DesignSize;
import com.cplerings.core.domain.shared.valueobject.MetalWeight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@Table(name = "tbl_design")
public class Design extends AbstractEntity {

    private static final String DESIGN_SEQUENCE = "design_seq";

    @Id
    @GeneratedValue(generator = DESIGN_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DESIGN_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "design_id")
    private Long id;

    @Embedded
    private MetalWeight metalWeight;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "blueprint", nullable = false)
    private String blueprint;

    @Column(name = "characteristic", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private String characteristic;

    @Embedded
    private DesignSize size;

    @Column(name = "size_diamonds_count", nullable = false)
    private Integer sideDiamondsCount;
}
