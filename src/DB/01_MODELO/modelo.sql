BEGIN TRANSACTION;

DROP TABLE IF EXISTS campus CASCADE;
CREATE TABLE IF NOT EXISTS campus (
    id bigserial PRIMARY KEY,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    updated_at timestamptz NOT NULL DEFAULT NOW(),
    faculty varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    latitude double precision NOT NULL DEFAULT '0',
    longitude double precision NOT NULL DEFAULT '0'
);
CREATE UNIQUE INDEX cmp_name_uidx ON campus(UPPER(name));
INSERT INTO campus (faculty, name, latitude, longitude) VALUES
('Ingeniería', 'Campus Macul', '-33.4661701','-70.6016703'),
('Administración y Economía', 'Campus Providencia', '-33.4484591','-70.6623883');


DROP TABLE IF EXISTS rooms CASCADE;
CREATE TABLE IF NOT EXISTS rooms (
    id bigserial PRIMARY KEY,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    updated_at timestamptz NOT NULL DEFAULT NOW(),
    name varchar(255) NOT NULL,
    campus_id bigint NOT NULL,
    device_sn varchar(255) NOT NULL DEFAULT MD5(random()::text),
    CONSTRAINT room_campus_fkey FOREIGN KEY (campus_id) REFERENCES campus(id)
);
CREATE UNIQUE INDEX rms_devicesn_uidx ON rooms(UPPER(device_sn));
INSERT INTO rooms (name, campus_id)
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
CREATE UNIQUE INDEX usr_email_uidx ON users(UPPER(email));
CREATE INDEX user_profile_idx ON users(profile);
INSERT INTO users (email, profile) VALUES ('estudiante@utem.cl', '0');


DROP TABLE IF EXISTS attendance CASCADE;
CREATE TABLE IF NOT EXISTS attendance (
    id bigserial PRIMARY KEY,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    updated_at timestamptz NOT NULL DEFAULT NOW(),
    room_id bigint NOT NULL,
    user_id bigint NOT NULL,
    longitude double precision,
    latitude double precision,
    CONSTRAINT attendance_room_fkey FOREIGN KEY (room_id) REFERENCES rooms(id),
    CONSTRAINT attendance_user_fkey FOREIGN KEY (user_id) REFERENCES users(id)
);

COMMIT;