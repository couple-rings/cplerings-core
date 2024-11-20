package com.cplerings.core.application.diamond.implementation;

import static com.cplerings.core.application.diamond.error.CreateDiamondErrorCode.BRANCH_ID_REQUIRED;
import static com.cplerings.core.application.diamond.error.CreateDiamondErrorCode.BRANCH_NOT_FOUND;
import static com.cplerings.core.application.diamond.error.CreateDiamondErrorCode.DIAMOND_SPECIFICATION_ID_REQUIRED;
import static com.cplerings.core.application.diamond.error.CreateDiamondErrorCode.DIAMOND_SPECIFICATION_NOT_FOUND;
import static com.cplerings.core.application.diamond.error.CreateDiamondErrorCode.GIA_DOCUMENT_ID_REQUIRED;
import static com.cplerings.core.application.diamond.error.CreateDiamondErrorCode.GIA_DOCUMENT_NOT_FOUND;
import static com.cplerings.core.application.diamond.error.CreateDiamondErrorCode.GIA_REPORT_NUMBER_REQUIRED;
import static com.cplerings.core.application.diamond.error.CreateDiamondErrorCode.INVALID_BRANCH_ID;
import static com.cplerings.core.application.diamond.error.CreateDiamondErrorCode.INVALID_DIAMOND_SPECIFICATION_ID;
import static com.cplerings.core.application.diamond.error.CreateDiamondErrorCode.INVALID_GIA_DOCUMENT_ID;

import com.cplerings.core.application.diamond.CreateDiamondUseCase;
import com.cplerings.core.application.diamond.datasource.CreateDiamondDataSource;
import com.cplerings.core.application.diamond.input.CreateDiamondInput;
import com.cplerings.core.application.diamond.mapper.ACreateDiamondMapper;
import com.cplerings.core.application.diamond.output.CreateDiamondOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.number.NumberUtils;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.file.Document;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

@UseCaseImplementation
@RequiredArgsConstructor
public class CreateDiamondUseCaseImpl extends AbstractUseCase<CreateDiamondInput, CreateDiamondOutput>
        implements CreateDiamondUseCase {

    private final CreateDiamondDataSource dataSource;
    private final ACreateDiamondMapper mapper;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateDiamondInput input) {
        super.validateInput(validator, input);
        validator.validate(input.giaDocumentId() != null, GIA_DOCUMENT_ID_REQUIRED);
        validator.validate(input.diamondSpecificationId() != null, DIAMOND_SPECIFICATION_ID_REQUIRED);
        validator.validate(input.branchId() != null, BRANCH_ID_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.giaReportNumber()), GIA_REPORT_NUMBER_REQUIRED);
        validator.clearAndThrowErrorCodes();

        validator.validate(NumberUtils.isPositive(input.giaDocumentId()), INVALID_GIA_DOCUMENT_ID);
        validator.validate(NumberUtils.isPositive(input.diamondSpecificationId()), INVALID_DIAMOND_SPECIFICATION_ID);
        validator.validate(NumberUtils.isPositive(input.branchId()), INVALID_BRANCH_ID);
    }

    @Override
    protected CreateDiamondOutput internalExecute(UseCaseValidator validator, CreateDiamondInput input) {
        final Document giaDocument = dataSource.findGIADocumentById(input.giaDocumentId())
                .orElse(null);
        validator.validateAndStopExecution(giaDocument != null, GIA_DOCUMENT_NOT_FOUND);
        final DiamondSpecification diamondSpecification = dataSource.findDiamondSpecificationById(input.diamondSpecificationId())
                .orElse(null);
        validator.validateAndStopExecution(diamondSpecification != null, DIAMOND_SPECIFICATION_NOT_FOUND);
        final Branch branch = dataSource.findBranchById(input.branchId())
                .orElse(null);
        validator.validateAndStopExecution(branch != null, BRANCH_NOT_FOUND);
        Diamond diamond = Diamond.builder()
                .branch(branch)
                .giaDocument(giaDocument)
                .diamondSpecification(diamondSpecification)
                .giaReportNumber(input.giaReportNumber().trim())
                .build();
        diamond = dataSource.save(diamond);
        return mapper.toOutput(diamond);
    }
}
