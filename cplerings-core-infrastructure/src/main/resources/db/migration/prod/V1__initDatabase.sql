CREATE SEQUENCE IF NOT EXISTS account_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS account_verification_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS agreement_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS blog_image_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS blog_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS blog_tag_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS branch_manager_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS branch_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS branch_staff_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS design_session_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS diamond_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS diamond_specification_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS image_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS push_notification_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS ring_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS spouse_account_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS spouse_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS tag_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS topic_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS verification_request_detail_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE IF NOT EXISTS verification_request_seq START WITH 1 INCREMENT BY 10;

CREATE TABLE tbl_account
(
    account_id  BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(255)                NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(255),
    state       VARCHAR(12)                 NOT NULL,
    opt_version INTEGER                     NOT NULL,
    email       VARCHAR(100)                NOT NULL,
    password    VARCHAR(62)                 NOT NULL,
    username    VARCHAR(50)                 NOT NULL,
    phone       VARCHAR(10),
    avatar      VARCHAR(255),
    role        VARCHAR(12)                 NOT NULL,
    status      VARCHAR(12)                 NOT NULL,
    CONSTRAINT pk_tbl_account PRIMARY KEY (account_id)
);

CREATE TABLE tbl_account_verification
(
    account_verification_id BIGINT                      NOT NULL,
    created_at              TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by               VARCHAR(255)                NOT NULL,
    modified_at             TIMESTAMP WITHOUT TIME ZONE,
    modified_by             VARCHAR(255),
    state                   VARCHAR(12)                 NOT NULL,
    opt_version             INTEGER                     NOT NULL,
    code                    VARCHAR(6)                  NOT NULL,
    status                  VARCHAR(12)                 NOT NULL,
    account_id              BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_account_verification PRIMARY KEY (account_verification_id)
);

CREATE TABLE tbl_agreement
(
    agreement_id     BIGINT                      NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by        VARCHAR(255)                NOT NULL,
    modified_at      TIMESTAMP WITHOUT TIME ZONE,
    modified_by      VARCHAR(255),
    state            VARCHAR(12)                 NOT NULL,
    opt_version      INTEGER                     NOT NULL,
    main_spouse_id   BIGINT                      NOT NULL,
    second_spouse_id BIGINT                      NOT NULL,
    signed_date      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_tbl_agreement PRIMARY KEY (agreement_id)
);

CREATE TABLE tbl_blog
(
    blog_id     BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(255)                NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(255),
    state       VARCHAR(12)                 NOT NULL,
    opt_version INTEGER                     NOT NULL,
    title       VARCHAR(255)                NOT NULL,
    content     TEXT                        NOT NULL,
    cover_image BIGINT                      NOT NULL,
    blogger_id  BIGINT                      NOT NULL,
    topic_id    BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_blog PRIMARY KEY (blog_id)
);

CREATE TABLE tbl_blog_image
(
    blog_image_id BIGINT                      NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by     VARCHAR(255)                NOT NULL,
    modified_at   TIMESTAMP WITHOUT TIME ZONE,
    modified_by   VARCHAR(255),
    state         VARCHAR(12)                 NOT NULL,
    opt_version   INTEGER                     NOT NULL,
    blog_id       BIGINT                      NOT NULL,
    image_id      BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_blog_image PRIMARY KEY (blog_image_id)
);

CREATE TABLE tbl_blog_tag
(
    blog_tag_id BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(255)                NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(255),
    state       VARCHAR(12)                 NOT NULL,
    opt_version INTEGER                     NOT NULL,
    blog_id     BIGINT                      NOT NULL,
    tag_id      BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_blog_tag PRIMARY KEY (blog_tag_id)
);

CREATE TABLE tbl_branch
(
    branch_id   BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(255)                NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(255),
    state       VARCHAR(12)                 NOT NULL,
    opt_version INTEGER                     NOT NULL,
    address     VARCHAR(255)                NOT NULL,
    store_name  VARCHAR(255)                NOT NULL,
    phone       VARCHAR(255)                NOT NULL,
    cover_image VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_tbl_branch PRIMARY KEY (branch_id)
);

