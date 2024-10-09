package com.cplerings.core.test.shared.helper;

import com.cplerings.core.domain.AbstractEntity;
import com.cplerings.core.domain.Auditor;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

import java.util.Objects;

@TestComponent
@RequiredArgsConstructor
public class AccountHelper {

    private static final Auditor TEST_AUDITOR = (() -> "CoupleRings-test");

    public void updateAuditor(AbstractEntity entity) {
        Objects.requireNonNull(entity);
        if (entity.getId() == null || entity.getId() <= 0) {
            entity.setCreator(TEST_AUDITOR);
        }
        entity.updateModifier(TEST_AUDITOR);
    }
}
