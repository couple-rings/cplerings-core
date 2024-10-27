CREATE SEQUENCE IF NOT EXISTS account_password_reset_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_account_password_reset
(
    account_password_reset_id BIGINT                      NOT NULL,
    created_at                TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                 VARCHAR(255)                NOT NULL,
    modified_at               TIMESTAMP WITHOUT TIME ZONE,
    modified_by               VARCHAR(255),
    state                     VARCHAR(12)                 NOT NULL,
    opt_version               INTEGER                     NOT NULL,
    code                      VARCHAR(6)                  NOT NULL,
    status                    VARCHAR(12)                 NOT NULL,
    account_id                BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_account_password_reset PRIMARY KEY (account_password_reset_id)
);

ALTER TABLE tbl_account_password_reset
    ADD CONSTRAINT FK_TBL_ACCOUNT_PASSWORD_RESET_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES tbl_account (account_id);