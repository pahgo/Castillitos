-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: castillos
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `jugadores`
--

DROP TABLE IF EXISTS `jugadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jugadores` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `aka` varchar(45) DEFAULT NULL,
  `fecha_registro` date DEFAULT NULL,
  `id_alianza` int(11) NOT NULL DEFAULT '1',
  `puntos` int(11) DEFAULT '0',
  `puntos_prev` int(11) DEFAULT '0',
  `fragmentos` int(11) DEFAULT '0',
  `fragmentos_prev` int(11) DEFAULT '0',
  `id_old` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `TMP_INDEX` (`id_old`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugadores`
--

LOCK TABLES `jugadores` WRITE;
/*!40000 ALTER TABLE `jugadores` DISABLE KEYS */;
INSERT INTO `jugadores` VALUES (1,'antonioferez',NULL,NULL,1,41140,11,6301,0,1),(2,'bambuinaa',NULL,NULL,1,69910,6137,45840,3800,2),(3,'dad_glub',NULL,NULL,1,120554,4850,71748,2464,3),(4,'elsoriano',NULL,NULL,1,58779,1563,33889,2410,4),(5,'fungi',NULL,NULL,1,158564,1210,0,0,5),(6,'HolyTrinity',NULL,NULL,1,21345,2255,1914,270,6),(7,'-JesuS-',NULL,NULL,1,63019,4015,9613,201,7),(8,'MickyAnghell',NULL,NULL,1,46331,1684,4789,550,8),(9,'Mifail',NULL,NULL,1,62532,3264,8514,2067,9),(10,'Nakyl',NULL,NULL,1,15943,0,8637,0,10),(11,'P3lu',NULL,NULL,1,16148,2012,159,30,11),(12,'Pahgo',NULL,NULL,1,82517,1651,49744,1875,12),(13,'Pennytencia',NULL,NULL,1,69880,5268,11000,4271,13),(14,'Zashgard',NULL,NULL,1,12063,759,1095,0,14);
/*!40000 ALTER TABLE `jugadores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-06  0:38:57
