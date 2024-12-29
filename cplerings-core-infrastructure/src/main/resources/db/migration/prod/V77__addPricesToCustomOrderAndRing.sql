ALTER TABLE tbl_crafting_stage
    ADD amount DECIMAL(12, 3);

ALTER TABLE tbl_crafting_stage
    ALTER COLUMN amount SET NOT NULL;

ALTER TABLE tbl_ring
    ADD crafting_fee DECIMAL(12, 3);

ALTER TABLE tbl_ring
    ADD diamond_price DECIMAL(12, 3);

ALTER TABLE tbl_ring
    ADD metal_price_per_unit DECIMAL(12, 3);

ALTER TABLE tbl_ring
    ADD side_diamond_price DECIMAL(12, 3);

ALTER TABLE tbl_ring
    ALTER COLUMN crafting_fee SET NOT NULL;

ALTER TABLE tbl_ring
    ALTER COLUMN diamond_price SET NOT NULL;

ALTER TABLE tbl_ring
    ALTER COLUMN metal_price_per_unit SET NOT NULL;

ALTER TABLE tbl_custom_order
    ADD shipping_fee DECIMAL(12, 3);

ALTER TABLE tbl_custom_order
    ALTER COLUMN shipping_fee SET NOT NULL;

ALTER TABLE tbl_ring
    ALTER COLUMN side_diamond_price SET NOT NULL;