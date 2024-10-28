CREATE SEQUENCE IF NOT EXISTS custom_request_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_custom_request
(
    custom_request_id BIGINT                      NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by         VARCHAR(255)                NOT NULL,
    modified_at       TIMESTAMP WITHOUT TIME ZONE,
    modified_by       VARCHAR(255),
    state             VARCHAR(12)                 NOT NULL,
    opt_version       INTEGER                     NOT NULL,
    comment           VARCHAR(500),
    status            VARCHAR(12)                 NOT NULL,
    customer_id       BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_custom_request PRIMARY KEY (custom_request_id)
);

ALTER TABLE tbl_custom_request
    ADD CONSTRAINT FK_TBL_CUSTOM_REQUEST_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES tbl_account (account_id);