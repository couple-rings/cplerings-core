ALTER TABLE tbl_custom_order
    ADD order_no VARCHAR(8);

DO
$$
    BEGIN
        UPDATE tbl_custom_order
        SET order_no = lpad(custom_order_id::text, 8, '0');
    END;
$$;

ALTER TABLE tbl_custom_order
    ALTER COLUMN order_no SET NOT NULL;

ALTER TABLE tbl_transport_order
    ADD order_no VARCHAR(8);

DO
$$
    BEGIN
        UPDATE tbl_transport_order
        SET order_no = lpad(transport_order_id::text, 8, '0');
    END;
$$;

ALTER TABLE tbl_transport_order
    ALTER COLUMN order_no SET NOT NULL;