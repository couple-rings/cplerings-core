DO
$$
    BEGIN
        -- CRFE
        IF EXISTS(SELECT 1 FROM tbl_configuration WHERE key LIKE 'CRFE') THEN
            UPDATE tbl_configuration
            SET status = 'INVALID'
            WHERE key like 'CRFE';
        END IF;

        INSERT INTO tbl_configuration (configuration_id, created_at, create_by, modified_at, modified_by, state,
                                       opt_version, key, value, status)
        VALUES (nextval('configuration_seq'), current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'CRFE',
                300000.000, 'ACTIVE');

        -- SHFE
        IF EXISTS(SELECT 1 FROM tbl_configuration WHERE key LIKE 'SHFE') THEN
            UPDATE tbl_configuration
            SET status = 'INVALID'
            WHERE key like 'SHFE';
        END IF;

        INSERT INTO tbl_configuration (configuration_id, created_at, create_by, modified_at, modified_by, state,
                                       opt_version, key, value, status)
        VALUES (nextval('configuration_seq'), current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'SHFE',
                0, 'ACTIVE');

        -- REFU
        IF EXISTS(SELECT 1 FROM tbl_configuration WHERE key LIKE 'REFU') THEN
            UPDATE tbl_configuration
            SET status = 'INVALID'
            WHERE key like 'REFU';
        END IF;

        INSERT INTO tbl_configuration (configuration_id, created_at, create_by, modified_at, modified_by, state,
                                       opt_version, key, value, status)
        VALUES (nextval('configuration_seq'), current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'REFU',
                80, 'ACTIVE');

        -- CSP3
        IF EXISTS(SELECT 1 FROM tbl_configuration WHERE key LIKE 'RESE') THEN
            UPDATE tbl_configuration
            SET status = 'INVALID'
            WHERE key like 'RESE';
        END IF;

        INSERT INTO tbl_configuration (configuration_id, created_at, create_by, modified_at, modified_by, state,
                                       opt_version, key, value, status)
        VALUES (nextval('configuration_seq'), current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'RESE',
                70, 'ACTIVE');
    END;
$$