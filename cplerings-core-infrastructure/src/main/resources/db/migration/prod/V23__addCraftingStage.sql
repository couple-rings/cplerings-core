CREATE SEQUENCE IF NOT EXISTS crafting_stage_sequence START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_crafting_stage
(
    crafting_stage_id     BIGINT                      NOT NULL,
    created_at            TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by             VARCHAR(255)                NOT NULL,
    modified_at           TIMESTAMP WITHOUT TIME ZONE,
    modified_by           VARCHAR(255),
    state                 VARCHAR(12)                 NOT NULL,
    opt_version           INTEGER                     NOT NULL,
    custom_order_id       BIGINT                      NOT NULL,
    name                  VARCHAR(255)                NOT NULL,
    progress              INTEGER                     NOT NULL,
    completion_date       TIMESTAMP WITHOUT TIME ZONE,
    crafting_stage_status VARCHAR(255),
    CONSTRAINT pk_tbl_crafting_stage PRIMARY KEY (crafting_stage_id)
);

ALTER TABLE tbl_crafting_stage
    ADD CONSTRAINT FK_TBL_CRAFTING_STAGE_ON_CUSTOM_ORDER FOREIGN KEY (custom_order_id) REFERENCES tbl_custom_order (custom_order_id);