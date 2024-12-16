package com.cplerings.core.application.jewelry.implementation;

import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.CUSTOMER_ID_REQUIRED;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.CUSTOMER_NOT_FOUND;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.INVALID_CUSTOMER_ID;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.INVALID_JEWELRY_ID;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.INVALID_PROOF_IMAGE_ID;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.JEWELRY_ID_REQUIRED;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.JEWELRY_NOT_FOUND;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.JEWELRY_NOT_PURCHASED;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.NOTE_REQUIRED;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.PAYMENT_METHOD_REQUIRED;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.PROOF_IMAGE_ID_REQUIRED;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.PROOF_IMAGE_NOT_FOUND;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.STANDARD_ORDER_ITEM_NOT_FOUND;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.WRONG_CUSTOMER;

import com.cplerings.core.application.jewelry.ResellJewelryUseCase;
import com.cplerings.core.application.jewelry.datasource.ResellJewelryDataSource;
import com.cplerings.core.application.jewelry.input.ResellJewelryInput;
import com.cplerings.core.application.jewelry.output.ResellJewelryOutput;
import com.cplerings.core.application.shared.mapper.AResellOrderMapper;
import com.cplerings.core.application.shared.service.configuration.ConfigurationService;
import com.cplerings.core.application.shared.service.price.CalculationTotalPriceService;
import com.cplerings.core.application.shared.service.security.CurrentUser;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.number.NumberUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.jewelry.JewelryStatus;
import com.cplerings.core.domain.order.StandardOrderItem;
import com.cplerings.core.domain.resell.ResellOrder;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UseCaseImplementation
@RequiredArgsConstructor
public class ResellJewelryUseCaseImpl extends AbstractUseCase<ResellJewelryInput, ResellJewelryOutput> implements ResellJewelryUseCase {

    private final ResellJewelryDataSource dataSource;
    private final AResellOrderMapper mapper;
    private final SecurityService securityService;
    private final ConfigurationService configurationService;
    private final CalculationTotalPriceService calculationTotalPriceService;

    @Override
    protected void validateInput(UseCaseValidator validator, ResellJewelryInput input) {
        super.validateInput(validator, input);
        validator.validate(input.jewelryId() != null, JEWELRY_ID_REQUIRED);
        validator.validate(input.customerId() != null, CUSTOMER_ID_REQUIRED);
        validator.validate(input.proofImageId() != null, PROOF_IMAGE_ID_REQUIRED);
        validator.validate(input.paymentMethod() != null, PAYMENT_METHOD_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.note()), NOTE_REQUIRED);
        validator.clearAndThrowErrorCodes();

        validator.validate(NumberUtils.isPositive(input.jewelryId()), INVALID_JEWELRY_ID);
        validator.validate(NumberUtils.isPositive(input.customerId()), INVALID_CUSTOMER_ID);
        validator.validate(NumberUtils.isPositive(input.proofImageId()), INVALID_PROOF_IMAGE_ID);
    }

    @Override
    protected ResellJewelryOutput internalExecute(UseCaseValidator validator, ResellJewelryInput input) {
        final CurrentUser currentUser = securityService.getCurrentUser();

        Jewelry jewelry = dataSource.findJewelryById(input.jewelryId())
                .orElse(null);
        validator.validateAndStopExecution(jewelry != null, JEWELRY_NOT_FOUND);
        validator.validateAndStopExecution(jewelry.getStatus() == JewelryStatus.PURCHASED, JEWELRY_NOT_PURCHASED);

        final Account customer = dataSource.findCustomerById(input.customerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, CUSTOMER_NOT_FOUND);

        final Image proofImage = dataSource.findProofImageById(input.proofImageId())
                .orElse(null);
        validator.validateAndStopExecution(proofImage != null, PROOF_IMAGE_NOT_FOUND);

        StandardOrderItem standardOrderItem = dataSource.findByJewelryId(jewelry.getId())
                        .orElse(null);
        validator.validateAndStopExecution(standardOrderItem != null, STANDARD_ORDER_ITEM_NOT_FOUND);
        var standardOrder = standardOrderItem.getStandardOrder();
        validator.validateAndStopExecution(standardOrder.getCustomer().getId() == customer.getId(), WRONG_CUSTOMER);

        jewelry.setStatus(JewelryStatus.RESOLD);
        jewelry = dataSource.save(jewelry);

        ResellOrder resellOrder = ResellOrder.builder()
                .jewelry(jewelry)
                .customer(customer)
                .staff(dataSource.getStaffReference(currentUser.id()))
                .proofImage(proofImage)
                .note(input.note().trim())
                .paymentMethod(input.paymentMethod())
                .amount(calculateAmount(jewelry))
                .build();
        resellOrder = dataSource.save(resellOrder);

        return ResellJewelryOutput.builder()
                .resellOrder(mapper.toAResellOrder(resellOrder))
                .build();
    }

    private Money calculateAmount(Jewelry jewelry) {
        final Money jewelryPrice = calculationTotalPriceService.calculationPriceForJewelry(jewelry.getMetalSpecification().getPricePerUnit(),
                jewelry.getDesign().getMetalWeight().getWeightValue(),
                jewelry.getDesign().getSideDiamondsCount(),
                configurationService.getSideDiamondPrice().getAmount());
        final BigDecimal resellPercentage = BigDecimal.valueOf(configurationService.getResellPercentage())
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN);
        return jewelryPrice.multiply(resellPercentage);
    }
}
