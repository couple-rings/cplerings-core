ALTER TABLE tbl_crafting_request
    ADD branch_id BIGINT;

ALTER TABLE tbl_crafting_request
    ALTER COLUMN branch_id SET NOT NULL;

ALTER TABLE tbl_crafting_request
    ADD CONSTRAINT FK_TBL_CRAFTING_REQUEST_ON_BRANCH FOREIGN KEY (branch_id) REFERENCES tbl_branch (branch_id);