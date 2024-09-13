CREATE SCHEMA IF NOT EXISTS core;

CREATE SEQUENCE IF NOT EXISTS core.account_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.account_verification_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.address_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.agreement_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.collection_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.contract_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.custom_request_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.custom_request_tracing_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.deposit_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.design_finder_size_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.design_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.diamond_category_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.diamond_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.discount_campaign_collection_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.discount_campaign_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.finger_size_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.gia_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.metal_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.order_ring_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.order_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.order_tracing_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.payment_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.ring_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.spouse_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.spouse_verification_request_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.transaction_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.transportation_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.transportation_tracing_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS core.warranty_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE core.tbl_account
(
    account_id  BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(50)                 NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(50),
    version     INTEGER                     NOT NULL,
    email       VARCHAR(100)                NOT NULL,
    password    VARCHAR(62)                 NOT NULL,
    role        VARCHAR(12)                 NOT NULL,
    status      VARCHAR(10)                 NOT NULL,
    address_id  BIGINT,
    CONSTRAINT pk_tbl_account PRIMARY KEY (account_id)
);

CREATE TABLE core.tbl_account_verification
(
    account_verification_id BIGINT                      NOT NULL,
    created_at              TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by               VARCHAR(50)                 NOT NULL,
    modified_at             TIMESTAMP WITHOUT TIME ZONE,
    modified_by             VARCHAR(50),
    version                 INTEGER                     NOT NULL,
    verification_code       VARCHAR(6)                  NOT NULL,
    status                  VARCHAR(10)                 NOT NULL,
    account_id              BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_account_verification PRIMARY KEY (account_verification_id)
);

CREATE TABLE core.tbl_address
(
    address_id   BIGINT                      NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by    VARCHAR(50)                 NOT NULL,
    modified_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_by  VARCHAR(50),
    version      INTEGER                     NOT NULL,
    house_number VARCHAR(255),
    street       VARCHAR(255),
    ward         VARCHAR(255),
    district     VARCHAR(255),
    province     VARCHAR(255),
    CONSTRAINT pk_tbl_address PRIMARY KEY (address_id)
);

CREATE TABLE core.tbl_agreement
(
    agreement_id   BIGINT                      NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by      VARCHAR(50)                 NOT NULL,
    modified_at    TIMESTAMP WITHOUT TIME ZONE,
    modified_by    VARCHAR(50),
    version        INTEGER                     NOT NULL,
    marriage_date  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    first_ring_id  BIGINT                      NOT NULL,
    second_ring_id BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_agreement PRIMARY KEY (agreement_id)
);

CREATE TABLE core.tbl_collection
(
    collection_id BIGINT                      NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by     VARCHAR(50)                 NOT NULL,
    modified_at   TIMESTAMP WITHOUT TIME ZONE,
    modified_by   VARCHAR(50),
    version       INTEGER                     NOT NULL,
    title         VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_tbl_collection PRIMARY KEY (collection_id)
);

CREATE TABLE core.tbl_contract
(
    contract_id       BIGINT                      NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by         VARCHAR(50)                 NOT NULL,
    modified_at       TIMESTAMP WITHOUT TIME ZONE,
    modified_by       VARCHAR(50),
    version           INTEGER                     NOT NULL,
    design_id         BIGINT                      NOT NULL,
    custom_request_id BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_contract PRIMARY KEY (contract_id)
);

CREATE TABLE core.tbl_custom_request
(
    custom_request_id BIGINT                      NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by         VARCHAR(50)                 NOT NULL,
    modified_at       TIMESTAMP WITHOUT TIME ZONE,
    modified_by       VARCHAR(50),
    version           INTEGER                     NOT NULL,
    customer_id       BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_custom_request PRIMARY KEY (custom_request_id)
);

