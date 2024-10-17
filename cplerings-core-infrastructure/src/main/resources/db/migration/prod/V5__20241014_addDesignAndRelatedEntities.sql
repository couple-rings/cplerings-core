CREATE SEQUENCE IF NOT EXISTS design_collection_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS design_couple_design_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS design_couple_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS design_image_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS design_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS document_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS jewelry_category_design_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS jewelry_category_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS metal_specification_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_design
(
    design_id                BIGINT                      NOT NULL,
    created_at               TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                VARCHAR(255)                NOT NULL,
    modified_at              TIMESTAMP WITHOUT TIME ZONE,
    modified_by              VARCHAR(255),
    state                    VARCHAR(12)                 NOT NULL,
    opt_version              INTEGER                     NOT NULL,
    name                     VARCHAR(255)                NOT NULL,
    description              VARCHAR(1000)               NOT NULL,
    blueprint_id             BIGINT                      NOT NULL,
    characteristic           VARCHAR(12)                 NOT NULL,
    size_diamonds_count      INTEGER                     NOT NULL,
    diamond_specification_id BIGINT                      NOT NULL,
    metal_specification_id   BIGINT                      NOT NULL,
    design_collection_id     BIGINT                      NOT NULL,
    metal_weight             DECIMAL(10, 2)              NOT NULL,
    size                     INTEGER                     NOT NULL,
    CONSTRAINT pk_tbl_design PRIMARY KEY (design_id)
);

CREATE TABLE tbl_design_collection
(
    design_collection_id BIGINT                      NOT NULL,
    created_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by            VARCHAR(255)                NOT NULL,
    modified_at          TIMESTAMP WITHOUT TIME ZONE,
    modified_by          VARCHAR(255),
    state                VARCHAR(12)                 NOT NULL,
    opt_version          INTEGER                     NOT NULL,
    name                 VARCHAR(255)                NOT NULL,
    description          VARCHAR(1000)               NOT NULL,
    CONSTRAINT pk_tbl_design_collection PRIMARY KEY (design_collection_id)
);

CREATE TABLE tbl_design_couple
(
    design_couple_id BIGINT                      NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by        VARCHAR(255)                NOT NULL,
    modified_at      TIMESTAMP WITHOUT TIME ZONE,
    modified_by      VARCHAR(255),
    state            VARCHAR(12)                 NOT NULL,
    opt_version      INTEGER                     NOT NULL,
    preview_image_id BIGINT                      NOT NULL,
    name             VARCHAR(255)                NOT NULL,
    description      VARCHAR(1000)               NOT NULL,
    CONSTRAINT pk_tbl_design_couple PRIMARY KEY (design_couple_id)
);

CREATE TABLE tbl_design_couple_design
(
    design_couple_design_id BIGINT                      NOT NULL,
    created_at              TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by               VARCHAR(255)                NOT NULL,
    modified_at             TIMESTAMP WITHOUT TIME ZONE,
    modified_by             VARCHAR(255),
    state                   VARCHAR(12)                 NOT NULL,
    opt_version             INTEGER                     NOT NULL,
    design_id               BIGINT                      NOT NULL,
    design_couple_id        BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_design_couple_design PRIMARY KEY (design_couple_design_id)
);

CREATE TABLE tbl_design_image
(
    design_image_id        BIGINT                      NOT NULL,
    created_at             TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by              VARCHAR(255)                NOT NULL,
    modified_at            TIMESTAMP WITHOUT TIME ZONE,
    modified_by            VARCHAR(255),
    state                  VARCHAR(12)                 NOT NULL,
    opt_version            INTEGER                     NOT NULL,
    design_id              BIGINT                      NOT NULL,
    metal_specification_id BIGINT                      NOT NULL,
    image_id               BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_design_image PRIMARY KEY (design_image_id)
);

CREATE TABLE tbl_file
(
    document_id BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(255)                NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(255),
    state       VARCHAR(12)                 NOT NULL,
    opt_version INTEGER                     NOT NULL,
    url         VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_tbl_file PRIMARY KEY (document_id)
);

CREATE TABLE tbl_jewelry_category
(
    jewelry_category_id BIGINT                      NOT NULL,
    created_at          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by           VARCHAR(255)                NOT NULL,
    modified_at         TIMESTAMP WITHOUT TIME ZONE,
    modified_by         VARCHAR(255),
    state               VARCHAR(12)                 NOT NULL,
    opt_version         INTEGER                     NOT NULL,
    name                VARCHAR(255)                NOT NULL,
    description         VARCHAR(1000)               NOT NULL,
    CONSTRAINT pk_tbl_jewelry_category PRIMARY KEY (jewelry_category_id)
);

CREATE TABLE tbl_jewelry_category_design
(
    jewelry_category_design_id BIGINT                      NOT NULL,
    created_at                 TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                  VARCHAR(255)                NOT NULL,
    modified_at                TIMESTAMP WITHOUT TIME ZONE,
    modified_by                VARCHAR(255),
    state                      VARCHAR(12)                 NOT NULL,
    opt_version                INTEGER                     NOT NULL,
    jewelry_category_id        BIGINT                      NOT NULL,
    design_id                  BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_jewelry_category_design PRIMARY KEY (jewelry_category_design_id)
);

CREATE TABLE tbl_metal_specification
(
    metal_specification_id BIGINT                      NOT NULL,
    created_at             TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by              VARCHAR(255)                NOT NULL,
    modified_at            TIMESTAMP WITHOUT TIME ZONE,
    modified_by            VARCHAR(255),
    state                  VARCHAR(12)                 NOT NULL,
    opt_version            INTEGER                     NOT NULL,
    name                   VARCHAR(255)                NOT NULL,
    color                  SMALLINT                    NOT NULL,
    price_per_unit         DECIMAL(12, 3)              NOT NULL,
    CONSTRAINT pk_tbl_metal_specification PRIMARY KEY (metal_specification_id)
);

