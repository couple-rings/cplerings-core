package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewTotalTransactionOfBranchData;
import com.cplerings.core.api.dashboard.response.ViewTotalTransactionOfBranchResponse;
import com.cplerings.core.api.shared.mapper.APINoRequestMapper;
import com.cplerings.core.application.dashboard.output.ViewTotalTransactionsOfBranchOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTotalTransactionOfBranchMapper extends APINoRequestMapper<ViewTotalTransactionsOfBranchOutput, ViewTotalTransactionOfBranchData, ViewTotalTransactionOfBranchResponse> {
}
