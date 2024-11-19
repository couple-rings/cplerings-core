INSERT INTO tbl_finger_size(
    finger_size_id, created_at, create_by, modified_at, modified_by, state, opt_version, size, diameter)
VALUES
    (nextval('finger_size_seq'), current_timestamp, 'system', current_timestamp, 'system', 'ACTIVE', 0, 4, 14),
    (nextval('finger_size_seq'), current_timestamp, 'system', current_timestamp, 'system', 'ACTIVE', 0, 5, 14.3),
    (nextval('finger_size_seq'), current_timestamp, 'system', current_timestamp, 'system', 'ACTIVE', 0, 6, 14.6),
    (nextval('finger_size_seq'), current_timestamp, 'system', current_timestamp, 'system', 'ACTIVE', 0, 7, 15),
    (nextval('finger_size_seq'), current_timestamp, 'system', current_timestamp, 'system', 'ACTIVE', 0, 8, 15.3);
