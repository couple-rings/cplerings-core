ALTER TABLE tbl_resell_order
    ADD custom_order_id BIGINT;

ALTER TABLE tbl_resell_order
    ADD CONSTRAINT uc_tbl_resell_order_custom_order UNIQUE (custom_order_id);

ALTER TABLE tbl_resell_order
    ADD CONSTRAINT FK_TBL_RESELL_ORDER_ON_CUSTOM_ORDER FOREIGN KEY (custom_order_id) REFERENCES tbl_custom_order (custom_order_id);

ALTER TABLE tbl_resell_order
    ALTER COLUMN jewelry_id DROP NOT NULL;