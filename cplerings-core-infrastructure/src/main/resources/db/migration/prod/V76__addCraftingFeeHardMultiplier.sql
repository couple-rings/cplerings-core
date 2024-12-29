DO
$$
    BEGIN
        IF EXISTS(SELECT 1 FROM tbl_configuration WHERE key LIKE 'CFHM') THEN
            UPDATE tbl_configuration
            SET status = 'INVALID'
            WHERE key like 'CFHM';
        END IF;

        INSERT INTO tbl_configuration (configuration_id, created_at, create_by, modified_at, modified_by, state,
                                       opt_version, key, value, status)
        VALUES (nextval('configuration_seq'), current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'CFHM',
                2, 'ACTIVE');
    END;
$$