package com.cplerings.core.application.shared.entity.crafting;

import java.time.Instant;
import java.util.Collection;

import com.cplerings.core.application.shared.entity.file.AImage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ACraftingStage {

    private Long id;
    private String name;
    private Long customOrderId;
    private Integer progress;
    private Instant completionDate;
    private AImage image;
    private ACraftingStageStatus status;
    private Collection<ACraftingStageHistory> craftingStageHistories;
    private Instant createdAt;

}
