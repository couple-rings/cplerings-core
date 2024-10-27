package com.cplerings.core.infrastructure.service.storage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.cplerings.core.application.file.input.FileInput;
import com.cplerings.core.application.shared.service.storage.FileReturn;
import com.cplerings.core.application.shared.service.storage.S3StorageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S3StorageServiceImpl implements S3StorageService {

    private static final String JPEG_MAGIC_BYTES_1 = "/9j/";
    private static final String JPEG_MAGIC_BYTES_2 = "/9k/";
    private static final String PNG_MAGIC_BYTES = "iVBORw0KGgoAAAANSUhEUgAA";
    private static final String PDF_MAGIC_BYTES = "JVBER";

    private static final Map<String, String> MIMES = Map.of(
            "data:image/jpeg;base64", ".jpeg",
            "data:image/jpg;base64", ".jpg",
            "data:image/png;base64", ".png",
            "data:application/pdf;base64", ".pdf"
    );

    @Value("${application.bucket.name}")
    private String bucketName;

    private final AmazonS3 s3Client;

    @Override
    public FileReturn uploadFile(FileInput file) {
        final String[] base64ImageParts = file.getFileBase64().split(",");
        if (base64ImageParts.length != 2 || !MIMES.containsKey(base64ImageParts[0])) {
            return new FileReturn(null, true);
        }

        final String contentType = base64ImageParts[0].substring(5, base64ImageParts[0].indexOf(";"));
        final String imageExtension = MIMES.get(base64ImageParts[0]);
        final byte[] imageBytes = Base64.getDecoder().decode(base64ImageParts[1]);
        if(!checkIfImageHasCorrectMagicBytes(base64ImageParts[1], imageExtension))
            return new FileReturn(null, true);

        final String imageId = UUID.randomUUID().toString();
        final String imageKey = imageId + imageExtension;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(imageBytes.length);
        metadata.setContentType(contentType);

        s3Client.putObject(new PutObjectRequest(bucketName, imageKey, new ByteArrayInputStream(imageBytes), metadata));

        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, imageKey)
                .withMethod(HttpMethod.GET)
                .withExpiration(new Date(System.currentTimeMillis() + 7L * 24 * 3600 * 1000)); // available 1 year

        String fileUrl = s3Client.generatePresignedUrl(request).toString();
        return new FileReturn(fileUrl, false);
    }


    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean checkIfImageHasCorrectMagicBytes(String base64Image, String imageExtension) {
        if (StringUtils.equals(".jpg", imageExtension) || StringUtils.equals(".jpeg", imageExtension)) {
            if (base64Image.startsWith(JPEG_MAGIC_BYTES_1) || base64Image.startsWith(JPEG_MAGIC_BYTES_2)) {
                return true;
            }
            return false;
        }
        if (StringUtils.equals(".png", imageExtension)) {
            if (base64Image.startsWith(PNG_MAGIC_BYTES)) {
                return true;
            }
            return false;
        }

        if (StringUtils.equals(".pdf", imageExtension)) {
            if (base64Image.startsWith(PDF_MAGIC_BYTES)) {
                return true;
            }
            return false;
        }

        return false;
    }
}
