INSERT INTO public.tbl_branch (
    branch_id, created_at, create_by, modified_at, modified_by, state, opt_version, address, store_name, phone, cover_image
)
VALUES
    (nextval('branch_seq'), current_timestamp, 'system', current_timestamp, 'system', 'ACTIVE', 0, '123 Đường Lê Lợi, Quận 1 Thành phố Hồ Chí Minh', 'Cửa hàng cưới quận 1', '0943295561', 1),
    (nextval('branch_seq'), current_timestamp, 'system', current_timestamp, 'system', 'ACTIVE', 0, '456 Đường Nguyễn Đình Chiểu, Quận 3 Thành phố Hồ Chí Minh', 'Cửa hàng cưới quận 2', '0943295562', 11),
    (nextval('branch_seq'), current_timestamp, 'system', current_timestamp, 'system', 'ACTIVE', 0, '789 Đường Trần Hưng Đạo, Quận 5 Thành phố Hồ Chí Minh', 'Cửa hàng cưới quận 3', '0943295563', 21);
