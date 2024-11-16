CREATE SEQUENCE IF NOT EXISTS dummy_order_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_dummy_order
(
    dummy_order_id BIGINT                      NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by      VARCHAR(255)                NOT NULL,
    modified_at    TIMESTAMP WITHOUT TIME ZONE,
    modified_by    VARCHAR(255),
    state          VARCHAR(12)                 NOT NULL,
    opt_version    INTEGER                     NOT NULL,
    order_no       VARCHAR(8)                  NOT NULL,
    CONSTRAINT pk_tbl_dummy_order PRIMARY KEY (dummy_order_id)
);