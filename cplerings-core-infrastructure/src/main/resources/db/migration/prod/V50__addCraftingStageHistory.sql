CREATE SEQUENCE IF NOT EXISTS crafting_stage_history_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_crafting_stage_history
(
    crafting_stage_history_id BIGINT                      NOT NULL,
    created_at                TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                 VARCHAR(255)                NOT NULL,
    modified_at               TIMESTAMP WITHOUT TIME ZONE,
    modified_by               VARCHAR(255),
    state                     VARCHAR(12)                 NOT NULL,
    opt_version               INTEGER                     NOT NULL,
    status                    VARCHAR(12)                 NOT NULL,
    crafting_stage_id         BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_crafting_stage_history PRIMARY KEY (crafting_stage_history_id)
);

ALTER TABLE tbl_crafting_stage_history
    ADD CONSTRAINT FK_TBL_CRAFTING_STAGE_HISTORY_ON_CRAFTING_STAGE FOREIGN KEY (crafting_stage_id) REFERENCES tbl_crafting_stage (crafting_stage_id);