CREATE SEQUENCE IF NOT EXISTS custom_design_diamond_specification_sequence START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS custom_design_sequence START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_custom_design
(
    custom_design_id    BIGINT         NOT NULL,
    created_at          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by           VARCHAR(255)   NOT NULL,
    modified_at         TIMESTAMP WITHOUT TIME ZONE,
    modified_by         VARCHAR(255),
    state               VARCHAR(12)    NOT NULL,
    opt_version         INTEGER        NOT NULL,
    design_version_id   BIGINT         NOT NULL,
    spouse_id           BIGINT         NOT NULL,
    customer_id         BIGINT         NOT NULL,
    side_diamonds_count INTEGER        NOT NULL,
    metal_weight        DECIMAL(10, 2) NOT NULL,
    CONSTRAINT pk_tbl_custom_design PRIMARY KEY (custom_design_id)
);

CREATE TABLE tbl_custom_design_diamond_specification
(
    custom_design_diamond_specification_id BIGINT       NOT NULL,
    created_at                             TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                              VARCHAR(255) NOT NULL,
    modified_at                            TIMESTAMP WITHOUT TIME ZONE,
    modified_by                            VARCHAR(255),
    state                                  VARCHAR(12)  NOT NULL,
    opt_version                            INTEGER      NOT NULL,
    custom_design_id                       BIGINT       NOT NULL,
    diamond_specification_id               BIGINT       NOT NULL,
    CONSTRAINT pk_tbl_custom_design_diamond_specification PRIMARY KEY (custom_design_diamond_specification_id)
);

ALTER TABLE tbl_custom_design
    ADD CONSTRAINT uc_tbl_custom_design_design_version UNIQUE (design_version_id);

ALTER TABLE tbl_custom_design_diamond_specification
    ADD CONSTRAINT FK_TBLCUSTOMDESIGNDIAMONDSPECIFICATION_ON_DIAMONDSPECIFICATION FOREIGN KEY (diamond_specification_id) REFERENCES tbl_diamond_specification (diamond_specification_id);

ALTER TABLE tbl_custom_design_diamond_specification
    ADD CONSTRAINT FK_TBL_CUSTOM_DESIGN_DIAMOND_SPECIFICATION_ON_CUSTOM_DESIGN FOREIGN KEY (custom_design_id) REFERENCES tbl_custom_design (custom_design_id);

ALTER TABLE tbl_custom_design
    ADD CONSTRAINT FK_TBL_CUSTOM_DESIGN_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_custom_design
    ADD CONSTRAINT FK_TBL_CUSTOM_DESIGN_ON_DESIGN_VERSION FOREIGN KEY (design_version_id) REFERENCES tbl_design_version (design_version_id);

ALTER TABLE tbl_custom_design
    ADD CONSTRAINT FK_TBL_CUSTOM_DESIGN_ON_SPOUSE FOREIGN KEY (spouse_id) REFERENCES tbl_spouse (spouse_id);