CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    created_at date NOT NULL,
    updated_at date NOT NULL,
    correo varchar(255) NOT NULL,
    rol varchar(255) NOT NULL,
);


CREATE TABLE salas (
    id SERIAL PRIMARY KEY,
    created_at date NOT NULL,
    updated_at date NOT NULL,
    sala varchar(255) NOT NULL,
    sede varchar(255) NOT NULL,
    REFERENCES sede(sedes),
);

CREATE TABLE sedes (
    id SERIAL PRIMARY KEY,
    created_at date NOT NULL,
    updated_at date NOT NULL,
    facultad varchar(255) NOT NULL,
    sede varchar(255) NOT NULL,
    comuna varchar(255) NOT NULL,
);

CREATE TABLE asistencia (
    id SERIAL PRIMARY KEY,
    created_at date NOT NULL,
    updated_at date NOT NULL,
    sala varchar(255) NOT NULL,
    geolocalizacion varchar(255) NOT NULL,
    longitud numeric NOT NULL,
    latitud numeric NOT NULL,
    REFERENCES sala(sala),
);