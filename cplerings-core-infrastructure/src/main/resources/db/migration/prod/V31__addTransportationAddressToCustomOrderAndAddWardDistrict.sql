ALTER TABLE tbl_transportation_address
    ADD district VARCHAR(255);

ALTER TABLE tbl_transportation_address
    ADD ward VARCHAR(255);

ALTER TABLE tbl_transportation_address
    ALTER COLUMN district SET NOT NULL;

ALTER TABLE tbl_custom_order
    ADD transportation_address_id BIGINT;

ALTER TABLE tbl_transportation_address
    ALTER COLUMN ward SET NOT NULL;

ALTER TABLE tbl_custom_order
    ADD CONSTRAINT uc_tbl_custom_order_transportation_address UNIQUE (transportation_address_id);

ALTER TABLE tbl_custom_order
    ADD CONSTRAINT FK_TBL_CUSTOM_ORDER_ON_TRANSPORTATION_ADDRESS FOREIGN KEY (transportation_address_id) REFERENCES tbl_transportation_address (transportation_address_id);