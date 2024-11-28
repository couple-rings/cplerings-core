package com.cplerings.core.domain.design.crafting;

import com.cplerings.core.common.database.DatabaseConstant;
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
@Table(name = "tbl_crafting_request_history")
public class CraftingRequestHistory extends AbstractEntity {

    private static final String CRAFTING_REQUEST_HISTORY_SEQUENCE = "crafting_request_history_sequence";

    @Id
    @GeneratedValue(generator = CRAFTING_REQUEST_HISTORY_SEQUENCE, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = CRAFTING_REQUEST_HISTORY_SEQUENCE, allocationSize = DatabaseConstant.SEQ_ALLOCATION_SIZE)
    @Column(name = "crafting_request_history_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = DatabaseConstant.DEFAULT_ENUM_LENGTH, nullable = false)
    private CraftingRequestStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "crafting_request_id")
    private CraftingRequest craftingRequest;
}
