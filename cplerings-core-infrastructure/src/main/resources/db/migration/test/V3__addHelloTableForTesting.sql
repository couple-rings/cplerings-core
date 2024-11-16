CREATE SEQUENCE IF NOT EXISTS hello_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_hello
(
    hello_id    BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(255)                NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(255),
    state       VARCHAR(12)                 NOT NULL,
    opt_version INTEGER                     NOT NULL,
    name        VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_tbl_hello PRIMARY KEY (hello_id)
);

ALTER TABLE tbl_hello
    ADD CONSTRAINT uc_hello_name UNIQUE (name);