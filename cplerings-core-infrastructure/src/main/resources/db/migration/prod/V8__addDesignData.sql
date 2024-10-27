DO
$$
    DECLARE
        diamond_spec_heart_id1       BIGINT;
        diamond_spec_heart_id2       BIGINT;
        diamond_spec_heart_id3       BIGINT;
        diamond_spec_oval_id1        BIGINT;
        diamond_spec_oval_id2        BIGINT;
        diamond_spec_oval_id3        BIGINT;
        diamond_spec_round_id1       BIGINT;
        diamond_spec_round_id2       BIGINT;
        diamond_spec_round_id3       BIGINT;
        metal_spec_id1               BIGINT;
        metal_spec_id2               BIGINT;
        metal_spec_id3               BIGINT;
        metal_spec_id4               BIGINT;
        metal_spec_id5               BIGINT;
        metal_spec_id6               BIGINT;
        design_collection_id1        BIGINT;
        design_collection_id2        BIGINT;
        design_collection_id3        BIGINT;
        design_couple_image_id1      BIGINT;
        design_couple_image_id2      BIGINT;
        design_couple_image_id3      BIGINT;
        design_couple_image_id4      BIGINT;
        design_couple_image_id5      BIGINT;
        design_couple_image_id6      BIGINT;
        design_couple_image_id7      BIGINT;
        design_couple_image_id8      BIGINT;
        design_couple_image_id9      BIGINT;
        design_couple_id1            BIGINT;
        design_couple_id2            BIGINT;
        design_couple_id3            BIGINT;
        design_couple_id4            BIGINT;
        design_couple_id5            BIGINT;
        design_couple_id6            BIGINT;
        design_couple_id7            BIGINT;
        design_couple_id8            BIGINT;
        design_couple_id9            BIGINT;
        design_blueprint_id1         BIGINT;
        design_blueprint_id2         BIGINT;
        design_blueprint_id3         BIGINT;
        design_blueprint_id4         BIGINT;
        design_blueprint_id5         BIGINT;
        design_blueprint_id6         BIGINT;
        design_blueprint_id7         BIGINT;
        design_blueprint_id8         BIGINT;
        design_blueprint_id9         BIGINT;
        design_blueprint_id10        BIGINT;
        design_blueprint_id11        BIGINT;
        design_blueprint_id12        BIGINT;
        design_blueprint_id13        BIGINT;
        design_blueprint_id14        BIGINT;
        design_blueprint_id15        BIGINT;
        design_blueprint_id16        BIGINT;
        design_blueprint_id17        BIGINT;
        design_blueprint_id18        BIGINT;
        design_id1                   BIGINT;
        design_id2                   BIGINT;
        design_id3                   BIGINT;
        design_id4                   BIGINT;
        design_id5                   BIGINT;
        design_id6                   BIGINT;
        design_id7                   BIGINT;
        design_id8                   BIGINT;
        design_id9                   BIGINT;
        design_id10                  BIGINT;
        design_id11                  BIGINT;
        design_id12                  BIGINT;
        design_id13                  BIGINT;
        design_id14                  BIGINT;
        design_id15                  BIGINT;
        design_id16                  BIGINT;
        design_id17                  BIGINT;
        design_id18                  BIGINT;
        design_metal_spec_image_id1  BIGINT;
        design_metal_spec_image_id2  BIGINT;
        design_metal_spec_image_id3  BIGINT;
        design_metal_spec_image_id4  BIGINT;
        design_metal_spec_image_id5  BIGINT;
        design_metal_spec_image_id6  BIGINT;
        design_metal_spec_image_id7  BIGINT;
        design_metal_spec_image_id8  BIGINT;
        design_metal_spec_image_id9  BIGINT;
        design_metal_spec_image_id10 BIGINT;
        design_metal_spec_image_id11 BIGINT;
        design_metal_spec_image_id12 BIGINT;
        design_metal_spec_image_id13 BIGINT;
        design_metal_spec_image_id14 BIGINT;
        design_metal_spec_image_id15 BIGINT;
        design_metal_spec_image_id16 BIGINT;
        design_metal_spec_image_id17 BIGINT;
        design_metal_spec_image_id18 BIGINT;
        design_metal_spec_image_id19 BIGINT;
        design_metal_spec_image_id20 BIGINT;
        design_metal_spec_image_id21 BIGINT;
        design_metal_spec_image_id22 BIGINT;
        design_metal_spec_image_id23 BIGINT;
        design_metal_spec_image_id24 BIGINT;
        design_metal_spec_image_id25 BIGINT;
        design_metal_spec_image_id26 BIGINT;
        design_metal_spec_image_id27 BIGINT;
        design_metal_spec_image_id28 BIGINT;
        design_metal_spec_image_id29 BIGINT;
        design_metal_spec_image_id30 BIGINT;
        design_metal_spec_image_id31 BIGINT;
        design_metal_spec_image_id32 BIGINT;
        design_metal_spec_image_id33 BIGINT;
        design_metal_spec_image_id34 BIGINT;
        design_metal_spec_image_id35 BIGINT;
        design_metal_spec_image_id36 BIGINT;
        design_metal_spec_image_id37 BIGINT;
        design_metal_spec_image_id38 BIGINT;
        design_metal_spec_image_id39 BIGINT;
        design_metal_spec_image_id40 BIGINT;
        design_metal_spec_image_id41 BIGINT;
        design_metal_spec_image_id42 BIGINT;
        design_metal_spec_image_id43 BIGINT;
        design_metal_spec_image_id44 BIGINT;
        design_metal_spec_image_id45 BIGINT;
        design_metal_spec_image_id46 BIGINT;
        design_metal_spec_image_id47 BIGINT;
        design_metal_spec_image_id48 BIGINT;
        design_metal_spec_image_id49 BIGINT;
        design_metal_spec_image_id50 BIGINT;
        design_metal_spec_image_id51 BIGINT;
        design_metal_spec_image_id52 BIGINT;
        design_metal_spec_image_id53 BIGINT;
        design_metal_spec_image_id54 BIGINT;
        design_metal_spec_id1        BIGINT;
        design_metal_spec_id2        BIGINT;
        design_metal_spec_id3        BIGINT;
        design_metal_spec_id4        BIGINT;
        design_metal_spec_id5        BIGINT;
        design_metal_spec_id6        BIGINT;
        design_metal_spec_id7        BIGINT;
        design_metal_spec_id8        BIGINT;
        design_metal_spec_id9        BIGINT;
        design_metal_spec_id10       BIGINT;
        design_metal_spec_id11       BIGINT;
        design_metal_spec_id12       BIGINT;
        design_metal_spec_id13       BIGINT;
        design_metal_spec_id14       BIGINT;
        design_metal_spec_id15       BIGINT;
        design_metal_spec_id16       BIGINT;
        design_metal_spec_id17       BIGINT;
        design_metal_spec_id18       BIGINT;
        design_metal_spec_id19       BIGINT;
        design_metal_spec_id20       BIGINT;
        design_metal_spec_id21       BIGINT;
        design_metal_spec_id22       BIGINT;
        design_metal_spec_id23       BIGINT;
        design_metal_spec_id24       BIGINT;
        design_metal_spec_id25       BIGINT;
        design_metal_spec_id26       BIGINT;
        design_metal_spec_id27       BIGINT;
        design_metal_spec_id28       BIGINT;
        design_metal_spec_id29       BIGINT;
        design_metal_spec_id30       BIGINT;
        design_metal_spec_id31       BIGINT;
        design_metal_spec_id32       BIGINT;
        design_metal_spec_id33       BIGINT;
        design_metal_spec_id34       BIGINT;
        design_metal_spec_id35       BIGINT;
        design_metal_spec_id36       BIGINT;
        design_metal_spec_id37       BIGINT;
        design_metal_spec_id38       BIGINT;
        design_metal_spec_id39       BIGINT;
        design_metal_spec_id40       BIGINT;
        design_metal_spec_id41       BIGINT;
        design_metal_spec_id42       BIGINT;
        design_metal_spec_id43       BIGINT;
        design_metal_spec_id44       BIGINT;
        design_metal_spec_id45       BIGINT;
        design_metal_spec_id46       BIGINT;
        design_metal_spec_id47       BIGINT;
        design_metal_spec_id48       BIGINT;
        design_metal_spec_id49       BIGINT;
        design_metal_spec_id50       BIGINT;
        design_metal_spec_id51       BIGINT;
        design_metal_spec_id52       BIGINT;
        design_metal_spec_id53       BIGINT;
        design_metal_spec_id54       BIGINT;
        design_diamond_spec_id1      BIGINT;
        design_diamond_spec_id2      BIGINT;
        design_diamond_spec_id3      BIGINT;
        design_diamond_spec_id4      BIGINT;
        design_diamond_spec_id5      BIGINT;
        design_diamond_spec_id6      BIGINT;
        design_diamond_spec_id7      BIGINT;
        design_diamond_spec_id8      BIGINT;
        design_diamond_spec_id9      BIGINT;
        design_diamond_spec_id10     BIGINT;
        design_diamond_spec_id11     BIGINT;
        design_diamond_spec_id12     BIGINT;
        design_diamond_spec_id13     BIGINT;
        design_diamond_spec_id14     BIGINT;
        design_diamond_spec_id15     BIGINT;
        design_diamond_spec_id16     BIGINT;
        design_diamond_spec_id17     BIGINT;
        design_diamond_spec_id18     BIGINT;
        design_diamond_spec_id19     BIGINT;
        design_diamond_spec_id20     BIGINT;
        design_diamond_spec_id21     BIGINT;
        design_diamond_spec_id22     BIGINT;
        design_diamond_spec_id23     BIGINT;
        design_diamond_spec_id24     BIGINT;
        design_diamond_spec_id25     BIGINT;
        design_diamond_spec_id26     BIGINT;
        design_diamond_spec_id27     BIGINT;
        design_diamond_spec_id28     BIGINT;
        design_diamond_spec_id29     BIGINT;
        design_diamond_spec_id30     BIGINT;
        design_diamond_spec_id31     BIGINT;
        design_diamond_spec_id32     BIGINT;
        design_diamond_spec_id33     BIGINT;
        design_diamond_spec_id34     BIGINT;
        design_diamond_spec_id35     BIGINT;
        design_diamond_spec_id36     BIGINT;
        design_diamond_spec_id37     BIGINT;
        design_diamond_spec_id38     BIGINT;
        design_diamond_spec_id39     BIGINT;
        design_diamond_spec_id40     BIGINT;
        design_diamond_spec_id41     BIGINT;
        design_diamond_spec_id42     BIGINT;
        design_diamond_spec_id43     BIGINT;
        design_diamond_spec_id44     BIGINT;
        design_diamond_spec_id45     BIGINT;
        design_diamond_spec_id46     BIGINT;
        design_diamond_spec_id47     BIGINT;
        design_diamond_spec_id48     BIGINT;
        design_diamond_spec_id49     BIGINT;
        design_diamond_spec_id50     BIGINT;
        design_diamond_spec_id51     BIGINT;
        design_diamond_spec_id52     BIGINT;
        design_diamond_spec_id53     BIGINT;
        design_diamond_spec_id54     BIGINT;
    BEGIN
        -- Diamond specifications
        diamond_spec_heart_id1 := nextval('diamond_specification_seq');
        diamond_spec_heart_id2 := nextval('diamond_specification_seq');
        diamond_spec_heart_id3 := nextval('diamond_specification_seq');

        diamond_spec_oval_id1 := nextval('diamond_specification_seq');
        diamond_spec_oval_id2 := nextval('diamond_specification_seq');
        diamond_spec_oval_id3 := nextval('diamond_specification_seq');

        diamond_spec_round_id1 := nextval('diamond_specification_seq');
        diamond_spec_round_id2 := nextval('diamond_specification_seq');
        diamond_spec_round_id3 := nextval('diamond_specification_seq');

        INSERT INTO tbl_diamond_specification (diamond_specification_id, created_at, create_by, modified_at,
                                               modified_by, state, opt_version, name, weight, color, clarity, shape,
                                               price)
        VALUES (diamond_spec_heart_id1, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Pure Heart', '0.05', 'D', 'VS2', 'HEART', '3600000.0'),
               (diamond_spec_heart_id2, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Delicate Heart', '0.1', 'H', 'SI1', 'HEART', '6000000.0'),
               (diamond_spec_heart_id3, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Radiant Heart', '0.15', 'H', 'VS2', 'HEART', '9600000.0'),
               (diamond_spec_oval_id1, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Graceful Oval', '0.05', 'G', 'SI1', 'OVAL', '3120000.0'),
               (diamond_spec_oval_id2, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Radiant Oval', '0.1', 'G', 'VS2', 'OVAL', '5520000.0'),
               (diamond_spec_oval_id3, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Brilliant Oval', '0.15', 'G', 'VS2', 'OVAL', '9120000.0'),
               (diamond_spec_round_id1, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Elegant Round', '0.05', 'G', 'VS2', 'ROUND', '3360000.0'),
               (diamond_spec_round_id2, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Luminous Round', '0.1', 'E', 'VS1', 'ROUND', '6720000.0'),
               (diamond_spec_round_id3, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Dazzling Round', '0.15', 'G', 'VS1', 'ROUND', '10080000.0');

        -- Metal specifications
        metal_spec_id1 := nextval('metal_specification_seq');
        metal_spec_id2 := nextval('metal_specification_seq');
        metal_spec_id3 := nextval('metal_specification_seq');
        metal_spec_id4 := nextval('metal_specification_seq');
        metal_spec_id5 := nextval('metal_specification_seq');
        metal_spec_id6 := nextval('metal_specification_seq');

        INSERT INTO tbl_metal_specification (metal_specification_id, created_at, create_by, modified_at, modified_by,
                                             state, opt_version, name, color, price_per_unit)
        VALUES (metal_spec_id1, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Gold 14K - Yellow', 'YELLOW', 860000.0),
               (metal_spec_id2, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Gold 18K - Yellow', 'YELLOW', 1080000.0),
               (metal_spec_id3, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Gold 14K - White', 'WHITE', 912000.0),
               (metal_spec_id4, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Gold 18K - White', 'WHITE', 1152000.0),
               (metal_spec_id5, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Gold 14K - Rose', 'ROSE', 888000.0),
               (metal_spec_id6, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Gold 18K - Rose', 'ROSE', 1104000.0);

        -- Design collections
        design_collection_id1 := nextval('design_collection_seq');
        design_collection_id2 := nextval('design_collection_seq');
        design_collection_id3 := nextval('design_collection_seq');

        INSERT INTO tbl_design_collection (design_collection_id, created_at, create_by, modified_at, modified_by, state,
                                           opt_version, name, description)
        VALUES (design_collection_id1, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Eternal Bond',
                '''Eternal Bond'' symbolizes the lasting connection between two souls. With designs that reflect harmony and unity, these rings are crafted to honor the beauty of commitment. Each couple is carefully paired to complement each other, representing the perfect balance of love and partnership, making these rings a timeless choice for couples who value tradition and elegance.'),
               (design_collection_id2, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Timeless Elegance',
                '''Timeless Elegance'' is inspired by the stars and celestial beauty, offering designs that capture the infinite nature of love. These rings feature graceful, refined details, perfect for couples who want a touch of sophistication. Each design pair is a unique representation of the bond that stands the test of time, bringing both style and meaning to your special day.'),
               (design_collection_id3, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Infinity Love',
                '''Infinity Love'' celebrates the boundless nature of love. With each design couple symbolizing the everlasting devotion between two partners, these rings are crafted to convey both grace and strength. Perfect for modern couples, the collection combines contemporary aesthetics with timeless sentiments, making it a beautiful testament to love that knows no limits.');

        -- Design couple images
        design_couple_image_id1 := nextval('image_seq');
        design_couple_image_id2 := nextval('image_seq');
        design_couple_image_id3 := nextval('image_seq');
        design_couple_image_id4 := nextval('image_seq');
        design_couple_image_id5 := nextval('image_seq');
        design_couple_image_id6 := nextval('image_seq');
        design_couple_image_id7 := nextval('image_seq');
        design_couple_image_id8 := nextval('image_seq');
        design_couple_image_id9 := nextval('image_seq');

        INSERT INTO tbl_image (image_id, created_at, create_by, modified_at, modified_by, state, opt_version, url)
        VALUES (design_couple_image_id1, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-couple-unity_1729022054942.jpg'),
               (design_couple_image_id2, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-couple-luminous_1729022272296.jpg'),
               (design_couple_image_id3, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-couple-traquility_1729022367681.jpg'),
               (design_couple_image_id4, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-couple-cosmos_1729022431708.jpg'),
               (design_couple_image_id5, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-couple-eclipse_1729022490966.jpg'),
               (design_couple_image_id6, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-couple-horizon_1729022606055.jpg'),
               (design_couple_image_id7, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-couple-reverence_1729022664674.jpg'),
               (design_couple_image_id8, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-couple-endearment_1729022719618.jpg'),
               (design_couple_image_id9, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-couple-devotion_1729022813105.jpg');

        -- Design couples
        design_couple_id1 := nextval('design_couple_seq');
        design_couple_id2 := nextval('design_couple_seq');
        design_couple_id3 := nextval('design_couple_seq');
        design_couple_id4 := nextval('design_couple_seq');
        design_couple_id5 := nextval('design_couple_seq');
        design_couple_id6 := nextval('design_couple_seq');
        design_couple_id7 := nextval('design_couple_seq');
        design_couple_id8 := nextval('design_couple_seq');
        design_couple_id9 := nextval('design_couple_seq');

        INSERT INTO tbl_design_couple (design_couple_id, created_at, create_by, modified_at, modified_by, state,
                                       opt_version, preview_image_id, name, description)
        VALUES (design_couple_id1, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                design_couple_image_id1, 'Unity',
                '''Unity'' represents the harmonious balance between two souls, perfectly complementing each other. Their intricate design embodies the strength and unity found in lasting partnerships, making them the ideal symbol for a love that stands the test of time.'),
               (design_couple_id2, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                design_couple_image_id2, 'Luminous',
                '''Luminous'' captures the dazzling glow of love with its sparkling details and timeless elegance. This design couple is for those who seek to celebrate the light and brilliance that love brings into their lives, reflecting the clarity and beauty of a deep connection.'),
               (design_couple_id3, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                design_couple_image_id3, 'Tranquility',
                '''Tranquility'' reflects the peaceful bond between two partners who bring each other comfort. Simple yet elegant, these rings are designed for couples who value serenity and stability, creating a calming presence in each other''s lives.'),
               (design_couple_id4, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                design_couple_image_id4, 'Cosmos',
                '''Cosmos'' is a reflection of a love that transcends space and time. Inspired by the stars, this design couple captures the boundless nature of a relationship that is ever-expanding, perfect for couples who dream of exploring the universe together.'),
               (design_couple_id5, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                design_couple_image_id5, 'Eclipse',
                '''Eclipse'' blends the beauty of dawn and starlight into a single breathtaking design. This couple’s elegance and balance are ideal for those whose love shines brightly, offering a beautiful connection that grows more radiant with every moment shared.'),
               (design_couple_id6, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                design_couple_image_id6, 'Horizon',
                '''Horizon'' symbolizes the perfect balance between light and dark, moon and sun, day and night. This design couple reflects a relationship where two forces blend seamlessly, creating a bond that balances and complements both partners'' strengths.'),
               (design_couple_id7, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                design_couple_image_id7, 'Reverence',
                '''Reverence'' captures the grace and dignity of a love built on mutual respect. This design couple is crafted for those who hold their relationship in high regard, celebrating the elegance and honor that form the foundation of their commitment to each other.'),
               (design_couple_id8, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                design_couple_image_id8, 'Endearment',
                '''Endearment'' represents a deep sense of care and devotion between two people. These rings are a perfect symbol of the cherished moments and loyalty that keep a relationship thriving, serving as a constant reminder of love’s enduring power.'),
               (design_couple_id9, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                design_couple_image_id9, 'Devotion',
                '''Devotion'' celebrates the deep admiration and affection that partners have for one another. This design couple reflects the shared admiration that grows stronger over time, making them a beautiful representation of a love that continues to deepen.');

        -- Design blueprints
        design_blueprint_id1 := nextval('document_seq');
        design_blueprint_id2 := nextval('document_seq');
        design_blueprint_id3 := nextval('document_seq');
        design_blueprint_id4 := nextval('document_seq');
        design_blueprint_id5 := nextval('document_seq');
        design_blueprint_id6 := nextval('document_seq');
        design_blueprint_id7 := nextval('document_seq');
        design_blueprint_id8 := nextval('document_seq');
        design_blueprint_id9 := nextval('document_seq');
        design_blueprint_id10 := nextval('document_seq');
        design_blueprint_id11 := nextval('document_seq');
        design_blueprint_id12 := nextval('document_seq');
        design_blueprint_id13 := nextval('document_seq');
        design_blueprint_id14 := nextval('document_seq');
        design_blueprint_id15 := nextval('document_seq');
        design_blueprint_id16 := nextval('document_seq');
        design_blueprint_id17 := nextval('document_seq');
        design_blueprint_id18 := nextval('document_seq');

        INSERT INTO tbl_document (document_id, created_at, create_by, modified_at, modified_by, state, opt_version, url)
        VALUES (design_blueprint_id1, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-harmonia_1729022431708.pdf'),
               (design_blueprint_id2, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-concordia_1729022432708.pdf'),
               (design_blueprint_id3, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-radiance_1729022433708.pdf'),
               (design_blueprint_id4, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-brilliance_1729022434708.pdf'),
               (design_blueprint_id5, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-serenity_1729022435708.pdf'),
               (design_blueprint_id6, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-solace_1729022436708.pdf'),
               (design_blueprint_id7, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-celeste_1729022437708.pdf'),
               (design_blueprint_id8, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-orion_1729022438708.pdf'),
               (design_blueprint_id9, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-aurora_1729022439708.pdf'),
               (design_blueprint_id10, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-stellar_1729022440708.pdf'),
               (design_blueprint_id11, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-luna_1729022441708.pdf'),
               (design_blueprint_id12, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-apollo_1729022442708.pdf'),
               (design_blueprint_id13, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-grace_1729022443708.pdf'),
               (design_blueprint_id14, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-honor_1729022444708.pdf'),
               (design_blueprint_id15, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-cherish_1729022445708.pdf'),
               (design_blueprint_id16, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-devotion_1729022446708.pdf'),
               (design_blueprint_id17, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-adore_1729022447708.pdf'),
               (design_blueprint_id18, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-blueprint-admire_1729022448708.pdf');

        -- Designs
        design_id1 := nextval('design_seq');
        design_id2 := nextval('design_seq');
        design_id3 := nextval('design_seq');
        design_id4 := nextval('design_seq');
        design_id5 := nextval('design_seq');
        design_id6 := nextval('design_seq');
        design_id7 := nextval('design_seq');
        design_id8 := nextval('design_seq');
        design_id9 := nextval('design_seq');
        design_id10 := nextval('design_seq');
        design_id11 := nextval('design_seq');
        design_id12 := nextval('design_seq');
        design_id13 := nextval('design_seq');
        design_id14 := nextval('design_seq');
        design_id15 := nextval('design_seq');
        design_id16 := nextval('design_seq');
        design_id17 := nextval('design_seq');
        design_id18 := nextval('design_seq');

        INSERT INTO tbl_design (design_id, created_at, create_by, modified_at, modified_by, state, opt_version, name,
                                description, blueprint_id, characteristic, side_diamonds_count, design_collection_id,
                                metal_weight, size, design_couple_id)
        VALUES (design_id1, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Harmonia',
                'Harmonia is a symbol of balance and grace, designed for brides who embody harmony in their relationship. Its elegant and refined details represent the peace and unity a loving partner brings to their union, making it a timeless choice for brides seeking beauty and poise.',
                design_blueprint_id1, 'FEMININE', 0,
                design_collection_id1, 2.0, 16, design_couple_id1),
               (design_id2, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Concordia',
                'Concordia is crafted for grooms who value strength and unity in their partnership. The design reflects a sense of stability and harmony, perfect for men who appreciate the balance and enduring nature of their commitment to their partner.',
                design_blueprint_id2, 'MASCULINE', 1,
                design_collection_id1, 3.0, 18, design_couple_id1),
               (design_id3, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Radiance',
                'Radiance shines with brilliance, designed for brides who light up the lives of those around them. Its sparkling facets and intricate design reflect the joy and energy a loving bride brings to her relationship, making it a dazzling choice for her special day.',
                design_blueprint_id3, 'FEMININE', 2,
                design_collection_id1, 2.5, 17, design_couple_id2),
               (design_id4, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Brilliance',
                'Brilliance is designed for grooms who radiate clarity and strength. With its clean, bold lines, this ring symbolizes the brilliance a groom brings to his partnership, making it a fitting choice for those who want their love to shine brightly.',
                design_blueprint_id4, 'MASCULINE', 3,
                design_collection_id1, 3, 18, design_couple_id2),
               (design_id5, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Serenity',
                'Serenity is for brides who find peace and comfort in their relationship. Its simple yet elegant design speaks to a quiet strength and grace, making it a perfect reflection of a love that is calm, enduring, and steadfast.',
                design_blueprint_id5, 'FEMININE', 0,
                design_collection_id1, 2, 16, design_couple_id3),
               (design_id6, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Solace',
                'Solace represents a groom’s quiet, steady support in a relationship. Crafted with subtle elegance, this ring is ideal for men who provide comfort and strength to their partner, embodying a love that offers calm in the storm.',
                design_blueprint_id6, 'MASCULINE', 1,
                design_collection_id1, 3, 17, design_couple_id3),
               (design_id7, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Celeste',
                'Celeste is for brides who dream beyond the stars. With its celestial-inspired design, this ring reflects the infinite possibilities of a love that knows no bounds, perfect for women who see their relationship as a journey across the universe.',
                design_blueprint_id7, 'FEMININE', 2,
                design_collection_id2, 2, 16, design_couple_id4),
               (design_id8, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Orion',
                'Orion is a strong, celestial design for grooms who see themselves as protectors and companions in their relationship. Its bold and cosmic motifs are for men whose love is as vast as the night sky, offering stability and adventure in equal measure.',
                design_blueprint_id8, 'MASCULINE', 3,
                design_collection_id2, 3, 18, design_couple_id4),
               (design_id9, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Aurora',
                'Aurora is inspired by the first light of dawn, perfect for brides who bring warmth and new beginnings to their partnership. Its radiant and delicate design symbolizes a love that continues to grow and brighten each day, ideal for a woman whose presence lights up the room.',
                design_blueprint_id9, 'FEMININE', 0,
                design_collection_id2, 2.5, 17, design_couple_id5),
               (design_id10, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Stellar',
                'Stellar is for the groom who shines with quiet confidence. Its sleek and refined design reflects a man who brings constant light to his relationship, a steady guiding star in his partner’s life, symbolizing strength and reliability.',
                design_blueprint_id10, 'MASCULINE', 1,
                design_collection_id2, 3, 18, design_couple_id5),
               (design_id11, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Luna',
                'Luna reflects the quiet beauty of the moon, for brides who offer gentle strength and light. Its soft curves and serene design are ideal for women who embrace a calm, nurturing presence in their relationship, bringing peace and balance to their union.',
                design_blueprint_id11, 'FEMININE', 2,
                design_collection_id2, 2, 16, design_couple_id6),
               (design_id12, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Apollo',
                'Apollo represents the brilliance of the sun, designed for grooms who lead with strength and energy. Bold and bright, this ring is a symbol of a man whose love is fierce and constant, shining down on his partner with unwavering commitment.',
                design_blueprint_id12, 'MASCULINE', 3,
                design_collection_id2, 3, 18, design_couple_id6),
               (design_id13, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Grace',
                'Grace is for brides who carry themselves with elegance and dignity. Its refined lines and classic design represent a love filled with beauty and poise, making it the ideal choice for women who want their ring to reflect the grace they bring to their relationship.',
                design_blueprint_id13, 'FEMININE', 0,
                design_collection_id3, 2.5, 17, design_couple_id7),
               (design_id14, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Honor',
                'Honor is designed for grooms who value loyalty and integrity. With its strong, classic design, this ring is perfect for men who hold their relationship in the highest regard, offering a timeless symbol of the respect and devotion they bring to their partner.',
                design_blueprint_id14, 'MASCULINE', 1,
                design_collection_id3, 3, 18, design_couple_id7),
               (design_id15, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Cherish',
                'Cherish symbolizes a bride’s deep affection and care in her relationship. Its soft, delicate design is for women who nurture and protect their bond, representing a love that is cherished and cared for with tenderness and devotion.',
                design_blueprint_id15, 'FEMININE', 1,
                design_collection_id3, 2, 16, design_couple_id8),
               (design_id16, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Devotion',
                'Devotion is crafted for grooms who are dedicated and loyal to their partner. This ring’s strong, enduring design is perfect for men who approach their relationship with unwavering commitment, serving as a constant reminder of their enduring love.',
                design_blueprint_id16, 'MASCULINE', 2,
                design_collection_id3, 3, 18, design_couple_id8),
               (design_id17, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Adore',
                'Adore reflects a bride’s deep love and admiration for her partner. Its intricate details represent a love that is cherished above all else, making it the ideal choice for women who treasure their bond and hold their partner in the highest regard.',
                design_blueprint_id17, 'FEMININE', 3,
                design_collection_id3, 2, 16, design_couple_id9),
               (design_id18, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, 'Admire',
                'Admire is designed for grooms who hold their partner in the highest esteem. This ring is for men who show deep respect and admiration for their relationship, offering a design that celebrates a love that grows deeper with every passing day.',
                design_blueprint_id18, 'MASCULINE', 4,
                design_collection_id3, 3, 18, design_couple_id9);

        -- Design metal specification images
        design_metal_spec_image_id1 := nextval('image_seq');
        design_metal_spec_image_id2 := nextval('image_seq');
        design_metal_spec_image_id3 := nextval('image_seq');
        design_metal_spec_image_id4 := nextval('image_seq');
        design_metal_spec_image_id5 := nextval('image_seq');
        design_metal_spec_image_id6 := nextval('image_seq');
        design_metal_spec_image_id7 := nextval('image_seq');
        design_metal_spec_image_id8 := nextval('image_seq');
        design_metal_spec_image_id9 := nextval('image_seq');
        design_metal_spec_image_id10 := nextval('image_seq');
        design_metal_spec_image_id11 := nextval('image_seq');
        design_metal_spec_image_id12 := nextval('image_seq');
        design_metal_spec_image_id13 := nextval('image_seq');
        design_metal_spec_image_id14 := nextval('image_seq');
        design_metal_spec_image_id15 := nextval('image_seq');
        design_metal_spec_image_id16 := nextval('image_seq');
        design_metal_spec_image_id17 := nextval('image_seq');
        design_metal_spec_image_id18 := nextval('image_seq');
        design_metal_spec_image_id19 := nextval('image_seq');
        design_metal_spec_image_id20 := nextval('image_seq');
        design_metal_spec_image_id21 := nextval('image_seq');
        design_metal_spec_image_id22 := nextval('image_seq');
        design_metal_spec_image_id23 := nextval('image_seq');
        design_metal_spec_image_id24 := nextval('image_seq');
        design_metal_spec_image_id25 := nextval('image_seq');
        design_metal_spec_image_id26 := nextval('image_seq');
        design_metal_spec_image_id27 := nextval('image_seq');
        design_metal_spec_image_id28 := nextval('image_seq');
        design_metal_spec_image_id29 := nextval('image_seq');
        design_metal_spec_image_id30 := nextval('image_seq');
        design_metal_spec_image_id31 := nextval('image_seq');
        design_metal_spec_image_id32 := nextval('image_seq');
        design_metal_spec_image_id33 := nextval('image_seq');
        design_metal_spec_image_id34 := nextval('image_seq');
        design_metal_spec_image_id35 := nextval('image_seq');
        design_metal_spec_image_id36 := nextval('image_seq');
        design_metal_spec_image_id37 := nextval('image_seq');
        design_metal_spec_image_id38 := nextval('image_seq');
        design_metal_spec_image_id39 := nextval('image_seq');
        design_metal_spec_image_id40 := nextval('image_seq');
        design_metal_spec_image_id41 := nextval('image_seq');
        design_metal_spec_image_id42 := nextval('image_seq');
        design_metal_spec_image_id43 := nextval('image_seq');
        design_metal_spec_image_id44 := nextval('image_seq');
        design_metal_spec_image_id45 := nextval('image_seq');
        design_metal_spec_image_id46 := nextval('image_seq');
        design_metal_spec_image_id47 := nextval('image_seq');
        design_metal_spec_image_id48 := nextval('image_seq');
        design_metal_spec_image_id49 := nextval('image_seq');
        design_metal_spec_image_id50 := nextval('image_seq');
        design_metal_spec_image_id51 := nextval('image_seq');
        design_metal_spec_image_id52 := nextval('image_seq');
        design_metal_spec_image_id53 := nextval('image_seq');
        design_metal_spec_image_id54 := nextval('image_seq');

        INSERT INTO tbl_image (image_id, created_at, create_by, modified_at, modified_by, state, opt_version, url)
        VALUES (design_metal_spec_image_id1, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-harmonia-1_1729022431708.jpg'),
               (design_metal_spec_image_id2, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-harmonia-2_1729022432708.jpg'),
               (design_metal_spec_image_id3, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-harmonia-3_1729022433708.jpg'),
               (design_metal_spec_image_id4, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-concordia-1_1729022434708.jpg'),
               (design_metal_spec_image_id5, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-concordia-2_1729022435708.jpg'),
               (design_metal_spec_image_id6, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-concordia-3_1729022436708.jpg'),
               (design_metal_spec_image_id7, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-radiance-1_1729022437708.jpg'),
               (design_metal_spec_image_id8, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-radiance-2_1729022438708.jpg'),
               (design_metal_spec_image_id9, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-radiance-3_1729022439708.jpg'),
               (design_metal_spec_image_id10, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-brilliance-1_1729022440708.jpg'),
               (design_metal_spec_image_id11, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-brilliance-2_1729022441708.jpg'),
               (design_metal_spec_image_id12, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-brilliance-3_1729022442708.jpg'),
               (design_metal_spec_image_id13, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-serenity-1_1729022443708.jpg'),
               (design_metal_spec_image_id14, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-serenity-2_1729022444708.jpg'),
               (design_metal_spec_image_id15, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-serenity-3_1729022445708.jpg'),
               (design_metal_spec_image_id16, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-solace-1_1729022446708.jpg'),
               (design_metal_spec_image_id17, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-solace-2_1729022447708.jpg'),
               (design_metal_spec_image_id18, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-solace-3_1729022448708.jpg'),
               (design_metal_spec_image_id19, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-celeste-1_1729022449708.jpg'),
               (design_metal_spec_image_id20, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-celeste-2_1729022450708.jpg'),
               (design_metal_spec_image_id21, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-celeste-3_1729022451708.jpg'),
               (design_metal_spec_image_id22, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-orion-1_1729022452708.jpg'),
               (design_metal_spec_image_id23, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-orion-2_1729022453708.jpg'),
               (design_metal_spec_image_id24, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-orion-3_1729022454708.jpg'),
               (design_metal_spec_image_id25, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-aurora-1_1729022455708.jpg'),
               (design_metal_spec_image_id26, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-aurora-2_1729022456708.jpg'),
               (design_metal_spec_image_id27, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-aurora-3_1729022457708.jpg'),
               (design_metal_spec_image_id28, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-stellar-1_1729022458708.jpg'),
               (design_metal_spec_image_id29, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-stellar-2_1729022459708.jpg'),
               (design_metal_spec_image_id30, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-stellar-3_1729022460708.jpg'),
               (design_metal_spec_image_id31, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-luna-1_1729022461708.jpg'),
               (design_metal_spec_image_id32, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-luna-2_1729022462708.jpg'),
               (design_metal_spec_image_id33, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-luna-3_1729022463708.jpg'),
               (design_metal_spec_image_id34, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-apollo-1_1729022464708.jpg'),
               (design_metal_spec_image_id35, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-apollo-2_1729022465708.jpg'),
               (design_metal_spec_image_id36, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-apollo-3_1729022466708.jpg'),
               (design_metal_spec_image_id37, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-grace-1_1729022467708.jpg'),
               (design_metal_spec_image_id38, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-grace-2_1729022468708.jpg'),
               (design_metal_spec_image_id39, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-grace-3_1729022469708.jpg'),
               (design_metal_spec_image_id40, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-honor-1_1729022470708.jpg'),
               (design_metal_spec_image_id41, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-honor-2_1729022471708.jpg'),
               (design_metal_spec_image_id42, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-honor-3_1729022472708.jpg'),
               (design_metal_spec_image_id43, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-cherish-1_1729022473708.jpg'),
               (design_metal_spec_image_id44, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-cherish-2_1729022474708.jpg'),
               (design_metal_spec_image_id45, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-cherish-3_1729022475708.jpg'),
               (design_metal_spec_image_id46, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-devotion-1_1729022476708.jpg'),
               (design_metal_spec_image_id47, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-devotion-2_1729022477708.jpg'),
               (design_metal_spec_image_id48, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-devotion-3_1729022478708.jpg'),
               (design_metal_spec_image_id49, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-adore-1_1729022479708.jpg'),
               (design_metal_spec_image_id50, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-adore-2_1729022480708.jpg'),
               (design_metal_spec_image_id51, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-adore-3_1729022481708.jpg'),
               (design_metal_spec_image_id52, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-admire-1_1729022482708.jpg'),
               (design_metal_spec_image_id53, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-admire-2_1729022483708.jpg'),
               (design_metal_spec_image_id54, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'https://cplerings-bucket.s3.ap-southeast-1.amazonaws.com/static/static_design-metal-spec-admire-3_1729022484708.jpg');

        -- Design metal specifications
        design_metal_spec_id1 := nextval('design_metal_specification_seq');
        design_metal_spec_id2 := nextval('design_metal_specification_seq');
        design_metal_spec_id3 := nextval('design_metal_specification_seq');
        design_metal_spec_id4 := nextval('design_metal_specification_seq');
        design_metal_spec_id5 := nextval('design_metal_specification_seq');
        design_metal_spec_id6 := nextval('design_metal_specification_seq');
        design_metal_spec_id7 := nextval('design_metal_specification_seq');
        design_metal_spec_id8 := nextval('design_metal_specification_seq');
        design_metal_spec_id9 := nextval('design_metal_specification_seq');
        design_metal_spec_id10 := nextval('design_metal_specification_seq');
        design_metal_spec_id11 := nextval('design_metal_specification_seq');
        design_metal_spec_id12 := nextval('design_metal_specification_seq');
        design_metal_spec_id13 := nextval('design_metal_specification_seq');
        design_metal_spec_id14 := nextval('design_metal_specification_seq');
        design_metal_spec_id15 := nextval('design_metal_specification_seq');
        design_metal_spec_id16 := nextval('design_metal_specification_seq');
        design_metal_spec_id17 := nextval('design_metal_specification_seq');
        design_metal_spec_id18 := nextval('design_metal_specification_seq');
        design_metal_spec_id19 := nextval('design_metal_specification_seq');
        design_metal_spec_id20 := nextval('design_metal_specification_seq');
        design_metal_spec_id21 := nextval('design_metal_specification_seq');
        design_metal_spec_id22 := nextval('design_metal_specification_seq');
        design_metal_spec_id23 := nextval('design_metal_specification_seq');
        design_metal_spec_id24 := nextval('design_metal_specification_seq');
        design_metal_spec_id25 := nextval('design_metal_specification_seq');
        design_metal_spec_id26 := nextval('design_metal_specification_seq');
        design_metal_spec_id27 := nextval('design_metal_specification_seq');
        design_metal_spec_id28 := nextval('design_metal_specification_seq');
        design_metal_spec_id29 := nextval('design_metal_specification_seq');
        design_metal_spec_id30 := nextval('design_metal_specification_seq');
        design_metal_spec_id31 := nextval('design_metal_specification_seq');
        design_metal_spec_id32 := nextval('design_metal_specification_seq');
        design_metal_spec_id33 := nextval('design_metal_specification_seq');
        design_metal_spec_id34 := nextval('design_metal_specification_seq');
        design_metal_spec_id35 := nextval('design_metal_specification_seq');
        design_metal_spec_id36 := nextval('design_metal_specification_seq');
        design_metal_spec_id37 := nextval('design_metal_specification_seq');
        design_metal_spec_id38 := nextval('design_metal_specification_seq');
        design_metal_spec_id39 := nextval('design_metal_specification_seq');
        design_metal_spec_id40 := nextval('design_metal_specification_seq');
        design_metal_spec_id41 := nextval('design_metal_specification_seq');
        design_metal_spec_id42 := nextval('design_metal_specification_seq');
        design_metal_spec_id43 := nextval('design_metal_specification_seq');
        design_metal_spec_id44 := nextval('design_metal_specification_seq');
        design_metal_spec_id45 := nextval('design_metal_specification_seq');
        design_metal_spec_id46 := nextval('design_metal_specification_seq');
        design_metal_spec_id47 := nextval('design_metal_specification_seq');
        design_metal_spec_id48 := nextval('design_metal_specification_seq');
        design_metal_spec_id49 := nextval('design_metal_specification_seq');
        design_metal_spec_id50 := nextval('design_metal_specification_seq');
        design_metal_spec_id51 := nextval('design_metal_specification_seq');
        design_metal_spec_id52 := nextval('design_metal_specification_seq');
        design_metal_spec_id53 := nextval('design_metal_specification_seq');
        design_metal_spec_id54 := nextval('design_metal_specification_seq');

        INSERT INTO tbl_design_metal_specification (design_metal_specification_id, created_at, create_by, modified_at,
                                                    modified_by, state, opt_version, design_id, metal_specification_id,
                                                    image_id)
        VALUES (design_metal_spec_id1, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id1,
                metal_spec_id1, design_metal_spec_image_id1),
               (design_metal_spec_id2, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id1,
                metal_spec_id3, design_metal_spec_image_id2),
               (design_metal_spec_id3, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id1,
                metal_spec_id6, design_metal_spec_image_id3),
               (design_metal_spec_id4, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id2,
                metal_spec_id2, design_metal_spec_image_id4),
               (design_metal_spec_id5, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id2,
                metal_spec_id3, design_metal_spec_image_id5),
               (design_metal_spec_id6, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id2,
                metal_spec_id6, design_metal_spec_image_id6),
               (design_metal_spec_id7, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id3,
                metal_spec_id2, design_metal_spec_image_id7),
               (design_metal_spec_id8, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id3,
                metal_spec_id3, design_metal_spec_image_id8),
               (design_metal_spec_id9, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id3,
                metal_spec_id6, design_metal_spec_image_id9),
               (design_metal_spec_id10, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id4,
                metal_spec_id2, design_metal_spec_image_id10),
               (design_metal_spec_id11, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id4,
                metal_spec_id3, design_metal_spec_image_id11),
               (design_metal_spec_id12, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id4,
                metal_spec_id5, design_metal_spec_image_id12),
               (design_metal_spec_id13, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id5,
                metal_spec_id2, design_metal_spec_image_id13),
               (design_metal_spec_id14, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id5,
                metal_spec_id3, design_metal_spec_image_id14),
               (design_metal_spec_id15, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id5,
                metal_spec_id5, design_metal_spec_image_id15),
               (design_metal_spec_id16, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id6,
                metal_spec_id2, design_metal_spec_image_id16),
               (design_metal_spec_id17, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id6,
                metal_spec_id3, design_metal_spec_image_id17),
               (design_metal_spec_id18, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id6,
                metal_spec_id5, design_metal_spec_image_id18),
               (design_metal_spec_id19, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id7,
                metal_spec_id1, design_metal_spec_image_id19),
               (design_metal_spec_id20, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id7,
                metal_spec_id4, design_metal_spec_image_id20),
               (design_metal_spec_id21, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id7,
                metal_spec_id6, design_metal_spec_image_id21),
               (design_metal_spec_id22, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id8,
                metal_spec_id1, design_metal_spec_image_id22),
               (design_metal_spec_id23, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id8,
                metal_spec_id3, design_metal_spec_image_id23),
               (design_metal_spec_id24, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id8,
                metal_spec_id5, design_metal_spec_image_id24),
               (design_metal_spec_id25, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id9,
                metal_spec_id1, design_metal_spec_image_id25),
               (design_metal_spec_id26, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id9,
                metal_spec_id3, design_metal_spec_image_id26),
               (design_metal_spec_id27, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id9,
                metal_spec_id5, design_metal_spec_image_id27),
               (design_metal_spec_id28, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id10,
                metal_spec_id1, design_metal_spec_image_id28),
               (design_metal_spec_id29, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id10,
                metal_spec_id4, design_metal_spec_image_id29),
               (design_metal_spec_id30, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id10,
                metal_spec_id6, design_metal_spec_image_id30),
               (design_metal_spec_id31, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id11,
                metal_spec_id1, design_metal_spec_image_id31),
               (design_metal_spec_id32, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id11,
                metal_spec_id4, design_metal_spec_image_id32),
               (design_metal_spec_id33, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id11,
                metal_spec_id5, design_metal_spec_image_id33),
               (design_metal_spec_id34, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id12,
                metal_spec_id1, design_metal_spec_image_id34),
               (design_metal_spec_id35, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id12,
                metal_spec_id4, design_metal_spec_image_id35),
               (design_metal_spec_id36, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id12,
                metal_spec_id5, design_metal_spec_image_id36),
               (design_metal_spec_id37, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id13,
                metal_spec_id2, design_metal_spec_image_id37),
               (design_metal_spec_id38, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id13,
                metal_spec_id4, design_metal_spec_image_id38),
               (design_metal_spec_id39, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id13,
                metal_spec_id6, design_metal_spec_image_id39),
               (design_metal_spec_id40, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id14,
                metal_spec_id1, design_metal_spec_image_id40),
               (design_metal_spec_id41, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id14,
                metal_spec_id4, design_metal_spec_image_id41),
               (design_metal_spec_id42, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id14,
                metal_spec_id6, design_metal_spec_image_id42),
               (design_metal_spec_id43, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id15,
                metal_spec_id1, design_metal_spec_image_id43),
               (design_metal_spec_id44, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id15,
                metal_spec_id4, design_metal_spec_image_id44),
               (design_metal_spec_id45, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id15,
                metal_spec_id5, design_metal_spec_image_id45),
               (design_metal_spec_id46, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id16,
                metal_spec_id1, design_metal_spec_image_id46),
               (design_metal_spec_id47, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id16,
                metal_spec_id3, design_metal_spec_image_id47),
               (design_metal_spec_id48, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id16,
                metal_spec_id6, design_metal_spec_image_id48),
               (design_metal_spec_id49, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id17,
                metal_spec_id1, design_metal_spec_image_id49),
               (design_metal_spec_id50, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id17,
                metal_spec_id3, design_metal_spec_image_id50),
               (design_metal_spec_id51, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id17,
                metal_spec_id5, design_metal_spec_image_id51),
               (design_metal_spec_id52, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id18,
                metal_spec_id2, design_metal_spec_image_id52),
               (design_metal_spec_id53, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id18,
                metal_spec_id4, design_metal_spec_image_id53),
               (design_metal_spec_id54, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id18,
                metal_spec_id6, design_metal_spec_image_id54);

        -- design diamond specification
        design_diamond_spec_id1 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id2 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id3 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id4 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id5 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id6 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id7 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id8 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id9 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id10 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id11 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id12 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id13 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id14 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id15 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id16 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id17 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id18 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id19 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id20 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id21 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id22 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id23 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id24 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id25 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id26 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id27 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id28 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id29 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id30 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id31 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id32 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id33 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id34 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id35 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id36 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id37 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id38 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id39 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id40 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id41 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id42 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id43 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id44 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id45 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id46 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id47 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id48 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id49 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id50 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id51 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id52 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id53 := nextval('design_diamond_specification_seq');
        design_diamond_spec_id54 := nextval('design_diamond_specification_seq');

        INSERT INTO tbl_design_diamond_specification (design_diamond_specification_id, created_at, create_by,
                                                      modified_at, modified_by, state, opt_version, design_id,
                                                      diamond_specification_id)
        VALUES (design_diamond_spec_id1, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id1,
                diamond_spec_round_id3),
               (design_diamond_spec_id2, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id1,
                diamond_spec_heart_id2),
               (design_diamond_spec_id3, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id1,
                diamond_spec_heart_id3),
               (design_diamond_spec_id4, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id2,
                diamond_spec_heart_id2),
               (design_diamond_spec_id5, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id2,
                diamond_spec_round_id2),
               (design_diamond_spec_id6, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id2,
                diamond_spec_heart_id2),
               (design_diamond_spec_id7, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id3,
                diamond_spec_round_id3),
               (design_diamond_spec_id8, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id3,
                diamond_spec_round_id2),
               (design_diamond_spec_id9, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id3,
                diamond_spec_round_id2),
               (design_diamond_spec_id10, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id4,
                diamond_spec_oval_id3),
               (design_diamond_spec_id11, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id4,
                diamond_spec_heart_id1),
               (design_diamond_spec_id12, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id4,
                diamond_spec_heart_id3),
               (design_diamond_spec_id13, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id5,
                diamond_spec_heart_id3),
               (design_diamond_spec_id14, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id5,
                diamond_spec_round_id3),
               (design_diamond_spec_id15, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id5,
                diamond_spec_round_id3),
               (design_diamond_spec_id16, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id6,
                diamond_spec_oval_id2),
               (design_diamond_spec_id17, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id6,
                diamond_spec_oval_id2),
               (design_diamond_spec_id18, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id6,
                diamond_spec_round_id3),
               (design_diamond_spec_id19, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id7,
                diamond_spec_heart_id1),
               (design_diamond_spec_id20, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id7,
                diamond_spec_round_id1),
               (design_diamond_spec_id21, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id7,
                diamond_spec_round_id1),
               (design_diamond_spec_id22, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id8,
                diamond_spec_oval_id1),
               (design_diamond_spec_id23, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id8,
                diamond_spec_round_id2),
               (design_diamond_spec_id24, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id8,
                diamond_spec_round_id1),
               (design_diamond_spec_id25, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id9,
                diamond_spec_oval_id3),
               (design_diamond_spec_id26, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id9,
                diamond_spec_round_id1),
               (design_diamond_spec_id27, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id9,
                diamond_spec_round_id2),
               (design_diamond_spec_id28, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id10,
                diamond_spec_heart_id1),
               (design_diamond_spec_id29, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id10,
                diamond_spec_oval_id3),
               (design_diamond_spec_id30, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id10,
                diamond_spec_round_id2),
               (design_diamond_spec_id31, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id11,
                diamond_spec_heart_id1),
               (design_diamond_spec_id32, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id11,
                diamond_spec_oval_id2),
               (design_diamond_spec_id33, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id11,
                diamond_spec_heart_id2),
               (design_diamond_spec_id34, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id12,
                diamond_spec_round_id1),
               (design_diamond_spec_id35, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id12,
                diamond_spec_oval_id2),
               (design_diamond_spec_id36, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id12,
                diamond_spec_heart_id3),
               (design_diamond_spec_id37, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id13,
                diamond_spec_round_id2),
               (design_diamond_spec_id38, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id13,
                diamond_spec_heart_id2),
               (design_diamond_spec_id39, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id13,
                diamond_spec_oval_id2),
               (design_diamond_spec_id40, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id14,
                diamond_spec_round_id2),
               (design_diamond_spec_id41, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id14,
                diamond_spec_round_id3),
               (design_diamond_spec_id42, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id14,
                diamond_spec_heart_id1),
               (design_diamond_spec_id43, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id15,
                diamond_spec_oval_id3),
               (design_diamond_spec_id44, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id15,
                diamond_spec_round_id2),
               (design_diamond_spec_id45, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id15,
                diamond_spec_heart_id3),
               (design_diamond_spec_id46, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id16,
                diamond_spec_heart_id2),
               (design_diamond_spec_id47, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id16,
                diamond_spec_heart_id3),
               (design_diamond_spec_id48, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id16,
                diamond_spec_round_id3),
               (design_diamond_spec_id49, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id17,
                diamond_spec_round_id2),
               (design_diamond_spec_id50, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id17,
                diamond_spec_round_id2),
               (design_diamond_spec_id51, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id17,
                diamond_spec_round_id2),
               (design_diamond_spec_id52, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id18,
                diamond_spec_oval_id1),
               (design_diamond_spec_id53, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id18,
                diamond_spec_heart_id3),
               (design_diamond_spec_id54, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0, design_id18,
                diamond_spec_heart_id1);
    END;
$$;