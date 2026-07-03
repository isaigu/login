-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-07-2026 a las 08:50:36
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dwi_26_ejemplo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `NL` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Paterno` varchar(50) NOT NULL,
  `Materno` varchar(50) NOT NULL,
  `DDI` decimal(5,2) NOT NULL DEFAULT 0.00,
  `DWI` decimal(5,2) NOT NULL DEFAULT 0.00,
  `ECBD` decimal(5,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`NL`, `Nombre`, `Paterno`, `Materno`, `DDI`, `DWI`, `ECBD`) VALUES
(1, 'isai', 'gutierrez', 'zamudio', 8.00, 8.00, 7.00),
(4, 'Ana', 'Ramirez', 'Torres', 10.00, 9.50, 9.00),
(5, 'Luis', 'Gomez', 'Diaz', 6.50, 7.00, 7.50),
(6, 'Sofia', 'Flores', 'Castro', 8.00, 8.00, 8.50),
(7, 'miguel', 'cuevas', 'cuevas', 9.00, 9.00, 9.00),
(8, 'jose', 'martinez', 'cuevas', 9.00, 10.00, 8.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre_completo` varchar(150) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `correo` varchar(150) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` enum('PENDIENTE','ACTIVO','INACTIVO') DEFAULT 'PENDIENTE',
  `token_verificacion` varchar(100) DEFAULT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre_completo`, `usuario`, `correo`, `password`, `status`, `token_verificacion`, `fecha_registro`) VALUES
(2, 'Isai Gutierrez', 'isai', 'gutirrresisai@gmail.com', '4LRYdGRqpocuFPKIdxW7Tw==:Ws252AoXNTGOfAk3Dj/wX1DmoMPi3Tx2ckGd9WEXlN8=', 'ACTIVO', NULL, '2026-07-01 09:22:54'),
(4, 'pablo', 'pablito', '5723190007@utrng.edu.mx', 'yG1nvUxCfVIICjlu/70QMQ==:xynvk5zOwjfrkokmnnPfVKBJH5c283Bq5iV5LqUnHig=', 'PENDIENTE', '61ca1e4b-cc34-4770-b9d3-40200a96fddb', '2026-07-01 14:30:22'),
(5, 'usuario', 'user543', 'isaig4814@gmail.com', '9GdThTY2JOVHTm/Cwa7IEw==:Cu5s98VqOBD6Q/LQw5h7ap4dgIypw27UTVoXHt89d1A=', 'PENDIENTE', 'b33d7386-18a9-49f3-9876-e7cc749539ff', '2026-07-02 19:37:47'),
(7, 'isai prueba', 'pruebai', 'gisai5887@gmail.com', 'z0yY4z/PVABkwtsJ6UkjKQ==:EUYHlV+hnrvUzHDiJh5wWO56PCDXxDrqvFPxL6CuUhU=', 'ACTIVO', NULL, '2026-07-02 20:39:20'),
(8, 'isa guti zam', 'prueba2', '57231900072_i@utrng.edu.mx', 'BhSbN4PCbVhAbj1avoNYFg==:BEZUy/zkAWFNSdxAHhRFAxm1anxPqaD5DNkIAPGxRow=', 'ACTIVO', NULL, '2026-07-02 20:57:24');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`NL`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuario` (`usuario`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
