package com.cplerings.core.test.integration.crafting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.payment.request.VNPayPaymentRequest;
import com.cplerings.core.application.payment.input.VNPayPaymentInput;
import com.cplerings.core.application.shared.service.payment.PaymentVerificationService;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.address.TransportationAddress;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.CraftingStageStatus;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentReceiverType;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.PaymentType;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.AgreementRepository;
import com.cplerings.core.infrastructure.repository.CraftingStageRepository;
import com.cplerings.core.infrastructure.repository.CustomOrderRepository;
import com.cplerings.core.infrastructure.repository.TransportationOrderRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.TestDataLoader;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.order.CustomOrderTestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

class ProcessCraftingStageDepositUseCaseIT extends AbstractIT {

    private static final String PAYMENT_FOLDER = "data/integration/payment";
    private static final String VNPAY_WEBHOOK_RESULT = "/vnpay-webhook-result.json";
    private static final String VNPAY_WEBHOOK_RESULT2 = "/vnpay-webhook-result2.json";

    @MockBean
    private PaymentVerificationService<VNPayPaymentInput> paymentVerificationService;

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CraftingStageRepository craftingStageRepository;

    @Autowired
    private CustomOrderRepository customOrderRepository;

    @Autowired
    private TransportationOrderRepository transportationOrderRepository;

    @Autowired
    private AgreementRepository agreementRepository;

    @Autowired
    private CustomOrderTestHelper customOrderTestHelper;

    private VNPayPaymentRequest request;

    private CraftingStage firstCraftingStage;

    private CraftingStage secondCraftingStage;

    private CustomOrder customOrder;

    private Payment payment;

    private Payment payment2;

    @BeforeEach
    public void loadPaymentResult() {
        when(paymentVerificationService.paymentIsValid(any(VNPayPaymentInput.class))).thenReturn(true);

        final TestDataLoader testDataLoader = TestDataLoader.builder()
                .folder(PAYMENT_FOLDER)
                .objectMapper(objectMapper)
                .build();
        this.request = testDataLoader.loadAsObject(VNPAY_WEBHOOK_RESULT, VNPayPaymentRequest.class);

        Payment paymentLocal = Payment.builder()
                .type(PaymentType.VNPAY)
                .amount(Money.create(BigDecimal.valueOf(request.getVnp_Amount())))
                .status(PaymentStatus.PENDING)
                .secureHash(request.getVnp_SecureHash())
                .description(request.getVnp_OrderInfo())
                .paymentReceiverType(PaymentReceiverType.CRAFT_STAGE)
                .build();
        this.payment = testDataSource.save(paymentLocal);

        final TestDataLoader testDataLoader2 = TestDataLoader.builder()
                .folder(PAYMENT_FOLDER)
                .objectMapper(objectMapper)
                .build();
        this.request = testDataLoader2.loadAsObject(VNPAY_WEBHOOK_RESULT2, VNPayPaymentRequest.class);

        Payment paymentLocal2 = Payment.builder()
                .type(PaymentType.VNPAY)
                .amount(Money.create(BigDecimal.valueOf(request.getVnp_Amount())))
                .status(PaymentStatus.PENDING)
                .secureHash(request.getVnp_SecureHash())
                .description(request.getVnp_OrderInfo())
                .paymentReceiverType(PaymentReceiverType.CRAFT_STAGE)
                .build();
        this.payment2 = testDataSource.save(paymentLocal2);

        populateCraftingStage();
    }

    private void populateCraftingStage() {
        this.customOrder = customOrderTestHelper.createCustomOrder();

        CraftingStage firstCraftingStageLocal = CraftingStage.builder()
                .customOrder(this.customOrder)
                .status(CraftingStageStatus.PENDING)
                .name("Stage 1")
                .progress(30)
                .payment(payment)
                .build();
        this.firstCraftingStage = testDataSource.save(firstCraftingStageLocal);

        CraftingStage secondCraftingStageLocal = CraftingStage.builder()
                .customOrder(this.customOrder)
                .status(CraftingStageStatus.PENDING)
                .name("Stage 2")
                .progress(100)
                .payment(payment2)
                .build();
        this.secondCraftingStage = testDataSource.save(secondCraftingStageLocal);
    }

    @Test
    void givenPayment_whenProcessFirstCraftingStageDeposit() {
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VNPAY_PATH)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();

        thenResponseIsOk(response);
        final Long customOrderId = thenCraftingStageStatusIsPaid(firstCraftingStage.getId());
        thenCustomOrderStatusIsWaiting(customOrderId);
    }

    @Test
    void givenPayment_whenProcessNotFirstCraftingStageDeposit() {
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VNPAY_PATH)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();

        thenResponseIsOk(response);
        thenCraftingStageStatusIsPaid(secondCraftingStage.getId());
    }

    @Test
    void givenPayment_whenProcessFinalCraftingStageDepositWithTransportAddress() {
        final CustomOrder localCustomOrder = customOrderRepository.findById(customOrder.getId())
                .orElse(null);
        assertThat(localCustomOrder).isNotNull();
        TransportationAddress transportationAddress = TransportationAddress.builder()
                .address("123 Hello")
                .customer(accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL).orElse(null))
                .districtCode("01")
                .district("District 1")
                .wardCode("02")
                .ward("Ward 1")
                .receiverName("John Doe")
                .receiverPhone("1234567890")
                .build();
        transportationAddress = testDataSource.save(transportationAddress);
        localCustomOrder.setTransportationAddress(transportationAddress);
        testDataSource.save(localCustomOrder);

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VNPAY_PATH)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();

        thenResponseIsOk(response);
        thenCraftingStageStatusIsPaid(secondCraftingStage.getId());
        thenTransportationOrderIsCreated(customOrder.getId());
        thenAgreementIsCreated(customOrder.getCustomer().getId());
    }

    private void thenCustomOrderStatusIsWaiting(Long customOrderId) {
        final CustomOrder customOrderLocal = customOrderRepository.findById(customOrderId)
                .orElse(null);
        assertThat(customOrderLocal).isNotNull();
        assertThat(customOrderLocal.getStatus()).isEqualTo(CustomOrderStatus.WAITING);
    }

    private Long thenCraftingStageStatusIsPaid(Long craftingStageId) {
        final CraftingStage craftingStage = craftingStageRepository.findById(craftingStageId)
                .orElse(null);
        assertThat(craftingStage).isNotNull();
        assertThat(craftingStage.getStatus()).isEqualTo(CraftingStageStatus.PAID);
        return craftingStage.getCustomOrder().getId();
    }

    private void thenTransportationOrderIsCreated(Long customOrderId) {
        assertThat(transportationOrderRepository.existsByCustomOrderId(customOrderId)).isTrue();
    }

    private void thenAgreementIsCreated(Long customerId) {
        assertThat(agreementRepository.existsByCustomerId(customerId)).isTrue();
    }
}
