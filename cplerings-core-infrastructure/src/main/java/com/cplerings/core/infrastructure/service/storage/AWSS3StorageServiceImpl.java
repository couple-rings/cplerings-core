package com.cplerings.core.infrastructure.service.storage;

import static com.cplerings.core.application.file.error.FileErrorCode.EMPTY_FILE;
import static com.cplerings.core.application.file.error.FileErrorCode.FILE_EXCEED_MAX_ALLOWED_SIZE;
import static com.cplerings.core.application.file.error.FileErrorCode.FILE_UPLOAD_FAILED;
import static com.cplerings.core.application.file.error.FileErrorCode.INVALID_FILE_FORMAT;
import static com.cplerings.core.application.file.error.FileErrorCode.INVALID_MAGIC_BYTES;

import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.application.shared.service.file.FileInfo;
import com.cplerings.core.application.shared.service.file.FileStorageService;
import com.cplerings.core.application.shared.service.file.FileUploadInfo;
import com.cplerings.core.common.either.Either;
import com.cplerings.core.common.file.FileType;
import com.cplerings.core.common.temporal.TemporalUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.util.Base64;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AWSS3StorageServiceImpl implements FileStorageService {

    private final S3Client s3Client;

    @Value("${cplerings.aws.s3.bucket-name}")
    private String bucketName;

    @Value("${cplerings.aws.s3.max-file-upload-size}")
    private int maxFileUploadSizeInMB;

    @Value("${cplerings.aws.s3.file-path-format}")
    private String awsFilePathFormat;

    @Override
    public Either<FileInfo, ErrorCode> uploadFile(FileUploadInfo fileUploadInfo) {
        final String[] base64FileParts = fileUploadInfo.getFileBase64().split(",");
        if (base64FileParts.length != 2) {
            return Either.right(INVALID_FILE_FORMAT);
        }
        if (StringUtils.isBlank(base64FileParts[1])) {
            return Either.right(EMPTY_FILE);
        }

        final String base64ContentType = base64FileParts[0].substring(0, base64FileParts[0].indexOf(";"));
        final FileType fileType = FileType.getFileTypeByBase64Extension(base64ContentType);
        if (fileType == null) {
            return Either.right(INVALID_FILE_FORMAT);
        }
        if (fileHasIncorrectMagicBytes(base64FileParts[1], fileType)) {
            return Either.right(INVALID_MAGIC_BYTES);
        }

        final byte[] fileBytes;
        try {
            fileBytes = Base64.getDecoder().decode(base64FileParts[1]);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Either.right(INVALID_FILE_FORMAT);
        }
        if (fileExceedAllowedSize(fileBytes)) {
            return Either.right(FILE_EXCEED_MAX_ALLOWED_SIZE);
        }

        try {
            final String filePath = prepareFilePath(fileUploadInfo, fileType);
            final PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filePath)
                    .contentType(fileType.getContentType())
                    .build();

            final PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileBytes));
            if (StringUtils.isBlank(putObjectResponse.eTag())) {
                return Either.right(FILE_UPLOAD_FAILED);
            }
            return Either.left(FileInfo.builder()
                    .url(constructFileURL(filePath))
                    .build());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Either.right(ErrorCode.System.ERROR);
        }
    }

    private boolean fileHasIncorrectMagicBytes(String base64File, FileType fileType) {
        final String magicBytes = fileType.getMagicBytesInBase64();
        return !StringUtils.startsWith(base64File, magicBytes);
    }

    private boolean fileExceedAllowedSize(byte[] fileBytes) {
        return (fileBytes.length > (maxFileUploadSizeInMB * 1024 * 1024));
    }

    private String prepareFilePath(FileUploadInfo fileUploadInfo, FileType fileType) {
        final StringBuilder builder = new StringBuilder();
        switch (fileUploadInfo.getType()) {
            case STATIC -> builder.append("static/static_");
            case DYNAMIC -> builder.append("dynamic/dynamic_");
            default -> throw new IllegalStateException("Unexpected value: " + fileType.name());
        }
        builder.append(UUID.randomUUID());
        builder.append('_');
        builder.append(TemporalUtils.getCurrentInstantUTC().toEpochMilli());
        builder.append('.');
        builder.append(fileType.getExtension());
        return builder.toString();
    }

    private String constructFileURL(String filePath) {
        return String.format(awsFilePathFormat, filePath);
    }
}
