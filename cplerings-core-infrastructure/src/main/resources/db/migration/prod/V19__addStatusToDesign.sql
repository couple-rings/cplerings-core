ALTER TABLE tbl_design
    ADD status VARCHAR(12);

UPDATE tbl_design
SET status = 'AVAILABLE'
WHERE status IS NULL;

ALTER TABLE tbl_design
    ALTER COLUMN status SET NOT NULL;