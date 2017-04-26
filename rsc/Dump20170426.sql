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
  `userid` bigint(20) NOT NULL,
  `palabra1` varchar(1000) NOT NULL,
  `palabra2` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aprendizaje`
--

LOCK TABLES `aprendizaje` WRITE;
/*!40000 ALTER TABLE `aprendizaje` DISABLE KEYS */;
INSERT INTO `aprendizaje` VALUES (1,170407210630337,'grado','http://wwww.ubu.es/grado-en-ingenieria-informatica'),(2,170423125452094,'ingeneria','http://www.ubu.es/master-universitario-en-ingenieria-industrial'),(3,170423132739083,'calallate','http://www.ubu.es/master-universitario-en-integridad-y-durabilidad-de-materiales-componentes-y-estructuras-interuniversitario');
/*!40000 ALTER TABLE `aprendizaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casedescription`
--

DROP TABLE IF EXISTS `casedescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casedescription` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `keyWord1` varchar(1000) DEFAULT NULL,
  `keyWord2` varchar(1000) DEFAULT NULL,
  `keyWord3` varchar(1000) DEFAULT NULL,
  `keyWord4` varchar(1000) DEFAULT NULL,
  `keyWord5` varchar(1000) DEFAULT NULL,
  `categoria` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `casedescription`
--

LOCK TABLES `casedescription` WRITE;
/*!40000 ALTER TABLE `casedescription` DISABLE KEYS */;
INSERT INTO `casedescription` VALUES (1,'online','distancia',NULL,NULL,'master','estudios'),(2,'lengua (online)','literatura','lengua','artes','online','estudios'),(3,'lengua','literatura',NULL,'artes','humanidades','estudios'),(4,'historia (online)','patrimonio','historia','artes','online','estudios'),(5,'historia','patrimonio',NULL,'artes','humanidades','estudios'),(6,'química','ciencias',NULL,NULL,NULL,'estudios'),(7,'cyta','tecnologia','alimentos','salud',NULL,'estudios'),(8,'enfermería','salud',NULL,NULL,NULL,'estudios'),(9,'terapia','ocupacional',NULL,'salud',NULL,'estudios'),(10,'dade','derecho','administracion','direccion','empresas','estudios'),(11,'derecho politica','politica','publica','doble','derecho','estudios'),(12,'ade','administracion','direccion','empresas','sociales','estudios'),(13,'politica (online)','politica','publica','online','sociales','estudios'),(14,'politica','ciencia','publica','gestion','sociales','estudios'),(15,'audiovisual','comunicacion',NULL,'juridicas','sociales','estudios'),(16,'derecho','unica',NULL,'juridicas','sociales','estudios'),(17,'educacion social','educacion','social','juridicas','sociales','estudios'),(18,'finanzas','contabilidad',NULL,'juridicas','sociales','estudios'),(19,'eduacion infantil','eduacion','infantil','maestro','sociales','estudios'),(20,'eduacion primaria','eduacion','primaria','maestro','sociales','estudios'),(21,'pedagogia',NULL,NULL,'juridicas','sociales','estudios'),(22,'relaciones laborales','relaciones','laborales','recursos','humanos','estudios'),(23,'turismo (online)','turismo','online','juridicas','sociales','estudios'),(24,'turismo',NULL,NULL,'juridicas','sociales','estudios'),(25,'civil y tecnica','civil','arquitectura','tecnica','doble','estudios'),(26,'caminos y tecnica','caminos','arquitectura','tecnica','doble','estudios'),(27,'mecanica y electronica','mecanica','electronica','automatica','doble','estudios'),(28,'tecnica','arquitectura',NULL,NULL,NULL,'estudios'),(29,'agroalimentaria','rural','medio','ingenieria',NULL,'estudios'),(30,'civil','ingenieria',NULL,NULL,NULL,'estudios'),(31,'organizacion industrial',NULL,'organizacion','industrial','ingenieria','estudios'),(32,'caminos','tecnologias',NULL,NULL,'ingenieria','estudios'),(33,'electronica','automatica','ingenieria',NULL,NULL,'estudios'),(34,'informatica (online)','informatica','online','ingenieria',NULL,'estudios'),(35,'informatica',NULL,NULL,'ingenieria',NULL,'estudios'),(36,'mecanica','ingenieria',NULL,NULL,NULL,'estudios'),(37,'patrimonio (master)','patrimonio','comunicacion','master','artes','estudios'),(38,'vino','cultura','enoturismo','master','semipresencial','estudios'),(39,'quimica (master)','quimica','avanzada','master','ciencias','estudios'),(40,'evolucion humana','evolucion','humana','master','interuniversitario','estudios'),(41,'electroquimica','ciencia','tecnología','master','interuniversitario','estudios'),(42,'seguridad y biotecnología','seguridad','biotecnología','master','alimentarias','estudios'),(43,'formación pedagógica didáctica','pedagógica','didáctica','curso','formacion','estudios'),(44,'abogacía','acceso','sociales','master',NULL,'estudios'),(45,'administracion empresas (master)','administracion','empresas','master','MBA','estudios'),(46,'multimedia','desarrollo','comunicacion','master',NULL,'estudios'),(47,'contabilidad','avanzada','auditoria','master','cuentas','estudios'),(48,'cooperacion','internacional','desarrollo','master','interuniversitario','estudios'),(49,'derecho y administracion local','derecho','administracion','master','local','estudios'),(50,'educacion y sociedad inclusivas','educacion','sociedad','master','inclusivas','estudios'),(51,'economía empresa','investigacion','administracion','master','empresa','estudios'),(52,'profesor','educacion','secundaria','master','bachillerato','estudios'),(53,'informatica (master)','informatica','master','ingenieria',NULL,'estudios'),(54,'informatica (master, online)','informatica','master','ingenieria','online','estudios'),(55,'industrial (master)','industrial','master','ingenieria',NULL,'estudios'),(56,'gestión agrosostenible','agrosostenible','master','ingenieria','gestion','estudios'),(57,'caminos (master)','caminos','master','canales','puertos','estudios'),(58,'edificacion','inspeccion','master','enegetica','rehabilitacion','estudios'),(59,'edificacion (online)','inspeccion','master','online','rehabilitacion','estudios'),(60,'integridad','durabilidad','master','materiales','componentes','estudios'),(61,'titulos','propios',NULL,NULL,NULL,'estudios'),(62,'idiomas','cursos','lenguas','modernas',NULL,'estudios'),(63,'internacionales','cursos','español',NULL,NULL,'estudios'),(64,'licenciaturas','extinguir','ingenierias',NULL,NULL,'estudios'),(65,'UBUabierta presenciales','cursos','presenciales','ubuabierta',NULL,'estudios'),(66,'UBUabierta online','cursos','presenciales','ubuabierta',NULL,'estudios'),(67,'nivel 0','cursos','0','ubuabierta','cero','estudios'),(68,'verano','cursos',NULL,'ubuabierta',NULL,'estudios'),(69,'infantiles','cursos','campus','ubuabierta',NULL,'estudios'),(70,'mayores','cursos','formacion','ubuabierta',NULL,'estudios'),(71,'departamentos','centros',NULL,NULL,NULL,'estudios'),(72,'becas','ayudas','bonificaciones',NULL,NULL,'estudios'),(73,'calendario','academico','festivos','docencia',NULL,'estudios'),(74,'tramites','academicos','expediente','compulsar','reconocimiento','estudios'),(75,'calidad','docencia',NULL,NULL,NULL,'estudios'),(76,'empleo','practicas','orientacion','emprender',NULL,'estudios'),(77,'formacion permanente','formacion','permanente','complementaria',NULL,'estudios'),(78,'curriculum docente','curriculum','docente','profesorado',NULL,'estudios'),(79,'estudios',NULL,NULL,NULL,NULL,'estudios'),(80,'ubuabierta',NULL,NULL,NULL,NULL,'estudios');
/*!40000 ALTER TABLE `casedescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casesolution`
--

DROP TABLE IF EXISTS `casesolution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casesolution` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `answer` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `casesolution`
--

LOCK TABLES `casesolution` WRITE;
/*!40000 ALTER TABLE `casesolution` DISABLE KEYS */;
INSERT INTO `casesolution` VALUES (1,'http://www.ubu.es/titulos-online'),(2,'http://www.ubu.es/grado-oficial-online-en-espanol-lengua-y-literatura'),(3,'http://www.ubu.es/grado-en-espanol-lengua-y-literatura'),(4,'http://www.ubu.es/grado-oficial-online-en-historia-y-patrimonio'),(5,'http://www.ubu.es/grado-en-historia-y-patrimonio'),(6,'http://www.ubu.es/grado-en-quimica'),(7,'http://www.ubu.es/grado-en-ciencia-y-tecnologia-de-los-alimentos'),(8,'http://www.ubu.es/grado-en-enfermeria'),(9,'http://www.ubu.es/grado-en-terapia-ocupacional'),(10,'http://www.ubu.es/doble-grado-en-derecho-y-administracion-y-direccion-de-empresas'),(11,'http://www.ubu.es/doble-grado-en-derecho-y-en-ciencia-politica-y-gestion-publica'),(12,'http://www.ubu.es/grado-en-administracion-y-direccion-de-empresas-bilingue-espanol'),(13,'http://www.ubu.es/grado-oficial-online-en-ciencia-politica-y-gestion-publica'),(14,'http://www.ubu.es/grado-en-ciencia-politica-y-gestion-publica'),(15,'http://www.ubu.es/grado-en-comunicacion-audiovisual'),(16,'http://www.ubu.es/grado-en-derecho'),(17,'http://www.ubu.es/grado-en-educacion-social'),(18,'http://www.ubu.es/grado-en-finanzas-y-contabilidad'),(19,'http://www.ubu.es/grado-en-maestro-de-educacion-infantil'),(20,'http://www.ubu.es/grado-en-maestro-de-educacion-primaria'),(21,'http://www.ubu.es/grado-en-pedagogia'),(22,'http://www.ubu.es/grado-en-relaciones-laborales-y-recursos-humanos'),(23,'http://www.ubu.es/grado-oficial-online-en-turismo'),(24,'http://www.ubu.es/grado-en-turismo'),(25,'http://www.ubu.es/doble-grado-en-ingenieria-civil-y-en-arquitectura-tecnica'),(26,'http://www.ubu.es/doble-grado-en-ingenieria-de-tecnologias-de-caminos-y-en-arquitectura-tecnica'),(27,'http://www.ubu.es/doble-grado-en-ingenieria-mecanica-e-ingenieria-electronica-industrial-y-automatica'),(28,'http://www.ubu.es/grado-en-arquitectura-tecnica'),(29,'http://www.ubu.es/grado-en-ingenieria-agroalimentaria-y-del-medio-rural'),(30,'http://www.ubu.es/grado-en-ingenieria-civil'),(31,'http://www.ubu.es/grado-en-ingenieria-de-organizacion-industrial-espanol-y-bilingue-en-ingles'),(32,'http://www.ubu.es/grado-en-ingenieria-de-tecnologias-de-caminos'),(33,'http://www.ubu.es/grado-en-ingenieria-electronica-industrial-y-automatica'),(34,'http://www.ubu.es/grado-oficial-online-en-ingenieria-informatica'),(35,'http://www.ubu.es/grado-en-ingenieria-informatica'),(36,'http://www.ubu.es/grado-en-ingenieria-mecanica'),(37,'http://www.ubu.es/master-universitario-en-patrimonio-y-comunicacion'),(38,'http://www.ubu.es/master-universitario-en-cultura-del-vino-enoturismo-en-la-cuenca-del-duero-semipresencial'),(39,'http://www.ubu.es/master-universitario-en-quimica-avanzada'),(40,'http://www.ubu.es/master-universitario-en-evolucion-humana-interuniversitario'),(41,'http://www.ubu.es/master-universitario-en-electroquimica-ciencia-y-tecnologia-interuniversitario'),(42,'http://www.ubu.es/master-universitario-en-seguridad-y-biotecnologia-alimentarias'),(43,'http://www.ubu.es/curso-de-formacion-pedagogica-y-didactica'),(44,'http://www.ubu.es/master-universitario-en-acceso-la-abogacia'),(45,'http://www.ubu.es/master-universitario-en-administracion-de-empresas-mba'),(46,'http://www.ubu.es/master-universitario-en-comunicacion-y-desarrollo-multimedia'),(47,'http://www.ubu.es/master-universitario-en-contabilidad-avanzada-y-auditoria-de-cuentas-semipresencial'),(48,'http://www.ubu.es/estudios/oferta-de-estudios/masteres-universitarios-oficiales/ciencias-sociales-y-juridicas/master-universitario-en-cooperacion-internacional-para-el-desarrollo-interuniversitario'),(49,'http://cms.ual.es/UAL/estudios/masteres/MASTER7073'),(50,'http://www.ubu.es/master-universitario-en-educacion-y-sociedad-inclusivas'),(51,'http://www.ubu.es/facultad-de-ciencias-economicas-y-empresariales/informacion-academica/masteres/master-universitario-en-investigacion-en-administracion-y-economia-de-la-empresa-interuniversitario'),(52,'http://www.ubu.es/master-universitario-en-profesor-de-educacion-secundaria-obligatoria-y-bachillerato-formacion-profesional-y-ensenanza-de-idiomas'),(53,'http://www.ubu.es/master-universitario-en-ingenieria-informatica'),(54,'http://www.ubu.es/master-universitario-online-en-ingenieria-informatica'),(55,'http://www.ubu.es/master-universitario-en-ingenieria-industrial'),(56,'http://www.ubu.es/master-universitario-en-ingenieria-y-gestion-agrosostenible-semipresencial'),(57,'http://www.ubu.es/master-universitario-en-ingenieria-de-caminos-canales-y-puertos'),(58,'http://www.ubu.es/master-universitario-en-inspeccion-rehabilitacion-y-eficiencia-energetica-en-la-edificacion'),(59,'http://www.ubu.es/master-universitario-online-en-inspeccion-rehabilitacion-y-eficiencia-energetica-en-la-edificacion'),(60,'http://www.ubu.es/master-universitario-en-integridad-y-durabilidad-de-materiales-componentes-y-estructuras-interuniversitario'),(61,'http://www.ubu.es/estudios/oferta-de-estudios/titulos-propios'),(62,'http://www.ubu.es/centro-de-lenguas-modernas-modern-language-centre'),(63,'http://www.ubu.es/cursos-internacionales-cursos-de-espanol'),(64,'http://www.ubu.es/estudios/oferta-de-estudios/licenciaturas-e-ingenierias-extinguir'),(65,'http://www.ubu.es/ubuabierta-cursos-de-extension-universitaria/cursos-ubuabierta/cursos-ubuabierta-presenciales'),(66,'http://www.ubu.es/ubuabierta-cursos-de-extension-universitaria/cursos-ubuabierta/cursos-ubuabierta-online'),(67,'http://www.ubu.es/ubuabierta-cursos-de-extension-universitaria/cursos-0-cursos-complementarios-de-preparacion-para-el-inicio-de-una-carrera-universitaria'),(68,'http://www.ubu.es/cursos-de-verano-de-la-universidad-de-burgos'),(69,'http://www.ubu.es/ubuabierta-cursos-de-extension-universitaria/campus-infantiles'),(70,'http://www.ubu.es/ubuabierta-cursos-de-extension-universitaria/formacion-de-mayores'),(71,'http://www.ubu.es/la-universidad/organizacion/centros-y-departamentos'),(72,'http://www.ubu.es/ayudas-y-becas'),(73,'http://www.ubu.es/vicerrectorado-de-politicas-academicas/ordenacion-academica/calendarios-academicos'),(74,'http://www.ubu.es/acceso-admision-y-matricula/tramites-academicos'),(75,'http://www.ubu.es/vicerrectorado-de-profesorado-y-personal-de-administracion-y-servicios/calidad-de-la-docencia'),(76,'http://www.ubu.es/servicio-de-empleo-universitario-unidad-de-empleo'),(77,'http://www.ubu.es/acceso-admision-y-matricula/admision/formacion-permanente-y-complementaria'),(78,'http://www.ubu.es/vicerrectorado-de-ordenacion-academica-y-calidad/acceso-al-curriculum-abreviado-del-profesorado-de-la-ubu'),(79,'http://www.ubu.es/estudios/oferta-de-estudios'),(80,'http://www.ubu.es/ubuabierta-cursos-de-extension-universitaria');
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
  `userid` bigint(20) NOT NULL,
  `fecha` datetime NOT NULL,
  `keyWord1` varchar(1000) DEFAULT NULL,
  `keyWord2` varchar(1000) DEFAULT NULL,
  `keyWord3` varchar(1000) DEFAULT NULL,
  `keyWord4` varchar(1000) DEFAULT NULL,
  `keyWord5` varchar(1000) DEFAULT NULL,
  `categoria` varchar(1000) DEFAULT NULL,
  `respuesta` varchar(1000) DEFAULT NULL,
  `num_busquedas` int(10) NOT NULL,
  `num_votos` int(10) DEFAULT NULL,
  `valoracion_total` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadisticas`
