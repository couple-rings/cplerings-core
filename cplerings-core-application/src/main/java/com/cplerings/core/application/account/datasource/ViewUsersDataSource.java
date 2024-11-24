package com.cplerings.core.application.account.datasource;

import com.cplerings.core.application.account.datasource.result.Users;

import java.util.Collection;

public interface ViewUsersDataSource {

    Users getUsers(Collection<Long> userIds);
}
