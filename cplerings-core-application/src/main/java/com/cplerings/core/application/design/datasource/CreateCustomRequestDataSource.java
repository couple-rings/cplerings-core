package com.cplerings.core.application.design.datasource;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestHistory;
import com.cplerings.core.domain.design.request.DesignCustomRequest;

import java.util.Collection;
import java.util.Optional;

public interface CreateCustomRequestDataSource {

    Collection<Design> getAvailableDesignsByIds(Collection<Long> designIds);

    Collection<Design> saveDesigns(Collection<Design> designs);

    Optional<Account> getCustomerById(Long customerId);

    CustomRequest save(CustomRequest customRequest);

    Collection<DesignCustomRequest> saveDesignCustomRequests(Collection<DesignCustomRequest> designCustomRequests);

    Collection<DesignVersion> getAllDesignVersionsOfPreviousDesignSessions(Long id, Collection<Long> designIds);

    Collection<DesignVersion> saveAll(Collection<DesignVersion> oldDesignVersions);

    CustomRequestHistory saveCustomRequestHistory(CustomRequestHistory customRequestHistory);
}
