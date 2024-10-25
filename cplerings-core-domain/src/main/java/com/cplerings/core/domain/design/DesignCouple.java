package com.cplerings.core.domain.design;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.shared.AbstractEntity;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_design_couple")
public class DesignCouple extends AbstractEntity {

    private static final String DESIGN_COUPLE_SEQUENCE = "design_couple_seq";

    @Id
    @GeneratedValue(generator = DESIGN_COUPLE_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DESIGN_COUPLE_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "design_couple_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "preview_image_id")
    private Image previewImage;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = DatabaseConstant.DEFAULT_DESCRIPTION_LENGTH, nullable = false)
    private String description;

    @OneToMany(mappedBy = "designCouple", fetch = FetchType.LAZY)
    private Set<Design> designs;
}
