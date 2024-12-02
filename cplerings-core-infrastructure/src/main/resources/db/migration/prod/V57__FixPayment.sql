ALTER TABLE tbl_payment_receiver
    DROP CONSTRAINT fk_tbl_payment_receiver_on_payment;

CREATE SEQUENCE IF NOT EXISTS design_session_payment_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_design_session_payment
(
    design_session_payment_id BIGINT                      NOT NULL,
    created_at                TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                 VARCHAR(255)                NOT NULL,
    modified_at               TIMESTAMP WITHOUT TIME ZONE,
    modified_by               VARCHAR(255),
    state                     VARCHAR(12)                 NOT NULL,
    opt_version               INTEGER                     NOT NULL,
    design_session_id         UUID                        NOT NULL,
    customer_id               BIGINT                      NOT NULL,
    payment_id                BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_design_session_payment PRIMARY KEY (design_session_payment_id)
);

ALTER TABLE tbl_crafting_stage
    ADD payment_id BIGINT;

ALTER TABLE tbl_crafting_stage
    ALTER COLUMN payment_id SET NOT NULL;

ALTER TABLE tbl_custom_request
    ADD payment_id BIGINT;

ALTER TABLE tbl_custom_request
    ALTER COLUMN payment_id SET NOT NULL;

ALTER TABLE tbl_payment
    ADD payment_receiver_type VARCHAR(12);

ALTER TABLE tbl_payment
    ALTER COLUMN payment_receiver_type SET NOT NULL;

ALTER TABLE tbl_crafting_stage
    ADD CONSTRAINT uc_tbl_crafting_stage_payment UNIQUE (payment_id);

ALTER TABLE tbl_custom_request
    ADD CONSTRAINT uc_tbl_custom_request_payment UNIQUE (payment_id);

ALTER TABLE tbl_design_session_payment
    ADD CONSTRAINT uc_tbl_design_session_payment_payment UNIQUE (payment_id);

ALTER TABLE tbl_crafting_stage
    ADD CONSTRAINT FK_TBL_CRAFTING_STAGE_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES tbl_payment (payment_id);

ALTER TABLE tbl_custom_request
    ADD CONSTRAINT FK_TBL_CUSTOM_REQUEST_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES tbl_payment (payment_id);

ALTER TABLE tbl_design_session_payment
    ADD CONSTRAINT FK_TBL_DESIGN_SESSION_PAYMENT_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_design_session_payment
    ADD CONSTRAINT FK_TBL_DESIGN_SESSION_PAYMENT_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES tbl_payment (payment_id);

DROP TABLE tbl_payment_receiver CASCADE;

DROP SEQUENCE payment_receiver_seq CASCADE;