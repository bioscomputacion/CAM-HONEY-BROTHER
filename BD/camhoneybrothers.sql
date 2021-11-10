-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-11-2021 a las 23:29:35
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
-- Base de datos: `camhoneybrothers`
--
CREATE DATABASE IF NOT EXISTS `camhoneybrothers` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `camhoneybrothers`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `cod_cliente` int(11) NOT NULL,
  `cod_persona` int(11) NOT NULL,
  `razon_social` varchar(60) DEFAULT NULL,
  `condicion_iva` varchar(60) DEFAULT NULL,
  `cuit` varchar(30) DEFAULT NULL,
  `domicilio_fiscal` varchar(100) DEFAULT NULL,
  `estado` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cod_cliente`, `cod_persona`, `razon_social`, `condicion_iva`, `cuit`, `domicilio_fiscal`, `estado`) VALUES
(11, 66, '-', '-', '-', '-', '-');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cta_cte_cliente`
--

CREATE TABLE `cta_cte_cliente` (
  `codigo_cliente` int(11) NOT NULL,
  `codigo_movimiento` int(11) NOT NULL,
  `fecha_movimiento` date NOT NULL,
  `descripcion_movimiento` varchar(60) NOT NULL,
  `comprobante_asociado` int(11) DEFAULT NULL,
  `numero_comprobante` varchar(60) NOT NULL,
  `debe` decimal(20,2) DEFAULT NULL,
  `haber` decimal(20,2) DEFAULT NULL,
  `saldo` decimal(20,2) DEFAULT NULL,
  `estado_movimiento` varchar(60) DEFAULT NULL,
  `observacion` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cta_cte_productor`
--

CREATE TABLE `cta_cte_productor` (
  `codigo_productor` int(11) NOT NULL,
  `codigo_movimiento` int(11) NOT NULL,
  `fecha_movimiento` date NOT NULL,
  `descripcion_movimiento` varchar(60) NOT NULL,
  `comprobante_asociado` int(11) DEFAULT NULL,
  `numero_comprobante` varchar(60) NOT NULL,
  `debe` decimal(20,2) DEFAULT NULL,
  `haber` decimal(20,2) DEFAULT NULL,
  `saldo` decimal(20,2) DEFAULT NULL,
  `estado_movimiento` varchar(60) DEFAULT NULL,
  `observacion` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Volcado de datos para la tabla `cta_cte_productor`
--

INSERT INTO `cta_cte_productor` (`codigo_productor`, `codigo_movimiento`, `fecha_movimiento`, `descripcion_movimiento`, `comprobante_asociado`, `numero_comprobante`, `debe`, `haber`, `saldo`, `estado_movimiento`, `observacion`) VALUES
(39, 1, '2021-11-10', 'FACTURA', 30, '0001-0001', '1000.00', '0.00', '1000.00', 'PENDIENTE', ''),
(39, 2, '2021-11-10', 'FACTURA', 32, '0001-0002', '2000.00', '0.00', '2000.00', 'PENDIENTE', NULL),
(39, 3, '2021-11-10', 'FACTURA', 34, 'aaaaaa', '100000000.00', '0.00', '100000000.00', 'PENDIENTE', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura_cliente`
--

CREATE TABLE `factura_cliente` (
  `codigo_factura` int(11) NOT NULL,
  `numero_comprobante` varchar(60) NOT NULL,
  `codigo_movimiento_ctacte` int(11) NOT NULL,
  `codigo_cliente` int(11) NOT NULL,
  `fecha_factura` date NOT NULL,
  `importe_total_factura` double(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Volcado de datos para la tabla `factura_cliente`
--

INSERT INTO `factura_cliente` (`codigo_factura`, `numero_comprobante`, `codigo_movimiento_ctacte`, `codigo_cliente`, `fecha_factura`, `importe_total_factura`) VALUES
(3, '0', 0, 11, '2021-11-10', 0.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura_productor`
--

CREATE TABLE `factura_productor` (
  `codigo_factura` int(11) NOT NULL,
  `numero_comprobante` varchar(60) NOT NULL,
  `codigo_movimiento_ctacte` int(11) NOT NULL,
  `codigo_productor` int(11) NOT NULL,
  `fecha_factura` date NOT NULL,
  `importe_total_factura` double(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Volcado de datos para la tabla `factura_productor`
--

INSERT INTO `factura_productor` (`codigo_factura`, `numero_comprobante`, `codigo_movimiento_ctacte`, `codigo_productor`, `fecha_factura`, `importe_total_factura`) VALUES
(29, '0', 0, 39, '2021-11-10', 0.00),
(32, '0001-0001', 1, 39, '2021-11-10', 1000.00),
(33, '123456789', 1, 39, '2021-11-10', 50000.00),
(34, 'aaaaaa', 1, 39, '2021-11-10', 100000000.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `items_facturados_factura_productor`
--

CREATE TABLE `items_facturados_factura_productor` (
  `codigo_item_facturado` int(11) NOT NULL,
  `codigo_factura` int(11) NOT NULL,
  `descripcion_item_facturado` varchar(255) NOT NULL,
  `cantidad_item_facturado` int(11) NOT NULL,
  `importe_item_facturado` double(20,2) NOT NULL,
  `total_item_facturado` double(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `items_facturados_factura_productor`
--

INSERT INTO `items_facturados_factura_productor` (`codigo_item_facturado`, `codigo_factura`, `descripcion_item_facturado`, `cantidad_item_facturado`, `importe_item_facturado`, `total_item_facturado`) VALUES
(1, 33, 'LOTE DE MIEL (300 TAMB. / 21000 KGS.) ', 1, 50000.00, 50000.00),
(1, 34, 'LOTE DE MIEL (300 TAMB. / 21000 KGS.) ', 1, 100000000.00, 100000000.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `cod_persona` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `documento` varchar(20) DEFAULT '',
  `pais` varchar(60) DEFAULT NULL,
  `estado_provincia` varchar(60) DEFAULT NULL,
  `localidad` varchar(60) DEFAULT NULL,
  `domicilio` varchar(100) DEFAULT '',
  `telefono` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`cod_persona`, `nombre`, `documento`, `pais`, `estado_provincia`, `localidad`, `domicilio`, `telefono`, `correo`) VALUES
(63, 'PRODUCTOR GENERICO', '-', '-', '-', '-', '-', '-', '-'),
(64, 'Cesar Galoppo', '10', 'ARGENTINA', 'SAN LUIS', 'Justo Daract', 'Cordoba 40', '', ''),
(65, 'Jasmina Constanza Mana', '20', 'ARGENTINA', 'SAN LUIS', 'Villa Mercedes', 'Madre Cabrini', '', ''),
(66, 'CLIENTE GENERICO', '-', '-', '-', '-', '-', '-', '-'),
(67, 'CARLOS MANA', '-', NULL, NULL, NULL, '-', '-', '-');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `presupuesto_productor`
--

CREATE TABLE `presupuesto_productor` (
  `codigo_presupuesto` int(11) NOT NULL,
  `numero_comprobante` varchar(60) NOT NULL,
  `codigo_movimiento_ctacte` int(11) NOT NULL,
  `codigo_productor` int(11) NOT NULL,
  `fecha_presupuesto` date NOT NULL,
  `importe_total_presupuesto` double(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productor`
--

CREATE TABLE `productor` (
  `cod_productor` int(11) NOT NULL,
  `cod_persona` int(11) NOT NULL,
  `fecha_venta_miel_1` varchar(20) DEFAULT NULL,
  `fecha_venta_miel_2` varchar(20) DEFAULT NULL,
  `fecha_venta_miel_3` varchar(20) DEFAULT NULL,
  `nombre_fantasia` varchar(60) DEFAULT NULL,
  `razon_social` varchar(60) DEFAULT NULL,
  `condicion_iva` varchar(60) DEFAULT NULL,
  `cuit` varchar(30) DEFAULT NULL,
  `domicilio_fiscal` varchar(100) DEFAULT NULL,
  `estado` varchar(20) NOT NULL,
  `cantidad_colmenas` int(11) DEFAULT NULL,
  `ubicacion_colmenas` varchar(100) DEFAULT NULL,
  `floracion_miel` varchar(60) DEFAULT NULL,
  `cura_miel` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productor`
--

INSERT INTO `productor` (`cod_productor`, `cod_persona`, `fecha_venta_miel_1`, `fecha_venta_miel_2`, `fecha_venta_miel_3`, `nombre_fantasia`, `razon_social`, `condicion_iva`, `cuit`, `domicilio_fiscal`, `estado`, `cantidad_colmenas`, `ubicacion_colmenas`, `floracion_miel`, `cura_miel`) VALUES
(38, 63, '-', '-', '-', '-', '-', '-', '-', '-', '-', 0, '-', '-', '-'),
(39, 64, '', '', '', '', '', '', '', '', 'Activo', 0, '', '', ''),
(40, 65, '', '', '', '', '', '', '', '', 'Activo', 0, '', '', '');

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
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`cod_usuario`, `cod_persona`, `login`, `password`, `tipo`, `estado`) VALUES
(4, 67, 'CACO', 'CACO', 'Activo', 'Administrador');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cod_cliente`),
  ADD KEY `cliente` (`cod_persona`);

--
-- Indices de la tabla `cta_cte_cliente`
--
ALTER TABLE `cta_cte_cliente`
  ADD PRIMARY KEY (`codigo_cliente`,`codigo_movimiento`);

--
-- Indices de la tabla `cta_cte_productor`
--
ALTER TABLE `cta_cte_productor`
  ADD PRIMARY KEY (`codigo_productor`,`codigo_movimiento`);

--
-- Indices de la tabla `factura_cliente`
--
ALTER TABLE `factura_cliente`
  ADD PRIMARY KEY (`codigo_factura`),
  ADD KEY `clientefkf` (`codigo_cliente`);

--
-- Indices de la tabla `factura_productor`
--
ALTER TABLE `factura_productor`
  ADD PRIMARY KEY (`codigo_factura`),
  ADD KEY `productorfkf` (`codigo_productor`);

--
-- Indices de la tabla `items_facturados_factura_productor`
--
ALTER TABLE `items_facturados_factura_productor`
  ADD PRIMARY KEY (`codigo_item_facturado`,`codigo_factura`),
  ADD KEY `fkFactura` (`codigo_factura`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`cod_persona`),
  ADD KEY `nombre` (`nombre`);

--
-- Indices de la tabla `presupuesto_productor`
--
ALTER TABLE `presupuesto_productor`
  ADD PRIMARY KEY (`codigo_presupuesto`),
  ADD KEY `productorfkp` (`codigo_productor`);

--
-- Indices de la tabla `productor`
--
ALTER TABLE `productor`
  ADD PRIMARY KEY (`cod_productor`),
  ADD KEY `productor` (`cod_persona`);

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
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `cod_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `factura_cliente`
--
ALTER TABLE `factura_cliente`
  MODIFY `codigo_factura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `factura_productor`
--
ALTER TABLE `factura_productor`
  MODIFY `codigo_factura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `cod_persona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT de la tabla `presupuesto_productor`
--
ALTER TABLE `presupuesto_productor`
  MODIFY `codigo_presupuesto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productor`
--
ALTER TABLE `productor`
  MODIFY `cod_productor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `cod_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente` FOREIGN KEY (`cod_persona`) REFERENCES `persona` (`cod_persona`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cta_cte_cliente`
--
ALTER TABLE `cta_cte_cliente`
  ADD CONSTRAINT `ctacteClientefk` FOREIGN KEY (`codigo_cliente`) REFERENCES `cliente` (`cod_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `cta_cte_productor`
--
ALTER TABLE `cta_cte_productor`
  ADD CONSTRAINT `ctacteProductorfk` FOREIGN KEY (`codigo_productor`) REFERENCES `productor` (`cod_productor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `factura_cliente`
--
ALTER TABLE `factura_cliente`
  ADD CONSTRAINT `clientefkf` FOREIGN KEY (`codigo_cliente`) REFERENCES `cliente` (`cod_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `factura_productor`
--
ALTER TABLE `factura_productor`
  ADD CONSTRAINT `productorfkf` FOREIGN KEY (`codigo_productor`) REFERENCES `productor` (`cod_productor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `items_facturados_factura_productor`
--
ALTER TABLE `items_facturados_factura_productor`
  ADD CONSTRAINT `fkFactura` FOREIGN KEY (`codigo_factura`) REFERENCES `factura_productor` (`codigo_factura`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `presupuesto_productor`
--
ALTER TABLE `presupuesto_productor`
  ADD CONSTRAINT `productorfkp` FOREIGN KEY (`codigo_productor`) REFERENCES `productor` (`cod_productor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `productor`
--
ALTER TABLE `productor`
  ADD CONSTRAINT `productor` FOREIGN KEY (`cod_persona`) REFERENCES `persona` (`cod_persona`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuarioFK` FOREIGN KEY (`cod_persona`) REFERENCES `persona` (`cod_persona`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
