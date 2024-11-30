DO
$$
    BEGIN
        ALTER TABLE tbl_account
            ADD avatar_id BIGINT;

        ALTER TABLE tbl_account
            ADD staff_position VARCHAR(12);

        ALTER TABLE tbl_account
            ADD CONSTRAINT FK_TBL_ACCOUNT_ON_AVATAR FOREIGN KEY (avatar_id) REFERENCES tbl_image (image_id);

        ALTER TABLE tbl_account
            DROP COLUMN avatar;

        ALTER TABLE tbl_account
            ADD CONSTRAINT uc_tbl_account_avatar UNIQUE (avatar_id);

        ALTER TABLE tbl_account
            DROP CONSTRAINT uc_tbl_account_phone;

        ALTER TABLE tbl_account
            DROP CONSTRAINT uc_tbl_account_avatar;

        UPDATE tbl_account
        SET staff_position = 'SALES'
        WHERE role like N'STAFF';
    END;
$$