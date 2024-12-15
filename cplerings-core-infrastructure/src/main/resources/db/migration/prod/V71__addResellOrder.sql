CREATE SEQUENCE IF NOT EXISTS resell_order_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_resell_order
(
    resell_order_id BIGINT                      NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by       VARCHAR(255)                NOT NULL,
    modified_at     TIMESTAMP WITHOUT TIME ZONE,
    modified_by     VARCHAR(255),
    state           VARCHAR(12)                 NOT NULL,
    opt_version     INTEGER                     NOT NULL,
    jewelry_id      BIGINT                      NOT NULL,
    customer_id     BIGINT                      NOT NULL,
    staff_id        BIGINT                      NOT NULL,
    payment_method  VARCHAR(12)                 NOT NULL,
    proof_image_id  BIGINT                      NOT NULL,
    note            VARCHAR(500)                NOT NULL,
    amount          DECIMAL(12, 3)              NOT NULL,
    CONSTRAINT pk_tbl_resell_order PRIMARY KEY (resell_order_id)
);

ALTER TABLE tbl_account
    ADD CONSTRAINT uc_tbl_account_avatar UNIQUE (avatar_id);

ALTER TABLE tbl_resell_order
    ADD CONSTRAINT FK_TBL_RESELL_ORDER_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_resell_order
    ADD CONSTRAINT FK_TBL_RESELL_ORDER_ON_JEWELRY FOREIGN KEY (jewelry_id) REFERENCES tbl_jewelry (jewelry_id);

ALTER TABLE tbl_resell_order
    ADD CONSTRAINT FK_TBL_RESELL_ORDER_ON_PROOF_IMAGE FOREIGN KEY (proof_image_id) REFERENCES tbl_image (image_id);

ALTER TABLE tbl_resell_order
    ADD CONSTRAINT FK_TBL_RESELL_ORDER_ON_STAFF FOREIGN KEY (staff_id) REFERENCES tbl_account (account_id);