package com.cplerings.core.domain.image;

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
@Table(name = "tbl_image")
public class Image extends AbstractEntity {

    private static final String IMAGE_SEQUENCE = "image_seq";

    @Id
    @GeneratedValue(generator = IMAGE_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = IMAGE_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "URL", nullable = false, unique = true)
    private String url;
}
