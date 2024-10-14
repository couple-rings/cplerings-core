package com.cplerings.core.domain.design;

import com.cplerings.core.common.database.DatabaseConstant;

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
@Table(name = "design_couple")
public class DesignCouple {

    private static final String DESIGN_COUPLE_SEQUENCE = "design_couple_seq";

    @Id
    @GeneratedValue(generator = DESIGN_COUPLE_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DESIGN_COUPLE_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "design_couple_id")
    private Long id;

    @Column(name = "preview_image", nullable = false)
    private String previewImage;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = DatabaseConstant.DEFAULT_DESCRIPTION_LENGTH, nullable = false)
    private String description;
}
