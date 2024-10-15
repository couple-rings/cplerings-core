DO
$$
    DECLARE
        diamond_spec_heart_id1 BIGINT;
        diamond_spec_heart_id2 BIGINT;
        diamond_spec_heart_id3 BIGINT;
        diamond_spec_oval_id1  BIGINT;
        diamond_spec_oval_id2  BIGINT;
        diamond_spec_oval_id3  BIGINT;
        diamond_spec_round_id1 BIGINT;
        diamond_spec_round_id2 BIGINT;
        diamond_spec_round_id3 BIGINT;
        metal_spec_id1         BIGINT;
        metal_spec_id2         BIGINT;
        metal_spec_id3         BIGINT;
        metal_spec_id4         BIGINT;
        metal_spec_id5         BIGINT;
        metal_spec_id6         BIGINT;
        design_collection_id1  BIGINT;
        design_collection_id2  BIGINT;
        design_collection_id3  BIGINT;
        design_couple_id1      BIGINT;
        design_couple_id2      BIGINT;
        design_couple_id3      BIGINT;
        design_couple_id4      BIGINT;
        design_couple_id5      BIGINT;
        design_couple_id6      BIGINT;
        design_couple_id7      BIGINT;
        design_couple_id8      BIGINT;
        design_couple_id9      BIGINT;
    BEGIN
        diamond_spec_heart_id1 := nextval('diamond_specification_seq');
        diamond_spec_heart_id2 := nextval('diamond_specification_seq');
        diamond_spec_heart_id3 := nextval('diamond_specification_seq');

        diamond_spec_oval_id1 := nextval('diamond_specification_seq');
        diamond_spec_oval_id2 := nextval('diamond_specification_seq');
        diamond_spec_oval_id3 := nextval('diamond_specification_seq');

        diamond_spec_round_id1 := nextval('diamond_specification_seq');
        diamond_spec_round_id2 := nextval('diamond_specification_seq');
        diamond_spec_round_id3 := nextval('diamond_specification_seq');

        metal_spec_id1 := nextval('metal_specification_seq');
        metal_spec_id2 := nextval('metal_specification_seq');
        metal_spec_id3 := nextval('metal_specification_seq');
        metal_spec_id4 := nextval('metal_specification_seq');
        metal_spec_id5 := nextval('metal_specification_seq');
        metal_spec_id6 := nextval('metal_specification_seq');

        design_collection_id1 := nextval('design_collection_seq');
        design_collection_id2 := nextval('design_collection_seq');
        design_collection_id3 := nextval('design_collection_seq');

        design_couple_id1 := nextval('design_couple_seq');
        design_couple_id2 := nextval('design_couple_seq');
        design_couple_id3 := nextval('design_couple_seq');
        design_couple_id4 := nextval('design_couple_seq');
        design_couple_id5 := nextval('design_couple_seq');
        design_couple_id6 := nextval('design_couple_seq');
        design_couple_id7 := nextval('design_couple_seq');
        design_couple_id8 := nextval('design_couple_seq');
        design_couple_id9 := nextval('design_couple_seq');

        -- Diamond specifications
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
               (metal_spec_id3, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                'Gold 18K - Rose', 'ROSE', 1104000.0);

        -- Design collections
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

        -- Design couples
        INSERT INTO tbl_design_couple (design_couple_id, created_at, create_by, modified_at, modified_by, state,
                                       opt_version, preview_image_id, name, description)
        VALUES (design_couple_id1, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                null, 'Unity',
                '''Unity'' represents the harmonious balance between two souls, perfectly complementing each other. Their intricate design embodies the strength and unity found in lasting partnerships, making them the ideal symbol for a love that stands the test of time.'),
               (design_couple_id2, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                null, 'Luminous',
                '''Luminous'' captures the dazzling glow of love with its sparkling details and timeless elegance. This design couple is for those who seek to celebrate the light and brilliance that love brings into their lives, reflecting the clarity and beauty of a deep connection.'),
               (design_couple_id3, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                null, 'Tranquility',
                '''Tranquility'' reflects the peaceful bond between two partners who bring each other comfort. Simple yet elegant, these rings are designed for couples who value serenity and stability, creating a calming presence in each other''s lives.'),
               (design_couple_id4, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                null, 'Cosmos',
                '''Cosmos'' is a reflection of a love that transcends space and time. Inspired by the stars, this design couple captures the boundless nature of a relationship that is ever-expanding, perfect for couples who dream of exploring the universe together.'),
               (design_couple_id5, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                null, 'Eclipse',
                '''Eclipse'' blends the beauty of dawn and starlight into a single breathtaking design. This couple’s elegance and balance are ideal for those whose love shines brightly, offering a beautiful connection that grows more radiant with every moment shared.'),
               (design_couple_id6, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                null, 'Horizon',
                '''Horizon'' symbolizes the perfect balance between light and dark, moon and sun, day and night. This design couple reflects a relationship where two forces blend seamlessly, creating a bond that balances and complements both partners'' strengths.'),
               (design_couple_id7, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                null, 'Reverence',
                '''Reverence'' captures the grace and dignity of a love built on mutual respect. This design couple is crafted for those who hold their relationship in high regard, celebrating the elegance and honor that form the foundation of their commitment to each other.'),
               (design_couple_id8, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                null, 'Endearment',
                '''Endearment'' represents a deep sense of care and devotion between two people. These rings are a perfect symbol of the cherished moments and loyalty that keep a relationship thriving, serving as a constant reminder of love’s enduring power.'),
               (design_couple_id9, current_timestamp, 'CoupleRings',
                current_timestamp, 'CoupleRings', 'ACTIVE', 0,
                null, 'Devotion',
                '''Devotion'' celebrates the deep admiration and affection that partners have for one another. This design couple reflects the shared admiration that grows stronger over time, making them a beautiful representation of a love that continues to deepen.');
    END;
$$;