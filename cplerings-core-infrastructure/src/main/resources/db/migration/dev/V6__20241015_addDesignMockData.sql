DO
$$
    DECLARE
        diamond_spec_id1 BIGINT;
        diamond_spec_id2 BIGINT;
        diamond_spec_id3 BIGINT;
    BEGIN
        diamond_spec_id1 := nextval('diamond_specification_seq');
        diamond_spec_id2 := nextval('diamond_specification_seq');
        diamond_spec_id3 := nextval('diamond_specification_seq');

        INSERT INTO tbl_diamond_specification (diamond_specification_id, created_at, create_by, modified_at,
                                               modified_by, state, opt_version, name, weight, color, clarity, shape,
                                               price)
        VALUES ();
    END;
$$;

INSERT INTO tbl_design_collection (design_collection_id, created_at, create_by, modified_at, modified_by, state,
                                   opt_version, name, description)
VALUES (nextval('design_collection_seq'), current_timestamp, 'CoupleRings',
        current_timestamp, 'CoupleRings', 'ACTIVE', 0,
        'Eternal Bond Collection',
        'The Eternal Bond Collection symbolizes the lasting connection between two souls. With designs that reflect harmony and unity, these rings are crafted to honor the beauty of commitment. Each design couple is carefully paired to complement each other, representing the perfect balance of love and partnership, making these rings a timeless choice for couples who value tradition and elegance.'),
       (nextval('design_collection_seq'), current_timestamp, 'CoupleRings',
        current_timestamp, 'CoupleRings', 'ACTIVE', 0,
        'Timeless Elegance Collection',
        'The Timeless Elegance Collection is inspired by the stars and celestial beauty, offering designs that capture the infinite nature of love. These rings feature graceful, refined details, perfect for couples who want a touch of sophistication. Each design pair is a unique representation of the bond that stands the test of time, bringing both style and meaning to your special day.'),
       (nextval('design_collection_seq'), current_timestamp, 'CoupleRings',
        current_timestamp, 'CoupleRings', 'ACTIVE', 0,
        'Infinity Love Collection',
        'The Infinity Love Collection celebrates the boundless nature of love. With each design couple symbolizing the everlasting devotion between two partners, these rings are crafted to convey both grace and strength. Perfect for modern couples, the collection combines contemporary aesthetics with timeless sentiments, making it a beautiful testament to love that knows no limits.');