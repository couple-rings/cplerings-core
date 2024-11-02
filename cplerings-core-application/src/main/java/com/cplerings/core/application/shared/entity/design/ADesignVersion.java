package com.cplerings.core.application.shared.entity.design;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ADesignVersion implements Serializable {

    private Long id;
    private ADesign design;
    private AImage image;
    private ADocument designFile;
    private int versionNumber;
    private boolean isAccepted;
    private boolean isOld;
}
