package com.cplerings.core.domain.design;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.shared.AbstractEntity;

import jakarta.persistence.UniqueConstraint;
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
@Table(
        name = "tbl_design_version",
        uniqueConstraints = @UniqueConstraint(
                name = "uc_tbl_design_version_design_id_version_number",
                columnNames = {"design_id", "version_number"}
        )
)
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Account customer;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "design_file_id", nullable = false)
    private Document designFile;

    @Column(name = "version_number", nullable = false)
    private Integer versionNumber;

    @Column(name = "is_accepted", nullable = false)
    private Boolean isAccepted;

    @Column(name = "is_old", nullable = false)
    private Boolean isOld;
}
