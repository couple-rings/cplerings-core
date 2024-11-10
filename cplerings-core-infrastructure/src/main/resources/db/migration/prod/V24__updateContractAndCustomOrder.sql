ALTER TABLE tbl_ring
    DROP CONSTRAINT fk_tbl_ring_on_custom_order;

ALTER TABLE tbl_custom_order
    ADD total_price DECIMAL(12, 3);

UPDATE tbl_custom_order
SET total_price = amount;

ALTER TABLE tbl_custom_order
    ALTER COLUMN total_price SET NOT NULL;

ALTER TABLE tbl_branch
    ADD CONSTRAINT uc_tbl_branch_cover_image UNIQUE (cover_image);

ALTER TABLE tbl_contract
    ADD CONSTRAINT uc_tbl_contract_signature UNIQUE (signature);

ALTER TABLE tbl_ring
    ADD CONSTRAINT uc_tbl_ring_maintenance_document UNIQUE (maintenance_document_id);

ALTER TABLE tbl_custom_order
    DROP COLUMN amount;

ALTER TABLE tbl_ring
    DROP COLUMN custom_order_id;

ALTER TABLE tbl_branch
    DROP COLUMN cover_image;

ALTER TABLE tbl_branch
    ADD cover_image BIGINT;

ALTER TABLE tbl_branch
    ADD CONSTRAINT uc_tbl_branch_cover_image UNIQUE (cover_image);

ALTER TABLE tbl_branch
    ADD CONSTRAINT FK_TBL_BRANCH_ON_COVER_IMAGE FOREIGN KEY (cover_image) REFERENCES tbl_image (image_id);

ALTER TABLE tbl_branch
    ALTER COLUMN cover_image DROP NOT NULL;

ALTER TABLE tbl_ring
    ALTER COLUMN maintenance_document_id DROP NOT NULL;

ALTER TABLE tbl_ring
    ALTER COLUMN maintenance_expired_date DROP NOT NULL;

ALTER TABLE tbl_ring
    ALTER COLUMN purchase_date DROP NOT NULL;

ALTER TABLE tbl_contract
    DROP COLUMN signature;

ALTER TABLE tbl_contract
    ADD signature BIGINT;

ALTER TABLE tbl_contract
    ADD CONSTRAINT uc_tbl_contract_signature UNIQUE (signature);

ALTER TABLE tbl_contract
    ADD CONSTRAINT FK_TBL_CONTRACT_ON_SIGNATURE FOREIGN KEY (signature) REFERENCES tbl_image (image_id);