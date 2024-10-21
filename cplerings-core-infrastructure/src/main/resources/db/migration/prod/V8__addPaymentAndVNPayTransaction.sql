CREATE SEQUENCE IF NOT EXISTS payment_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS vnpay_transaction_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_payment
(
    payment_id  BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(255)                NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(255),
    state       VARCHAR(12)                 NOT NULL,
    opt_version INTEGER                     NOT NULL,
    type        VARCHAR(12)                 NOT NULL,
    description VARCHAR(255)                NOT NULL,
    status      VARCHAR(12)                 NOT NULL,
    secure_hash VARCHAR(256)                NOT NULL,
    amount      DECIMAL(12, 3)              NOT NULL,
    CONSTRAINT pk_tbl_payment PRIMARY KEY (payment_id)
);

CREATE TABLE tbl_vnpay_transaction
(
    vnpay_transaction_id BIGINT                      NOT NULL,
    created_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by            VARCHAR(255)                NOT NULL,
    modified_at          TIMESTAMP WITHOUT TIME ZONE,
    modified_by          VARCHAR(255),
    state                VARCHAR(12)                 NOT NULL,
    opt_version          INTEGER                     NOT NULL,
    bank_code            VARCHAR(20)                 NOT NULL,
    bank_transfer_id     VARCHAR(255)                NOT NULL,
    card_type            VARCHAR(20)                 NOT NULL,
    pay_date             TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    order_info           VARCHAR(255),
    transaction_id       VARCHAR(255)                NOT NULL,
    status               VARCHAR(12)                 NOT NULL,
    payment_id           BIGINT                      NOT NULL,
    secure_hash          VARCHAR(256)                NOT NULL,
    amount               DECIMAL(12, 3)              NOT NULL,
    CONSTRAINT pk_tbl_vnpay_transaction PRIMARY KEY (vnpay_transaction_id)
);

ALTER TABLE tbl_vnpay_transaction
    ADD CONSTRAINT uc_tbl_vnpay_transaction_payment UNIQUE (payment_id);

ALTER TABLE tbl_vnpay_transaction
    ADD CONSTRAINT FK_TBL_VNPAY_TRANSACTION_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES tbl_payment (payment_id);