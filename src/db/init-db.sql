INSERT INTO artist (
    id,
    stage_name,
    real_name,
    bio,
    avatar_url,
    country,
    hometown,
    is_verified,
    created_at,
    updated_at
)
VALUES (
    uuid_generate_v4(),
    'Martin Garrix',
    'Martijn Gerard Garritsen',
    'Martin Garrix is a Dutch DJ and record producer known for hits like "Animals", "Scared to Be Lonely", and "High on Life". He has headlined major festivals like Tomorrowland and Ultra Music Festival.',
    'https://your-s3-bucket-url.com/images/martin-garrix.jpg',
    'Netherlands',
    'Amstelveen',
    true,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO artist (
    id,
    stage_name,
    real_name,
    bio,
    avatar_url,
    country,
    hometown,
    is_verified,
    created_at,
    updated_at
)
VALUES (
    uuid_generate_v4(),
    'Zedd',
    'Anton Zaslavski',
    'Zedd is a Russian-German DJ, record producer, and songwriter known for chart-topping tracks like "Clarity", "Stay", and "The Middle". He blends EDM with pop and has collaborated with artists like Alessia Cara, Foxes, and Maren Morris.',
    'https://your-s3-bucket-url.com/images/zedd.jpg',
    'Germany',
    'Kaiserslautern',
    true,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO artist_events (event_id, artist_id)
VALUES('e0033333-cccc-dddd-eeee-333333333333', '5704e502-c400-43bb-b637-e1c0bc1c0a16');

INSERT INTO artist_events (event_id, artist_id)
VALUES('e0033333-cccc-dddd-eeee-333333333333', '5704e502-c400-43bb-b637-e1c0bc1c0a16');

SELECT * FROM artist_events;

SELECT * FROM event;
UPDATE event SET event_type = 'SOLO_PERFORMANCE' WHERE event_type = 'solo performance';
UPDATE event SET event_type = 'FESTIVAL' WHERE event_type = 'festival';
UPDATE event SET event_type = 'BACK_TO_BACK' WHERE event_type = 'b2b';

UPDATE event SET age_restriction = 'EIGHTEEN_PLUS' WHERE age_restriction = '18+';
UPDATE event SET age_restriction = 'TWENTY_ONE_PLUS' WHERE age_restriction = '21+';
UPDATE event SET age_restriction = 'ALL_AGES' WHERE age_restriction = 'All Ages';


SELECT * FROM artist;
SELECT * FROM addresses;

INSERT INTO addresses (id, venue_name, street, city, state, zip, country)
VALUES 
('f001a111-8d9e-4d65-9d09-1a1e1a111111', 'Red Rocks Amphitheatre', '18300 W Alameda Pkwy', 'Morrison', 'CO', '80465', 'USA'),
('f002b222-7c8f-4b76-8d1f-2b2e2b222222', 'Echostage', '2135 Queens Chapel Rd NE', 'Washington', 'DC', '20018', 'USA'),
('f003c333-9a7d-4c65-9f2a-3c3f3c333333', 'NOS Events Center', '689 S E St', 'San Bernardino', 'CA', '92408', 'USA'),
('f004d444-6b5a-4d32-9e3f-4d4d4d444444', 'Brooklyn Mirage', '140 Stewart Ave', 'Brooklyn', 'NY', '11237', 'USA'),
('f005e555-5e4f-4e21-9d4f-5e5e5e555555', 'Zouk Nightclub', '3000 S Las Vegas Blvd', 'Las Vegas', 'NV', '89109', 'USA');

INSERT INTO event (event_id, name, description, start_date, end_date, address, event_type, genre, external_link, image_path, support_email, age_restriction)
VALUES 
('e0011111-aaaa-bbbb-cccc-111111111111', 'Zeds Dead: Deadbeats Tour', 'Zeds Dead returns with their legendary Deadbeats tour featuring heavy bass and stunning visuals.', '2025-07-18T20:00:00Z', '2025-07-19T02:00:00Z', 'f001a111-8d9e-4d65-9d09-1a1e1a111111', 'solo performance', 'Dubstep', 'https://www.deadbeatsofficial.com', '/images/events/deadbeats.jpg', 'info@deadbeatstour.com', '18+'),

('e0022222-bbbb-cccc-dddd-222222222222', 'Eric Prydz presents HOLO', 'A mind-bending audiovisual journey by Eric Prydz with his revolutionary HOLO live show.', '2025-08-10T21:00:00Z', '2025-08-11T02:30:00Z', 'f004d444-6b5a-4d32-9e3f-4d4d4d444444', 'solo performance', 'Progressive House', 'https://ericprydz.com/holo', '/images/events/holo.jpg', 'support@prydzshows.com', '21+'),

('e0033333-cccc-dddd-eeee-333333333333', 'Electric Daisy Carnival (EDC) 2025', 'The world’s premier EDM festival returns to Las Vegas for a weekend of light, sound, and love.', '2025-05-16T17:00:00Z', '2025-05-19T06:00:00Z', 'f005e555-5e4f-4e21-9d4f-5e5e5e555555', 'festival', 'Mixed EDM', 'https://lasvegas.electricdaisycarnival.com', '/images/events/edc.jpg', 'help@edc.com', '18+'),

('e0044444-dddd-eeee-ffff-444444444444', 'Brownies & Lemonade: Open Air', 'A carefully curated outdoor event with rising stars of the underground EDM scene.', '2025-09-14T15:00:00Z', '2025-09-14T23:00:00Z', 'f003c333-9a7d-4c65-9f2a-3c3f3c333333', 'festival', 'Trap / Future Bass', 'https://browniesandlemonade.com', '/images/events/bnl.jpg', 'support@browniesandlemonade.com', 'All Ages'),

('e0055555-eeee-ffff-aaaa-555555555555', 'Charlotte de Witte x Amelie Lens: Techno Titans', 'Two of the top techno artists go B2B in an intense night of relentless beats.', '2025-10-04T22:00:00Z', '2025-10-05T04:00:00Z', 'f002b222-7c8f-4b76-8d1f-2b2e2b222222', 'b2b', 'Techno', 'https://technotitans.live', '/images/events/technotitans.jpg', 'info@technotitans.live', '21+');

-- Corrected DC Venues
INSERT INTO addresses (id, venue_name, street, city, state, zip, country)
VALUES 
('f006f666-1a1a-4b4b-8c8c-6d6d6d666666', 'Flash', '645 Florida Ave NW', 'Washington', 'DC', '20001', 'USA'),
('f007f777-2b2b-5c5c-9d9d-7e7e7e777777', 'Soundcheck', '1420 K St NW', 'Washington', 'DC', '20005', 'USA');

-- Corrected LA Venues
INSERT INTO addresses (id, venue_name, street, city, state, zip, country)
VALUES 
('f008f888-3c3c-6d6d-0e0e-8f8f8f888888', 'Academy LA', '6021 Hollywood Blvd', 'Los Angeles', 'CA', '90028', 'USA'),
('f009f999-4d4d-7e7e-1f1f-9f9f9f999999', 'Exchange LA', '618 S Spring St', 'Los Angeles', 'CA', '90014', 'USA'),
('f010f000-5e5e-8f8f-2a2a-0f0f0f000000', 'Los Angeles State Historic Park', '1245 N Spring St', 'Los Angeles', 'CA', '90012', 'USA');

-- DC-Based Events
INSERT INTO event (
    event_id, name, description, start_date, end_date, address, event_type, genre, 
    external_link, image_path, support_email, age_restriction
)
VALUES 
('e0066666-a1a1-b2b2-c3c3-666666666666', 'Boiler Room: Washington DC', 
 'Boiler Room brings the raw underground energy to DC’s Flash rooftop stage.', 
 '2025-07-27T18:00:00Z', '2025-07-28T01:00:00Z', 
 'f006f666-1a1a-4b4b-8c8c-6d6d6d666666', 'festival', 'House / Techno', 
 'https://boilerroom.tv', '/images/events/boilerroomdc.jpg', 'contact@boilerroom.tv', '21+'),

('e0077777-b2b2-c3c3-d4d4-777777777777', 'RL Grime at Soundcheck', 
 'Trap titan RL Grime shakes the walls of Soundcheck for an intimate DC experience.', 
 '2025-08-15T22:00:00Z', '2025-08-16T02:00:00Z', 
 'f007f777-2b2b-5c5c-9d9d-7e7e7e777777', 'solo performance', 'Trap / Bass', 
 'https://rlgrime.com', '/images/events/rlgrime.jpg', 'support@soundcheckdc.com', '18+');

-- LA-Based Events
INSERT INTO event (
    event_id, name, description, start_date, end_date, address, event_type, genre, 
    external_link, image_path, support_email, age_restriction
)
VALUES 
('e0088888-c3c3-d4d4-e5e5-888888888888', 'Above & Beyond at Academy LA', 
 'Trance icons Above & Beyond take over Academy LA for a night of emotional, soaring beats.', 
 '2025-09-07T21:00:00Z', '2025-09-08T02:00:00Z', 
 'f008f888-3c3c-6d6d-0e0e-8f8f8f888888', 'solo performance', 'Trance / Progressive', 
 'https://aboveandbeyond.nu', '/images/events/academyab.jpg', 'events@academy.la', '21+'),

('e0099999-d4d4-e5e5-f6f6-999999999999', 'Anjunadeep Open Air: LA', 
 'Sunset vibes, deep grooves, and lush ambiance at LA State Historic Park with Anjunadeep artists.', 
 '2025-08-24T15:00:00Z', '2025-08-24T23:00:00Z', 
 'f010f000-5e5e-8f8f-2a2a-0f0f0f000000', 'festival', 'Deep House / Melodic', 
 'https://anjunadeep.com', '/images/events/anjunadeep.jpg', 'info@anjunadeep.com', 'All Ages'),

('e0100000-e5e5-f6f6-a1a1-000000000000', 'REZZ: Spiral Tour at Exchange LA', 
 'REZZ returns with her hypnotic Spiral visuals and a night of midtempo madness.', 
 '2025-10-18T22:00:00Z', '2025-10-19T03:00:00Z', 
 'f009f999-4d4d-7e7e-1f1f-9f9f9f999999', 'solo performance', 'Midtempo / Bass', 
 'https://officialrezz.com', '/images/events/rezzspiral.jpg', 'tickets@exchangela.com', '21+');

 -- WASHINGTON DC EVENTS

INSERT INTO event (
    event_id, name, description, start_date, end_date, address, event_type, genre,
    external_link, image_path, support_email, age_restriction
) VALUES 
('e0111111-a1a1-b2b2-c3c3-111111111111', 'Zeds Dead at Echostage',
 'Zeds Dead delivers a night of high-energy bass and dubstep in the heart of DC.',
 '2025-09-06T22:00:00Z', '2025-09-07T03:00:00Z',
 'f007f777-2b2b-5c5c-9d9d-7e7e7e777777', 'solo performance', 'Dubstep / Bass',
 'https://zedsdead.net', '/images/events/zedsdead.jpg', 'info@soundcheckdc.com', '18+'),

('e0122222-b2b2-c3c3-d4d4-222222222222', 'Groove District: DC Edition',
 'An all-day mini-festival celebrating house, disco, and garage music with local and touring DJs.',
 '2025-08-03T14:00:00Z', '2025-08-03T22:00:00Z',
 'f006f666-1a1a-4b4b-8c8c-6d6d6d666666', 'festival', 'House / Disco',
 'https://groovedistrict.io', '/images/events/groovedc.jpg', 'support@groovedistrict.io', '21+'),

('e0133333-c3c3-d4d4-e5e5-333333333333', 'Subtronics: Cyclops Tour DC',
 'Subtronics hits DC with his Cyclops Army for a night of face-melting bass.',
 '2025-11-10T20:00:00Z', '2025-11-11T01:00:00Z',
 'f007f777-2b2b-5c5c-9d9d-7e7e7e777777', 'solo performance', 'Bass / Dubstep',
 'https://subtronics.net', '/images/events/subtronicsdc.jpg', 'bookings@soundcheckdc.com', '18+');

-- LOS ANGELES EVENTS

INSERT INTO event (
    event_id, name, description, start_date, end_date, address, event_type, genre,
    external_link, image_path, support_email, age_restriction
) VALUES 
('e0144444-d4d4-e5e5-f6f6-444444444444', 'Jamie Jones: Paradise LA',
 'Global tech-house legend Jamie Jones brings the Paradise brand to Exchange LA.',
 '2025-09-28T21:00:00Z', '2025-09-29T03:00:00Z',
 'f009f999-4d4d-7e7e-1f1f-9f9f9f999999', 'solo performance', 'Tech House / Deep House',
 'https://paradisejamiejones.com', '/images/events/paradisela.jpg', 'events@exchangela.com', '21+'),

('e0155555-e5e5-f6f6-a1a1-555555555555', 'CRSSD: LA Pop-Up Festival',
 'The CRSSD crew brings a special pop-up edition to LA’s historic park, featuring melodic house and techno.',
 '2025-10-19T12:00:00Z', '2025-10-19T22:00:00Z',
 'f010f000-5e5e-8f8f-2a2a-0f0f0f000000', 'festival', 'Melodic Techno / House',
 'https://crssdfest.com', '/images/events/crssdpopupla.jpg', 'info@crssdfest.com', 'All Ages'),

('e0166666-f6f6-a1a1-b2b2-666666666666', 'John Summit at Academy LA',
 'One of the hottest names in house music returns for a wild headline set at Academy.',
 '2025-08-09T22:00:00Z', '2025-08-10T02:00:00Z',
 'f008f888-3c3c-6d6d-0e0e-8f8f8f888888', 'solo performance', 'Tech House',
 'https://johnsummit.com', '/images/events/johnsummitla.jpg', 'contact@academy.la', '21+');

INSERT INTO event (
	name, description, start_date, end_date, address, event_type, genre, external_link, image_path, support_email, age_restriction
) VALUES
('Martin Garrix: Sentio Tour', 'Martin Garrix embarks on his biggest tour yet for his new album, Sentio.',  '2025-09-29T21:00:00Z', '2025-09-30T03:00:00Z', 'f002b222-7c8f-4b76-8d1f-2b2e2b222222', 'PROGRESSIVE_HOUSE', 'SOLO_PERFORMANCE', 'https://martingarrix.com', 'avatars/martin-garrix', 'mgarrix@support.com', 'EIGHTEEN_PLUS' );

INSERT INTO user_favorite_artists (
	user_profile_id,
	artist_id
)
VALUES ('e7cf803f-5242-400e-9f8a-e30f234c100d', '1562cf2c-08c2-44a1-be10-eea5ed2e7e34');


INSERT INTO user_event_history(user_profile_id, event_id, user_rating, user_review)
VALUES ('e7cf803f-5242-400e-9f8a-e30f234c100d', 'e0033333-cccc-dddd-eeee-333333333333', 5, 'Loved all of it. The Electric Sky was amazing and I loved every second of it!');

INSERT INTO event_rating (
    user_profile_id,
    event_id,
    rating_sound_quality,
    rating_bathrooms,
    rating_crowd_vibes,
    rating_venue,
    rating_overall,
    comment
) VALUES (
    'e7cf803f-5242-400e-9f8a-e30f234c100d',
    'e0033333-cccc-dddd-eeee-333333333333',
    5,   -- sound quality
    3,   -- bathrooms
    4,   -- crowd vibes
    4,   -- venue
    5,   -- overall
    'Amazing sound system and great crowd! Bathrooms were just okay.'
);
