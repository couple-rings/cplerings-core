ALTER TABLE tbl_custom_request
    ADD staff_id BIGINT;

ALTER TABLE tbl_ring
    ADD CONSTRAINT uc_tbl_ring_maintenance_document UNIQUE (maintenance_document_id);

ALTER TABLE tbl_custom_request
    ADD CONSTRAINT FK_TBL_CUSTOM_REQUEST_ON_STAFF FOREIGN KEY (staff_id) REFERENCES tbl_account (account_id);