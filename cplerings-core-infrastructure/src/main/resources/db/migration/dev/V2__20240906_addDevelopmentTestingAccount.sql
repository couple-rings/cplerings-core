SELECT nextval('ACCOUNT_SEQ');

INSERT INTO ACCOUNT (ACCOUNT_ID, CREATED_AT, CREATE_BY, MODIFIED_AT, MODIFIED_BY, VERSION, EMAIL, PASSWORD, ROLE,
                     STATUS)
VALUES (1, current_timestamp, 'system', current_timestamp, 'system', 0, 'customer@cplerings.com',
        '$2a$10$qJXQTQykGglKbqlBB5R4H.OjLojogvXbZzMtMiSDkaus9Ms6XTdyW', 'CUSTOMER', 'ACTIVE'),
       (2, current_timestamp, 'system', current_timestamp, 'system', 0, 'manager@cplerings.com',
        '$2a$10$i0NumWUkJzZ4JsGrHG6DmeGajzBb76Y3kYEFbS.UV1cmt2SBdSu6m', 'MANAGER', 'ACTIVE'),
       (3, current_timestamp, 'system', current_timestamp, 'system', 0, 'staff@cplerings.com',
        '$2a$10$x5JWuG3L/iOcWsWVh.XSx.kDRYJfSyUeKG.Jqi.FODbOGiVR/RZ1G', 'STAFF', 'ACTIVE'),
       (4, current_timestamp, 'system', current_timestamp, 'system', 0, 'admin@cplerings.com',
        '$2a$10$3mT4JrzkUa5anKwy44l3m.0ItTkkE84kR/MHhCab2pg5c5murx0cu', 'ADMIN', 'ACTIVE'),
       (5, current_timestamp, 'system', current_timestamp, 'system', 0, 'jeweler@cplerings.com',
        '$2a$10$SICsn7qzibM2IS1i2plG5.FsdhfwKJReRO6NwbG7oMrb9tgM2d8ci', 'JEWELER', 'ACTIVE'),
       (6, current_timestamp, 'system', current_timestamp, 'system', 0, 'transporter@cplerings.com',
        '$2a$10$qSM5oGiCLWWxGP1iWWKgEeTIIr3hKVlBDjEshL58M1WDS9HkuX46q', 'TRANSPORTER', 'ACTIVE');