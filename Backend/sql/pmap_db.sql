CREATE DATABASE pmap_db;

\c pmap_db;

DROP TABLE IF EXISTS sesiones_estudio;
DROP TABLE IF EXISTS materias;
DROP TABLE IF EXISTS usuarios;

CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo VARCHAR(120) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    rol VARCHAR(30) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    fecha_registro TIMESTAMP NOT NULL
);

CREATE TABLE materias (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL UNIQUE,
    descripcion VARCHAR(500) NOT NULL,
    categoria VARCHAR(80) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL
);

CREATE TABLE sesiones_estudio (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    materia_id BIGINT NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    objetivo VARCHAR(500) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    CONSTRAINT fk_sesiones_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
    CONSTRAINT fk_sesiones_materia FOREIGN KEY (materia_id) REFERENCES materias (id)
);

ALTER TABLE usuarios ADD CONSTRAINT chk_usuarios_rol CHECK (rol IN ('ADMINISTRADOR', 'ESTUDIANTE'));
ALTER TABLE usuarios ADD CONSTRAINT chk_usuarios_estado CHECK (estado IN ('ACTIVO', 'INACTIVO'));
ALTER TABLE materias ADD CONSTRAINT chk_materias_estado CHECK (estado IN ('ACTIVA', 'INACTIVA'));
ALTER TABLE sesiones_estudio ADD CONSTRAINT chk_sesiones_estado CHECK (estado IN ('PENDIENTE', 'EN_PROGRESO', 'FINALIZADA', 'CANCELADA'));

INSERT INTO usuarios (nombres, apellidos, correo, contraseña, rol, estado, fecha_registro) VALUES
('Admin', 'Principal', 'admin@pmap.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoOa0uN4Qf2Kp7ZJH3mB1r6Kj7Q0Q1m4mu', 'ADMINISTRADOR', 'ACTIVO', NOW()),
('Ana', 'Perez', 'ana@pmap.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoOa0uN4Qf2Kp7ZJH3mB1r6Kj7Q0Q1m4mu', 'ESTUDIANTE', 'ACTIVO', NOW()),
('Luis', 'Gomez', 'luis@pmap.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoOa0uN4Qf2Kp7ZJH3mB1r6Kj7Q0Q1m4mu', 'ESTUDIANTE', 'ACTIVO', NOW()),
('Sara', 'Martinez', 'sara@pmap.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoOa0uN4Qf2Kp7ZJH3mB1r6Kj7Q0Q1m4mu', 'ESTUDIANTE', 'ACTIVO', NOW()),
('Carlos', 'Lopez', 'carlos@pmap.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoOa0uN4Qf2Kp7ZJH3mB1r6Kj7Q0Q1m4mu', 'ESTUDIANTE', 'ACTIVO', NOW()),
('Marta', 'Ruiz', 'marta@pmap.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoOa0uN4Qf2Kp7ZJH3mB1r6Kj7Q0Q1m4mu', 'ESTUDIANTE', 'INACTIVO', NOW()),
('Pedro', 'Santos', 'pedro@pmap.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoOa0uN4Qf2Kp7ZJH3mB1r6Kj7Q0Q1m4mu', 'ESTUDIANTE', 'ACTIVO', NOW()),
('Laura', 'Diaz', 'laura@pmap.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoOa0uN4Qf2Kp7ZJH3mB1r6Kj7Q0Q1m4mu', 'ESTUDIANTE', 'ACTIVO', NOW()),
('Julian', 'Torres', 'julian@pmap.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoOa0uN4Qf2Kp7ZJH3mB1r6Kj7Q0Q1m4mu', 'ESTUDIANTE', 'ACTIVO', NOW()),
('Elena', 'Vargas', 'elena@pmap.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoOa0uN4Qf2Kp7ZJH3mB1r6Kj7Q0Q1m4mu', 'ESTUDIANTE', 'INACTIVO', NOW());

INSERT INTO materias (nombre, descripcion, categoria, estado, fecha_creacion) VALUES
('Matematicas', 'Algebra y calculo', 'Ciencias', 'ACTIVA', NOW()),
('Lenguaje', 'Lectura y escritura', 'Humanidades', 'ACTIVA', NOW()),
('Programacion', 'Fundamentos de desarrollo', 'Tecnologia', 'ACTIVA', NOW()),
('Historia', 'Historia universal', 'Ciencias Sociales', 'ACTIVA', NOW()),
('Fisica', 'Mecanica y energia', 'Ciencias', 'ACTIVA', NOW()),
('Quimica', 'Materia y reacciones', 'Ciencias', 'INACTIVA', NOW()),
('Ingles', 'Idioma extranjero', 'Idiomas', 'ACTIVA', NOW()),
('Etica', 'Valores y ciudadania', 'Humanidades', 'ACTIVA', NOW()),
('Bases de Datos', 'Modelado y SQL', 'Tecnologia', 'ACTIVA', NOW()),
('Estadistica', 'Analisis de datos', 'Ciencias', 'INACTIVA', NOW());

INSERT INTO sesiones_estudio (usuario_id, materia_id, fecha, hora_inicio, hora_fin, objetivo, estado) VALUES
(2, 1, CURRENT_DATE + 1, '08:00', '09:30', 'Repasar ejercicios de algebra', 'PENDIENTE'),
(3, 2, CURRENT_DATE + 1, '10:00', '11:00', 'Practicar lectura comprensiva', 'EN_PROGRESO'),
(4, 3, CURRENT_DATE + 2, '14:00', '15:30', 'Construir API REST', 'FINALIZADA'),
(5, 4, CURRENT_DATE + 2, '16:00', '17:00', 'Estudiar linea de tiempo', 'CANCELADA'),
(6, 5, CURRENT_DATE + 3, '09:00', '10:00', 'Resolver problemas de mecanica', 'PENDIENTE'),
(7, 6, CURRENT_DATE + 3, '11:00', '12:00', 'Identificar reacciones quimicas', 'FINALIZADA'),
(8, 7, CURRENT_DATE + 4, '08:30', '10:00', 'Vocabulario y speaking', 'EN_PROGRESO'),
(9, 8, CURRENT_DATE + 4, '13:00', '14:00', 'Reflexionar sobre valores', 'PENDIENTE'),
(10, 9, CURRENT_DATE + 5, '15:00', '16:30', 'Crear consultas SQL', 'FINALIZADA'),
(2, 10, CURRENT_DATE + 5, '17:00', '18:00', 'Analizar dispersion y tendencia', 'CANCELADA');