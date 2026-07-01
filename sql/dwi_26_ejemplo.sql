-- Script de creacion de la base de datos para la practica "practica_con_bd"
-- Coincide con las credenciales definidas en conexion/ConexionMySQL.java

CREATE DATABASE IF NOT EXISTS dwi_26_ejemplo
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- Usuario de la aplicacion (usuario: dwi / password: dwi)
CREATE USER IF NOT EXISTS 'dwi'@'localhost' IDENTIFIED BY 'dwi';
GRANT ALL PRIVILEGES ON dwi_26_ejemplo.* TO 'dwi'@'localhost';
FLUSH PRIVILEGES;

USE dwi_26_ejemplo;

CREATE TABLE IF NOT EXISTS alumnos (
    NL      INT PRIMARY KEY,
    Nombre  VARCHAR(50) NOT NULL,
    Paterno VARCHAR(50) NOT NULL,
    Materno VARCHAR(50) NOT NULL,
    DDI     DECIMAL(5,2) NOT NULL DEFAULT 0,
    DWI     DECIMAL(5,2) NOT NULL DEFAULT 0,
    ECBD    DECIMAL(5,2) NOT NULL DEFAULT 0
);

-- Tabla de usuarios para registro / login / verificacion por correo
CREATE TABLE IF NOT EXISTS usuarios (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo     VARCHAR(100) NOT NULL,
    usuario             VARCHAR(50)  NOT NULL UNIQUE,
    correo              VARCHAR(100) NOT NULL UNIQUE,
    password            VARCHAR(255) NOT NULL,
    status              VARCHAR(20)  NOT NULL DEFAULT 'PENDIENTE',
    token_verificacion  VARCHAR(100)
);

-- Datos de ejemplo
INSERT INTO alumnos (NL, Nombre, Paterno, Materno, DDI, DWI, ECBD) VALUES
    (1, 'Juan',   'Perez',   'Garcia',    8.5, 9.0, 7.5),
    (2, 'Maria',  'Lopez',   'Hernandez', 9.0, 8.5, 9.5),
    (3, 'Carlos', 'Sanchez', 'Martinez',  7.0, 7.5, 8.0),
    (4, 'Ana',    'Ramirez', 'Torres',    10.0, 9.5, 9.0),
    (5, 'Luis',   'Gomez',   'Diaz',      6.5, 7.0, 7.5),
    (6, 'Sofia',  'Flores',  'Castro',    8.0, 8.0, 8.5);
