package com.cplerings.core.application.jewelry.datasource;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.resell.ResellOrder;

import java.util.Optional;

public interface ResellJewelryDataSource {

    Optional<Jewelry> findJewelryById(Long jewelryId);

    Optional<Account> findCustomerById(Long customerId);

    Optional<Image> findProofImageById(Long proofImageId);

    Jewelry save(Jewelry jewelry);

    Account getStaffReference(Long staffId);

    ResellOrder save(ResellOrder resellOrder);
}
