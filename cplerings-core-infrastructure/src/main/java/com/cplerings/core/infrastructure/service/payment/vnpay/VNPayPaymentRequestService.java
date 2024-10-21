package com.cplerings.core.infrastructure.service.payment.vnpay;

import com.cplerings.core.application.shared.service.payment.PaymentInfo;
import com.cplerings.core.application.shared.service.payment.PaymentRequest;
import com.cplerings.core.application.shared.service.payment.PaymentRequestService;
import com.cplerings.core.common.ip.IPUtils;
import com.cplerings.core.common.payment.PaymentConstant;
import com.cplerings.core.common.payment.PaymentUtils;
import com.cplerings.core.common.payment.VNPayConstant;
import com.cplerings.core.common.payment.VNPayUtils;
import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.PaymentType;
import com.cplerings.core.infrastructure.service.payment.datasource.VNPayPaymentServiceDataSource;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

@Service(PaymentConstant.VNPAY_PAYMENT_SERVICE_NAME)
@Transactional(rollbackOn = Throwable.class)
@RequiredArgsConstructor
public class VNPayPaymentRequestService extends AbstractVNPayPaymentService implements PaymentRequestService {

    private static final String PAYMENT_URL_FORMAT = "%s?%s&%s=%s";

    private final VNPayPaymentServiceDataSource vnPayPaymentServiceDataSource;

    @Value("${cplerings.vnpay.version}")
    protected String vnPayVersion;

    @Value("${cplerings.vnpay.tmn-code}")
    protected String terminalCode;

    @Value("${cplerings.vnpay.return-url}")
    protected String returnURL;

    @Value("${cplerings.vnpay.expiration-duration}")
    protected int expirationDuration;

    @Value("${cplerings.vnpay.secret-key}")
    protected String secretKey;

    @Value("${cplerings.vnpay.url}")
    private String vnPayURL;

    @Override
    public PaymentRequest requestPayment(PaymentInfo paymentInfo) {
        Payment payment = Payment.builder()
                .amount(paymentInfo.getAmount())
                .secureHash(StringUtils.EMPTY)
                .type(PaymentType.VNPAY)
                .description(paymentInfo.getDescription())
                .status(PaymentStatus.PENDING)
                .build();
        payment = vnPayPaymentServiceDataSource.save(payment);
        final Map<String, Object> paymentQueries = createQueriesWithoutSecureHash(paymentInfo, payment.getId());
        final String secureHash = PaymentUtils.hashQueriesWithSHA512(paymentQueries, secretKey);
        final String paymentURL = String.format(PAYMENT_URL_FORMAT,
                vnPayURL,
                PaymentUtils.joinQueriesWithURLEncoded(paymentQueries),
                VNPayConstant.VNP_SECURE_HASH,
                secureHash);
        payment.setSecureHash(secureHash);
        payment = vnPayPaymentServiceDataSource.save(payment);
        return PaymentRequest.builder()
                .paymentLink(paymentURL)
                .paymentType(PaymentType.VNPAY)
                .payment(payment)
                .build();
    }

    private Map<String, Object> createQueriesWithoutSecureHash(PaymentInfo paymentInfo, Long paymentId) {
        final Map<String, Object> paymentQueries = new TreeMap<>();
        final LocalDateTime now = TemporalUtils.getCurrentDateTimeInVietnam();
        paymentQueries.put(VNPayConstant.VNP_VERSION, vnPayVersion);
        paymentQueries.put(VNPayConstant.VNP_COMMAND, VNPayConstant.VNP_COMMAND_PAY);
        paymentQueries.put(VNPayConstant.VNP_TMN_CODE, terminalCode);
        paymentQueries.put(VNPayConstant.VNP_AMOUNT, toAmountWithConversion(paymentInfo.getAmount()));
        paymentQueries.put(VNPayConstant.VNP_CREATE_DATE, VNPayUtils.toDate(now));
        paymentQueries.put(VNPayConstant.VNP_CURR_CODE, VNPayConstant.VNP_CURRENCY_VND);
        paymentQueries.put(VNPayConstant.VNP_IP_ADDR, IPUtils.generateRandomIP());
        paymentQueries.put(VNPayConstant.VNP_LOCALE, VNPayConstant.VNP_LOCALE_VN);
        paymentQueries.put(VNPayConstant.VNP_ORDER_INFO, paymentInfo.getDescription());
        paymentQueries.put(VNPayConstant.VNP_ORDER_TYPE, VNPayConstant.VNP_ORDER_TYPE_JEWELRY);
        paymentQueries.put(VNPayConstant.VNP_RETURN_URL, returnURL);
        paymentQueries.put(VNPayConstant.VNP_EXPIRE_DATE, VNPayUtils.toDate(now.plusSeconds(expirationDuration)));
        paymentQueries.put(VNPayConstant.VNP_TXN_REF, paymentId);
        return paymentQueries;
    }
}
