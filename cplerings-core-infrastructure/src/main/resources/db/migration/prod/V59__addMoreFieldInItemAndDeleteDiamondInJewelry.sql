ALTER TABLE tbl_jewelry
    DROP CONSTRAINT fk_tbl_jewelry_on_diamond;

ALTER TABLE tbl_standard_order_item
    ADD branch_id BIGINT;

ALTER TABLE tbl_standard_order_item
    ADD design_id BIGINT;

ALTER TABLE tbl_standard_order_item
    ADD metal_spec_id BIGINT;

ALTER TABLE tbl_standard_order_item
    ALTER COLUMN branch_id SET NOT NULL;

ALTER TABLE tbl_standard_order_item
    ALTER COLUMN design_id SET NOT NULL;

ALTER TABLE tbl_standard_order_item
    ALTER COLUMN metal_spec_id SET NOT NULL;

ALTER TABLE tbl_standard_order_item
    ADD CONSTRAINT FK_TBL_STANDARD_ORDER_ITEM_ON_BRANCH FOREIGN KEY (branch_id) REFERENCES tbl_branch (branch_id);

ALTER TABLE tbl_standard_order_item
    ADD CONSTRAINT FK_TBL_STANDARD_ORDER_ITEM_ON_DESIGN FOREIGN KEY (design_id) REFERENCES tbl_design (design_id);

ALTER TABLE tbl_standard_order_item
    ADD CONSTRAINT FK_TBL_STANDARD_ORDER_ITEM_ON_METAL_SPEC FOREIGN KEY (metal_spec_id) REFERENCES tbl_metal_specification (metal_specification_id);

ALTER TABLE tbl_jewelry
    DROP COLUMN diamond_id;

ALTER TABLE tbl_standard_order_item
    ALTER COLUMN jewelry_id DROP NOT NULL;