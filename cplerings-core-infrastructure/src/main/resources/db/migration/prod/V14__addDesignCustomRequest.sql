CREATE SEQUENCE IF NOT EXISTS design_custom_request_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_design_custom_request
(
    design_custom_request_id BIGINT                      NOT NULL,
    created_at               TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                VARCHAR(255)                NOT NULL,
    modified_at              TIMESTAMP WITHOUT TIME ZONE,
    modified_by              VARCHAR(255),
    state                    VARCHAR(12)                 NOT NULL,
    opt_version              INTEGER                     NOT NULL,
    design_id                BIGINT                      NOT NULL,
    custom_request_id        BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_design_custom_request PRIMARY KEY (design_custom_request_id)
);

ALTER TABLE tbl_design_custom_request
    ADD CONSTRAINT FK_TBL_DESIGN_CUSTOM_REQUEST_ON_CUSTOM_REQUEST FOREIGN KEY (custom_request_id) REFERENCES tbl_custom_request (custom_request_id);

ALTER TABLE tbl_design_custom_request
    ADD CONSTRAINT FK_TBL_DESIGN_CUSTOM_REQUEST_ON_DESIGN FOREIGN KEY (design_id) REFERENCES tbl_design (design_id);