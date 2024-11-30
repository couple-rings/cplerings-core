ALTER TABLE tbl_ring
    ADD total_price DECIMAL(12, 3);

ALTER TABLE tbl_ring
    ALTER COLUMN total_price SET NOT NULL;