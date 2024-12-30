ALTER TABLE tbl_custom_order
    ADD price_application_ratio DECIMAL(4, 2);

ALTER TABLE tbl_custom_order
    ALTER COLUMN price_application_ratio SET NOT NULL;