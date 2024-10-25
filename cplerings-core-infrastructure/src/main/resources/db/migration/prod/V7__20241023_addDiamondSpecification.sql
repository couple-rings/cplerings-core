ALTER TABLE tbl_design_couple_design
    DROP CONSTRAINT fk_tbl_design_couple_design_on_design;

ALTER TABLE tbl_design_couple_design
    DROP CONSTRAINT fk_tbl_design_couple_design_on_design_couple;

ALTER TABLE tbl_design
    DROP CONSTRAINT fk_tbl_design_on_diamond_specification;

ALTER TABLE tbl_jewelry_category_design
    DROP CONSTRAINT fk_tbl_jewelry_category_design_on_design;

ALTER TABLE tbl_jewelry_category_design
    DROP CONSTRAINT fk_tbl_jewelry_category_design_on_jewelry_category;

CREATE SEQUENCE IF NOT EXISTS design_diamond_specification_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_design_diamond_specification
(
    design_diamond_specification_id BIGINT                      NOT NULL,
    created_at                      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                       VARCHAR(255)                NOT NULL,
    modified_at                     TIMESTAMP WITHOUT TIME ZONE,
    modified_by                     VARCHAR(255),
    state                           VARCHAR(12)                 NOT NULL,
    opt_version                     INTEGER                     NOT NULL,
    design_id                       BIGINT                      NOT NULL,
    diamond_specification_id        BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_design_diamond_specification PRIMARY KEY (design_diamond_specification_id)
);

ALTER TABLE tbl_design
    ADD design_couple_id BIGINT;

ALTER TABLE tbl_design
    ADD jewelry_category_id BIGINT;

ALTER TABLE tbl_design_diamond_specification
    ADD CONSTRAINT FK_TBL_DESIGN_DIAMOND_SPECIFICATION_ON_DESIGN FOREIGN KEY (design_id) REFERENCES tbl_design (design_id);

ALTER TABLE tbl_design_diamond_specification
    ADD CONSTRAINT FK_TBL_DESIGN_DIAMOND_SPECIFICATION_ON_DIAMOND_SPECIFICATION FOREIGN KEY (diamond_specification_id) REFERENCES tbl_diamond_specification (diamond_specification_id);

ALTER TABLE tbl_design
    ADD CONSTRAINT FK_TBL_DESIGN_ON_DESIGN_COUPLE FOREIGN KEY (design_couple_id) REFERENCES tbl_design_couple (design_couple_id);

ALTER TABLE tbl_design
    ADD CONSTRAINT FK_TBL_DESIGN_ON_JEWELRY_CATEGORY FOREIGN KEY (jewelry_category_id) REFERENCES tbl_jewelry_category (jewelry_category_id);

DROP TABLE tbl_design_couple_design CASCADE;

DROP TABLE tbl_jewelry_category_design CASCADE;

ALTER TABLE tbl_design
    DROP COLUMN diamond_specification_id;

DROP SEQUENCE design_couple_design_seq CASCADE;

DROP SEQUENCE jewelry_category_design_seq CASCADE;