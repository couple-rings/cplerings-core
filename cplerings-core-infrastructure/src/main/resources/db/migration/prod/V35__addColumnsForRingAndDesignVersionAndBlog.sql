ALTER TABLE tbl_ring
    ADD engraving VARCHAR(15);

ALTER TABLE tbl_design_version
    ADD owner VARCHAR(12);

ALTER TABLE tbl_blog
    ADD summary VARCHAR(255);

ALTER TABLE tbl_blog
    ALTER COLUMN summary SET NOT NULL;