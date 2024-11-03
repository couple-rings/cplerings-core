CREATE SEQUENCE IF NOT EXISTS design_version_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_design_version
(
    design_version_id BIGINT                      NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by         VARCHAR(255)                NOT NULL,
    modified_at       TIMESTAMP WITHOUT TIME ZONE,
    modified_by       VARCHAR(255),
    state             VARCHAR(12)                 NOT NULL,
    opt_version       INTEGER                     NOT NULL,
    design_id         BIGINT                      NOT NULL,
    image_id          BIGINT                      NOT NULL,
    design_file_id    BIGINT                      NOT NULL,
    version_number    INTEGER                     NOT NULL,
    is_accepted       BOOLEAN                     NOT NULL,
    is_old            BOOLEAN                     NOT NULL,
    CONSTRAINT pk_tbl_design_version PRIMARY KEY (design_version_id)
);

ALTER TABLE tbl_design_version
    ADD CONSTRAINT uc_tbl_design_version_design_file UNIQUE (design_file_id);

ALTER TABLE tbl_design_version
    ADD CONSTRAINT uc_tbl_design_version_image UNIQUE (image_id);

ALTER TABLE tbl_design_version
    ADD CONSTRAINT FK_TBL_DESIGN_VERSION_ON_DESIGN FOREIGN KEY (design_id) REFERENCES tbl_design (design_id);

ALTER TABLE tbl_design_version
    ADD CONSTRAINT FK_TBL_DESIGN_VERSION_ON_DESIGN_FILE FOREIGN KEY (design_file_id) REFERENCES tbl_document (document_id);