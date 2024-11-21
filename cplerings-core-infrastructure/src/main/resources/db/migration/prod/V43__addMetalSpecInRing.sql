ALTER TABLE tbl_ring
    ADD metal_specification_id BIGINT;

ALTER TABLE tbl_ring
    ALTER COLUMN metal_specification_id SET NOT NULL;

ALTER TABLE tbl_ring
    ADD CONSTRAINT FK_TBL_RING_ON_METAL_SPECIFICATION FOREIGN KEY (metal_specification_id) REFERENCES tbl_metal_specification (metal_specification_id);