CREATE TABLE core.tbl_custom_request_tracking
(
    custom_request_tracing_id BIGINT                      NOT NULL,
    created_at                TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                 VARCHAR(50)                 NOT NULL,
    modified_at               TIMESTAMP WITHOUT TIME ZONE,
    modified_by               VARCHAR(50),
    version                   INTEGER                     NOT NULL,
    status                    VARCHAR(10)                 NOT NULL,
    custom_request_id         BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_custom_request_tracking PRIMARY KEY (custom_request_tracing_id)
);

CREATE TABLE core.tbl_deposit
(
    deposit_id  BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(50)                 NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(50),
    version     INTEGER                     NOT NULL,
    contract_id BIGINT                      NOT NULL,
    payment_id  BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_deposit PRIMARY KEY (deposit_id)
);

CREATE TABLE core.tbl_design
(
    design_id     BIGINT                      NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by     VARCHAR(50)                 NOT NULL,
    modified_at   TIMESTAMP WITHOUT TIME ZONE,
    modified_by   VARCHAR(50),
    version       INTEGER                     NOT NULL,
    collection_id BIGINT,
    CONSTRAINT pk_tbl_design PRIMARY KEY (design_id)
);

CREATE TABLE core.tbl_design_finger_size
(
    design_finger_size_id BIGINT                      NOT NULL,
    created_at            TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by             VARCHAR(50)                 NOT NULL,
    modified_at           TIMESTAMP WITHOUT TIME ZONE,
    modified_by           VARCHAR(50),
    version               INTEGER                     NOT NULL,
    design_id             BIGINT                      NOT NULL,
    finger_size_id        BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_design_finger_size PRIMARY KEY (design_finger_size_id)
);

CREATE TABLE core.tbl_diamond
(
    diamond_id  BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(50)                 NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(50),
    version     INTEGER                     NOT NULL,
    gia_id      BIGINT                      NOT NULL,
    category_id BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_diamond PRIMARY KEY (diamond_id)
);

CREATE TABLE core.tbl_diamond_category
(
    diamond_category_id BIGINT                      NOT NULL,
    created_at          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by           VARCHAR(50)                 NOT NULL,
    modified_at         TIMESTAMP WITHOUT TIME ZONE,
    modified_by         VARCHAR(50),
    version             INTEGER                     NOT NULL,
    CONSTRAINT pk_tbl_diamond_category PRIMARY KEY (diamond_category_id)
);

CREATE TABLE core.tbl_discount_campaign
(
    discount_campaign_id BIGINT                      NOT NULL,
    created_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by            VARCHAR(50)                 NOT NULL,
    modified_at          TIMESTAMP WITHOUT TIME ZONE,
    modified_by          VARCHAR(50),
    version              INTEGER                     NOT NULL,
    CONSTRAINT pk_tbl_discount_campaign PRIMARY KEY (discount_campaign_id)
);

CREATE TABLE core.tbl_discount_campaign_collection
(
    discount_campaign_collection_id BIGINT                      NOT NULL,
    created_at                      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                       VARCHAR(50)                 NOT NULL,
    modified_at                     TIMESTAMP WITHOUT TIME ZONE,
    modified_by                     VARCHAR(50),
    version                         INTEGER                     NOT NULL,
    collection_id                   BIGINT                      NOT NULL,
    discount_campaign_id            BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_discount_campaign_collection PRIMARY KEY (discount_campaign_collection_id)
);

CREATE TABLE core.tbl_finger_size
(
    fincer_size_id BIGINT                      NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by      VARCHAR(50)                 NOT NULL,
    modified_at    TIMESTAMP WITHOUT TIME ZONE,
    modified_by    VARCHAR(50),
    version        INTEGER                     NOT NULL,
    CONSTRAINT pk_tbl_finger_size PRIMARY KEY (fincer_size_id)
);

CREATE TABLE core.tbl_gia
(
    gia_id           BIGINT                      NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by        VARCHAR(50)                 NOT NULL,
    modified_at      TIMESTAMP WITHOUT TIME ZONE,
    modified_by      VARCHAR(50),
    version          INTEGER                     NOT NULL,
    gia_document_url VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_tbl_gia PRIMARY KEY (gia_id)
);

