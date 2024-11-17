ALTER TABLE tbl_ring
    ADD custom_design_id BIGINT;

ALTER TABLE tbl_ring
    ALTER COLUMN custom_design_id SET NOT NULL;

ALTER TABLE tbl_ring
    ADD CONSTRAINT uc_tbl_ring_custom_design UNIQUE (custom_design_id);

ALTER TABLE tbl_ring
    ADD CONSTRAINT FK_TBL_RING_ON_CUSTOM_DESIGN FOREIGN KEY (custom_design_id) REFERENCES tbl_custom_design (custom_design_id);