CREATE TABLE tbl_branch_manager
(
    branch_manager_id BIGINT                      NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by         VARCHAR(255)                NOT NULL,
    modified_at       TIMESTAMP WITHOUT TIME ZONE,
    modified_by       VARCHAR(255),
    state             VARCHAR(12)                 NOT NULL,
    opt_version       INTEGER                     NOT NULL,
    branch_id         BIGINT                      NOT NULL,
    manager_id        BIGINT                      NOT NULL,
    start_date        date                        NOT NULL,
    end_date          date                        NOT NULL,
    allocation_type   VARCHAR(12)                 NOT NULL,
    CONSTRAINT pk_tbl_branch_manager PRIMARY KEY (branch_manager_id)
);

CREATE TABLE tbl_branch_staff
(
    branch_staff_id BIGINT                      NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by       VARCHAR(255)                NOT NULL,
    modified_at     TIMESTAMP WITHOUT TIME ZONE,
    modified_by     VARCHAR(255),
    state           VARCHAR(12)                 NOT NULL,
    opt_version     INTEGER                     NOT NULL,
    branch_id       BIGINT                      NOT NULL,
    staff_id        BIGINT                      NOT NULL,
    start_date      date                        NOT NULL,
    end_date        date                        NOT NULL,
    allocation_type VARCHAR(12)                 NOT NULL,
    CONSTRAINT pk_tbl_branch_staff PRIMARY KEY (branch_staff_id)
);

CREATE TABLE tbl_design_session
(
    design_session_id BIGINT                      NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by         VARCHAR(255)                NOT NULL,
    modified_at       TIMESTAMP WITHOUT TIME ZONE,
    modified_by       VARCHAR(255),
    state             VARCHAR(12)                 NOT NULL,
    opt_version       INTEGER                     NOT NULL,
    customer_id       BIGINT                      NOT NULL,
    status            VARCHAR(12)                 NOT NULL,
    session_id        UUID                        NOT NULL,
    CONSTRAINT pk_tbl_design_session PRIMARY KEY (design_session_id)
);

CREATE TABLE tbl_diamond
(
    diamond_id               BIGINT                      NOT NULL,
    created_at               TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                VARCHAR(255)                NOT NULL,
    modified_at              TIMESTAMP WITHOUT TIME ZONE,
    modified_by              VARCHAR(255),
    state                    VARCHAR(12)                 NOT NULL,
    opt_version              INTEGER                     NOT NULL,
    gia_document             VARCHAR(255)                NOT NULL,
    gia_report_number        VARCHAR(255)                NOT NULL,
    diamond_specification_id BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_diamond PRIMARY KEY (diamond_id)
);

CREATE TABLE tbl_diamond_specification
(
    diamond_specification_id BIGINT                      NOT NULL,
    created_at               TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                VARCHAR(255)                NOT NULL,
    modified_at              TIMESTAMP WITHOUT TIME ZONE,
    modified_by              VARCHAR(255),
    state                    VARCHAR(12)                 NOT NULL,
    opt_version              INTEGER                     NOT NULL,
    name                     VARCHAR(255)                NOT NULL,
    weight                   DOUBLE PRECISION            NOT NULL,
    color                    VARCHAR(255)                NOT NULL,
    clarity                  VARCHAR(255)                NOT NULL,
    cut                      VARCHAR(255)                NOT NULL,
    shape                    VARCHAR(255)                NOT NULL,
    price                    DOUBLE PRECISION            NOT NULL,
    CONSTRAINT pk_tbl_diamond_specification PRIMARY KEY (diamond_specification_id)
);

CREATE TABLE tbl_image
(
    image_id    BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(255)                NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(255),
    state       VARCHAR(12)                 NOT NULL,
    opt_version INTEGER                     NOT NULL,
    url         VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_tbl_image PRIMARY KEY (image_id)
);

