ALTER TABLE tbl_agreement
    DROP CONSTRAINT fk_tbl_agreement_on_main_spouse;

ALTER TABLE tbl_agreement
    DROP CONSTRAINT fk_tbl_agreement_on_second_spouse;

ALTER TABLE tbl_agreement
    DROP CONSTRAINT uc_tbl_agreement_second_spouse;

ALTER TABLE tbl_agreement
    DROP CONSTRAINT uc_tbl_agreement_main_spouse;

ALTER TABLE tbl_agreement
    ADD customer_id BIGINT;

ALTER TABLE tbl_agreement
    ADD main_name VARCHAR(255);

ALTER TABLE tbl_agreement
    ADD main_signature_id BIGINT;

ALTER TABLE tbl_agreement
    ADD partner_name VARCHAR(255);

ALTER TABLE tbl_agreement
    ADD partner_signature_id BIGINT;

ALTER TABLE tbl_agreement
    ALTER COLUMN customer_id SET NOT NULL;

ALTER TABLE tbl_agreement
    ADD CONSTRAINT uc_tbl_agreement_customer UNIQUE (customer_id);

ALTER TABLE tbl_agreement
    ADD CONSTRAINT uc_tbl_agreement_main_signature UNIQUE (main_signature_id);

ALTER TABLE tbl_agreement
    ADD CONSTRAINT uc_tbl_agreement_partner_signature UNIQUE (partner_signature_id);

ALTER TABLE tbl_agreement
    ADD CONSTRAINT FK_TBL_AGREEMENT_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_agreement
    ADD CONSTRAINT FK_TBL_AGREEMENT_ON_MAIN_SIGNATURE FOREIGN KEY (main_signature_id) REFERENCES tbl_image (image_id);

ALTER TABLE tbl_agreement
    ADD CONSTRAINT FK_TBL_AGREEMENT_ON_PARTNER_SIGNATURE FOREIGN KEY (partner_signature_id) REFERENCES tbl_image (image_id);

ALTER TABLE tbl_agreement
    DROP COLUMN main_spouse_id;

ALTER TABLE tbl_agreement
    DROP COLUMN second_spouse_id;

ALTER TABLE tbl_branch
    ALTER COLUMN cover_image SET NOT NULL;

ALTER TABLE tbl_agreement
    ALTER COLUMN signed_date DROP NOT NULL;