CREATE TABLE core.tbl_metal
(
    metal_id    BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(50)                 NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(50),
    version     INTEGER                     NOT NULL,
    CONSTRAINT pk_tbl_metal PRIMARY KEY (metal_id)
);

CREATE TABLE core.tbl_order
(
    order_id    BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(50)                 NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(50),
    version     INTEGER                     NOT NULL,
    CONSTRAINT pk_tbl_order PRIMARY KEY (order_id)
);

CREATE TABLE core.tbl_order_ring
(
    order_ring_id BIGINT                      NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by     VARCHAR(50)                 NOT NULL,
    modified_at   TIMESTAMP WITHOUT TIME ZONE,
    modified_by   VARCHAR(50),
    version       INTEGER                     NOT NULL,
    order_id      BIGINT                      NOT NULL,
    ring_id       BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_order_ring PRIMARY KEY (order_ring_id)
);

CREATE TABLE core.tbl_order_tracing
(
    order_tracing_id BIGINT                      NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by        VARCHAR(50)                 NOT NULL,
    modified_at      TIMESTAMP WITHOUT TIME ZONE,
    modified_by      VARCHAR(50),
    version          INTEGER                     NOT NULL,
    status           VARCHAR(10)                 NOT NULL,
    order_id         BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_order_tracing PRIMARY KEY (order_tracing_id)
);

CREATE TABLE core.tbl_payment
(
    payment_id  BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(50)                 NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(50),
    version     INTEGER                     NOT NULL,
    type        VARCHAR(10)                 NOT NULL,
    status      VARCHAR(10)                 NOT NULL,
    CONSTRAINT pk_tbl_payment PRIMARY KEY (payment_id)
);

CREATE TABLE core.tbl_ring
(
    ring_id     BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(50)                 NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(50),
    version     INTEGER                     NOT NULL,
    design_id   BIGINT                      NOT NULL,
    diamond_id  BIGINT                      NOT NULL,
    metal_id    BIGINT                      NOT NULL,
    warranty_id BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_ring PRIMARY KEY (ring_id)
);

CREATE TABLE core.tbl_spouse
(
    spouse_id               BIGINT                      NOT NULL,
    created_at              TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by               VARCHAR(50)                 NOT NULL,
    modified_at             TIMESTAMP WITHOUT TIME ZONE,
    modified_by             VARCHAR(50),
    version                 INTEGER                     NOT NULL,
    first_name              VARCHAR(50)                 NOT NULL,
    middle_name             VARCHAR(50),
    last_name               VARCHAR(50)                 NOT NULL,
    birth_date              TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    id_card_number          VARCHAR(12)                 NOT NULL,
    id_card_image_url       VARCHAR(255)                NOT NULL,
    customer_id             BIGINT,
    verification_request_id BIGINT                      NOT NULL,
    agreement_id            BIGINT,
    CONSTRAINT pk_tbl_spouse PRIMARY KEY (spouse_id)
);

CREATE TABLE core.tbl_spouse_verification_request
(
    spouse_verification_request_id BIGINT                      NOT NULL,
    created_at                     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                      VARCHAR(50)                 NOT NULL,
    modified_at                    TIMESTAMP WITHOUT TIME ZONE,
    modified_by                    VARCHAR(50),
    version                        INTEGER                     NOT NULL,
    customer_id                    BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_spouse_verification_request PRIMARY KEY (spouse_verification_request_id)
);

CREATE TABLE core.tbl_transaction
(
    transaction_id BIGINT                      NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by      VARCHAR(50)                 NOT NULL,
    modified_at    TIMESTAMP WITHOUT TIME ZONE,
    modified_by    VARCHAR(50),
    version        INTEGER                     NOT NULL,
    order_id       BIGINT                      NOT NULL,
    payment_id     BIGINT,
    CONSTRAINT pk_tbl_transaction PRIMARY KEY (transaction_id)
);