CREATE TABLE tbl_push_notification
(
    push_notification_id BIGINT                      NOT NULL,
    created_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by            VARCHAR(255)                NOT NULL,
    modified_at          TIMESTAMP WITHOUT TIME ZONE,
    modified_by          VARCHAR(255),
    state                VARCHAR(12)                 NOT NULL,
    opt_version          INTEGER                     NOT NULL,
    token                VARCHAR(200),
    account_id           BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_push_notification PRIMARY KEY (push_notification_id)
);

CREATE TABLE tbl_ring
(
    ring_id                  BIGINT                      NOT NULL,
    created_at               TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                VARCHAR(255)                NOT NULL,
    modified_at              TIMESTAMP WITHOUT TIME ZONE,
    modified_by              VARCHAR(255),
    state                    VARCHAR(12)                 NOT NULL,
    opt_version              INTEGER                     NOT NULL,
    purchase_date            TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    status                   VARCHAR(12)                 NOT NULL,
    maintanence_expired_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    maintanance_document     VARCHAR(255)                NOT NULL,
    branch_id                BIGINT                      NOT NULL,
    spouse_id                BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_ring PRIMARY KEY (ring_id)
);

CREATE TABLE tbl_spouse
(
    spouse_id     BIGINT                      NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by     VARCHAR(255)                NOT NULL,
    modified_at   TIMESTAMP WITHOUT TIME ZONE,
    modified_by   VARCHAR(255),
    state         VARCHAR(12)                 NOT NULL,
    opt_version   INTEGER                     NOT NULL,
    citizen_id    VARCHAR(12)                 NOT NULL,
    date_of_birth TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    full_name     VARCHAR(255)                NOT NULL,
    couple_id     UUID                        NOT NULL,
    CONSTRAINT pk_tbl_spouse PRIMARY KEY (spouse_id)
);

CREATE TABLE tbl_spouse_account
(
    spouse_account_id BIGINT                      NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by         VARCHAR(255)                NOT NULL,
    modified_at       TIMESTAMP WITHOUT TIME ZONE,
    modified_by       VARCHAR(255),
    state             VARCHAR(12)                 NOT NULL,
    opt_version       INTEGER                     NOT NULL,
    spouse_id         BIGINT                      NOT NULL,
    customer_id       BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_spouse_account PRIMARY KEY (spouse_account_id)
);

CREATE TABLE tbl_tag
(
    tag_id      BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(255)                NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(255),
    state       VARCHAR(12)                 NOT NULL,
    opt_version INTEGER                     NOT NULL,
    name        VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_tbl_tag PRIMARY KEY (tag_id)
);

CREATE TABLE tbl_topic
(
    topic_id    BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by   VARCHAR(255)                NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    modified_by VARCHAR(255),
    state       VARCHAR(12)                 NOT NULL,
    opt_version INTEGER                     NOT NULL,
    name        VARCHAR(255)                NOT NULL,
    description VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_tbl_topic PRIMARY KEY (topic_id)
);

CREATE TABLE tbl_verification_request
(
    verification_request_id BIGINT                      NOT NULL,
    created_at              TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by               VARCHAR(255)                NOT NULL,
    modified_at             TIMESTAMP WITHOUT TIME ZONE,
    modified_by             VARCHAR(255),
    state                   VARCHAR(12)                 NOT NULL,
    opt_version             INTEGER                     NOT NULL,
    status                  VARCHAR(12)                 NOT NULL,
    comment                 VARCHAR(500),
    customer_id             BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_verification_request PRIMARY KEY (verification_request_id)
);

CREATE TABLE tbl_verification_request_detail
(
    verification_request_detail_id BIGINT                      NOT NULL,
    created_at                     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    create_by                      VARCHAR(255)                NOT NULL,
    modified_at                    TIMESTAMP WITHOUT TIME ZONE,
    modified_by                    VARCHAR(255),
    state                          VARCHAR(12)                 NOT NULL,
    opt_version                    INTEGER                     NOT NULL,
    face_photo_id                  BIGINT                      NOT NULL,
    citizen_id_image_id            BIGINT                      NOT NULL,
    verification_request_id        BIGINT                      NOT NULL,
    CONSTRAINT pk_tbl_verification_request_detail PRIMARY KEY (verification_request_detail_id)
);

