INSERT INTO tbl_account (account_id, created_at, create_by, modified_at, modified_by, state, opt_version, email,
                         password, username, phone, role, status, branch_id, staff_position)
VALUES (nextval('account_seq'), current_timestamp, 'CoupleRings',
        current_timestamp, 'CoupleRings', 'ACTIVE', 0,
        'staff.sales@cplerings.com', '$2a$10$LO1eT5rZMkP6FaXAc2Vjm.TEFmfa03SRN9Uc7JuaK/k6NKa38G0cq',
        'staff-sales', '1234567890', 'STAFF', 'ACTIVE', 1, 'SALES'),
       (nextval('account_seq'), current_timestamp, 'CoupleRings',
        current_timestamp, 'CoupleRings', 'ACTIVE', 0,
        'staff.designer@cplerings.com', '$2a$10$CZCF4ah/.2PlrIHkGP8U9ulB/dfkOEV5zZ7OLtX/LYqTTz5DZnekG',
        'staff-designer', '1234567890', 'STAFF', 'ACTIVE', 1, 'DESIGNER');