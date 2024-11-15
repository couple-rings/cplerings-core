package com.cplerings.core.application.design.datasource;

import java.util.Optional;

import com.cplerings.core.application.design.datasource.result.CustomDesigns;
import com.cplerings.core.application.design.input.ViewCustomDesignsInput;
import com.cplerings.core.domain.account.Account;

public interface ViewCustomDesignsDataSource {

    CustomDesigns getCustomDesigns(ViewCustomDesignsInput viewCustomDesignsInput);
    Optional<Account> findAccountByEmail(String email);
}
