CREATE DATABASE  IF NOT EXISTS `mariospizzabar` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mariospizzabar`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: mariospizzabar
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient` (
  `pizzalist_id` int NOT NULL,
  `Ingredient` varchar(45) NOT NULL,
  PRIMARY KEY (`pizzalist_id`),
  CONSTRAINT `fk_PizzaIngredients_PizzaList` FOREIGN KEY (`pizzalist_id`) REFERENCES `pizzalist` (`pizzalist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `ID` int NOT NULL,
  `pizzalist_id` int NOT NULL,
  PRIMARY KEY (`ID`,`pizzalist_id`),
  KEY `fk_OrderList_PizzaList1_idx` (`pizzalist_id`),
  CONSTRAINT `fk_OrderList_PizzaList1` FOREIGN KEY (`pizzalist_id`) REFERENCES `pizzalist` (`pizzalist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizzalist`
--

DROP TABLE IF EXISTS `pizzalist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizzalist` (
  `pizzalist_id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Price` float NOT NULL,
  PRIMARY KEY (`pizzalist_id`),
  UNIQUE KEY `Name_UNIQUE` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizzalist`
--

LOCK TABLES `pizzalist` WRITE;
/*!40000 ALTER TABLE `pizzalist` DISABLE KEYS */;
INSERT INTO `pizzalist` VALUES (1,'Margherita',57),(2,'Vesuvio',66),(3,'Capricciosa',68),(4,'Calzone',68),(5,'Quattro stagioni',74),(6,'Marinara',74),(7,'Vegetariana',72),(8,'Italiana',72),(9,'Gorganzola',73),(10,'Salat pizza',74),(11,'Napoli',70),(12,'Vinchiga',72),(13,'Napoletana',70),(14,'Hawaii pizza',70),(15,'Pepo italiano',76),(16,'Sargegna',74),(17,'Romana',72),(18,'Sole',72),(19,'Costa smeralda',79),(20,'La salami',72),(21,'Rocco',74),(22,'Marco',75),(23,'Kokcode',76),(24,'Antonello',76),(25,'Pasqualino',70),(26,'Felix',68),(27,'Parma special',82),(28,'Ventricina special',82),(29,'Margherita special',79),(30,'4 Formaggi',81);
/*!40000 ALTER TABLE `pizzalist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-23 13:13:17
