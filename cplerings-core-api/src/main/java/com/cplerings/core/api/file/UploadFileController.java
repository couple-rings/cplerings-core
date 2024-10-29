package com.cplerings.core.api.file;

import com.cplerings.core.api.file.data.FileData;
import com.cplerings.core.api.file.mapper.APIUploadFileMapper;
import com.cplerings.core.api.file.request.FileUploadRequest;
import com.cplerings.core.api.file.response.FileUploadResponse;
import com.cplerings.core.api.shared.AbstractDataController;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.shared.openapi.ErrorAPIResponse;
import com.cplerings.core.api.shared.openapi.FileTag;
import com.cplerings.core.application.file.UploadFileUseCase;
import com.cplerings.core.application.file.input.UploadFileInput;
import com.cplerings.core.application.file.output.UploadFileOutput;
import com.cplerings.core.application.shared.usecase.UseCase;
import com.cplerings.core.common.api.APIConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
public class UploadFileController extends AbstractDataController<UploadFileInput, UploadFileOutput, FileData, FileUploadRequest, FileUploadResponse> {

    private final UploadFileUseCase uploadFileUseCase;
    private final APIUploadFileMapper apiUploadFileMapper;

    @PostMapping(APIConstant.FILES_PATH)
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
    protected UseCase<UploadFileInput, UploadFileOutput> getUseCase() {
        return uploadFileUseCase;
    }

    @Override
    protected APIMapper<UploadFileInput, UploadFileOutput, FileData, FileUploadRequest, FileUploadResponse> getMapper() {
        return apiUploadFileMapper;
    }
}
