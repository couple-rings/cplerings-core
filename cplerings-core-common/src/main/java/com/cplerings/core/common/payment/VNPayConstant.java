package com.cplerings.core.common.payment;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class VNPayConstant {

    public static final String VNP_VERSION = "vnp_Version";
    public static final String VNP_COMMAND = "vnp_Command";
    public static final String VNP_TMN_CODE = "vnp_TmnCode";
    public static final String VNP_AMOUNT = "vnp_Amount";
    public static final String VNP_CREATE_DATE = "vnp_CreateDate";
    public static final String VNP_CURR_CODE = "vnp_CurrCode";
    public static final String VNP_IP_ADDR = "vnp_IpAddr";
    public static final String VNP_LOCALE = "vnp_Locale";
    public static final String VNP_ORDER_INFO = "vnp_OrderInfo";
    public static final String VNP_ORDER_TYPE = "vnp_OrderType";
    public static final String VNP_RETURN_URL = "vnp_ReturnUrl";
    public static final String VNP_EXPIRE_DATE = "vnp_ExpireDate";
    public static final String VNP_TXN_REF = "vnp_TxnRef";
    public static final String VNP_SECURE_HASH = "vnp_SecureHash";
    public static final String VNP_BANK_CODE = "vnp_BankCode";
    public static final String VNP_BANK_TRAN_NO = "vnp_BankTranNo";
    public static final String VNP_CARD_TYPE = "vnp_CardType";
    public static final String VNP_PAY_DATE = "vnp_PayDate";
    public static final String VNP_TRANSACTION_NO = "vnp_TransactionNo";
    public static final String VNP_RESPONSE_CODE = "vnp_ResponseCode";
    public static final String VNP_TRANSACTION_STATUS = "vnp_TransactionStatus";

    public static final String VNP_COMMAND_PAY = "pay";
    public static final String VNP_CURRENCY_VND = "VND";
    public static final String VNP_ORDER_TYPE_JEWELRY = "200000";
    public static final String VNP_LOCALE_VN = "vn";

    public static final String RESPONSE_CODE_SUCCESSFUL = "00";
    public static final String RESPONSE_CODE_ABNORMAL_SUCCESSFUL = "07";
    public static final String RESPONSE_CODE_EXPIRED = "11";
    public static final String RESPONSE_CODE_CANCELLED = "24";
    public static final String RESPONSE_CODE_ERROR = "99";
}
