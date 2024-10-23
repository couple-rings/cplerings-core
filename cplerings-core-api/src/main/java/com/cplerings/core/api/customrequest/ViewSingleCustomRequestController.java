package com.cplerings.core.api.customrequest;

import com.cplerings.core.api.customrequest.data.CustomRequest;
import com.cplerings.core.api.customrequest.request.ViewCustomRequestRequest;
import com.cplerings.core.api.customrequest.response.ViewCustomRequestResponse;
import com.cplerings.core.api.shared.AbstractController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.customrequest.input.ViewCustomRequestInput;
import com.cplerings.core.application.customrequest.output.ViewCustomRequestOutput;
import com.cplerings.core.application.shared.usecase.UseCase;

public class ViewSingleCustomRequestController extends AbstractController<ViewCustomRequestInput, ViewCustomRequestOutput, CustomRequest, ViewCustomRequestRequest, ViewCustomRequestResponse> {


    @Override
    protected UseCase<ViewCustomRequestInput, ViewCustomRequestOutput> getUseCase() {
        return null;
    }

    @Override
    protected APIMapper<ViewCustomRequestInput, ViewCustomRequestOutput, CustomRequest, ViewCustomRequestRequest, ViewCustomRequestResponse> getMapper() {
        return null;
    }
}
