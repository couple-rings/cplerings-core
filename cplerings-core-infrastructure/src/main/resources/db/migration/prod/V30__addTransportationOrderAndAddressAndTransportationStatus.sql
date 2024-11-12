CREATE SEQUENCE IF NOT EXISTS transport_order_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS transport_status_order_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS transportation_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_transport_order
(
    transport_order_id BIGINT                      NOT NULL,
    created_at         TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by          VARCHAR(255)                NOT NULL,
    modified_at        TIMESTAMP WITHOUT TIME ZONE,
    modified_by        VARCHAR(255),
    state              VARCHAR(12)                 NOT NULL,
    opt_version        INTEGER                     NOT NULL,
    status             VARCHAR(12)                 NOT NULL,
    receiver_name      VARCHAR(255)                NOT NULL,
    receiver_phone     VARCHAR(255)                NOT NULL,
    delivery_address   VARCHAR(255)                NOT NULL,
    custom_order_id    BIGINT                      NOT NULL,
    transporter_id     BIGINT,
    CONSTRAINT pk_tbl_transport_order PRIMARY KEY (transport_order_id)
);

CREATE TABLE tbl_transportation_address
(
    transportation_address_id BIGINT                      NOT NULL,
    created_at                TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                 VARCHAR(255)                NOT NULL,
    modified_at               TIMESTAMP WITHOUT TIME ZONE,
    modified_by               VARCHAR(255),
    state                     VARCHAR(12)                 NOT NULL,
    opt_version               INTEGER                     NOT NULL,
    address                   VARCHAR(255)                NOT NULL,
    district_code             VARCHAR(255)                NOT NULL,
    ward_code                 VARCHAR(255)                NOT NULL,
    receiver_name             VARCHAR(255)                NOT NULL,
    receiver_phone            VARCHAR(255)                NOT NULL,
    customer_id               BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_transportation_address PRIMARY KEY (transportation_address_id)
);

CREATE TABLE tbl_transportation_status
(
    transportation_status_id BIGINT                      NOT NULL,
    created_at               TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                VARCHAR(255)                NOT NULL,
    modified_at              TIMESTAMP WITHOUT TIME ZONE,
    modified_by              VARCHAR(255),
    state                    VARCHAR(12)                 NOT NULL,
    opt_version              INTEGER                     NOT NULL,
    date                     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    note                     VARCHAR(255),
    transport_order_id       BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_transportation_status PRIMARY KEY (transportation_status_id)
);

ALTER TABLE tbl_transportation_address
    ADD CONSTRAINT FK_TBL_TRANSPORTATION_ADDRESS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_transportation_status
    ADD CONSTRAINT FK_TBL_TRANSPORTATION_STATUS_ON_TRANSPORT_ORDER FOREIGN KEY (transport_order_id) REFERENCES tbl_transport_order (transport_order_id);

ALTER TABLE tbl_transport_order
    ADD CONSTRAINT FK_TBL_TRANSPORT_ORDER_ON_CUSTOM_ORDER FOREIGN KEY (custom_order_id) REFERENCES tbl_custom_order (custom_order_id);

ALTER TABLE tbl_transport_order
    ADD CONSTRAINT FK_TBL_TRANSPORT_ORDER_ON_TRANSPORTER FOREIGN KEY (transporter_id) REFERENCES tbl_account (account_id);