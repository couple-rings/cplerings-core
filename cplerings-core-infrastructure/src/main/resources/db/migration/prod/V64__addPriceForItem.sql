ALTER TABLE tbl_standard_order_item
    ADD price DECIMAL(12, 3);

ALTER TABLE tbl_standard_order_item
    ALTER COLUMN price SET NOT NULL;