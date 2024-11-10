package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.file.ADocument;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.file.Document;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface ADocumentMapper {

    ADocument toDocument(Document document);
}