CREATE TABLE core.tbl_transportation
(
    transportation_id BIGINT                      NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by         VARCHAR(50)                 NOT NULL,
    modified_at       TIMESTAMP WITHOUT TIME ZONE,
    modified_by       VARCHAR(50),
    version           INTEGER                     NOT NULL,
    order_id          BIGINT                      NOT NULL,
    transporter_id    BIGINT                      NOT NULL,
    address_id        BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_transportation PRIMARY KEY (transportation_id)
);

CREATE TABLE core.tbl_transportation_tracing
(
    transportation_tracing_id BIGINT                      NOT NULL,
    created_at                TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                 VARCHAR(50)                 NOT NULL,
    modified_at               TIMESTAMP WITHOUT TIME ZONE,
    modified_by               VARCHAR(50),
    version                   INTEGER                     NOT NULL,
    status                    VARCHAR(12)                 NOT NULL,
    transportation_id         BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_transportation_tracing PRIMARY KEY (transportation_tracing_id)
);

CREATE TABLE core.tbl_warranty
(
    warranty_id BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(50)                 NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(50),
    version     INTEGER                     NOT NULL,
    start_time  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_time    TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_tbl_warranty PRIMARY KEY (warranty_id)
);

ALTER TABLE core.tbl_account
    ADD CONSTRAINT uc_tbl_account_address UNIQUE (address_id);

ALTER TABLE core.tbl_account
    ADD CONSTRAINT uc_tbl_account_email UNIQUE (email);

ALTER TABLE core.tbl_account
    ADD CONSTRAINT uc_tbl_account_password UNIQUE (password);

ALTER TABLE core.tbl_account_verification
    ADD CONSTRAINT uc_tbl_account_verification_account UNIQUE (account_id);

ALTER TABLE core.tbl_agreement
    ADD CONSTRAINT uc_tbl_agreement_first_ring UNIQUE (first_ring_id);

ALTER TABLE core.tbl_agreement
    ADD CONSTRAINT uc_tbl_agreement_second_ring UNIQUE (second_ring_id);

ALTER TABLE core.tbl_contract
    ADD CONSTRAINT uc_tbl_contract_custom_request UNIQUE (custom_request_id);

ALTER TABLE core.tbl_deposit
    ADD CONSTRAINT uc_tbl_deposit_payment UNIQUE (payment_id);

ALTER TABLE core.tbl_diamond
    ADD CONSTRAINT uc_tbl_diamond_gia UNIQUE (gia_id);

ALTER TABLE core.tbl_gia
    ADD CONSTRAINT uc_tbl_gia_gia_document_url UNIQUE (gia_document_url);

ALTER TABLE core.tbl_ring
    ADD CONSTRAINT uc_tbl_ring_diamond UNIQUE (diamond_id);

ALTER TABLE core.tbl_ring
    ADD CONSTRAINT uc_tbl_ring_metal UNIQUE (metal_id);

ALTER TABLE core.tbl_ring
    ADD CONSTRAINT uc_tbl_ring_warranty UNIQUE (warranty_id);

ALTER TABLE core.tbl_spouse
    ADD CONSTRAINT uc_tbl_spouse_customer UNIQUE (customer_id);

ALTER TABLE core.tbl_spouse
    ADD CONSTRAINT uc_tbl_spouse_id_card_image_url UNIQUE (id_card_image_url);

ALTER TABLE core.tbl_spouse
    ADD CONSTRAINT uc_tbl_spouse_id_card_number UNIQUE (id_card_number);

ALTER TABLE core.tbl_spouse
    ADD CONSTRAINT uc_tbl_spouse_verification_request UNIQUE (verification_request_id);

ALTER TABLE core.tbl_transaction
    ADD CONSTRAINT uc_tbl_transaction_order UNIQUE (order_id);

