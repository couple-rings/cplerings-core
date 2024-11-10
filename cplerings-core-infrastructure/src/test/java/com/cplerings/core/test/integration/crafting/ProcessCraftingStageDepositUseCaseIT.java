package com.cplerings.core.test.integration.crafting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.cplerings.core.api.payment.request.VNPayPaymentRequest;
import com.cplerings.core.application.payment.input.VNPayPaymentInput;
import com.cplerings.core.application.shared.service.payment.PaymentVerificationService;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.CraftingStageStatus;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentReceiver;
import com.cplerings.core.domain.payment.PaymentReceiverType;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.PaymentType;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingStatus;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.CraftingStageRepository;
import com.cplerings.core.infrastructure.repository.CustomOrderRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.TestDataLoader;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.spouse.SpouseTestHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

class ProcessCraftingStageDepositUseCaseIT extends AbstractIT {

    private static final String PAYMENT_FOLDER = "data/integration/payment";
    private static final String VNPAY_WEBHOOK_RESULT = "/vnpay-webhook-result.json";

    @MockBean
    private PaymentVerificationService<VNPayPaymentInput> paymentVerificationService;

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SpouseTestHelper spouseTestHelper;

    @Autowired
    private CraftingStageRepository craftingStageRepository;

    @Autowired
    private CustomOrderRepository customOrderRepository;

    private VNPayPaymentRequest request;

    private CraftingStage firstCraftingStage;

    private CraftingStage secondCraftingStage;

    private Payment payment;

    @BeforeEach
    public void loadPaymentResult() {
        when(paymentVerificationService.paymentIsValid(any(VNPayPaymentInput.class))).thenReturn(true);

        final TestDataLoader testDataLoader = TestDataLoader.builder()
                .folder(PAYMENT_FOLDER)
                .objectMapper(objectMapper)
                .build();
        this.request = testDataLoader.loadAsObject(VNPAY_WEBHOOK_RESULT, VNPayPaymentRequest.class);

        Payment payment = Payment.builder()
                .type(PaymentType.VNPAY)
                .amount(Money.create(BigDecimal.valueOf(request.getVnp_Amount())))
                .status(PaymentStatus.PENDING)
                .secureHash(request.getVnp_SecureHash())
                .description(request.getVnp_OrderInfo())
                .build();
        this.payment = testDataSource.save(payment);

        populateCraftingStage();
    }

    private void populateCraftingStage() {
        final Spouse[] spouses = spouseTestHelper.createSpouseFromCustomerEmail(AccountTestConstant.CUSTOMER_EMAIL);
        Contract contract = Contract.builder()
                .build();
        contract = testDataSource.save(contract);
        Branch branch = Branch.builder()
                .address("123 Hello")
                .phone("1234567890")
                .storeName("Hello")
                .build();
        branch = testDataSource.save(branch);
        Ring firstRing = Ring.builder()
                .status(RingStatus.NOT_AVAIL)
                .branch(branch)
                .spouse(spouses[0])
                .build();
        firstRing = testDataSource.save(firstRing);
        Ring secondRing = Ring.builder()
                .status(RingStatus.NOT_AVAIL)
                .branch(branch)
                .spouse(spouses[1])
                .build();
        secondRing = testDataSource.save(secondRing);
        CustomOrder customOrder = CustomOrder.builder()
                .customer(accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL).orElse(null))
                .contract(contract)
                .status(CustomOrderStatus.PENDING)
                .totalPrice(Money.create(BigDecimal.valueOf(100000)))
                .firstRing(firstRing)
                .secondRing(secondRing)
                .build();
        customOrder = testDataSource.save(customOrder);

        CraftingStage firstCraftingStage = CraftingStage.builder()
                .customOrder(customOrder)
                .status(CraftingStageStatus.PENDING)
                .name("Stage 1")
                .progress(30)
                .build();
        this.firstCraftingStage = testDataSource.save(firstCraftingStage);
        CraftingStage secondCraftingStage = CraftingStage.builder()
                .customOrder(customOrder)
                .status(CraftingStageStatus.PENDING)
                .name("Stage 1")
                .progress(100)
                .build();
        this.secondCraftingStage = testDataSource.save(secondCraftingStage);
    }

    @Test
    void givenPayment_whenProcessFirstCraftingStageDeposit() {
        final PaymentReceiver paymentReceiver = PaymentReceiver.builder()
                .payment(this.payment)
                .receiverType(PaymentReceiverType.CRAFT_STAGE)
                .receiverId(String.valueOf(firstCraftingStage.getId()))
                .build();
        testDataSource.save(paymentReceiver);

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
        final PaymentReceiver paymentReceiver = PaymentReceiver.builder()
                .payment(this.payment)
                .receiverType(PaymentReceiverType.CRAFT_STAGE)
                .receiverId(String.valueOf(secondCraftingStage.getId()))
                .build();
        testDataSource.save(paymentReceiver);

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VNPAY_PATH)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();

        thenResponseIsOk(response);
        thenCraftingStageStatusIsPaid(secondCraftingStage.getId());
    }

    private void thenCustomOrderStatusIsWaiting(Long customOrderId) {
        final CustomOrder customOrder = customOrderRepository.findById(customOrderId)
                .orElse(null);
        assertThat(customOrder).isNotNull();
        assertThat(customOrder.getStatus()).isEqualTo(CustomOrderStatus.WAITING);
    }

    private Long thenCraftingStageStatusIsPaid(Long craftingStageId) {
        final CraftingStage craftingStage = craftingStageRepository.findById(craftingStageId)
                .orElse(null);
        assertThat(craftingStage).isNotNull();
        assertThat(craftingStage.getStatus()).isEqualTo(CraftingStageStatus.PAID);
        return craftingStage.getCustomOrder().getId();
    }
}
