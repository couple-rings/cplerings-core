CREATE SEQUENCE IF NOT EXISTS refund_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_refund
(
    refund_id         BIGINT                      NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by         VARCHAR(255)                NOT NULL,
    modified_at       TIMESTAMP WITHOUT TIME ZONE,
    modified_by       VARCHAR(255),
    state             VARCHAR(12)                 NOT NULL,
    opt_version       INTEGER                     NOT NULL,
    reason            VARCHAR(255)                NOT NULL,
    method            SMALLINT                    NOT NULL,
    staff_id          BIGINT                      NOT NULL,
    image_id          BIGINT                      NOT NULL,
    standard_order_id BIGINT,
    custom_order_id   BIGINT,
    price             DECIMAL(12, 3)              NOT NULL,
    CONSTRAINT pk_tbl_refund PRIMARY KEY (refund_id)
);

ALTER TABLE tbl_refund
    ADD CONSTRAINT uc_tbl_refund_custom_order UNIQUE (custom_order_id);

ALTER TABLE tbl_refund
    ADD CONSTRAINT uc_tbl_refund_image UNIQUE (image_id);

ALTER TABLE tbl_refund
    ADD CONSTRAINT uc_tbl_refund_staff UNIQUE (staff_id);

ALTER TABLE tbl_refund
    ADD CONSTRAINT uc_tbl_refund_standard_order UNIQUE (standard_order_id);

ALTER TABLE tbl_refund
    ADD CONSTRAINT FK_TBL_REFUND_ON_CUSTOM_ORDER FOREIGN KEY (custom_order_id) REFERENCES tbl_custom_order (custom_order_id);

ALTER TABLE tbl_refund
    ADD CONSTRAINT FK_TBL_REFUND_ON_IMAGE FOREIGN KEY (image_id) REFERENCES tbl_image (image_id);

ALTER TABLE tbl_refund
    ADD CONSTRAINT FK_TBL_REFUND_ON_STAFF FOREIGN KEY (staff_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_refund
    ADD CONSTRAINT FK_TBL_REFUND_ON_STANDARD_ORDER FOREIGN KEY (standard_order_id) REFERENCES tbl_standard_order (standard_order_id);