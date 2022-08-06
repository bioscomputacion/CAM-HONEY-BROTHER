-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-08-2022 a las 22:57:28
-- Versión del servidor: 10.4.6-MariaDB
-- Versión de PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
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

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `cod_cliente` int(11) NOT NULL,
  `cod_persona` int(11) NOT NULL,
  `nombre_fantasia` varchar(60) DEFAULT NULL,
  `razon_social` varchar(60) DEFAULT NULL,
  `condicion_iva` varchar(60) DEFAULT NULL,
  `cuit` varchar(30) DEFAULT NULL,
  `domicilio_fiscal` varchar(100) DEFAULT NULL,
  `estado` varchar(20) NOT NULL,
  `categoria` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprobantes_relacionados_compra_credito`
--

DROP TABLE IF EXISTS `comprobantes_relacionados_compra_credito`;
CREATE TABLE `comprobantes_relacionados_compra_credito` (
  `codigo_productor` int(11) NOT NULL,
  `codigo_compra_consignacion` int(11) NOT NULL,
  `codigo_comprobante_relacionado` int(11) NOT NULL,
  `tipo_comprobante_relacionado` varchar(100) NOT NULL,
  `cantidad_miel_afectada` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credito_productor`
--

DROP TABLE IF EXISTS `credito_productor`;
CREATE TABLE `credito_productor` (
  `codigo_credito` int(11) NOT NULL,
  `numero_comprobante` varchar(60) NOT NULL,
  `codigo_movimiento_ctacte` int(11) NOT NULL,
  `codigo_productor` int(11) NOT NULL,
  `fecha_credito` date NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `cantidad_miel` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cta_cte_cliente`
--

DROP TABLE IF EXISTS `cta_cte_cliente`;
CREATE TABLE `cta_cte_cliente` (
  `codigo_cliente` int(11) NOT NULL,
  `codigo_movimiento` int(11) NOT NULL,
  `fecha_movimiento` date NOT NULL,
  `descripcion_movimiento` varchar(60) NOT NULL,
  `comprobante_asociado` int(11) DEFAULT NULL,
  `numero_comprobante` varchar(60) NOT NULL,
  `cantidad_miel` decimal(20,2) DEFAULT NULL,
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

DROP TABLE IF EXISTS `cta_cte_productor`;
CREATE TABLE `cta_cte_productor` (
  `codigo_productor` int(11) NOT NULL,
  `codigo_movimiento` int(11) NOT NULL,
  `fecha_movimiento` date NOT NULL,
  `descripcion_movimiento` varchar(60) NOT NULL,
  `comprobante_asociado` int(11) DEFAULT NULL,
  `numero_comprobante` varchar(60) NOT NULL,
  `cantidad_miel` decimal(20,2) DEFAULT NULL,
  `debe` decimal(20,2) DEFAULT NULL,
  `haber` decimal(20,2) DEFAULT NULL,
  `saldo` decimal(20,2) DEFAULT NULL,
  `estado_movimiento` varchar(60) DEFAULT NULL,
  `observacion` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `devolucion_productor`
--

DROP TABLE IF EXISTS `devolucion_productor`;
CREATE TABLE `devolucion_productor` (
  `codigo_devolucion` int(11) NOT NULL,
  `numero_comprobante` varchar(100) NOT NULL,
  `codigo_movimiento_ctacte` int(11) NOT NULL,
  `codigo_productor` int(11) NOT NULL,
  `fecha_devolucion` date NOT NULL,
  `cantidad_miel` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura_cliente`
--

DROP TABLE IF EXISTS `factura_cliente`;
CREATE TABLE `factura_cliente` (
  `codigo_factura` int(11) NOT NULL,
  `tipo_factura` varchar(60) NOT NULL,
  `numero_comprobante` varchar(60) NOT NULL,
  `codigo_movimiento_ctacte` int(11) NOT NULL,
  `codigo_cliente` int(11) NOT NULL,
  `fecha_factura` date NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `importe_total_factura` double(20,2) NOT NULL,
  `cantidad_miel` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura_productor`
--

DROP TABLE IF EXISTS `factura_productor`;
CREATE TABLE `factura_productor` (
  `codigo_factura` int(11) NOT NULL,
  `tipo_factura` varchar(60) NOT NULL,
  `numero_comprobante` varchar(60) NOT NULL,
  `codigo_movimiento_ctacte` int(11) NOT NULL,
  `codigo_productor` int(11) NOT NULL,
  `fecha_factura` date NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `importe_total_factura` double(20,2) NOT NULL,
  `cantidad_miel_facturada` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingreso_miel_propia`
--

DROP TABLE IF EXISTS `ingreso_miel_propia`;
CREATE TABLE `ingreso_miel_propia` (
  `codigo_ingreso` int(11) NOT NULL,
  `numero_comprobante` varchar(60) NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `cantidad_miel` decimal(20,2) NOT NULL,
  `observacion` varchar(200) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `items_devueltos_devolucion_productor`
--

DROP TABLE IF EXISTS `items_devueltos_devolucion_productor`;
CREATE TABLE `items_devueltos_devolucion_productor` (
  `codigo_item_devuelto` int(11) NOT NULL,
  `codigo_devolucion` int(11) NOT NULL,
  `descripcion_item_devuelto` varchar(255) NOT NULL,
  `cantidad_item_devuelto` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `items_facturados_credito_productor`
--

DROP TABLE IF EXISTS `items_facturados_credito_productor`;
CREATE TABLE `items_facturados_credito_productor` (
  `codigo_item_financiado` int(11) NOT NULL,
  `codigo_credito` int(11) NOT NULL,
  `descripcion_item_financiado` varchar(255) NOT NULL,
  `cantidad_item_financiado` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `items_facturados_factura_cliente`
--

DROP TABLE IF EXISTS `items_facturados_factura_cliente`;
CREATE TABLE `items_facturados_factura_cliente` (
  `codigo_item_facturado` int(11) NOT NULL,
  `codigo_factura` int(11) NOT NULL,
  `descripcion_item_facturado` varchar(255) NOT NULL,
  `cantidad_item_facturado` decimal(20,2) DEFAULT NULL,
  `importe_item_facturado` decimal(20,2) DEFAULT NULL,
  `total_item_facturado` decimal(20,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `items_facturados_factura_productor`
--

DROP TABLE IF EXISTS `items_facturados_factura_productor`;
CREATE TABLE `items_facturados_factura_productor` (
  `codigo_item_facturado` int(11) NOT NULL,
  `codigo_factura` int(11) NOT NULL,
  `descripcion_item_facturado` varchar(255) NOT NULL,
  `cantidad_item_facturado` decimal(20,2) NOT NULL,
  `importe_item_facturado` decimal(20,2) NOT NULL,
  `total_item_facturado` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `items_facturados_presupuesto_productor`
--

DROP TABLE IF EXISTS `items_facturados_presupuesto_productor`;
CREATE TABLE `items_facturados_presupuesto_productor` (
  `codigo_item_presupuestado` int(11) NOT NULL,
  `codigo_presupuesto` int(11) NOT NULL,
  `descripcion_item_presupuestado` varchar(255) NOT NULL,
  `cantidad_item_presupuestado` decimal(20,2) NOT NULL,
  `importe_item_presupuestado` decimal(20,2) NOT NULL,
  `total_item_presupuestado` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `items_ingresados_ingreso_miel_propia`
--

DROP TABLE IF EXISTS `items_ingresados_ingreso_miel_propia`;
CREATE TABLE `items_ingresados_ingreso_miel_propia` (
  `codigo_item_ingresado` int(11) NOT NULL,
  `codigo_ingreso` int(11) NOT NULL,
  `descripcion_item_ingresado` varchar(60) NOT NULL,
  `cantidad_item_ingresado` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `locacion`
--

DROP TABLE IF EXISTS `locacion`;
CREATE TABLE `locacion` (
  `codigo_locacion` int(11) NOT NULL,
  `nombre_locacion` varchar(60) NOT NULL,
  `ubicacion_locacion` varchar(60) NOT NULL,
  `observacion` varchar(200) DEFAULT '',
  `categoria` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nota_credito_productor`
--

DROP TABLE IF EXISTS `nota_credito_productor`;
CREATE TABLE `nota_credito_productor` (
  `codigo_nota_credito` int(11) NOT NULL,
  `tipo_nota_credito` varchar(60) NOT NULL,
  `numero_comprobante` varchar(60) NOT NULL,
  `codigo_movimiento_cta_cte` int(11) NOT NULL,
  `codigo_productor` int(11) NOT NULL,
  `fecha_nota_credito` date NOT NULL,
  `importe_total_nota_credito` double(20,2) NOT NULL,
  `cantidad_miel_afectada` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago_cliente`
--

DROP TABLE IF EXISTS `pago_cliente`;
CREATE TABLE `pago_cliente` (
  `codigo_pago` int(11) NOT NULL,
  `codigo_movimiento_cta_cte` int(11) NOT NULL,
  `codigo_cliente` int(11) NOT NULL,
  `fecha_pago` date NOT NULL,
  `metodo_pago` varchar(60) NOT NULL,
  `observacion` varchar(100) NOT NULL,
  `monto_pago` decimal(20,2) NOT NULL,
  `codigo_comprobante_pagado` int(11) NOT NULL,
  `tipo_comprobante_pagado` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago_productor`
--

DROP TABLE IF EXISTS `pago_productor`;
CREATE TABLE `pago_productor` (
  `codigo_pago` int(11) NOT NULL,
  `codigo_movimiento_ctacte` int(11) NOT NULL,
  `codigo_productor` int(11) NOT NULL,
  `fecha_pago` date NOT NULL,
  `metodo_pago` varchar(60) NOT NULL,
  `observacion` varchar(100) NOT NULL,
  `monto_pago` double(20,2) NOT NULL,
  `codigo_comprobante_pagado` int(11) NOT NULL,
  `tipo_comprobante_pagado` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

DROP TABLE IF EXISTS `persona`;
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `presupuesto_productor`
--

DROP TABLE IF EXISTS `presupuesto_productor`;
CREATE TABLE `presupuesto_productor` (
  `codigo_presupuesto` int(11) NOT NULL,
  `numero_comprobante` varchar(60) NOT NULL,
  `codigo_movimiento_ctacte` int(11) NOT NULL,
  `codigo_productor` int(11) NOT NULL,
  `fecha_presupuesto` date NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `importe_total_presupuesto` double(20,2) NOT NULL,
  `cantidad_miel` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productor`
--

DROP TABLE IF EXISTS `productor`;
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stock_real_miel`
--

DROP TABLE IF EXISTS `stock_real_miel`;
CREATE TABLE `stock_real_miel` (
  `codigo_movimiento` int(11) NOT NULL,
  `fecha_movimiento` date NOT NULL,
  `tipo_movimiento` varchar(60) NOT NULL,
  `comprobante_asociado` varchar(60) NOT NULL,
  `id_comprobante_asociado` int(11) NOT NULL,
  `numero_comprobante_asociado` varchar(60) NOT NULL,
  `cantidad_miel` decimal(20,2) NOT NULL,
  `locacion_miel` int(11) NOT NULL,
  `miel_deposito_productor` int(11) DEFAULT NULL,
  `estado_compra` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `traslado`
--

DROP TABLE IF EXISTS `traslado`;
CREATE TABLE `traslado` (
  `codigo_traslado` int(11) NOT NULL,
  `numero_comprobante` varchar(60) NOT NULL,
  `descripcion_item_trasladado` varchar(60) NOT NULL,
  `cantidad_item_trasladado` decimal(20,0) NOT NULL,
  `observacion_traslado` varchar(200) NOT NULL,
  `origen_traslado` int(11) NOT NULL,
  `destino_traslado` int(11) NOT NULL,
  `fecha_traslado` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
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
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cod_cliente`),
  ADD KEY `cliente` (`cod_persona`);

--
-- Indices de la tabla `comprobantes_relacionados_compra_credito`
--
ALTER TABLE `comprobantes_relacionados_compra_credito`
  ADD PRIMARY KEY (`codigo_productor`,`codigo_compra_consignacion`,`codigo_comprobante_relacionado`);

--
-- Indices de la tabla `credito_productor`
--
ALTER TABLE `credito_productor`
  ADD PRIMARY KEY (`codigo_credito`),
  ADD KEY `pfkp` (`codigo_productor`);

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
-- Indices de la tabla `devolucion_productor`
--
ALTER TABLE `devolucion_productor`
  ADD PRIMARY KEY (`codigo_devolucion`),
  ADD KEY `pfk` (`codigo_productor`);

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
-- Indices de la tabla `ingreso_miel_propia`
--
ALTER TABLE `ingreso_miel_propia`
  ADD PRIMARY KEY (`codigo_ingreso`);

--
-- Indices de la tabla `items_devueltos_devolucion_productor`
--
ALTER TABLE `items_devueltos_devolucion_productor`
  ADD PRIMARY KEY (`codigo_item_devuelto`,`codigo_devolucion`),
  ADD KEY `devolucionfk` (`codigo_devolucion`);

--
-- Indices de la tabla `items_facturados_credito_productor`
--
ALTER TABLE `items_facturados_credito_productor`
  ADD PRIMARY KEY (`codigo_item_financiado`,`codigo_credito`),
  ADD KEY `creditofk` (`codigo_credito`);

--
-- Indices de la tabla `items_facturados_factura_cliente`
--
ALTER TABLE `items_facturados_factura_cliente`
  ADD PRIMARY KEY (`codigo_item_facturado`,`codigo_factura`),
  ADD KEY `fkFacturaCliente` (`codigo_factura`);

--
-- Indices de la tabla `items_facturados_factura_productor`
--
ALTER TABLE `items_facturados_factura_productor`
  ADD PRIMARY KEY (`codigo_item_facturado`,`codigo_factura`),
  ADD KEY `fkFactura` (`codigo_factura`);

--
-- Indices de la tabla `items_facturados_presupuesto_productor`
--
ALTER TABLE `items_facturados_presupuesto_productor`
  ADD PRIMARY KEY (`codigo_item_presupuestado`,`codigo_presupuesto`),
  ADD KEY `fkPresupuesto` (`codigo_presupuesto`);

--
-- Indices de la tabla `items_ingresados_ingreso_miel_propia`
--
ALTER TABLE `items_ingresados_ingreso_miel_propia`
  ADD PRIMARY KEY (`codigo_item_ingresado`,`codigo_ingreso`),
  ADD KEY `ingresofk` (`codigo_ingreso`);

--
-- Indices de la tabla `locacion`
--
ALTER TABLE `locacion`
  ADD PRIMARY KEY (`codigo_locacion`);

--
-- Indices de la tabla `nota_credito_productor`
--
ALTER TABLE `nota_credito_productor`
  ADD PRIMARY KEY (`codigo_nota_credito`),
  ADD KEY `productorfkfff` (`codigo_productor`);

--
-- Indices de la tabla `pago_cliente`
--
ALTER TABLE `pago_cliente`
  ADD PRIMARY KEY (`codigo_pago`);

--
-- Indices de la tabla `pago_productor`
--
ALTER TABLE `pago_productor`
  ADD PRIMARY KEY (`codigo_pago`),
  ADD KEY `productorFK` (`codigo_productor`);

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
-- Indices de la tabla `stock_real_miel`
--
ALTER TABLE `stock_real_miel`
  ADD PRIMARY KEY (`codigo_movimiento`),
  ADD KEY `locacionfk` (`locacion_miel`);

--
-- Indices de la tabla `traslado`
--
ALTER TABLE `traslado`
  ADD PRIMARY KEY (`codigo_traslado`),
  ADD KEY `origenfkLocacion` (`origen_traslado`),
  ADD KEY `destinofkLocacion` (`destino_traslado`);

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
  MODIFY `cod_cliente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `credito_productor`
--
ALTER TABLE `credito_productor`
  MODIFY `codigo_credito` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `devolucion_productor`
--
ALTER TABLE `devolucion_productor`
  MODIFY `codigo_devolucion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `factura_cliente`
--
ALTER TABLE `factura_cliente`
  MODIFY `codigo_factura` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `factura_productor`
--
ALTER TABLE `factura_productor`
  MODIFY `codigo_factura` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ingreso_miel_propia`
--
ALTER TABLE `ingreso_miel_propia`
  MODIFY `codigo_ingreso` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `locacion`
--
ALTER TABLE `locacion`
  MODIFY `codigo_locacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `nota_credito_productor`
--
ALTER TABLE `nota_credito_productor`
  MODIFY `codigo_nota_credito` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pago_productor`
--
ALTER TABLE `pago_productor`
  MODIFY `codigo_pago` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `cod_persona` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `presupuesto_productor`
--
ALTER TABLE `presupuesto_productor`
  MODIFY `codigo_presupuesto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productor`
--
ALTER TABLE `productor`
  MODIFY `cod_productor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `stock_real_miel`
--
ALTER TABLE `stock_real_miel`
  MODIFY `codigo_movimiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `traslado`
--
ALTER TABLE `traslado`
  MODIFY `codigo_traslado` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `cod_usuario` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente` FOREIGN KEY (`cod_persona`) REFERENCES `persona` (`cod_persona`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `credito_productor`
--
ALTER TABLE `credito_productor`
  ADD CONSTRAINT `pfkp` FOREIGN KEY (`codigo_productor`) REFERENCES `productor` (`cod_productor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
-- Filtros para la tabla `devolucion_productor`
--
ALTER TABLE `devolucion_productor`
  ADD CONSTRAINT `pfk` FOREIGN KEY (`codigo_productor`) REFERENCES `productor` (`cod_productor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
-- Filtros para la tabla `items_devueltos_devolucion_productor`
--
ALTER TABLE `items_devueltos_devolucion_productor`
  ADD CONSTRAINT `devolucionfk` FOREIGN KEY (`codigo_devolucion`) REFERENCES `devolucion_productor` (`codigo_devolucion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `items_facturados_credito_productor`
--
ALTER TABLE `items_facturados_credito_productor`
  ADD CONSTRAINT `creditofk` FOREIGN KEY (`codigo_credito`) REFERENCES `credito_productor` (`codigo_credito`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `items_facturados_factura_cliente`
--
ALTER TABLE `items_facturados_factura_cliente`
  ADD CONSTRAINT `fkFacturaCliente` FOREIGN KEY (`codigo_factura`) REFERENCES `factura_cliente` (`codigo_factura`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `items_facturados_factura_productor`
--
ALTER TABLE `items_facturados_factura_productor`
  ADD CONSTRAINT `fkFactura` FOREIGN KEY (`codigo_factura`) REFERENCES `factura_productor` (`codigo_factura`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `items_facturados_presupuesto_productor`
--
ALTER TABLE `items_facturados_presupuesto_productor`
  ADD CONSTRAINT `fkPresupuesto` FOREIGN KEY (`codigo_presupuesto`) REFERENCES `presupuesto_productor` (`codigo_presupuesto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `items_ingresados_ingreso_miel_propia`
--
ALTER TABLE `items_ingresados_ingreso_miel_propia`
  ADD CONSTRAINT `ingresofk` FOREIGN KEY (`codigo_ingreso`) REFERENCES `ingreso_miel_propia` (`codigo_ingreso`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `nota_credito_productor`
--
ALTER TABLE `nota_credito_productor`
  ADD CONSTRAINT `productorfkfff` FOREIGN KEY (`codigo_productor`) REFERENCES `productor` (`cod_productor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pago_productor`
--
ALTER TABLE `pago_productor`
  ADD CONSTRAINT `productorFK` FOREIGN KEY (`codigo_productor`) REFERENCES `productor` (`cod_productor`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
-- Filtros para la tabla `stock_real_miel`
--
ALTER TABLE `stock_real_miel`
  ADD CONSTRAINT `locacionfk` FOREIGN KEY (`locacion_miel`) REFERENCES `locacion` (`codigo_locacion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `traslado`
--
ALTER TABLE `traslado`
  ADD CONSTRAINT `destinofkLocacion` FOREIGN KEY (`destino_traslado`) REFERENCES `locacion` (`codigo_locacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `origenfkLocacion` FOREIGN KEY (`origen_traslado`) REFERENCES `locacion` (`codigo_locacion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuarioFK` FOREIGN KEY (`cod_persona`) REFERENCES `persona` (`cod_persona`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
