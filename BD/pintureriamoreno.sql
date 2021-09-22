-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-09-2021 a las 18:35:56
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pintureriamoreno`
--
CREATE DATABASE IF NOT EXISTS `pintureriamoreno` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `pintureriamoreno`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apertura`
--

CREATE TABLE `apertura` (
  `cod_apertura` int(11) NOT NULL,
  `cod_usuario` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `saldo_apertura` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `caja`
--

CREATE TABLE `caja` (
  `cod_caja` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `estado` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `caja`
--

INSERT INTO `caja` (`cod_caja`, `nombre`, `estado`) VALUES
(1, 'CAJA 1', 'CERRADA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cierre`
--

CREATE TABLE `cierre` (
  `cod_cierre` int(11) NOT NULL,
  `cod_apertura` int(11) NOT NULL,
  `cod_usuario` int(11) NOT NULL,
  `saldo_cierre` decimal(20,2) NOT NULL,
  `fecha_cierre` datetime NOT NULL,
  `diferencia_cierre` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `cod_persona` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `num_documento` varchar(20) NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `telefono` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planilla`
--

CREATE TABLE `planilla` (
  `cod_movimiento` int(11) NOT NULL,
  `nom_vendedor` varchar(60) NOT NULL,
  `fecha_movimiento` datetime NOT NULL,
  `rubro` varchar(100) NOT NULL,
  `observacion` varchar(100) NOT NULL,
  `tipo_moneda` varchar(60) NOT NULL,
  `ingresos` decimal(20,2) DEFAULT NULL,
  `egresos` decimal(20,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `cod_usuario` int(11) NOT NULL,
  `cod_persona` int(11) NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `estado` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `apertura`
--
ALTER TABLE `apertura`
  ADD PRIMARY KEY (`cod_apertura`),
  ADD KEY `usuarioApertura` (`cod_usuario`);

--
-- Indices de la tabla `caja`
--
ALTER TABLE `caja`
  ADD PRIMARY KEY (`cod_caja`);

--
-- Indices de la tabla `cierre`
--
ALTER TABLE `cierre`
  ADD PRIMARY KEY (`cod_cierre`),
  ADD KEY `usuarioCierre` (`cod_usuario`),
  ADD KEY `apertura` (`cod_apertura`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`cod_persona`),
  ADD KEY `nombre` (`nombre`);

--
-- Indices de la tabla `planilla`
--
ALTER TABLE `planilla`
  ADD PRIMARY KEY (`cod_movimiento`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`cod_usuario`),
  ADD KEY `usuarioFK` (`cod_persona`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `apertura`
--
ALTER TABLE `apertura`
  MODIFY `cod_apertura` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `caja`
--
ALTER TABLE `caja`
  MODIFY `cod_caja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `cierre`
--
ALTER TABLE `cierre`
  MODIFY `cod_cierre` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `cod_persona` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `planilla`
--
ALTER TABLE `planilla`
  MODIFY `cod_movimiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `cod_usuario` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `apertura`
--
ALTER TABLE `apertura`
  ADD CONSTRAINT `usuarioApertura` FOREIGN KEY (`cod_usuario`) REFERENCES `usuario` (`cod_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cierre`
--
ALTER TABLE `cierre`
  ADD CONSTRAINT `apertura` FOREIGN KEY (`cod_apertura`) REFERENCES `apertura` (`cod_apertura`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuarioCierre` FOREIGN KEY (`cod_usuario`) REFERENCES `usuario` (`cod_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuarioFK` FOREIGN KEY (`cod_persona`) REFERENCES `persona` (`cod_persona`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
