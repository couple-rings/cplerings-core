package com.cplerings.core.application.diamond.implementation;

import com.cplerings.core.application.diamond.UpdateDiamondUseCase;
import com.cplerings.core.application.diamond.datasource.UpdateDiamondDataSource;
import com.cplerings.core.application.diamond.error.UpdateDiamondErrorCode;
import com.cplerings.core.application.diamond.input.UpdateDiamondInput;
import com.cplerings.core.application.diamond.mapper.AUpdateDiamondMapper;
import com.cplerings.core.application.diamond.output.UpdateDiamondOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.file.Document;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class UpdateDiamondUseCaseImpl extends AbstractUseCase<UpdateDiamondInput, UpdateDiamondOutput> implements UpdateDiamondUseCase {

    private final AUpdateDiamondMapper aUpdateDiamondMapper;
    private final UpdateDiamondDataSource updateDiamondDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, UpdateDiamondInput input) {
        super.validateInput(validator, input);
        validator.validate(input.diamondId() != null, UpdateDiamondErrorCode.DIAMOND_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validateAndStopExecution(input.diamondId() > 0, UpdateDiamondErrorCode.DIAMOND_ID_WRONG_INTEGER);
    }

    @Override
    protected UpdateDiamondOutput internalExecute(UseCaseValidator validator, UpdateDiamondInput input) {
        Diamond diamond = updateDiamondDataSource.getDiamondById(input.diamondId())
                .orElse(null);
        validator.validateAndStopExecution(diamond != null, UpdateDiamondErrorCode.DIAMOND_NOT_FOUND);
        if (input.diamondSpecificationId() != null) {
            DiamondSpecification diamondSpecification = updateDiamondDataSource.getDiamondSpecById(input.diamondSpecificationId())
                    .orElse(null);
            validator.validateAndStopExecution(diamondSpecification != null, UpdateDiamondErrorCode.DIAMOND_SPEC_NOT_FOUND);
            diamond.setDiamondSpecification(diamondSpecification);
        }

        if (input.giaReportNumber() != null) {
            Diamond getDiamondByGiaReportNo = updateDiamondDataSource.getDiamondByGiaReportNumberAndNotEqualTheDiamondId(input.giaReportNumber(), diamond.getId())
                    .orElse(null);
            validator.validateAndStopExecution(getDiamondByGiaReportNo == null, UpdateDiamondErrorCode.FOUND_DIAMOND_WITH_THIS_GIA_NUMBER);
            diamond.setGiaReportNumber(input.giaReportNumber());
        }
        if (input.giaDocumentId() != null) {
            Document giaDocument = updateDiamondDataSource.getDocumentById(input.giaDocumentId())
                    .orElse(null);
            validator.validateAndStopExecution(giaDocument != null, UpdateDiamondErrorCode.DOCUMENT_NOT_FOUND);
            diamond.setGiaDocument(giaDocument);
        }

        Diamond diamondUpdated = updateDiamondDataSource.save(diamond);
        return aUpdateDiamondMapper.toOutput(diamondUpdated);
    }
}
