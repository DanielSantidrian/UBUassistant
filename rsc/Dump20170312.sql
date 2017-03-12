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
-- Table structure for table `aprendizaje`
--

DROP TABLE IF EXISTS `aprendizaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aprendizaje` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `palabra1` varchar(1000) NOT NULL,
  `palabra2` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aprendizaje`
--

LOCK TABLES `aprendizaje` WRITE;
/*!40000 ALTER TABLE `aprendizaje` DISABLE KEYS */;
/*!40000 ALTER TABLE `aprendizaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casedescription`
--

DROP TABLE IF EXISTS `casedescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casedescription` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `keyWord1` varchar(1000) NOT NULL,
  `keyWord2` varchar(1000) NOT NULL,
  `keyWord3` varchar(1000) NOT NULL,
  `keyWord4` varchar(1000) NOT NULL,
  `keyWord5` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `casedescription`
--

LOCK TABLES `casedescription` WRITE;
/*!40000 ALTER TABLE `casedescription` DISABLE KEYS */;
INSERT INTO `casedescription` VALUES (1,'informatica','ingenieria','matriculo','grados','estudios'),(2,'mecanica','ingenieria','matriculo','grados','estudios'),(3,'electronica','ingenieria','matriculo','grados','estudios'),(4,'organizacion industrial','matriculo','organizacion industrial','grados','estudios'),(5,'civil','Ingenieria','matriculo','grados','estudios'),(6,'becas','informacion','ayudas','movilidad','colaboracion'),(7,'calendario academico','calendario','dias festivos','laborables','docencia'),(8,'practicas','empleo','empresa','externas','trabajo'),(9,'ubuvirtual','ubu virtual','portal asignaturas','portal alumnos','mis cursos'),(10,'telefono','contacto','numero','llamar','movil'),(11,'email','correo electronico','contacto','correo','mail'),(12,'noticias','novedades','actualidad','hoy','suceso'),(13,'mapas','campus','como llegar','ruta','calle'),(14,'tarjeta universitaria','ventajas','tarjeta','carne','documento'),(15,'biblioteca','libros','material','revistas','recursos'),(16,'secretaria','matricula','secretaria virtual','grupos','desmatricular'),(17,'deporte','actividades','UBUabono','senderismo','esqui'),(18,'direccion','calle','como llegar','codigo postal','localidad');
/*!40000 ALTER TABLE `casedescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casesolution`
--

DROP TABLE IF EXISTS `casesolution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casesolution` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `answer` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `casesolution`
--

LOCK TABLES `casesolution` WRITE;
/*!40000 ALTER TABLE `casesolution` DISABLE KEYS */;
INSERT INTO `casesolution` VALUES (1,'http://wwww.ubu.es/grado-en-ingenieria-informatica'),(2,'http://wwww.ubu.es/grado-en-ingenieria-mecanica'),(3,'http://wwww.ubu.es/grado-en-ingenieria-electronica-industrial-y-automatica'),(4,'http://wwww.ubu.es/grado-en-ingenieria-de-organizacion-industrial'),(5,'http://wwww.ubu.es/grado-en-ingenieria-civil'),(6,'http://wwww.ubu.es/ayudas-y-becas'),(7,'http://wwww.ubu.es/vicerrectorado-de-politicas-academicas/ordenacion-academica/calendarios-academicos'),(8,'http://wwww.ubu.es/servicio-de-empleo-universitario-unidad-de-empleo'),(9,'https://ubuvirtual.ubu.es/'),(10,'El numero de teléfono de la universidad es 947258700'),(11,'El correo electrónico de la universidad es info@ubu.es'),(12,'http://wwww.ubu.es/noticias'),(13,'http://wwww.ubu.es/mapas'),(14,'http://wwww.ubu.es/ubuventajas'),(15,'http://wwww.ubu.es/biblioteca'),(16,'https://secretariavirtual.ubu.es/cosmos/Controlador/?apl=Uninavs&gu=a&idNav=inicio&NuevaSesionUsuario=true&NombreUsuarioAlumno=ALUMNO&responsive=S'),(17,'http://wwww.ubu.es/deportes'),(18,'La Univerisidad de Burgos está en la calle Hospital del Rey s/n 09001 Burgos España');
/*!40000 ALTER TABLE `casesolution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadisticas`
--

DROP TABLE IF EXISTS `estadisticas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadisticas` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `palabra` varchar(1000) NOT NULL,
  `categoria` varchar(1000) NOT NULL,
  `num_busquedas` int(10) NOT NULL,
  `num_votos` int(10) DEFAULT NULL,
  `valoracion_total` int(10) DEFAULT NULL,
  `valoracion_media_respuesta` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadisticas`
--

LOCK TABLES `estadisticas` WRITE;
/*!40000 ALTER TABLE `estadisticas` DISABLE KEYS */;
/*!40000 ALTER TABLE `estadisticas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frases`
--

DROP TABLE IF EXISTS `frases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `frases` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `frase` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frases`
--

LOCK TABLES `frases` WRITE;
/*!40000 ALTER TABLE `frases` DISABLE KEYS */;
INSERT INTO `frases` VALUES (1,'Hola soy UBUassistant, ¿en qué puedo ayudarte?'),(2,'Muy buenas, me llamo UBUassistant y estoy lista para ayudarte'),(3,'Hola, si quieres ayuda aquí tienes a UBUassistant'),(4,'Bienvenido, ante cualquier duda UBUassistant al rescate'),(5,'UBUassistant te saluda, ¿Empezamos?'),(6,'Tal vez esto te ayude'),(7,'Prueba con la siguiente información'),(8,'Espero que esto te ayude'),(9,'Esto es lo que he encontrado al respecto'),(10,'Aquí tienes mis respuestas');
/*!40000 ALTER TABLE `frases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saludos`
--

DROP TABLE IF EXISTS `saludos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `saludos` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `saludo` varchar(1000) NOT NULL,
  `respuesta` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saludos`
--

LOCK TABLES `saludos` WRITE;
/*!40000 ALTER TABLE `saludos` DISABLE KEYS */;
INSERT INTO `saludos` VALUES (1,'Hola','Hola, estoy preparada para responder, adelante'),(2,'Buenos dias','Buenos días, ponme a prueba con tus preguntas'),(3,'Buenas tardes','Buenas tardes, ¿tienes alguna duda? No dudes en preguntarme'),(4,'Buenas noches','Buenas noches, ¿qué te apetece preguntar?'),(5,'Buenas','Muy buenas serán si te contesto correctamente'),(6,'Adios','¡Adios!, espero haberte servido de ayuda'),(7,'Hasta luego','Hasta luego, espero que volvamos a vernos'),(8,'Ciao','¡Arrivederci!'),(9,'Eres una pesada','No te pongas así, seguro que podemos tener una conversación productiva'),(10,'Callate','Solo intentaba ayudar');
/*!40000 ALTER TABLE `saludos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-12 19:20:03
