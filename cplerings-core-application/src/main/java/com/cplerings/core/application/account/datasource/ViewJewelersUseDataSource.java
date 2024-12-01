package com.cplerings.core.application.account.datasource;

import com.cplerings.core.application.account.datasource.result.Jewelers;
import com.cplerings.core.application.account.input.ViewJewelersInput;
import com.cplerings.core.application.account.output.result.JewelersOutputResult;
import com.cplerings.core.application.shared.entity.account.AJeweler;

public interface ViewJewelersUseDataSource {

    Jewelers getJewelers(ViewJewelersInput input);

    Long calculateNoOfHandleCustomOrder(AJeweler jeweler);
}
