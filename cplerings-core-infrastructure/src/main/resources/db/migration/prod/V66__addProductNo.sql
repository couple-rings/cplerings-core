ALTER TABLE tbl_jewelry
    ADD product_no VARCHAR(8);

ALTER TABLE tbl_jewelry
    ALTER COLUMN product_no SET NOT NULL;

ALTER TABLE tbl_ring
    ADD product_no VARCHAR(8);

ALTER TABLE tbl_ring
    ALTER COLUMN product_no SET NOT NULL;