ALTER TABLE core.tbl_transaction
    ADD CONSTRAINT uc_tbl_transaction_payment UNIQUE (payment_id);

ALTER TABLE core.tbl_transportation
    ADD CONSTRAINT uc_tbl_transportation_address UNIQUE (address_id);

ALTER TABLE core.tbl_transportation
    ADD CONSTRAINT uc_tbl_transportation_order UNIQUE (order_id);

ALTER TABLE core.tbl_account
    ADD CONSTRAINT FK_TBL_ACCOUNT_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES core.tbl_address (address_id);

ALTER TABLE core.tbl_account_verification
    ADD CONSTRAINT FK_TBL_ACCOUNT_VERIFICATION_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES core.tbl_account (account_id);

ALTER TABLE core.tbl_agreement
    ADD CONSTRAINT FK_TBL_AGREEMENT_ON_FIRST_RING FOREIGN KEY (first_ring_id) REFERENCES core.tbl_ring (ring_id);

ALTER TABLE core.tbl_agreement
    ADD CONSTRAINT FK_TBL_AGREEMENT_ON_SECOND_RING FOREIGN KEY (second_ring_id) REFERENCES core.tbl_ring (ring_id);

ALTER TABLE core.tbl_contract
    ADD CONSTRAINT FK_TBL_CONTRACT_ON_CUSTOM_REQUEST FOREIGN KEY (custom_request_id) REFERENCES core.tbl_custom_request (custom_request_id);

ALTER TABLE core.tbl_contract
    ADD CONSTRAINT FK_TBL_CONTRACT_ON_DESIGN FOREIGN KEY (design_id) REFERENCES core.tbl_design (design_id);

ALTER TABLE core.tbl_custom_request
    ADD CONSTRAINT FK_TBL_CUSTOM_REQUEST_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES core.tbl_account (account_id);

ALTER TABLE core.tbl_custom_request_tracking
    ADD CONSTRAINT FK_TBL_CUSTOM_REQUEST_TRACKING_ON_CUSTOM_REQUEST FOREIGN KEY (custom_request_id) REFERENCES core.tbl_custom_request (custom_request_id);

ALTER TABLE core.tbl_deposit
    ADD CONSTRAINT FK_TBL_DEPOSIT_ON_CONTRACT FOREIGN KEY (contract_id) REFERENCES core.tbl_contract (contract_id);

ALTER TABLE core.tbl_deposit
    ADD CONSTRAINT FK_TBL_DEPOSIT_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES core.tbl_payment (payment_id);

ALTER TABLE core.tbl_design_finger_size
    ADD CONSTRAINT FK_TBL_DESIGN_FINGER_SIZE_ON_DESIGN FOREIGN KEY (design_id) REFERENCES core.tbl_design (design_id);

ALTER TABLE core.tbl_design_finger_size
    ADD CONSTRAINT FK_TBL_DESIGN_FINGER_SIZE_ON_FINGER_SIZE FOREIGN KEY (finger_size_id) REFERENCES core.tbl_finger_size (fincer_size_id);

ALTER TABLE core.tbl_design
    ADD CONSTRAINT FK_TBL_DESIGN_ON_COLLECTION FOREIGN KEY (collection_id) REFERENCES core.tbl_collection (collection_id);

ALTER TABLE core.tbl_diamond
    ADD CONSTRAINT FK_TBL_DIAMOND_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES core.tbl_diamond_category (diamond_category_id);

ALTER TABLE core.tbl_diamond
    ADD CONSTRAINT FK_TBL_DIAMOND_ON_GIA FOREIGN KEY (gia_id) REFERENCES core.tbl_gia (gia_id);

ALTER TABLE core.tbl_discount_campaign_collection
    ADD CONSTRAINT FK_TBL_DISCOUNT_CAMPAIGN_COLLECTION_ON_COLLECTION FOREIGN KEY (collection_id) REFERENCES core.tbl_collection (collection_id);

