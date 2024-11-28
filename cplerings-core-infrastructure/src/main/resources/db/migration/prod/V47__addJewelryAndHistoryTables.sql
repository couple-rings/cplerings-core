CREATE SEQUENCE IF NOT EXISTS crafting_request_history_sequence START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS custom_order_history_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS custom_request_history_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS jewelry_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS standard_order_history_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS standard_order_item_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS standard_order_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS transport_order_history_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_crafting_request_history
(
    crafting_request_history_id BIGINT                      NOT NULL,
    created_at                  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                   VARCHAR(255)                NOT NULL,
    modified_at                 TIMESTAMP WITHOUT TIME ZONE,
    modified_by                 VARCHAR(255),
    state                       VARCHAR(12)                 NOT NULL,
    opt_version                 INTEGER                     NOT NULL,
    status                      VARCHAR(12)                 NOT NULL,
    CONSTRAINT pk_tbl_crafting_request_history PRIMARY KEY (crafting_request_history_id)
);

CREATE TABLE tbl_custom_order_history
(
    custom_order_history_id BIGINT                      NOT NULL,
    created_at              TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by               VARCHAR(255)                NOT NULL,
    modified_at             TIMESTAMP WITHOUT TIME ZONE,
    modified_by             VARCHAR(255),
    state                   VARCHAR(12)                 NOT NULL,
    opt_version             INTEGER                     NOT NULL,
    status                  VARCHAR(12)                 NOT NULL,
    CONSTRAINT pk_tbl_custom_order_history PRIMARY KEY (custom_order_history_id)
);

CREATE TABLE tbl_custom_request_history
(
    custom_request_history_id BIGINT                      NOT NULL,
    created_at                TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                 VARCHAR(255)                NOT NULL,
    modified_at               TIMESTAMP WITHOUT TIME ZONE,
    modified_by               VARCHAR(255),
    state                     VARCHAR(12)                 NOT NULL,
    opt_version               INTEGER                     NOT NULL,
    status                    VARCHAR(12)                 NOT NULL,
    CONSTRAINT pk_tbl_custom_request_history PRIMARY KEY (custom_request_history_id)
);

CREATE TABLE tbl_jewelry
(
    jewelry_id               BIGINT                      NOT NULL,
    created_at               TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                VARCHAR(255)                NOT NULL,
    modified_at              TIMESTAMP WITHOUT TIME ZONE,
    modified_by              VARCHAR(255),
    state                    VARCHAR(12)                 NOT NULL,
    opt_version              INTEGER                     NOT NULL,
    diamond_id               BIGINT                      NOT NULL,
    metal_specification_id   BIGINT                      NOT NULL,
    design_id                BIGINT                      NOT NULL,
    branch_id                BIGINT                      NOT NULL,
    purchase_date            TIMESTAMP WITHOUT TIME ZONE,
    status                   VARCHAR(12)                 NOT NULL,
    maintenance_document_id  BIGINT,
    maintenance_expired_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_tbl_jewelry PRIMARY KEY (jewelry_id)
);

CREATE TABLE tbl_standard_order
(
    standard_order_id BIGINT                      NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by         VARCHAR(255)                NOT NULL,
    modified_at       TIMESTAMP WITHOUT TIME ZONE,
    modified_by       VARCHAR(255),
    state             VARCHAR(12)                 NOT NULL,
    opt_version       INTEGER                     NOT NULL,
    customer_id       BIGINT                      NOT NULL,
    status            VARCHAR(12)                 NOT NULL,
    total_price       DECIMAL(12, 3)              NOT NULL,
    CONSTRAINT pk_tbl_standard_order PRIMARY KEY (standard_order_id)
);

CREATE TABLE tbl_standard_order_history
(
    standard_order_history_id BIGINT                      NOT NULL,
    created_at                TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                 VARCHAR(255)                NOT NULL,
    modified_at               TIMESTAMP WITHOUT TIME ZONE,
    modified_by               VARCHAR(255),
    state                     VARCHAR(12)                 NOT NULL,
    opt_version               INTEGER                     NOT NULL,
    status                    VARCHAR(12)                 NOT NULL,
    CONSTRAINT pk_tbl_standard_order_history PRIMARY KEY (standard_order_history_id)
);

CREATE TABLE tbl_standard_order_item
(
    standard_order_item_id BIGINT                      NOT NULL,
    created_at             TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by              VARCHAR(255)                NOT NULL,
    modified_at            TIMESTAMP WITHOUT TIME ZONE,
    modified_by            VARCHAR(255),
    state                  VARCHAR(12)                 NOT NULL,
    opt_version            INTEGER                     NOT NULL,
    standard_order_id      BIGINT                      NOT NULL,
    jewelry_id             BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_standard_order_item PRIMARY KEY (standard_order_item_id)
);

CREATE TABLE tbl_transport_order_history
(
    transport_order_history_id BIGINT                      NOT NULL,
    created_at                 TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                  VARCHAR(255)                NOT NULL,
    modified_at                TIMESTAMP WITHOUT TIME ZONE,
    modified_by                VARCHAR(255),
    state                      VARCHAR(12)                 NOT NULL,
    opt_version                INTEGER                     NOT NULL,
    status                     VARCHAR(12)                 NOT NULL,
    CONSTRAINT pk_tbl_transport_order_history PRIMARY KEY (transport_order_history_id)
);

ALTER TABLE tbl_jewelry
    ADD CONSTRAINT uc_tbl_jewelry_maintenance_document UNIQUE (maintenance_document_id);

ALTER TABLE tbl_jewelry
    ADD CONSTRAINT FK_TBL_JEWELRY_ON_BRANCH FOREIGN KEY (branch_id) REFERENCES tbl_branch (branch_id);

ALTER TABLE tbl_jewelry
    ADD CONSTRAINT FK_TBL_JEWELRY_ON_DESIGN FOREIGN KEY (design_id) REFERENCES tbl_design (design_id);

ALTER TABLE tbl_jewelry
    ADD CONSTRAINT FK_TBL_JEWELRY_ON_DIAMOND FOREIGN KEY (diamond_id) REFERENCES tbl_diamond (diamond_id);

ALTER TABLE tbl_jewelry
    ADD CONSTRAINT FK_TBL_JEWELRY_ON_MAINTENANCE_DOCUMENT FOREIGN KEY (maintenance_document_id) REFERENCES tbl_document (document_id);

ALTER TABLE tbl_jewelry
    ADD CONSTRAINT FK_TBL_JEWELRY_ON_METAL_SPECIFICATION FOREIGN KEY (metal_specification_id) REFERENCES tbl_metal_specification (metal_specification_id);

ALTER TABLE tbl_standard_order_item
    ADD CONSTRAINT FK_TBL_STANDARD_ORDER_ITEM_ON_JEWELRY FOREIGN KEY (jewelry_id) REFERENCES tbl_jewelry (jewelry_id);

ALTER TABLE tbl_standard_order_item
    ADD CONSTRAINT FK_TBL_STANDARD_ORDER_ITEM_ON_STANDARD_ORDER FOREIGN KEY (standard_order_id) REFERENCES tbl_standard_order (standard_order_id);

ALTER TABLE tbl_standard_order
    ADD CONSTRAINT FK_TBL_STANDARD_ORDER_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES tbl_account (account_id);