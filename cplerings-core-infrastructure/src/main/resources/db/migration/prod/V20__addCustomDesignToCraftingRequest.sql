ALTER TABLE tbl_crafting_request
    ADD custom_design_id BIGINT;

ALTER TABLE tbl_crafting_request
    ALTER COLUMN custom_design_id SET NOT NULL;

ALTER TABLE tbl_crafting_request
    ADD CONSTRAINT FK_TBL_CRAFTING_REQUEST_ON_CUSTOM_DESIGN FOREIGN KEY (custom_design_id) REFERENCES tbl_custom_design (custom_design_id);