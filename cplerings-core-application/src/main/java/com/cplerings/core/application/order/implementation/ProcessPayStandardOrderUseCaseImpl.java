package com.cplerings.core.application.order.implementation;

import java.util.List;

import com.cplerings.core.application.order.ProcessPayStandardOrderUseCase;
import com.cplerings.core.application.order.datasource.ProcessPayStandardOrderDataSource;
import com.cplerings.core.application.payment.error.PaymentErrorCode;
import com.cplerings.core.application.payment.input.PaymentSuccessfulResultInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.jewelry.JewelryStatus;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderItem;
import com.cplerings.core.domain.order.StandardOrderStatus;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ProcessPayStandardOrderUseCaseImpl extends AbstractUseCase<PaymentSuccessfulResultInput, NoOutput>
        implements ProcessPayStandardOrderUseCase {

    private final ProcessPayStandardOrderDataSource dataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, PaymentSuccessfulResultInput input) {
        super.validateInput(validator, input);
        validator.validate(input.payment() != null, PaymentErrorCode.PAYMENT_REQUIRED);
    }

    @Override
    protected NoOutput internalExecute(UseCaseValidator validator, PaymentSuccessfulResultInput input) {
        final StandardOrder standardOrder = input.payment().getStandardOrder();
        final List<Jewelry> jewelries = standardOrder.getStandardOrderItems()
                .stream()
                .map(StandardOrderItem::getJewelry)
                .toList();
        jewelries.forEach(jewelry -> jewelry.setStatus(JewelryStatus.PURCHASED));
        dataSource.saveJewelries(jewelries);
        standardOrder.setStatus(StandardOrderStatus.PAID);
        dataSource.save(standardOrder);
        return NoOutput.INSTANCE;
    }
}
