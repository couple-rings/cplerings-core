package com.cplerings.core.infrastructure.service.payment.vnpay;

import com.cplerings.core.application.payment.input.VNPayPaymentInput;
import com.cplerings.core.application.shared.service.payment.PaymentVerificationService;
import com.cplerings.core.common.payment.PaymentUtils;
import com.cplerings.core.common.payment.VNPayConstant;
import com.cplerings.core.common.payment.VNPayUtils;
import com.cplerings.core.infrastructure.service.payment.datasource.VNPayPaymentServiceDataSource;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;

@Service
@RequiredArgsConstructor
public class VNPayPaymentVerificationService extends AbstractVNPayPaymentService implements PaymentVerificationService<VNPayPaymentInput> {

    private final VNPayPaymentServiceDataSource vnPayPaymentServiceDataSource;

    @Value("${cplerings.vnpay.tmn-code}")
    protected String terminalCode;

    @Value("${cplerings.vnpay.secret-key}")
    protected String secretKey;

    @Override
    public boolean paymentIsValid(VNPayPaymentInput vnPayPaymentInput) {
        if (!StringUtils.equals(terminalCode, vnPayPaymentInput.getTerminalCode())) {
            return false;
        }
        final Map<String, Object> paymentQueries = createQueriesWithoutSecureHash(vnPayPaymentInput).entrySet()
                .stream()
                .filter(entry -> entry.getValue() != null)
                .collect(Collector.of(TreeMap::new,
                        (map, o) -> map.put(o.getKey(), o.getValue()),
                        (map1, map2) -> {
                            final TreeMap<String, Object> mergedMap = new TreeMap<>(map1);
                            mergedMap.putAll(map2);
                            return mergedMap;
                        }));
        final String secureHashType = StringUtils.trimToEmpty(vnPayPaymentInput.getSecureHashType());
        final String secureHash;
        if (StringUtils.equalsIgnoreCase("SHA256", secureHashType)) {
            secureHash = PaymentUtils.hashQueriesWithSHA256(paymentQueries, secretKey);
        } else {
            secureHash = PaymentUtils.hashQueriesWithSHA512(paymentQueries, secretKey);
        }
        if (StringUtils.isBlank(secureHash) || !StringUtils.equals(vnPayPaymentInput.getSecureHash(), secureHash)) {
            return false;
        }
        return vnPayPaymentServiceDataSource.paymentExistsAndIsPending(vnPayPaymentInput.getPaymentId());
    }

    private Map<String, Object> createQueriesWithoutSecureHash(VNPayPaymentInput vnPayPaymentInput) {
        final Map<String, Object> paymentQueries = new TreeMap<>();
        paymentQueries.put(VNPayConstant.VNP_TMN_CODE, vnPayPaymentInput.getTerminalCode());
        paymentQueries.put(VNPayConstant.VNP_AMOUNT, toAmountWithoutConversion(vnPayPaymentInput.getAmount()));
        paymentQueries.put(VNPayConstant.VNP_BANK_CODE, vnPayPaymentInput.getBankCode());
        paymentQueries.put(VNPayConstant.VNP_BANK_TRAN_NO, vnPayPaymentInput.getBankTransferId());
        paymentQueries.put(VNPayConstant.VNP_CARD_TYPE, vnPayPaymentInput.getCardType());
        paymentQueries.put(VNPayConstant.VNP_PAY_DATE, VNPayUtils.toDate(vnPayPaymentInput.getPayDate()));
        paymentQueries.put(VNPayConstant.VNP_ORDER_INFO, vnPayPaymentInput.getOrderInfo());
        paymentQueries.put(VNPayConstant.VNP_TRANSACTION_NO, vnPayPaymentInput.getTransactionId());
        paymentQueries.put(VNPayConstant.VNP_RESPONSE_CODE, vnPayPaymentInput.getResponseCode());
        paymentQueries.put(VNPayConstant.VNP_TRANSACTION_STATUS, vnPayPaymentInput.getTransactionStatus());
        paymentQueries.put(VNPayConstant.VNP_TXN_REF, vnPayPaymentInput.getPaymentId());
        return paymentQueries;
    }
}
