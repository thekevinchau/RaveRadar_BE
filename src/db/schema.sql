CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
	id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
	email VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	role VARCHAR(255) NOT NULL
);

CREATE TABLE user_profile (
	id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
	user_id UUID references users(id) NOT NULL,
	name VARCHAR(255),
	username VARCHAR(255) UNIQUE NOT NULL,
	bio TEXT,
	avatar_path TEXT, --path to the s3 bucket image
	pronouns VARCHAR(255)
);
CREATE TABLE addresses (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
	venue_name TEXT,
    street TEXT NOT NULL,
    city TEXT NOT NULL,
    state TEXT NOT NULL,
    zip TEXT NOT NULL,
    country TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE event (
	event_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
	name VARCHAR(255) NOT NULL,
	description TEXT,
	start_date TIMESTAMPTZ NOT NULL,
	end_date TIMESTAMPTZ NOT NULL,
	address UUID references addresses(id),
	event_type VARCHAR(255), --festival, solo performance, b2b, etc.
	genre VARCHAR(255),
	external_link TEXT,
	image_path TEXT,
	support_email TEXT,
	age_restriction VARCHAR(255),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE artist (
	id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
	stage_name VARCHAR(255) NOT NULL,
	real_name VARCHAR(255) NOT NULL,
	bio TEXT,
	avatar_url TEXT,
	country VARCHAR(255),
	hometown VARCHAR(255),
	is_verified BOOLEAN NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP  DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE artist_events (
    event_id UUID NOT NULL,
    artist_id UUID NOT NULL,
    PRIMARY KEY (event_id, artist_id),
    FOREIGN KEY (event_id) REFERENCES event(event_id),
    FOREIGN KEY (artist_id) REFERENCES artist(id)
);

CREATE TABLE user_favorite_artists (
	user_profile_id UUID NOT NULL,
	artist_id UUID NOT NULL,
	PRIMARY KEY (user_profile_id, artist_id),
	FOREIGN KEY (user_profile_id) REFERENCES user_profile(id),
	FOREIGN KEY (artist_id) REFERENCES artist(id)
);

CREATE TABLE user_event_history (
	user_profile_id UUID NOT NULL,
	event_id UUID NOT NULL,
	user_rating INT,
	user_review TEXT,
	PRIMARY KEY (user_profile_id, event_id),
	FOREIGN KEY (user_profile_id) REFERENCES user_profile(id),
	FOREIGN KEY (event_id) REFERENCES event(event_id)
);

CREATE TABLE user_followers (
    follower_id UUID REFERENCES user_profile(id) ON DELETE CASCADE,
    followee_id UUID REFERENCES user_profile(id) ON DELETE CASCADE,
    followed_at TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (follower_id, followee_id)
);

CREATE TABLE event_rating (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),

    user_profile_id UUID NOT NULL,
    event_id UUID NOT NULL,

    rating_sound_quality INTEGER CHECK (rating_sound_quality BETWEEN 1 AND 5),
    rating_bathrooms INTEGER CHECK (rating_bathrooms BETWEEN 1 AND 5),
    rating_crowd_vibes INTEGER CHECK (rating_crowd_vibes BETWEEN 1 AND 5),
    rating_venue INTEGER CHECK (rating_venue BETWEEN 1 AND 5),
    rating_overall INTEGER CHECK (rating_overall BETWEEN 1 AND 5),
	is_edited BOOLEAN DEFAULT false,
    comment TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_event_rating_user FOREIGN KEY (user_profile_id) REFERENCES user_profile(id) ON DELETE CASCADE,
    CONSTRAINT fk_event_rating_event FOREIGN KEY (event_id) REFERENCES event(event_id) ON DELETE CASCADE,
    CONSTRAINT unique_user_event_rating UNIQUE (user_profile_id, event_id)
);
SELECT * FROM artist_events;
SELECT * FROM addresses;
SELECT * FROM users;
SELECT * FROM user_profile;
SELECT * FROM artist;
SELECT * FROM user_favorite_artists;
SELECT * FROM event;
SELECT * FROM event_rating;
SELECT * FROM user_event_history;

