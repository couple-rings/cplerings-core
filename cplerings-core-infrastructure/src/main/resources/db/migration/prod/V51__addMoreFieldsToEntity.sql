ALTER TABLE tbl_design_version
    ADD accepted_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE tbl_custom_request
    ADD design_fee DECIMAL(12, 3);

ALTER TABLE tbl_custom_request
    ALTER COLUMN design_fee SET NOT NULL;

ALTER TABLE tbl_standard_order
    ADD order_no VARCHAR(8);

ALTER TABLE tbl_standard_order
    ALTER COLUMN order_no SET NOT NULL;