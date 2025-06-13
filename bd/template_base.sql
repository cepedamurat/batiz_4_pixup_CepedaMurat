-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.42 - MySQL Community Server - GPL
-- SO del servidor:              Linux
-- HeidiSQL Versión:             12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para pixup
CREATE DATABASE IF NOT EXISTS `pixup` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pixup`;

-- Volcando estructura para tabla pixup.artista
CREATE TABLE IF NOT EXISTS `artista` (
  `id_artista` int NOT NULL AUTO_INCREMENT,
  `nombre` tinytext NOT NULL,
  PRIMARY KEY (`id_artista`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla pixup.cancion
CREATE TABLE IF NOT EXISTS `cancion` (
  `id_cancion` int NOT NULL AUTO_INCREMENT,
  `titulo` tinytext NOT NULL,
  `duracion` int NOT NULL DEFAULT (0),
  `id_disco` int NOT NULL,
  PRIMARY KEY (`id_cancion`),
  KEY `id_disco` (`id_disco`),
  CONSTRAINT `id_disco` FOREIGN KEY (`id_disco`) REFERENCES `disco` (`id_disco`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla pixup.colonia
CREATE TABLE IF NOT EXISTS `colonia` (
  `id_colonia` int NOT NULL AUTO_INCREMENT,
  `nombre` tinytext NOT NULL,
  `cp` char(5) DEFAULT NULL,
  `id_municipio` int NOT NULL DEFAULT (0),
  PRIMARY KEY (`id_colonia`),
  KEY `id_municipio` (`id_municipio`),
  CONSTRAINT `id_municipio` FOREIGN KEY (`id_municipio`) REFERENCES `municipio` (`id_municipio`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla pixup.detalle_orden
CREATE TABLE IF NOT EXISTS `detalle_orden` (
  `id_detalle_orden` int NOT NULL AUTO_INCREMENT,
  `cantidad` int DEFAULT NULL,
  `subtotal` float DEFAULT NULL,
  `id_orden` int DEFAULT NULL,
  `id_disco` int DEFAULT NULL,
  PRIMARY KEY (`id_detalle_orden`),
  KEY `id_orden` (`id_orden`),
  CONSTRAINT `id_orden` FOREIGN KEY (`id_orden`) REFERENCES `orden` (`id_orden`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla pixup.disco
CREATE TABLE IF NOT EXISTS `disco` (
  `id_disco` int NOT NULL AUTO_INCREMENT,
  `titulo` tinytext NOT NULL,
  `precio` float NOT NULL DEFAULT (0),
  `existencia` int NOT NULL DEFAULT (0),
  `descuento` float NOT NULL DEFAULT (0),
  `fecha_lanzamiento` date DEFAULT NULL,
  `imagen` mediumtext NOT NULL,
  `id_disquera` int NOT NULL,
  `id_artista` int NOT NULL,
  `id_genero_musical` int NOT NULL,
  PRIMARY KEY (`id_disco`),
  KEY `id_disquera` (`id_disquera`),
  KEY `id_artista` (`id_artista`),
  KEY `id_genero_musical` (`id_genero_musical`),
  CONSTRAINT `id_artista` FOREIGN KEY (`id_artista`) REFERENCES `artista` (`id_artista`),
  CONSTRAINT `id_disquera` FOREIGN KEY (`id_disquera`) REFERENCES `disquera` (`id_disquera`),
  CONSTRAINT `id_genero_musical` FOREIGN KEY (`id_genero_musical`) REFERENCES `genero_musical` (`id_genero_musical`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla pixup.disquera
CREATE TABLE IF NOT EXISTS `disquera` (
  `id_disquera` int NOT NULL AUTO_INCREMENT,
  `nombre` tinytext NOT NULL,
  PRIMARY KEY (`id_disquera`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla pixup.domicilio
CREATE TABLE IF NOT EXISTS `domicilio` (
  `id_domicilio` int NOT NULL AUTO_INCREMENT,
  `calle` tinytext,
  `numero_exterior` int DEFAULT NULL,
  `numero_interior` int DEFAULT NULL,
  `id_usuario` int DEFAULT NULL,
  `id_colonia` int DEFAULT NULL,
  PRIMARY KEY (`id_domicilio`),
  KEY `id_colonia` (`id_colonia`),
  KEY `id_usuario_domicilio` (`id_usuario`),
  CONSTRAINT `id_colonia` FOREIGN KEY (`id_colonia`) REFERENCES `colonia` (`id_colonia`),
  CONSTRAINT `id_usuario_domicilio` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla pixup.estado
CREATE TABLE IF NOT EXISTS `estado` (
  `id_estado` int NOT NULL AUTO_INCREMENT,
  `nombre_estado` tinytext NOT NULL,
  PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla pixup.genero_musical
CREATE TABLE IF NOT EXISTS `genero_musical` (
  `id_genero_musical` int NOT NULL AUTO_INCREMENT,
  `genero_musical` tinytext NOT NULL,
  PRIMARY KEY (`id_genero_musical`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla pixup.municipio
CREATE TABLE IF NOT EXISTS `municipio` (
  `id_municipio` int NOT NULL AUTO_INCREMENT,
  `nombre` tinytext NOT NULL,
  `id_estado` int NOT NULL DEFAULT (0),
  PRIMARY KEY (`id_municipio`),
  KEY `id_estado` (`id_estado`),
  CONSTRAINT `id_estado` FOREIGN KEY (`id_estado`) REFERENCES `estado` (`id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla pixup.notificacion
