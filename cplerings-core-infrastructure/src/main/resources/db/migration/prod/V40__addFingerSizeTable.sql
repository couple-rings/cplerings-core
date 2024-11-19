CREATE SEQUENCE IF NOT EXISTS finger_size_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_finger_size
(
    finger_size_id BIGINT                      NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by      VARCHAR(255)                NOT NULL,
    modified_at    TIMESTAMP WITHOUT TIME ZONE,
    modified_by    VARCHAR(255),
    state          VARCHAR(12)                 NOT NULL,
    opt_version    INTEGER                     NOT NULL,
    size           INTEGER                     NOT NULL,
    diameter       DOUBLE PRECISION            NOT NULL,
    CONSTRAINT pk_tbl_finger_size PRIMARY KEY (finger_size_id)
);