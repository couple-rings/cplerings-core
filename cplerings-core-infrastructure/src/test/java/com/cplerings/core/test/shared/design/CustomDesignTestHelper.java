package com.cplerings.core.test.shared.design;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.context.TestComponent;

import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.shared.valueobject.Weight;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.DesignRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.spouse.SpouseTestHelper;

import lombok.RequiredArgsConstructor;

@TestComponent
@RequiredArgsConstructor
public class CustomDesignTestHelper {

    private final TestDataSource testDataSource;
    private final DocumentRepository documentRepository;
    private final AccountRepository accountRepository;
    private final SpouseTestHelper spouseTestHelper;
    private final DesignVersionTestHelper designVersionTestHelper;

    public CustomDesign createCustomDesign() {
        DesignVersion designVersion = designVersionTestHelper.createDesignVersion();
        final Spouse[] spouses = spouseTestHelper.createSpouseFromCustomerEmail(AccountTestConstant.CUSTOMER_EMAIL);
        CustomDesign customDesign = CustomDesign.builder()
                .blueprint(documentRepository.getReferenceById(1L))
                .metalWeight(Weight.create(BigDecimal.valueOf(12L)))
                .designVersion(designVersion)
                .sideDiamondsCount(12)
                .account(accountRepository.getReferenceById(1L))
                .spouse(spouses[0])
                .build();
        return testDataSource.save(customDesign);
    }

    public CustomDesignSpouse createCustomDesignsAndSpouses() {
        List<CustomDesign> customDesigns = new ArrayList<>();
        DesignVersion designVersion1 = designVersionTestHelper.createDesignVersion();
        DesignVersion designVersion2 = designVersionTestHelper.createDesignVersion2();
        final Spouse[] spouses = spouseTestHelper.createSpouseFromCustomerEmail(AccountTestConstant.CUSTOMER_EMAIL);
        CustomDesign customDesign1 = CustomDesign.builder()
                .blueprint(documentRepository.getReferenceById(1L))
                .metalWeight(Weight.create(BigDecimal.valueOf(12L)))
                .designVersion(designVersion1)
                .sideDiamondsCount(12)
                .account(accountRepository.getReferenceById(1L))
                .spouse(spouses[0])
                .build();
        CustomDesign customDesign2 = CustomDesign.builder()
                .blueprint(documentRepository.getReferenceById(11L))
                .metalWeight(Weight.create(BigDecimal.valueOf(12L)))
                .designVersion(designVersion2)
                .sideDiamondsCount(12)
                .account(accountRepository.getReferenceById(1L))
                .spouse(spouses[1])
                .build();
        CustomDesign customDesignCreated1 = testDataSource.save(customDesign1);
        CustomDesign customDesignCreated2 = testDataSource.save(customDesign2);
        customDesigns.add(customDesignCreated1);
        customDesigns.add(customDesignCreated2);
        return CustomDesignSpouse.builder()
                .customDesign(customDesigns)
                .spouses(spouses)
                .build();
    }
}
