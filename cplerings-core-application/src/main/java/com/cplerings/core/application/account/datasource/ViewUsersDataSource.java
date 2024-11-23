package com.cplerings.core.application.account.datasource;

import java.util.List;

import com.cplerings.core.application.account.datasource.result.Users;

public interface ViewUsersDataSource {

    Users getUsers(List<Long> userIds);
}
