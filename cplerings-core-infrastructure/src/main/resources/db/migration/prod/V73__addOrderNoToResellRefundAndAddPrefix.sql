ALTER TABLE tbl_refund
    ADD order_no VARCHAR(10);

ALTER TABLE tbl_refund
    ALTER COLUMN order_no SET NOT NULL;

ALTER TABLE tbl_resell_order
    ADD order_no VARCHAR(10);

ALTER TABLE tbl_resell_order
    ALTER COLUMN order_no SET NOT NULL;

ALTER TABLE tbl_custom_order
    ALTER COLUMN order_no TYPE VARCHAR(10) USING (order_no::VARCHAR(10));

ALTER TABLE tbl_standard_order
    ALTER COLUMN order_no TYPE VARCHAR(10) USING (order_no::VARCHAR(10));

ALTER TABLE tbl_transport_order
    ALTER COLUMN order_no TYPE VARCHAR(10) USING (order_no::VARCHAR(10));