--

LOCK TABLES `estadisticas` WRITE;
/*!40000 ALTER TABLE `estadisticas` DISABLE KEYS */;
INSERT INTO `estadisticas` VALUES (1,170406163350707,'2017-04-06 16:33:58','secretaria',NULL,NULL,NULL,NULL,'estudiantes','https://secretariavirtual.ubu.es',1,1,3),(2,170406163350707,'2017-04-06 16:34:00','mecanica',NULL,NULL,NULL,NULL,'estudiantes','http://wwww.ubu.es/grado-en-ingenieria-mecanica',1,0,0),(3,170407210630337,'2017-04-07 21:08:07','informatica',NULL,NULL,NULL,NULL,'estudiantes','http://wwww.ubu.es/grado-en-ingenieria-informatica',2,2,9),(4,170407210630337,'2017-04-07 21:08:00','donde esta la universidad?ç',NULL,NULL,NULL,NULL,NULL,NULL,1,0,0),(5,170407210630337,'2017-04-07 21:08:41','mapa',NULL,NULL,NULL,NULL,'estudiantes','http://wwww.ubu.es/mapas',1,0,0),(6,170407210630337,'2017-04-07 21:08:53','correo',NULL,NULL,NULL,NULL,'contacto','El correo electrónico de la universidad es info@ubu.es',1,0,0),(7,170407210630337,'2017-04-07 21:09:02','telefono',NULL,NULL,NULL,NULL,'contacto','El numero de teléfono de la universidad es 947258700',1,0,0),(8,170407210630337,'2017-04-07 21:09:32','puta',NULL,NULL,NULL,NULL,'estudiantes','http://wwww.ubu.es/mapas',1,1,1),(9,170407210630337,'2017-04-07 21:09:47','Silencio',NULL,NULL,NULL,NULL,NULL,NULL,1,0,0),(10,170411193404691,'2017-04-11 19:34:10','enfermeria',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/grado-en-enfermeria',1,0,0),(11,170411193404691,'2017-04-11 19:36:53','agroalimentario',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/grado-en-ingenieria-agroalimentaria-y-del-medio-rural',1,0,0),(12,170411193834048,'2017-04-11 19:38:41','derecho politica',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/doble-grado-en-derecho-y-en-ciencia-politica-y-gestion-publica',1,0,0),(13,170411193834048,'2017-04-11 19:38:55','caminos y tecnica',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/doble-grado-en-ingenieria-de-tecnologias-de-caminos-y-en-arquitectura-tecnica',1,0,0),(14,170411193834048,'2017-04-11 19:39:01','civil y tecnica',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/doble-grado-en-ingenieria-civil-y-en-arquitectura-tecnica',1,1,5),(15,170412182532659,'2017-04-12 18:25:44','vino',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/master-universitario-en-cultura-del-vino-enoturismo-en-la-cuenca-del-duero-semipresencial',2,0,0),(16,170412182532659,'2017-04-12 18:26:31','patrimonio master',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/master-universitario-en-patrimonio-y-comunicacion',1,0,0),(17,170412182532659,'2017-04-12 18:28:11','historia','patrimonio','online',NULL,NULL,'estudios','http://www.ubu.es/grado-oficial-online-en-historia-y-patrimonio',2,1,5),(18,170412183341930,'2017-04-12 18:34:23','historia','online',NULL,NULL,NULL,'estudios','http://www.ubu.es/grado-oficial-online-en-historia-y-patrimonio',1,0,0),(19,170412183341930,'2017-04-12 18:36:07','Seguridad y Biotecnología',NULL,NULL,NULL,NULL,NULL,NULL,1,0,0),(20,170412183814944,'2017-04-12 18:38:22','PATRIMONIO MASTER',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/master-universitario-en-patrimonio-y-comunicacion',1,0,0),(21,170412185425249,'2017-04-12 18:54:30','enfermeria',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/grado-en-enfermeria',1,0,0),(22,170412185425249,'2017-04-12 18:54:34','enfermería',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/grado-en-enfermeria',1,0,0),(23,170412200210991,'2017-04-12 20:02:30','curriculum',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/vicerrectorado-de-ordenacion-academica-y-calidad/acceso-al-curriculum-abreviado-del-profesorado-de-la-ubu',1,0,0),(24,170412200833281,'2017-04-12 20:08:37','estudios',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/estudios/oferta-de-estudios',1,0,0),(25,170412200833281,'2017-04-12 20:08:59','MECANICA Y ELECTRONICA',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/doble-grado-en-ingenieria-mecanica-e-ingenieria-electronica-industrial-y-automatica',1,0,0),(26,170417192452654,'2017-04-17 19:25:04','ingeniria',NULL,NULL,NULL,NULL,NULL,NULL,1,0,0),(27,170417192452654,'2017-04-17 19:25:18','INDUSTRIAL (MASTER)',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/master-universitario-en-ingenieria-industrial',1,0,0),(28,170417192452654,'2017-04-17 19:25:23','GESTIÓN AGROSOSTENIBLE',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/master-universitario-en-ingenieria-y-gestion-agrosostenible-semipresencial',1,1,4),(29,170417192452654,'2017-04-17 19:25:40','INFORMATICA (MASTER, ONLINE)',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/master-universitario-online-en-ingenieria-informatica',1,0,0),(30,170423124012818,'2017-04-23 12:40:31','enfermeria',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/grado-en-enfermeria',1,0,0),(31,170423125107448,'2017-04-23 12:51:15','enfermeria',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/grado-en-enfermeria',1,0,0),(32,170423125452094,'2017-04-23 12:55:07','becas',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/ayudas-y-becas',1,0,0),(33,170423125452094,'2017-04-23 12:55:13','ingeneria',NULL,NULL,NULL,NULL,NULL,NULL,1,0,0),(34,170423125452094,'2017-04-23 12:55:18','industrial (master)',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/master-universitario-en-ingenieria-industrial',1,0,0),(35,170423132249413,'2017-04-23 13:23:14','becas',NULL,NULL,NULL,NULL,NULL,NULL,1,0,0),(36,170423132739083,'2017-04-23 13:28:24','calallate',NULL,NULL,NULL,NULL,NULL,NULL,1,0,0),(37,170423132739083,'2017-04-23 13:28:28','integridad',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/master-universitario-en-integridad-y-durabilidad-de-materiales-componentes-y-estructuras-interuniversitario',1,0,0),(38,170423132739083,'2017-04-23 13:28:32','becas',NULL,NULL,NULL,NULL,'estudios','http://www.ubu.es/ayudas-y-becas',1,0,0),(39,170423132837452,'2017-04-23 13:28:42','becas',NULL,NULL,NULL,NULL,NULL,NULL,1,0,0);
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

-- Dump completed on 2017-04-26 10:38:57
