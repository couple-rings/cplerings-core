CREATE SEQUENCE IF NOT EXISTS ring_history_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_ring_history
(
    ring_history_id BIGINT                      NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by       VARCHAR(255)                NOT NULL,
    modified_at     TIMESTAMP WITHOUT TIME ZONE,
    modified_by     VARCHAR(255),
    state           VARCHAR(12)                 NOT NULL,
    opt_version     INTEGER                     NOT NULL,
    status          VARCHAR(12)                 NOT NULL,
    ring_id         BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_ring_history PRIMARY KEY (ring_history_id)
);

ALTER TABLE tbl_crafting_request_history
    ADD crafting_request_id BIGINT;

ALTER TABLE tbl_crafting_request_history
    ALTER COLUMN crafting_request_id SET NOT NULL;

ALTER TABLE tbl_custom_order_history
    ADD custom_order_id BIGINT;

ALTER TABLE tbl_custom_order_history
    ALTER COLUMN custom_order_id SET NOT NULL;

ALTER TABLE tbl_custom_request_history
    ADD custom_request_id BIGINT;

ALTER TABLE tbl_custom_request_history
    ALTER COLUMN custom_request_id SET NOT NULL;

ALTER TABLE tbl_standard_order_history
    ADD standard_order_id BIGINT;

ALTER TABLE tbl_standard_order_history
    ALTER COLUMN standard_order_id SET NOT NULL;

ALTER TABLE tbl_transport_order_history
    ADD transport_order_id BIGINT;

ALTER TABLE tbl_transport_order_history
    ALTER COLUMN transport_order_id SET NOT NULL;

ALTER TABLE tbl_crafting_request_history
    ADD CONSTRAINT FK_TBL_CRAFTING_REQUEST_HISTORY_ON_CRAFTING_REQUEST FOREIGN KEY (crafting_request_id) REFERENCES tbl_crafting_request (crafting_request_id);

ALTER TABLE tbl_custom_order_history
    ADD CONSTRAINT FK_TBL_CUSTOM_ORDER_HISTORY_ON_CUSTOM_ORDER FOREIGN KEY (custom_order_id) REFERENCES tbl_custom_order (custom_order_id);

ALTER TABLE tbl_custom_request_history
    ADD CONSTRAINT FK_TBL_CUSTOM_REQUEST_HISTORY_ON_CUSTOM_REQUEST FOREIGN KEY (custom_request_id) REFERENCES tbl_custom_request (custom_request_id);

ALTER TABLE tbl_ring_history
    ADD CONSTRAINT FK_TBL_RING_HISTORY_ON_RING FOREIGN KEY (ring_id) REFERENCES tbl_ring (ring_id);

ALTER TABLE tbl_standard_order_history
    ADD CONSTRAINT FK_TBL_STANDARD_ORDER_HISTORY_ON_STANDARD_ORDER FOREIGN KEY (standard_order_id) REFERENCES tbl_standard_order (standard_order_id);

ALTER TABLE tbl_transport_order_history
    ADD CONSTRAINT FK_TBL_TRANSPORT_ORDER_HISTORY_ON_TRANSPORT_ORDER FOREIGN KEY (transport_order_id) REFERENCES tbl_transport_order (transport_order_id);