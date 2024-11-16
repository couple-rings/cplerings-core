package com.cplerings.core.application.shared.entity.account;

import java.io.Serializable;
import java.util.List;

import com.cplerings.core.application.shared.entity.crafting.ACraftingRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AAccountCraftingRequest implements Serializable {

    private AAccount customer;
    private List<ACraftingRequest> craftingRequests;
}
