ALTER TABLE tbl_standard_order
    ADD payment_id BIGINT;

ALTER TABLE tbl_standard_order
    ALTER COLUMN payment_id SET NOT NULL;

ALTER TABLE tbl_standard_order
    ADD CONSTRAINT FK_TBL_STANDARD_ORDER_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES tbl_payment (payment_id);