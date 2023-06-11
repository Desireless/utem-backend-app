CREATE TABLE IF NOT EXISTS sedes (
    id SERIAL PRIMARY KEY,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    updated_at timestamptz NOT NULL DEFAULT NOW(),
    facultad varchar(255) NOT NULL,
    sede varchar(255) NOT NULL,
    comuna varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS salas (
    id SERIAL PRIMARY KEY,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    updated_at timestamptz NOT NULL DEFAULT NOW(),
    sala varchar(255) NOT NULL,
    sede_id INTEGER NOT NULL,
    CONSTRAINT salas_sede_fkey FOREIGN KEY (sede_id) REFERENCES sedes(id)
);

CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    updated_at timestamptz NOT NULL DEFAULT NOW(),
    correo varchar(255) NOT NULL,
    rol varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS asistencia (
    id SERIAL PRIMARY KEY,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    updated_at timestamptz NOT NULL DEFAULT NOW(),
    sala_id INTEGER NOT NULL,
    geolocalizacion varchar(255) NOT NULL,
    longitud numeric NOT NULL,
    latitud numeric NOT NULL,
	CONSTRAINT asistencia_salas_fkey FOREIGN KEY (sala_id) REFERENCES salas(id)
);