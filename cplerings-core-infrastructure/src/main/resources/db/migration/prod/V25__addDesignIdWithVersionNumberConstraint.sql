ALTER TABLE tbl_design_version
    ADD CONSTRAINT uc_tbl_design_version_design_id_version_number UNIQUE (design_id, version_number);