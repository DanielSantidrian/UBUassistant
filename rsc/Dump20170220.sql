CREATE DATABASE  IF NOT EXISTS `ubuassistant` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ubuassistant`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: ubuassistant
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `casedescription`
--

DROP TABLE IF EXISTS `casedescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casedescription` (
  `id` int(11) NOT NULL,
  `keyWord1` text NOT NULL,
  `keyWord2` text NOT NULL,
  `keyWord3` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `casedescription`
--

LOCK TABLES `casedescription` WRITE;
/*!40000 ALTER TABLE `casedescription` DISABLE KEYS */;
INSERT INTO `casedescription` VALUES (1,'matriculo','Ingenieria','Informatica'),(2,'matriculo','Ingenieria','Mecanica'),(3,'matriculo','Ingenieria','Electronica'),(4,'matriculo','Ingenieria','Organizacion Industrial'),(5,'matriculo','Ingenieria','Civil'),(6,'informacion','becas','ayudas'),(7,'calendario academico','calendario','dias festivos'),(8,'practicas','empleo','empresa'),(9,'ubuvirtual','ubu virtual','portal asignaturas'),(10,'telefono','contacto','numero'),(11,'email','correo electronico','contacto'),(12,'noticias','novedades','actualidad'),(13,'mapas','campus','como llegar'),(14,'tarjeta universitaria','ventajas','tarjeta'),(15,'biblioteca','libros','material'),(16,'matricula','secretaria','secretaria virtual'),(17,'grupos','asignaturas','desmatricular'),(18,'direcccion','calle','como llegar');
/*!40000 ALTER TABLE `casedescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casesolution`
--

DROP TABLE IF EXISTS `casesolution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casesolution` (
  `id` int(11) NOT NULL,
  `answer` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `casesolution`
--

LOCK TABLES `casesolution` WRITE;
/*!40000 ALTER TABLE `casesolution` DISABLE KEYS */;
INSERT INTO `casesolution` VALUES (1,'http://wwww.ubu.es/grado-en-ingenieria-informatica'),(2,'http://wwww.ubu.es/grado-en-ingenieria-mecanica'),(3,'http://wwww.ubu.es/grado-en-ingenieria-electronica-industrial-y-automatica'),(4,'http://wwww.ubu.es/grado-en-ingenieria-de-organizacion-industrial'),(5,'http://wwww.ubu.es/grado-en-ingenieria-civil'),(6,'http://wwww.ubu.es/ayudas-y-becas'),(7,'http://wwww.ubu.es/vicerrectorado-de-politicas-academicas/ordenacion-academica/calendarios-academicos'),(8,'http://wwww.ubu.es/servicio-de-empleo-universitario-unidad-de-empleo'),(9,'https://ubuvirtual.ubu.es/'),(10,'947258700'),(11,'info@ubu.es'),(12,'http://wwww.ubu.es/noticias'),(13,'http://wwww.ubu.es/mapas'),(14,'http://wwww.ubu.es/ubuventajas'),(15,'http://wwww.ubu.es/biblioteca'),(16,'https://secretariavirtual.ubu.es/cosmos/Controlador/?apl=Uninavs&gu=a&idNav=inicio&NuevaSesionUsuario=true&NombreUsuarioAlumno=ALUMNO&responsive=S'),(17,'https://secretariavirtual.ubu.es/cosmos/Controlador/?apl=Uninavs&gu=a&idNav=inicio&NuevaSesionUsuario=true&NombreUsuarioAlumno=ALUMNO&responsive=S'),(18,'Hospital del Rey s/n - 09001 Burgos (España)');
/*!40000 ALTER TABLE `casesolution` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-20 13:46:25
