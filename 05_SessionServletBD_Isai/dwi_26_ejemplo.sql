
CREATE DATABASE IF NOT EXISTS dwi_26_ejemplo
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- ---------------------------------------------------------------------
-- Usuario de la aplicacion (coincide con Conexion.java / ConexionMySQL.java)
-- ---------------------------------------------------------------------
CREATE USER IF NOT EXISTS 'dwi'@'localhost' IDENTIFIED BY 'dwi';
GRANT ALL PRIVILEGES ON dwi_26_ejemplo.* TO 'dwi'@'localhost';
FLUSH PRIVILEGES;

USE dwi_26_ejemplo;

-- ---------------------------------------------------------------------
-- Tabla: alumnos
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS alumnos;
CREATE TABLE alumnos (
    NL      INT PRIMARY KEY,
    Nombre  VARCHAR(50) NOT NULL,
    Paterno VARCHAR(50) NOT NULL,
    Materno VARCHAR(50) NOT NULL,
    DDI     DECIMAL(5,2) NOT NULL DEFAULT 0,
    DWI     DECIMAL(5,2) NOT NULL DEFAULT 0,
    ECBD    DECIMAL(5,2) NOT NULL DEFAULT 0
);

INSERT INTO alumnos (NL, Nombre, Paterno, Materno, DDI, DWI, ECBD) VALUES
    (1, 'Juan',   'Perez',   'Garcia',    8.5,  9.0, 7.5),
    (2, 'Maria',  'Lopez',   'Hernandez', 9.0,  8.5, 9.5),
    (3, 'Carlos', 'Sanchez', 'Martinez',  7.0,  7.5, 8.0),
    (4, 'Ana',    'Ramirez', 'Torres',    10.0, 9.5, 9.0),
    (5, 'Luis',   'Gomez',   'Diaz',      6.5,  7.0, 7.5),
    (6, 'Sofia',  'Flores',  'Castro',    8.0,  8.0, 8.5);

-- ---------------------------------------------------------------------
-- Tabla: usuarios
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo     VARCHAR(150) NOT NULL,
    usuario             VARCHAR(50)  NOT NULL UNIQUE,
    correo              VARCHAR(150) NOT NULL UNIQUE,
    password            VARCHAR(255) NOT NULL,
    status              ENUM('PENDIENTE','ACTIVO','INACTIVO') DEFAULT 'PENDIENTE',
    token_verificacion  VARCHAR(100),
    fecha_registro      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Usuario de ejemplo ya activo (para poder iniciar sesion sin verificar correo)
--   Usuario:    isai
--   Contrasena: isai123
INSERT INTO usuarios (nombre_completo, usuario, correo, password, status, token_verificacion) VALUES
    ('Isai Gutierrez', 'isai', 'gutirrresisai@gmail.com', 'isai123', 'ACTIVO', NULL);
