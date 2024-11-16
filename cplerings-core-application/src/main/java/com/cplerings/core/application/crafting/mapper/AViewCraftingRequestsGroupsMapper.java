package com.cplerings.core.application.crafting.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.crafting.datasource.result.CraftingRequestGroupsList;
import com.cplerings.core.application.crafting.output.ViewCraftingRequestsGroupsOutput;
import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.account.AAccountCraftingRequest;
import com.cplerings.core.application.shared.entity.crafting.ACraftingRequest;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.crafting.CraftingRequest;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                MoneyMapper.class
        }
)
public interface AViewCraftingRequestsGroupsMapper {

    @Mapping(target = "items", source = "customerCraftingRequests")
    ViewCraftingRequestsGroupsOutput toOutput(CraftingRequestGroupsList craftingRequestGroupsList);

    default List<AAccountCraftingRequest> mapToAAccountCraftingRequestList(List<Account> accounts) {
        if (accounts == null || accounts.isEmpty()) {
            return null;
        }

        return accounts.stream()
                .map(this::mapToAAccountCraftingRequest)
                .toList();
    }

    default AAccountCraftingRequest mapToAAccountCraftingRequest(Account account) {
        if (account == null) {
            return null;
        }

        AAccount customer = toAAccount(account);

        List<ACraftingRequest> associatedCraftingRequests = account.getCraftingRequests() != null
                ? account.getCraftingRequests().stream()
                .map(this::craftingRequestToACraftingRequest)
                .toList()
                : List.of();

        return AAccountCraftingRequest.builder()
                .customer(customer)
                .craftingRequests(associatedCraftingRequests) // Set the crafting requests here
                .build();
    }

    AAccount toAAccount(Account account);

    ACraftingRequest craftingRequestToACraftingRequest(CraftingRequest craftingRequest);
}