ALTER TABLE tbl_account
    ADD CONSTRAINT uc_tbl_account_avatar UNIQUE (avatar);

ALTER TABLE tbl_account
    ADD CONSTRAINT uc_tbl_account_email UNIQUE (email);

ALTER TABLE tbl_account
    ADD CONSTRAINT uc_tbl_account_password UNIQUE (password);

ALTER TABLE tbl_account
    ADD CONSTRAINT uc_tbl_account_phone UNIQUE (phone);

ALTER TABLE tbl_account
    ADD CONSTRAINT uc_tbl_account_username UNIQUE (username);

ALTER TABLE tbl_agreement
    ADD CONSTRAINT uc_tbl_agreement_main_spouse UNIQUE (main_spouse_id);

ALTER TABLE tbl_agreement
    ADD CONSTRAINT uc_tbl_agreement_second_spouse UNIQUE (second_spouse_id);

ALTER TABLE tbl_blog
    ADD CONSTRAINT uc_tbl_blog_cover_image UNIQUE (cover_image);

ALTER TABLE tbl_blog
    ADD CONSTRAINT uc_tbl_blog_title UNIQUE (title);

ALTER TABLE tbl_branch
    ADD CONSTRAINT uc_tbl_branch_address UNIQUE (address);

ALTER TABLE tbl_branch
    ADD CONSTRAINT uc_tbl_branch_store_name UNIQUE (store_name);

ALTER TABLE tbl_image
    ADD CONSTRAINT uc_tbl_image_url UNIQUE (url);

ALTER TABLE tbl_push_notification
    ADD CONSTRAINT uc_tbl_push_notification_account UNIQUE (account_id);

ALTER TABLE tbl_ring
    ADD CONSTRAINT uc_tbl_ring_spouse UNIQUE (spouse_id);

ALTER TABLE tbl_spouse_account
    ADD CONSTRAINT uc_tbl_spouse_account_customer UNIQUE (customer_id);

ALTER TABLE tbl_spouse_account
    ADD CONSTRAINT uc_tbl_spouse_account_spouse UNIQUE (spouse_id);

ALTER TABLE tbl_spouse
    ADD CONSTRAINT uc_tbl_spouse_citizen UNIQUE (citizen_id);

ALTER TABLE tbl_verification_request_detail
    ADD CONSTRAINT uc_tbl_verification_request_detail_citizen_id_image UNIQUE (citizen_id_image_id);

ALTER TABLE tbl_verification_request_detail
    ADD CONSTRAINT uc_tbl_verification_request_detail_face_photo UNIQUE (face_photo_id);

ALTER TABLE tbl_spouse_account
    ADD CONSTRAINT uq_spouse_account UNIQUE (spouse_id, customer_id);

ALTER TABLE tbl_account_verification
    ADD CONSTRAINT FK_TBL_ACCOUNT_VERIFICATION_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_agreement
    ADD CONSTRAINT FK_TBL_AGREEMENT_ON_MAIN_SPOUSE FOREIGN KEY (main_spouse_id) REFERENCES tbl_spouse (spouse_id);

ALTER TABLE tbl_agreement
    ADD CONSTRAINT FK_TBL_AGREEMENT_ON_SECOND_SPOUSE FOREIGN KEY (second_spouse_id) REFERENCES tbl_spouse (spouse_id);

ALTER TABLE tbl_blog_image
    ADD CONSTRAINT FK_TBL_BLOG_IMAGE_ON_BLOG FOREIGN KEY (blog_id) REFERENCES tbl_blog (blog_id);

ALTER TABLE tbl_blog_image
    ADD CONSTRAINT FK_TBL_BLOG_IMAGE_ON_IMAGE FOREIGN KEY (image_id) REFERENCES tbl_image (image_id);

