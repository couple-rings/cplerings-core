ALTER TABLE tbl_diamond_specification
    ALTER COLUMN clarity TYPE VARCHAR(12) USING (clarity::VARCHAR(12));

ALTER TABLE tbl_diamond_specification
    ALTER COLUMN color TYPE VARCHAR(12) USING (color::VARCHAR(12));

ALTER TABLE tbl_diamond_specification
    ALTER COLUMN price TYPE DECIMAL USING (price::DECIMAL);

ALTER TABLE tbl_diamond_specification
    ALTER COLUMN shape TYPE VARCHAR(12) USING (shape::VARCHAR(12));

ALTER TABLE tbl_diamond_specification
    DROP COLUMN weight;

ALTER TABLE tbl_diamond_specification
    ADD weight DECIMAL(10, 2);

UPDATE tbl_diamond_specification
SET weight = 0.0
WHERE weight IS NULL;

ALTER TABLE tbl_diamond_specification
    ALTER COLUMN weight SET NOT NULL;