CREATE TABLE IF NOT EXISTS `notificacion` (
  `id_notificacion` int NOT NULL AUTO_INCREMENT,
  `fecha_notificacion` date DEFAULT NULL,
  `mensaje` text,
  `id_usuario` int DEFAULT NULL,
  `id_tipo_notificacion` int DEFAULT NULL,
  PRIMARY KEY (`id_notificacion`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_tipo_notificacion` (`id_tipo_notificacion`),
  CONSTRAINT `id_tipo_notificacion` FOREIGN KEY (`id_tipo_notificacion`) REFERENCES `tipo_notificacion` (`id_tipo_notificacion`),
  CONSTRAINT `id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla pixup.orden
CREATE TABLE IF NOT EXISTS `orden` (
  `id_orden` int NOT NULL AUTO_INCREMENT,
  `costo_total` float NOT NULL DEFAULT (0),
  `fecha` date NOT NULL,
  `cantidad_total` int DEFAULT NULL,
  `id_usuario` int DEFAULT NULL,
  PRIMARY KEY (`id_orden`),
  KEY `id_ususario` (`id_usuario`),
  CONSTRAINT `id_ususario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla pixup.pago
CREATE TABLE IF NOT EXISTS `pago` (
  `id_pago` int NOT NULL AUTO_INCREMENT,
  `numero_tarjeta` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `monto` float NOT NULL DEFAULT (0),
  `fecha` date NOT NULL,
  `id_orden_pago` int NOT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `id_orden_pago` (`id_orden_pago`),
  CONSTRAINT `id_orden_pago` FOREIGN KEY (`id_orden_pago`) REFERENCES `orden` (`id_orden`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla pixup.tipo_domicilio
CREATE TABLE IF NOT EXISTS `tipo_domicilio` (
  `id_tipo_domicilio` int NOT NULL AUTO_INCREMENT,
  `nombre_tipo_domicilio` tinytext NOT NULL,
  PRIMARY KEY (`id_tipo_domicilio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla pixup.tipo_notificacion
CREATE TABLE IF NOT EXISTS `tipo_notificacion` (
  `id_tipo_notificacion` int NOT NULL AUTO_INCREMENT,
  `nombre_tipo_notificacion` tinytext NOT NULL,
  PRIMARY KEY (`id_tipo_notificacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla pixup.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre_usuario` tinytext NOT NULL,
  `apellido_paterno` tinytext NOT NULL,
  `apellido_materno` tinytext NOT NULL,
  `email` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rfc` tinytext NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- La exportación de datos fue deseleccionada.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
