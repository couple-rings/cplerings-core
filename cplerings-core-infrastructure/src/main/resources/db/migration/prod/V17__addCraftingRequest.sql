CREATE SEQUENCE IF NOT EXISTS crafting_request_sequence START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_crafting_request
(
    crafting_request_id      BIGINT                      NOT NULL,
    created_at               TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                VARCHAR(255)                NOT NULL,
    modified_at              TIMESTAMP WITHOUT TIME ZONE,
    modified_by              VARCHAR(255),
    state                    VARCHAR(12)                 NOT NULL,
    opt_version              INTEGER                     NOT NULL,
    customer_id              BIGINT                      NOT NULL,
    metal_specification_id   BIGINT                      NOT NULL,
    diamond_specification_id BIGINT                      NOT NULL,
    reviewer_id              BIGINT,
    engraving                VARCHAR(255),
    finger_size              INTEGER                     NOT NULL,
    comment                  VARCHAR(255),
    crafting_request_status  VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_tbl_crafting_request PRIMARY KEY (crafting_request_id)
);

ALTER TABLE tbl_crafting_request
    ADD CONSTRAINT FK_TBL_CRAFTING_REQUEST_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_crafting_request
    ADD CONSTRAINT FK_TBL_CRAFTING_REQUEST_ON_DIAMOND_SPECIFICATION FOREIGN KEY (diamond_specification_id) REFERENCES tbl_diamond_specification (diamond_specification_id);

ALTER TABLE tbl_crafting_request
    ADD CONSTRAINT FK_TBL_CRAFTING_REQUEST_ON_METAL_SPECIFICATION FOREIGN KEY (metal_specification_id) REFERENCES tbl_metal_specification (metal_specification_id);

ALTER TABLE tbl_crafting_request
    ADD CONSTRAINT FK_TBL_CRAFTING_REQUEST_ON_REVIEWER FOREIGN KEY (reviewer_id) REFERENCES tbl_account (account_id);