ALTER TABLE tbl_diamond
    ADD gia_document_id BIGINT;

ALTER TABLE tbl_diamond
    ALTER COLUMN gia_document_id SET NOT NULL;

ALTER TABLE tbl_ring
    ADD maintenance_document_id BIGINT;

ALTER TABLE tbl_ring
    ADD maintenance_expired_date TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE tbl_ring
    ALTER COLUMN maintenance_document_id SET NOT NULL;

ALTER TABLE tbl_ring
    ALTER COLUMN maintenance_expired_date SET NOT NULL;

ALTER TABLE tbl_design
    ADD CONSTRAINT uc_tbl_design_blueprint UNIQUE (blueprint_id);

ALTER TABLE tbl_design_image
    ADD CONSTRAINT uc_tbl_design_image_image UNIQUE (image_id);

ALTER TABLE tbl_diamond
    ADD CONSTRAINT uc_tbl_diamond_gia_document UNIQUE (gia_document_id);

ALTER TABLE tbl_file
    ADD CONSTRAINT uc_tbl_file_url UNIQUE (url);

ALTER TABLE tbl_ring
    ADD CONSTRAINT uc_tbl_ring_maintenance_document UNIQUE (maintenance_document_id);

ALTER TABLE tbl_design_couple_design
    ADD CONSTRAINT FK_TBL_DESIGN_COUPLE_DESIGN_ON_DESIGN FOREIGN KEY (design_id) REFERENCES tbl_design (design_id);

ALTER TABLE tbl_design_couple_design
    ADD CONSTRAINT FK_TBL_DESIGN_COUPLE_DESIGN_ON_DESIGN_COUPLE FOREIGN KEY (design_couple_id) REFERENCES tbl_design_couple (design_couple_id);

ALTER TABLE tbl_design_couple
    ADD CONSTRAINT FK_TBL_DESIGN_COUPLE_ON_PREVIEW_IMAGE FOREIGN KEY (preview_image_id) REFERENCES tbl_image (image_id);

ALTER TABLE tbl_design_image
    ADD CONSTRAINT FK_TBL_DESIGN_IMAGE_ON_DESIGN FOREIGN KEY (design_id) REFERENCES tbl_design (design_id);

ALTER TABLE tbl_design_image
    ADD CONSTRAINT FK_TBL_DESIGN_IMAGE_ON_IMAGE FOREIGN KEY (image_id) REFERENCES tbl_image (image_id);

ALTER TABLE tbl_design_image
    ADD CONSTRAINT FK_TBL_DESIGN_IMAGE_ON_METAL_SPECIFICATION FOREIGN KEY (metal_specification_id) REFERENCES tbl_metal_specification (metal_specification_id);

ALTER TABLE tbl_design
    ADD CONSTRAINT FK_TBL_DESIGN_ON_BLUEPRINT FOREIGN KEY (blueprint_id) REFERENCES tbl_file (document_id);

ALTER TABLE tbl_design
    ADD CONSTRAINT FK_TBL_DESIGN_ON_DESIGN_COLLECTION FOREIGN KEY (design_collection_id) REFERENCES tbl_design_collection (design_collection_id);

ALTER TABLE tbl_design
    ADD CONSTRAINT FK_TBL_DESIGN_ON_DIAMOND_SPECIFICATION FOREIGN KEY (diamond_specification_id) REFERENCES tbl_diamond_specification (diamond_specification_id);

ALTER TABLE tbl_design
    ADD CONSTRAINT FK_TBL_DESIGN_ON_METAL_SPECIFICATION FOREIGN KEY (metal_specification_id) REFERENCES tbl_metal_specification (metal_specification_id);

ALTER TABLE tbl_diamond
    ADD CONSTRAINT FK_TBL_DIAMOND_ON_GIA_DOCUMENT FOREIGN KEY (gia_document_id) REFERENCES tbl_file (document_id);

ALTER TABLE tbl_jewelry_category_design
    ADD CONSTRAINT FK_TBL_JEWELRY_CATEGORY_DESIGN_ON_DESIGN FOREIGN KEY (design_id) REFERENCES tbl_design (design_id);

ALTER TABLE tbl_jewelry_category_design
    ADD CONSTRAINT FK_TBL_JEWELRY_CATEGORY_DESIGN_ON_JEWELRY_CATEGORY FOREIGN KEY (jewelry_category_id) REFERENCES tbl_jewelry_category (jewelry_category_id);

ALTER TABLE tbl_ring
    ADD CONSTRAINT FK_TBL_RING_ON_MAINTENANCE_DOCUMENT FOREIGN KEY (maintenance_document_id) REFERENCES tbl_file (document_id);

ALTER TABLE tbl_diamond_specification
    DROP COLUMN cut;

ALTER TABLE tbl_diamond_specification
    DROP COLUMN price;

ALTER TABLE tbl_diamond
    DROP COLUMN gia_document;

ALTER TABLE tbl_ring
    DROP COLUMN maintanance_document;

ALTER TABLE tbl_ring
    DROP COLUMN maintanence_expired_date;

ALTER TABLE tbl_diamond_specification
    ADD price DECIMAL(12, 3);

UPDATE tbl_diamond_specification
SET price = 0.0
WHERE price IS NULL;

ALTER TABLE tbl_diamond_specification
    ALTER COLUMN price SET NOT NULL;