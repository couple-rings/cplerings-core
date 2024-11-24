INSERT INTO tbl_account (account_id, created_at, create_by, modified_at, modified_by, opt_version, email, password,
                         role, status, username, state)
VALUES (nextval('account_seq'), current_timestamp, 'CoupleRings', current_timestamp, 'CoupleRings', 0,
        'secondary.server@cplerings.com', ' $2a$10$8mkhRkXfEN/WThYW9EEZ4ueSHq9URb9Jb.E15.U4OmeALZpzsZTue', 'SERVICE',
        'ACTIVE', 'SecondaryServer', 'ACTIVE');