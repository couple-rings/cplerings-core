ALTER TABLE tbl_design
    DROP CONSTRAINT fk_tbl_design_on_blueprint;

ALTER TABLE tbl_diamond
    DROP CONSTRAINT fk_tbl_diamond_on_gia_document;

ALTER TABLE tbl_ring
    DROP CONSTRAINT fk_tbl_ring_on_maintenance_document;

CREATE TABLE tbl_document
(
    document_id BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(255)                NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(255),
    state       VARCHAR(12)                 NOT NULL,
    opt_version INTEGER                     NOT NULL,
    url         VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_tbl_document PRIMARY KEY (document_id)
);

ALTER TABLE tbl_document
    ADD CONSTRAINT uc_tbl_document_url UNIQUE (url);

ALTER TABLE tbl_design
    ADD CONSTRAINT FK_TBL_DESIGN_ON_BLUEPRINT FOREIGN KEY (blueprint_id) REFERENCES tbl_document (document_id);

ALTER TABLE tbl_diamond
    ADD CONSTRAINT FK_TBL_DIAMOND_ON_GIA_DOCUMENT FOREIGN KEY (gia_document_id) REFERENCES tbl_document (document_id);

ALTER TABLE tbl_ring
    ADD CONSTRAINT FK_TBL_RING_ON_MAINTENANCE_DOCUMENT FOREIGN KEY (maintenance_document_id) REFERENCES tbl_document (document_id);

ALTER TABLE tbl_file
    DROP CONSTRAINT uc_tbl_file_url;

DROP TABLE tbl_file CASCADE;

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

ALTER TABLE tbl_design
    ADD side_diamonds_count INTEGER;

ALTER TABLE tbl_design
    ALTER COLUMN side_diamonds_count SET NOT NULL;

ALTER TABLE tbl_design
    DROP COLUMN size_diamonds_count;

ALTER TABLE tbl_design
    DROP CONSTRAINT fk_tbl_design_on_metal_specification;

CREATE SEQUENCE IF NOT EXISTS design_metal_specification_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_design_metal_specification
(
    design_metal_specification_id BIGINT                      NOT NULL,
    created_at                    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                     VARCHAR(255)                NOT NULL,
    modified_at                   TIMESTAMP WITHOUT TIME ZONE,
    modified_by                   VARCHAR(255),
    state                         VARCHAR(12)                 NOT NULL,
    opt_version                   INTEGER                     NOT NULL,
    design_id                     BIGINT                      NOT NULL,
    metal_specification_id        BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_design_metal_specification PRIMARY KEY (design_metal_specification_id)
);

ALTER TABLE tbl_design_metal_specification
    ADD CONSTRAINT FK_TBL_DESIGN_METAL_SPECIFICATION_ON_DESIGN FOREIGN KEY (design_id) REFERENCES tbl_design (design_id);

ALTER TABLE tbl_design_metal_specification
    ADD CONSTRAINT FK_TBL_DESIGN_METAL_SPECIFICATION_ON_METAL_SPECIFICATION FOREIGN KEY (metal_specification_id) REFERENCES tbl_metal_specification (metal_specification_id);

ALTER TABLE tbl_design
    DROP COLUMN metal_specification_id;

ALTER TABLE tbl_design_image
    DROP CONSTRAINT fk_tbl_design_image_on_metal_specification;

ALTER TABLE tbl_design_metal_specification
    ADD image_id BIGINT;

ALTER TABLE tbl_design_metal_specification
    ALTER COLUMN image_id SET NOT NULL;

ALTER TABLE tbl_design_metal_specification
    ADD CONSTRAINT uc_tbl_design_metal_specification_image UNIQUE (image_id);

ALTER TABLE tbl_design_metal_specification
    ADD CONSTRAINT FK_TBL_DESIGN_METAL_SPECIFICATION_ON_IMAGE FOREIGN KEY (image_id) REFERENCES tbl_image (image_id);

ALTER TABLE tbl_design_image
    DROP COLUMN metal_specification_id;

ALTER TABLE tbl_metal_specification
    DROP COLUMN color;

ALTER TABLE tbl_metal_specification
    ADD color VARCHAR(12);

UPDATE tbl_metal_specification
SET color = 'YELLOW'
WHERE color IS NULL;

ALTER TABLE tbl_metal_specification
    ALTER COLUMN color SET NOT NULL;