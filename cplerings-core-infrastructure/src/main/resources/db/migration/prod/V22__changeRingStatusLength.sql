ALTER TABLE tbl_ring
    ALTER COLUMN status TYPE VARCHAR(20) USING (status::VARCHAR(20));

ALTER TABLE tbl_ring
    DROP CONSTRAINT uc_tbl_ring_maintenance_document