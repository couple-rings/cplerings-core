package com.cplerings.core.api.payment.mapper;

import com.cplerings.core.api.payment.data.VNPayPaymentResult;
import com.cplerings.core.api.payment.request.VNPayPaymentRequest;
import com.cplerings.core.api.payment.response.VNPayPaymentResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.payment.input.VNPayPaymentInput;
import com.cplerings.core.application.payment.output.VNPayPaymentOutput;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.common.payment.VNPayUtils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                MoneyMapper.class
        }
)
public interface APIProcessVNPayPaymentMapper extends APIMapper<VNPayPaymentInput, VNPayPaymentOutput, VNPayPaymentResult, VNPayPaymentRequest, VNPayPaymentResponse> {

    @Override
    @Mapping(target = "bankCode", source = "vnp_BankCode")
    @Mapping(target = "amount", source = "vnp_Amount")
    @Mapping(target = "bankTransferId", source = "vnp_BankTranNo")
    @Mapping(target = "paymentId", source = "vnp_TxnRef")
    @Mapping(target = "payDate", expression = "java(toDate(request.getVnp_PayDate()))")
    @Mapping(target = "cardType", source = "vnp_CardType")
    @Mapping(target = "orderInfo", source = "vnp_OrderInfo")
    @Mapping(target = "responseCode", source = "vnp_ResponseCode")
    @Mapping(target = "secureHash", source = "vnp_SecureHash")
    @Mapping(target = "secureHashType", source = "vnp_SecureHashType")
    @Mapping(target = "terminalCode", source = "vnp_TmnCode")
    @Mapping(target = "transactionId", source = "vnp_TransactionNo")
    @Mapping(target = "transactionStatus", source = "vnp_TransactionStatus")
    VNPayPaymentInput toInput(VNPayPaymentRequest request);

    default Instant toDate(String date) {
        return VNPayUtils.toInstant(date);
    }
}