ALTER TABLE tbl_blog
    ADD CONSTRAINT FK_TBL_BLOG_ON_BLOGGER FOREIGN KEY (blogger_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_blog
    ADD CONSTRAINT FK_TBL_BLOG_ON_COVER_IMAGE FOREIGN KEY (cover_image) REFERENCES tbl_image (image_id);

ALTER TABLE tbl_blog
    ADD CONSTRAINT FK_TBL_BLOG_ON_TOPIC FOREIGN KEY (topic_id) REFERENCES tbl_topic (topic_id);

ALTER TABLE tbl_blog_tag
    ADD CONSTRAINT FK_TBL_BLOG_TAG_ON_BLOG FOREIGN KEY (blog_id) REFERENCES tbl_blog (blog_id);

ALTER TABLE tbl_blog_tag
    ADD CONSTRAINT FK_TBL_BLOG_TAG_ON_TAG FOREIGN KEY (tag_id) REFERENCES tbl_tag (tag_id);

ALTER TABLE tbl_branch_manager
    ADD CONSTRAINT FK_TBL_BRANCH_MANAGER_ON_BRANCH FOREIGN KEY (branch_id) REFERENCES tbl_branch (branch_id);

ALTER TABLE tbl_branch_manager
    ADD CONSTRAINT FK_TBL_BRANCH_MANAGER_ON_MANAGER FOREIGN KEY (manager_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_branch_staff
    ADD CONSTRAINT FK_TBL_BRANCH_STAFF_ON_BRANCH FOREIGN KEY (branch_id) REFERENCES tbl_branch (branch_id);

ALTER TABLE tbl_branch_staff
    ADD CONSTRAINT FK_TBL_BRANCH_STAFF_ON_STAFF FOREIGN KEY (staff_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_design_session
    ADD CONSTRAINT FK_TBL_DESIGN_SESSION_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_diamond
    ADD CONSTRAINT FK_TBL_DIAMOND_ON_DIAMOND_SPECIFICATION FOREIGN KEY (diamond_specification_id) REFERENCES tbl_diamond_specification (diamond_specification_id);

ALTER TABLE tbl_push_notification
    ADD CONSTRAINT FK_TBL_PUSH_NOTIFICATION_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_ring
    ADD CONSTRAINT FK_TBL_RING_ON_BRANCH FOREIGN KEY (branch_id) REFERENCES tbl_branch (branch_id);

ALTER TABLE tbl_ring
    ADD CONSTRAINT FK_TBL_RING_ON_SPOUSE FOREIGN KEY (spouse_id) REFERENCES tbl_spouse (spouse_id);

ALTER TABLE tbl_spouse_account
    ADD CONSTRAINT FK_TBL_SPOUSE_ACCOUNT_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES tbl_account (account_id);

ALTER TABLE tbl_spouse_account
    ADD CONSTRAINT FK_TBL_SPOUSE_ACCOUNT_ON_SPOUSE FOREIGN KEY (spouse_id) REFERENCES tbl_spouse (spouse_id);

ALTER TABLE tbl_verification_request_detail
    ADD CONSTRAINT FK_TBL_VERIFICATION_REQUEST_DETAIL_ON_CITIZEN_ID_IMAGE FOREIGN KEY (citizen_id_image_id) REFERENCES tbl_image (image_id);

ALTER TABLE tbl_verification_request_detail
    ADD CONSTRAINT FK_TBL_VERIFICATION_REQUEST_DETAIL_ON_FACE_PHOTO FOREIGN KEY (face_photo_id) REFERENCES tbl_image (image_id);

ALTER TABLE tbl_verification_request_detail
    ADD CONSTRAINT FK_TBL_VERIFICATION_REQUEST_DETAIL_ON_VERIFICATION_REQUEST FOREIGN KEY (verification_request_id) REFERENCES tbl_verification_request (verification_request_id);

ALTER TABLE tbl_verification_request
    ADD CONSTRAINT FK_TBL_VERIFICATION_REQUEST_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES tbl_account (account_id);