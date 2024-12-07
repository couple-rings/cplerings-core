CREATE SEQUENCE IF NOT EXISTS dummy_product_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_dummy_product
(
    dummy_product_id BIGINT                      NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by        VARCHAR(255)                NOT NULL,
    modified_at      TIMESTAMP WITHOUT TIME ZONE,
    modified_by      VARCHAR(255),
    state            VARCHAR(12)                 NOT NULL,
    opt_version      INTEGER                     NOT NULL,
    product_no       VARCHAR(8)                  NOT NULL,
    CONSTRAINT pk_tbl_dummy_order PRIMARY KEY (dummy_product_id)
);