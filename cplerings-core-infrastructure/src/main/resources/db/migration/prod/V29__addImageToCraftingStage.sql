ALTER TABLE tbl_crafting_stage
    ADD image BIGINT;

ALTER TABLE tbl_crafting_stage
    ADD CONSTRAINT uc_tbl_crafting_stage_image UNIQUE (image);

ALTER TABLE tbl_crafting_stage
    ADD CONSTRAINT FK_TBL_CRAFTING_STAGE_ON_IMAGE FOREIGN KEY (image) REFERENCES tbl_image (image_id);