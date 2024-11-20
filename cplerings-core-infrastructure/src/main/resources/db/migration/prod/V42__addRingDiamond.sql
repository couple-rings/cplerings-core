CREATE SEQUENCE IF NOT EXISTS ring_diamond_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_ring_diamond
(
    ring_diamond_id BIGINT                      NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by       VARCHAR(255)                NOT NULL,
    modified_at     TIMESTAMP WITHOUT TIME ZONE,
    modified_by     VARCHAR(255),
    state           VARCHAR(12)                 NOT NULL,
    opt_version     INTEGER                     NOT NULL,
    ring_id         BIGINT                      NOT NULL,
    diamond_id      BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_ring_diamond PRIMARY KEY (ring_diamond_id)
);

ALTER TABLE tbl_diamond
    ADD branch_id BIGINT;

ALTER TABLE tbl_diamond
    ALTER COLUMN branch_id SET NOT NULL;

ALTER TABLE tbl_ring
    ADD finger_size INTEGER;

ALTER TABLE tbl_ring
    ALTER COLUMN finger_size SET NOT NULL;

ALTER TABLE tbl_diamond
    ADD CONSTRAINT FK_TBL_DIAMOND_ON_BRANCH FOREIGN KEY (branch_id) REFERENCES tbl_branch (branch_id);

ALTER TABLE tbl_ring_diamond
    ADD CONSTRAINT FK_TBL_RING_DIAMOND_ON_DIAMOND FOREIGN KEY (diamond_id) REFERENCES tbl_diamond (diamond_id);

ALTER TABLE tbl_ring_diamond
    ADD CONSTRAINT FK_TBL_RING_DIAMOND_ON_RING FOREIGN KEY (ring_id) REFERENCES tbl_ring (ring_id);