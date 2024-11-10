package com.cplerings.core.application.shared.entity.crafting;

import com.cplerings.core.application.shared.entity.file.AImage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

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
}
