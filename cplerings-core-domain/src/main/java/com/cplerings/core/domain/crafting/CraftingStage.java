package com.cplerings.core.domain.crafting;

import java.time.Instant;

import com.cplerings.core.common.database.DatabaseConstant;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.shared.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "tbl_crafting_stage")
public class CraftingStage extends AbstractEntity {

    private static final String CRAFTING_STAGE_SEQUENCE = "crafting_stage_sequence";

    @Id
    @GeneratedValue(generator = CRAFTING_STAGE_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = CRAFTING_STAGE_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "crafting_stage_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "custom_order_id")
    private CustomOrder customOrder;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "progress", nullable = false)
    private int progress;

    @Column(name = "completion_date")
    private Instant completionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "crafting_stage_status")
    private CraftingStageStatus craftingStageStatus;
}
