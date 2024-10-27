package com.cplerings.core.api.file.response;

import com.cplerings.core.api.account.data.CustomerEmailInfo;
import com.cplerings.core.api.account.data.Profile;
import com.cplerings.core.api.account.response.CustomerEmailInfoResponse;
import com.cplerings.core.api.file.data.FileData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileUploadResponse extends AbstractDataResponse<FileData>{

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, FileUploadResponse, FileData> {

        @Override
        protected FileUploadResponse getResponseInstance() {
            return new FileUploadResponse();
        }
    }
}