ALTER TABLE core.tbl_discount_campaign_collection
    ADD CONSTRAINT FK_TBL_DISCOUNT_CAMPAIGN_COLLECTION_ON_DISCOUNT_CAMPAIGN FOREIGN KEY (discount_campaign_id) REFERENCES core.tbl_discount_campaign (discount_campaign_id);

ALTER TABLE core.tbl_order_ring
    ADD CONSTRAINT FK_TBL_ORDER_RING_ON_ORDER FOREIGN KEY (order_id) REFERENCES core.tbl_order (order_id);

ALTER TABLE core.tbl_order_ring
    ADD CONSTRAINT FK_TBL_ORDER_RING_ON_RING FOREIGN KEY (ring_id) REFERENCES core.tbl_ring (ring_id);

ALTER TABLE core.tbl_order_tracing
    ADD CONSTRAINT FK_TBL_ORDER_TRACING_ON_ORDER FOREIGN KEY (order_id) REFERENCES core.tbl_order (order_id);

ALTER TABLE core.tbl_ring
    ADD CONSTRAINT FK_TBL_RING_ON_DESIGN FOREIGN KEY (design_id) REFERENCES core.tbl_design (design_id);

ALTER TABLE core.tbl_ring
    ADD CONSTRAINT FK_TBL_RING_ON_DIAMOND FOREIGN KEY (diamond_id) REFERENCES core.tbl_diamond (diamond_id);

ALTER TABLE core.tbl_ring
    ADD CONSTRAINT FK_TBL_RING_ON_METAL FOREIGN KEY (metal_id) REFERENCES core.tbl_metal (metal_id);

ALTER TABLE core.tbl_ring
    ADD CONSTRAINT FK_TBL_RING_ON_WARRANTY FOREIGN KEY (warranty_id) REFERENCES core.tbl_warranty (warranty_id);

ALTER TABLE core.tbl_spouse
    ADD CONSTRAINT FK_TBL_SPOUSE_ON_AGREEMENT FOREIGN KEY (agreement_id) REFERENCES core.tbl_agreement (agreement_id);

ALTER TABLE core.tbl_spouse
    ADD CONSTRAINT FK_TBL_SPOUSE_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES core.tbl_account (account_id);

ALTER TABLE core.tbl_spouse
    ADD CONSTRAINT FK_TBL_SPOUSE_ON_VERIFICATION_REQUEST FOREIGN KEY (verification_request_id) REFERENCES core.tbl_spouse_verification_request (spouse_verification_request_id);

ALTER TABLE core.tbl_spouse_verification_request
    ADD CONSTRAINT FK_TBL_SPOUSE_VERIFICATION_REQUEST_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES core.tbl_account (account_id);

ALTER TABLE core.tbl_transaction
    ADD CONSTRAINT FK_TBL_TRANSACTION_ON_ORDER FOREIGN KEY (order_id) REFERENCES core.tbl_order (order_id);

ALTER TABLE core.tbl_transaction
    ADD CONSTRAINT FK_TBL_TRANSACTION_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES core.tbl_payment (payment_id);

ALTER TABLE core.tbl_transportation
    ADD CONSTRAINT FK_TBL_TRANSPORTATION_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES core.tbl_address (address_id);

ALTER TABLE core.tbl_transportation
    ADD CONSTRAINT FK_TBL_TRANSPORTATION_ON_ORDER FOREIGN KEY (order_id) REFERENCES core.tbl_order (order_id);

ALTER TABLE core.tbl_transportation
    ADD CONSTRAINT FK_TBL_TRANSPORTATION_ON_TRANSPORTER FOREIGN KEY (transporter_id) REFERENCES core.tbl_account (account_id);

ALTER TABLE core.tbl_transportation_tracing
    ADD CONSTRAINT FK_TBL_TRANSPORTATION_TRACING_ON_TRANSPORTATION FOREIGN KEY (transportation_id) REFERENCES core.tbl_transportation (transportation_id);