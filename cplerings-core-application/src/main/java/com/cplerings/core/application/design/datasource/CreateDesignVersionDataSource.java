package com.cplerings.core.application.design.datasource;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;

import java.util.Optional;

public interface CreateDesignVersionDataSource {

    DesignVersion save(DesignVersion designVersion);

    Document saveDocument(Document document);

    Image saveImage(Image image);

    Optional<Design> getDesignByID(long designID);

    Optional<Account> getCustomerById(long customerId);
}
