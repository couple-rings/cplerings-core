ALTER TABLE tbl_transport_order
    ADD image_id BIGINT;

ALTER TABLE tbl_transport_order
    ADD CONSTRAINT uc_tbl_transport_order_image UNIQUE (image_id);

ALTER TABLE tbl_transport_order
    ADD CONSTRAINT FK_TBL_TRANSPORT_ORDER_ON_IMAGE FOREIGN KEY (image_id) REFERENCES tbl_image (image_id);