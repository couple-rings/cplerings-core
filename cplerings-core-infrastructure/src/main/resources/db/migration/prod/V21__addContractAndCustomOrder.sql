CREATE SEQUENCE IF NOT EXISTS contract_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS custom_order_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_contract
(
    contract_id BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(255)                NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(255),
    state       VARCHAR(12)                 NOT NULL,
    opt_version INTEGER                     NOT NULL,
    signature   VARCHAR(255),
    signed_date TIMESTAMP WITHOUT TIME ZONE,
    document_id BIGINT,
    CONSTRAINT pk_tbl_contract PRIMARY KEY (contract_id)
);

CREATE TABLE tbl_custom_order
(
    custom_order_id BIGINT                      NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by       VARCHAR(255)                NOT NULL,
    modified_at     TIMESTAMP WITHOUT TIME ZONE,
    modified_by     VARCHAR(255),
    state           VARCHAR(12)                 NOT NULL,
    opt_version     INTEGER                     NOT NULL,
    first_ring_id   BIGINT                      NOT NULL,
    second_ring_id  BIGINT                      NOT NULL,
    customer_id     BIGINT                      NOT NULL,
    jeweler_id      BIGINT,
    contract_id     BIGINT                      NOT NULL,
    status          VARCHAR(255)                NOT NULL,
    amount          DECIMAL(12, 3)              NOT NULL,
    CONSTRAINT pk_tbl_custom_order PRIMARY KEY (custom_order_id)
);

ALTER TABLE tbl_ring
    ADD custom_order_id BIGINT;

ALTER TABLE tbl_contract
    ADD CONSTRAINT uc_tbl_contract_document UNIQUE (document_id);

ALTER TABLE tbl_custom_order
    ADD CONSTRAINT uc_tbl_custom_order_contract UNIQUE (contract_id);

ALTER TABLE tbl_custom_order
    ADD CONSTRAINT uc_tbl_custom_order_first_ring UNIQUE (first_ring_id);

ALTER TABLE tbl_custom_order
    ADD CONSTRAINT uc_tbl_custom_order_second_ring UNIQUE (second_ring_id);

ALTER TABLE tbl_contract
    ADD CONSTRAINT FK_TBL_CONTRACT_ON_DOCUMENT FOREIGN KEY (document_id) REFERENCES tbl_document (document_id);

ALTER TABLE tbl_custom_order
    ADD CONSTRAINT FK_TBL_CUSTOM_ORDER_ON_CONTRACT FOREIGN KEY (contract_id) REFERENCES tbl_contract (contract_id);

ALTER TABLE tbl_custom_order
    ADD CONSTRAINT FK_TBL_CUSTOM_ORDER_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_custom_order
    ADD CONSTRAINT FK_TBL_CUSTOM_ORDER_ON_FIRST_RING FOREIGN KEY (first_ring_id) REFERENCES tbl_ring (ring_id);

ALTER TABLE tbl_custom_order
    ADD CONSTRAINT FK_TBL_CUSTOM_ORDER_ON_JEWELER FOREIGN KEY (jeweler_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_custom_order
    ADD CONSTRAINT FK_TBL_CUSTOM_ORDER_ON_SECOND_RING FOREIGN KEY (second_ring_id) REFERENCES tbl_ring (ring_id);

ALTER TABLE tbl_ring
    ADD CONSTRAINT FK_TBL_RING_ON_CUSTOM_ORDER FOREIGN KEY (custom_order_id) REFERENCES tbl_custom_order (custom_order_id);