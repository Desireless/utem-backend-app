BEGIN TRANSACTION;

DROP TABLE IF EXISTS campus CASCADE;
CREATE TABLE IF NOT EXISTS campus (
    id bigserial PRIMARY KEY,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    updated_at timestamptz NOT NULL DEFAULT NOW(),
    faculty_name varchar(255) NOT NULL,
    campus_name varchar(255) NOT NULL,
    commune_name varchar(255) NOT NULL
);
INSERT INTO campus (faculty_name, campus_name, commune_name)
VALUES
('Ingeniería', 'Campus Macul', 'Ñuñoa'),
('Administración y Economía', 'Campus Providencia', 'Providencia');


DROP TABLE IF EXISTS room CASCADE;
CREATE TABLE IF NOT EXISTS room (
    id bigserial PRIMARY KEY,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    updated_at timestamptz NOT NULL DEFAULT NOW(),
    room_name varchar(255) NOT NULL,
    campus_id bigint NOT NULL,
    device_sn varchar(255),
    CONSTRAINT room_campus_fkey FOREIGN KEY (campus_id) REFERENCES campus(id)
);
INSERT INTO room (room_name, campus_id)
VALUES
('LAB. INFORMATICA N 1', 1),
('LAB. INFORMATICA N 2', 1),
('LAB. INFORMATICA N 3', 1),
('LAB. INFORMATICA N 4', 1),
('LAB. INFORMATICA N 5', 1),
('LAB. INFORMATICA N 6', 1),
('LAB. INFORMATICA N 7', 1),
('M1-301', 1),
('M1-302', 1),
('M1-303', 1),
('M1-304', 1),
('M1-305', 1),
('M1-306', 1),
('M2-301', 1),
('M2-302', 1),
('M2-303', 1),
('M2-304', 1),
('M2-305', 1),
('M2-306', 1),
('M3-301', 1),
('M3-302', 1),
('M3-303', 1),
('M3-304', 1),
('M3-305', 1),
('M3-306', 1);

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE IF NOT EXISTS users (
    id bigserial PRIMARY KEY,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    updated_at timestamptz NOT NULL DEFAULT NOW(),
    email varchar(255) NOT NULL,
    profile smallint NOT NULL
);
CREATE UNIQUE INDEX usr_email_uidx ON users(UPPER(email);
CREATE INDEX user_profile_idx ON users(profile);
INSERT INTO users (email, profile) VALUES ('estudiante@utem.cl', '0');


DROP TABLE IF EXISTS attendance CASCADE;
CREATE TABLE IF NOT EXISTS attendance (
    id bigserial PRIMARY KEY,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    updated_at timestamptz NOT NULL DEFAULT NOW(),
    room_id bigint NOT NULL,
    user_id bigint NOT NULL,
    geolocation_address varchar(255),
    longitude numeric,
    latitude numeric,
    CONSTRAINT attendance_room_fkey FOREIGN KEY (room_id) REFERENCES room(id),
    CONSTRAINT attendance_user_fkey FOREIGN KEY (user_id) REFERENCES users(id)
);
INSERT INTO attendance (room_id, user_id, geolocation_address, longitude, latitude)
VALUES (1, 1, 'geolocalizacion', 123.456, 789.012);

COMMIT;