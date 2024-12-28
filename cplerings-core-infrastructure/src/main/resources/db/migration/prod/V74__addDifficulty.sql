ALTER TABLE tbl_crafting_request
    ADD difficulty VARCHAR(12);

ALTER TABLE tbl_ring
    ADD difficulty VARCHAR(12);

ALTER TABLE tbl_ring
    ALTER COLUMN difficulty SET NOT NULL;