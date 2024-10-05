package com.cplerings.core.test.shared;

import com.cplerings.core.common.profile.ProfileConstant;
import com.cplerings.core.infrastructure.CplringsCoreApplication;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        classes = {
                CplringsCoreApplication.class
        }
)
@Import(CTConfiguration.class)
@ActiveProfiles(ProfileConstant.TEST)
public abstract class AbstractCT {

}
