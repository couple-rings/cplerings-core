ALTER TABLE tbl_refund
    ADD payment_id BIGINT;

ALTER TABLE tbl_refund
    ALTER COLUMN payment_id SET NOT NULL;

ALTER TABLE tbl_resell_order
    ADD payment_id BIGINT;

ALTER TABLE tbl_resell_order
    ALTER COLUMN payment_id SET NOT NULL;

ALTER TABLE tbl_payment
    ADD payment_no VARCHAR(10);

ALTER TABLE tbl_payment
    ALTER COLUMN payment_no SET NOT NULL;

ALTER TABLE tbl_refund
    ADD CONSTRAINT FK_TBL_REFUND_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES tbl_payment (payment_id);

ALTER TABLE tbl_resell_order
    ADD CONSTRAINT FK_TBL_RESELL_ORDER_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES tbl_payment (payment_id);

ALTER TABLE tbl_payment
    ALTER COLUMN secure_hash DROP NOT NULL;