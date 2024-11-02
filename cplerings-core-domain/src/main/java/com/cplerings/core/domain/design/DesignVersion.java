package com.cplerings.core.domain.design;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.file.Document;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_design_version")
public class DesignVersion extends AbstractEntity {

    private static final String DESIGN_VERSION_SEQUENCE = "design_version_seq";

    @Id
    @GeneratedValue(generator = DESIGN_VERSION_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = DESIGN_VERSION_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "design_version_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "design_id")
    private Design design;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "design_file_id", nullable = false)
    private Document designFile;

    @Column(name = "version_number", nullable = false)
    private int versionNumber;

    @Column(name = "is_accepted", nullable = false)
    private boolean isAccepted;

    @Column(name = "is_old", nullable = false)
    private boolean isOld;
}
