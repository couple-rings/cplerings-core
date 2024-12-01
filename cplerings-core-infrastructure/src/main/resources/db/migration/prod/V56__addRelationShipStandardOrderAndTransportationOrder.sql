ALTER TABLE tbl_transport_order
    ADD standard_order_id BIGINT;

ALTER TABLE tbl_standard_order
    ADD transportation_address_id BIGINT;

ALTER TABLE tbl_standard_order
    ADD CONSTRAINT uc_tbl_standard_order_transportation_address UNIQUE (transportation_address_id);

ALTER TABLE tbl_standard_order
    ADD CONSTRAINT FK_TBL_STANDARD_ORDER_ON_TRANSPORTATION_ADDRESS FOREIGN KEY (transportation_address_id) REFERENCES tbl_transportation_address (transportation_address_id);

ALTER TABLE tbl_transport_order
    ADD CONSTRAINT FK_TBL_TRANSPORT_ORDER_ON_STANDARD_ORDER FOREIGN KEY (standard_order_id) REFERENCES tbl_standard_order (standard_order_id);

ALTER TABLE tbl_transport_order
    ALTER COLUMN custom_order_id DROP NOT NULL;