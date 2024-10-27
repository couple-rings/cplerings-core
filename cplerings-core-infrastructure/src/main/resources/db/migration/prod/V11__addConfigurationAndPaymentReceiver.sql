CREATE SEQUENCE IF NOT EXISTS configuration_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS payment_receiver_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_configuration
(
    configuration_id BIGINT                      NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by        VARCHAR(255)                NOT NULL,
    modified_at      TIMESTAMP WITHOUT TIME ZONE,
    modified_by      VARCHAR(255),
    state            VARCHAR(12)                 NOT NULL,
    opt_version      INTEGER                     NOT NULL,
    key              VARCHAR(10)                 NOT NULL,
    value            VARCHAR(255)                NOT NULL,
    status           VARCHAR(12)                 NOT NULL,
    CONSTRAINT pk_tbl_configuration PRIMARY KEY (configuration_id)
);

CREATE TABLE tbl_payment_receiver
(
    payment_receiver_id BIGINT                      NOT NULL,
    created_at          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by           VARCHAR(255)                NOT NULL,
    modified_at         TIMESTAMP WITHOUT TIME ZONE,
    modified_by         VARCHAR(255),
    state               VARCHAR(12)                 NOT NULL,
    opt_version         INTEGER                     NOT NULL,
    payment_id          BIGINT                      NOT NULL,
    receiver_id         VARCHAR(255)                NOT NULL,
    receiver_type       VARCHAR(12)                 NOT NULL,
    CONSTRAINT pk_tbl_payment_receiver PRIMARY KEY (payment_receiver_id)
);

ALTER TABLE tbl_payment_receiver
    ADD CONSTRAINT uc_tbl_payment_receiver_payment UNIQUE (payment_id);

ALTER TABLE tbl_payment_receiver
    ADD CONSTRAINT FK_TBL_PAYMENT_RECEIVER_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES tbl_payment (payment_id);