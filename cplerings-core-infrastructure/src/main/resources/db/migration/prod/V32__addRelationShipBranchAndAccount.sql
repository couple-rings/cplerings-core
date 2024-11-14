ALTER TABLE tbl_account
    ADD branch_id BIGINT;

ALTER TABLE tbl_account
    ADD CONSTRAINT FK_TBL_ACCOUNT_ON_BRANCH FOREIGN KEY (branch_id) REFERENCES tbl_branch (branch_id);