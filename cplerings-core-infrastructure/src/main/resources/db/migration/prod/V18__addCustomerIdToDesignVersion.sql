ALTER TABLE tbl_design_version
    ADD customer_id BIGINT;

ALTER TABLE tbl_design_version
    ALTER COLUMN customer_id SET NOT NULL;

ALTER TABLE tbl_design_version
    ADD CONSTRAINT FK_TBL_DESIGN_VERSION_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_design_version
    ADD CONSTRAINT FK_TBL_DESIGN_VERSION_ON_IMAGE FOREIGN KEY (image_id) REFERENCES tbl_image (image_id);