package com.cplerings.core.application.agreement.implementation;

import static com.cplerings.core.application.agreement.error.SignAgreementErrorCode.AGREEMENT_ALREADY_SIGNED;
import static com.cplerings.core.application.agreement.error.SignAgreementErrorCode.AGREEMENT_ID_REQUIRED;
import static com.cplerings.core.application.agreement.error.SignAgreementErrorCode.AGREEMENT_NOT_FOUND;
import static com.cplerings.core.application.agreement.error.SignAgreementErrorCode.INVALID_AGREEMENT_ID;
import static com.cplerings.core.application.agreement.error.SignAgreementErrorCode.INVALID_MAIN_SIGNATURE_ID;
import static com.cplerings.core.application.agreement.error.SignAgreementErrorCode.INVALID_PARTNER_SIGNATURE_ID;
import static com.cplerings.core.application.agreement.error.SignAgreementErrorCode.MAIN_NAME_REQUIRED;
import static com.cplerings.core.application.agreement.error.SignAgreementErrorCode.MAIN_SIGNATURE_ID_REQUIRED;
import static com.cplerings.core.application.agreement.error.SignAgreementErrorCode.NOT_SAME_CUSTOMER_TO_SIGN;
import static com.cplerings.core.application.agreement.error.SignAgreementErrorCode.PARTNER_NAME_REQUIRED;
import static com.cplerings.core.application.agreement.error.SignAgreementErrorCode.PARTNER_SIGNATURE_ID_REQUIRED;
import static com.cplerings.core.application.agreement.error.SignAgreementErrorCode.SIGNATURES_NOT_FOUND;
import static com.cplerings.core.application.agreement.error.SignAgreementErrorCode.TWO_SIGNATURES_ARE_THE_SAME;

import com.cplerings.core.application.agreement.SignAgreementUseCase;
import com.cplerings.core.application.agreement.datasource.SignAgreementDataSource;
import com.cplerings.core.application.agreement.input.SignAgreementInput;
import com.cplerings.core.application.agreement.mapper.ASignAgreementMapper;
import com.cplerings.core.application.agreement.output.SignAgreementOutput;
import com.cplerings.core.application.shared.service.security.CurrentUser;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.number.NumberUtils;
import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.spouse.Agreement;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@UseCaseImplementation
@RequiredArgsConstructor
public class SignAgreementUseCaseImpl extends AbstractUseCase<SignAgreementInput, SignAgreementOutput>
        implements SignAgreementUseCase {

    private final SignAgreementDataSource dataSource;
    private final ASignAgreementMapper mapper;
    private final SecurityService securityService;

    @Override
    protected void validateInput(UseCaseValidator validator, SignAgreementInput input) {
        super.validateInput(validator, input);
        validator.validate(StringUtils.isNotBlank(input.mainName()), MAIN_NAME_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.partnerName()), PARTNER_NAME_REQUIRED);
        validator.validate(input.agreementId() != null, AGREEMENT_ID_REQUIRED);
        validator.validate(input.mainSignatureId() != null, MAIN_SIGNATURE_ID_REQUIRED);
        validator.validate(input.partnerSignatureId() != null, PARTNER_SIGNATURE_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();

        validator.validate(NumberUtils.isPositive(input.agreementId()), INVALID_AGREEMENT_ID);
        validator.validate(NumberUtils.isPositive(input.mainSignatureId()), INVALID_MAIN_SIGNATURE_ID);
        validator.validate(NumberUtils.isPositive(input.partnerSignatureId()), INVALID_PARTNER_SIGNATURE_ID);
        validator.validate(ObjectUtils.notEqual(input.mainSignatureId(), input.partnerSignatureId()), TWO_SIGNATURES_ARE_THE_SAME);
    }

    @Override
    protected SignAgreementOutput internalExecute(UseCaseValidator validator, SignAgreementInput input) {
        Agreement agreement = dataSource.findAgreementById(input.agreementId())
                .orElse(null);
        validator.validateAndStopExecution(agreement != null, AGREEMENT_NOT_FOUND);
        final CurrentUser currentUser = securityService.getCurrentUser();
        validator.validateAndStopExecution(Objects.equals(currentUser.id(), agreement.getCustomer().getId()), NOT_SAME_CUSTOMER_TO_SIGN);
        validator.validateAndStopExecution(agreement.getSignedDate() == null, AGREEMENT_ALREADY_SIGNED);
        final Collection<Image> images = dataSource.findSignaturesByIds(List.of(input.mainSignatureId(), input.partnerSignatureId()));
        validator.validateAndStopExecution(images.size() == 2, SIGNATURES_NOT_FOUND);
        agreement.setMainName(input.mainName().trim());
        agreement.setPartnerName(input.partnerName().trim());
        agreement.setMainSignature(images.stream()
                .filter(image -> Objects.equals(image.getId(), input.mainSignatureId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Cannot find main signature")));
        agreement.setPartnerSignature(images.stream()
                .filter(image -> Objects.equals(image.getId(), input.partnerSignatureId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Cannot find partner signature")));
        agreement.setSignedDate(TemporalUtils.getCurrentInstantUTC());
        agreement = dataSource.save(agreement);
        return mapper.toOutput(agreement);
    }
}
