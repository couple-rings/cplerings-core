CREATE SEQUENCE IF NOT EXISTS custom_design_metal_specification_sequence START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_custom_design_metal_specification
(
    custom_design_metal_specification_id BIGINT                      NOT NULL,
    created_at                           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                            VARCHAR(255)                NOT NULL,
    modified_at                          TIMESTAMP WITHOUT TIME ZONE,
    modified_by                          VARCHAR(255),
    state                                VARCHAR(12)                 NOT NULL,
    opt_version                          INTEGER                     NOT NULL,
    custom_design_id                     BIGINT                      NOT NULL,
    metal_specification_id               BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_custom_design_metal_specification PRIMARY KEY (custom_design_metal_specification_id)
);

ALTER TABLE tbl_custom_design
    ADD blue_print_id BIGINT;

ALTER TABLE tbl_custom_design
    ALTER COLUMN blue_print_id SET NOT NULL;

ALTER TABLE tbl_custom_design
    ADD CONSTRAINT uc_tbl_custom_design_blue_print UNIQUE (blue_print_id);

ALTER TABLE tbl_custom_design_metal_specification
    ADD CONSTRAINT FK_TBL_CUSTOM_DESIGN_METAL_SPECIFICATION_ON_CUSTOM_DESIGN FOREIGN KEY (custom_design_id) REFERENCES tbl_custom_design (custom_design_id);

ALTER TABLE tbl_custom_design_metal_specification
    ADD CONSTRAINT FK_TBL_CUSTOM_DESIGN_METAL_SPECIFICATION_ON_METAL_SPECIFICATION FOREIGN KEY (metal_specification_id) REFERENCES tbl_metal_specification (metal_specification_id);

ALTER TABLE tbl_custom_design
    ADD CONSTRAINT FK_TBL_CUSTOM_DESIGN_ON_BLUE_PRINT FOREIGN KEY (blue_print_id) REFERENCES tbl_document (document_id);