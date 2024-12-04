DO
$$
    BEGIN
        -- SDPR
        IF EXISTS(SELECT 1 FROM tbl_configuration WHERE key LIKE 'SDPR') THEN
            UPDATE tbl_configuration
            SET status = 'INVALID'
            WHERE key like 'SDPR';
        END IF;

        INSERT INTO tbl_configuration (configuration_id, created_at, create_by, modified_at, modified_by, state,
                                       opt_version, key, value, status)
        VALUES (nextval('configuration_seq'), current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'SDPR',
                130000.000, 'ACTIVE');

        -- CSP1
        IF EXISTS(SELECT 1 FROM tbl_configuration WHERE key LIKE 'CSP1') THEN
            UPDATE tbl_configuration
            SET status = 'INVALID'
            WHERE key like 'CSP1';
        END IF;

        INSERT INTO tbl_configuration (configuration_id, created_at, create_by, modified_at, modified_by, state,
                                       opt_version, key, value, status)
        VALUES (nextval('configuration_seq'), current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'CSP1',
                50, 'ACTIVE');

        -- CSP2
        IF EXISTS(SELECT 1 FROM tbl_configuration WHERE key LIKE 'CSP2') THEN
            UPDATE tbl_configuration
            SET status = 'INVALID'
            WHERE key like 'CSP2';
        END IF;

        INSERT INTO tbl_configuration (configuration_id, created_at, create_by, modified_at, modified_by, state,
                                       opt_version, key, value, status)
        VALUES (nextval('configuration_seq'), current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'CSP2',
                75, 'ACTIVE');

        -- CSP3
        IF EXISTS(SELECT 1 FROM tbl_configuration WHERE key LIKE 'CSP3') THEN
            UPDATE tbl_configuration
            SET status = 'INVALID'
            WHERE key like 'CSP3';
        END IF;

        INSERT INTO tbl_configuration (configuration_id, created_at, create_by, modified_at, modified_by, state,
                                       opt_version, key, value, status)
        VALUES (nextval('configuration_seq'), current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'CSP3',
                100, 'ACTIVE');
    END;
$$