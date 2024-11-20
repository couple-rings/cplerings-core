package com.cplerings.core.application.diamond.datasource;

import com.cplerings.core.application.diamond.datasource.result.Diamonds;
import com.cplerings.core.application.diamond.input.ViewDiamondsInput;

public interface ViewDiamondsDataSource {

    Diamonds getDiamonds(ViewDiamondsInput  input);
}
