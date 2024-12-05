ALTER TABLE tbl_standard_order
    ADD CONSTRAINT uc_tbl_standard_order_payment UNIQUE (payment_id);

ALTER TABLE tbl_standard_order
    ALTER COLUMN payment_id DROP NOT NULL;