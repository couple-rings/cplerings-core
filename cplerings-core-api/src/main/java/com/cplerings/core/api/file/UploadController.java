package com.cplerings.core.api.file;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cplerings.core.api.file.data.FileData;
import com.cplerings.core.api.file.mapper.APIUploadFileMapper;
import com.cplerings.core.api.file.request.FileUploadRequest;
import com.cplerings.core.api.file.response.FileUploadResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.FileTag;
import com.cplerings.core.application.file.UploadUseCase;
import com.cplerings.core.application.file.input.FileInput;
import com.cplerings.core.application.file.output.FileOutPut;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UploadController extends AbstractDataController<FileInput, FileOutPut, FileData, FileUploadRequest, FileUploadResponse> {

    private final UploadUseCase uploadUseCase;
    private final APIUploadFileMapper apiUploadFileMapper;

    @PostMapping(APIConstant.UPLOAD_PATH)
    @FileTag
    @Operation(summary = "Upload file")
    @ApiResponse(
            description = "The url of file",
            responseCode = "200",
            content = @Content(
                    mediaType = APIConstant.APPLICATION_JSON,
                    schema = @Schema(implementation = FileUploadResponse.class)
            )
    )
    @ErrorAPIResponse
    public ResponseEntity<Object> uploadFile(@RequestBody FileUploadRequest request) {
        return handleRequest(request);
    }

    @Override
    protected UseCase<FileInput, FileOutPut> getUseCase() {
        return uploadUseCase;
    }

    @Override
    protected APIMapper<FileInput, FileOutPut, FileData, FileUploadRequest, FileUploadResponse> getMapper() {
        return apiUploadFileMapper;
    }
}
