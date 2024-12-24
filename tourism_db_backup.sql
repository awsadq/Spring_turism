-- MySQL dump 10.13  Distrib 9.1.0, for Win64 (x86_64)
--
-- Host: localhost    Database: tourism_db
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `booking_date` date DEFAULT NULL,
  `customer_email` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `tour_id` int NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqdqqvcmq2u1pyn3w6tpwpf64e` (`tour_id`),
  KEY `FKkgseyy7t56x7lkjgu3wah5s3t` (`user_id`),
  CONSTRAINT `FKkgseyy7t56x7lkjgu3wah5s3t` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqdqqvcmq2u1pyn3w6tpwpf64e` FOREIGN KEY (`tour_id`) REFERENCES `tours` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'2024-12-22',NULL,NULL,9,6);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image_urls`
--

DROP TABLE IF EXISTS `image_urls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image_urls` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `tour_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbm2pwd6a9vht7877htl07ar52` (`tour_id`),
  CONSTRAINT `FKbm2pwd6a9vht7877htl07ar52` FOREIGN KEY (`tour_id`) REFERENCES `tours` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_urls`
--

LOCK TABLES `image_urls` WRITE;
/*!40000 ALTER TABLE `image_urls` DISABLE KEYS */;
INSERT INTO `image_urls` VALUES (1,'images/dubai1.jpg',1),(2,'images/dubai2.jpg',2),(3,'images/dubai3.jpg',3),(4,'images/dubai4.jpg',3),(5,'images/egypt1.jpg',4),(6,'images/egypt2.jpg',5),(7,'images/italy1.jpg',6),(8,'images/italy2.jpg',7),(9,'images/italy3.jpg',7),(10,'images/korea1.jpg',8),(11,'images/korea2.jpg',9),(12,'images/korea3.jpg',9),(13,'images/spain.jpg',10),(14,'images/vietnam.jpg',11);
/*!40000 ALTER TABLE `image_urls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_trip_plan`
--

DROP TABLE IF EXISTS `person_trip_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_trip_plan` (
  `person_id` bigint NOT NULL,
  `trip_plan_id` bigint NOT NULL,
  KEY `FKt4q0umd6laqquotn0xb1l9ujd` (`person_id`),
  KEY `FK78w5jbsh3uonygolawoxiixrg` (`trip_plan_id`),
  CONSTRAINT `FK78w5jbsh3uonygolawoxiixrg` FOREIGN KEY (`trip_plan_id`) REFERENCES `trip_plans` (`id`),
  CONSTRAINT `FK82006aepe96h1rbh8ytb553dd` FOREIGN KEY (`trip_plan_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK8xe1x8qq3kjjgfnbpkak15co1` FOREIGN KEY (`person_id`) REFERENCES `trip_plans` (`id`),
  CONSTRAINT `FKt4q0umd6laqquotn0xb1l9ujd` FOREIGN KEY (`person_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_trip_plan`
--

LOCK TABLES `person_trip_plan` WRITE;
/*!40000 ALTER TABLE `person_trip_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `person_trip_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tourist_places`
--

DROP TABLE IF EXISTS `tourist_places`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tourist_places` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `country` varchar(255) DEFAULT NULL,
  `place_name` varchar(255) NOT NULL,
  `description` tinytext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tourist_places`
--

LOCK TABLES `tourist_places` WRITE;
/*!40000 ALTER TABLE `tourist_places` DISABLE KEYS */;
INSERT INTO `tourist_places` VALUES (1,'Дубай','Дубай','Описание для Дубая'),(2,'Египет','Каир','Описание для Египта'),(3,'Италия','Рим','Описание для Италии'),(4,'Корея','Сеул','Описание для Кореи'),(5,'Испания','Барселона','Описание для Испании'),(6,'Вьетнам','Ханой','Описание для Вьетнама');
/*!40000 ALTER TABLE `tourist_places` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tours`
--

DROP TABLE IF EXISTS `tours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tours` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `duration` int DEFAULT NULL,
  `is_hot` bit(1) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `type` enum('ГАСТРОНОМИЧЕСКИЙ_ТУР','ОТДЫХ','ШОПИНГ','ЭКСКУРСИЯ') DEFAULT NULL,
  `tourist_place_id` bigint NOT NULL,
  `booking_date` date DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `is_booked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FKc2sqa16br8c0gx2t3uyr5orfx` (`tourist_place_id`),
  KEY `FKk2mgug1ux85wsjji9ds086nco` (`user_id`),
  CONSTRAINT `FKc2sqa16br8c0gx2t3uyr5orfx` FOREIGN KEY (`tourist_place_id`) REFERENCES `tourist_places` (`id`),
  CONSTRAINT `FKk2mgug1ux85wsjji9ds086nco` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tours`
--

LOCK TABLES `tours` WRITE;
/*!40000 ALTER TABLE `tours` DISABLE KEYS */;
INSERT INTO `tours` VALUES (1,'Отдых в Дубае, тур 1',7,_binary '',3000,'ОТДЫХ',1,'2024-12-24',6,1),(2,'Отдых в Дубае, тур 2',5,_binary '\0',2500,'ОТДЫХ',1,'2024-12-24',6,1),(3,'Отдых в Дубае, тур 3',10,_binary '',4000,'ОТДЫХ',1,NULL,NULL,0),(4,'Экскурсия в Египте, тур 1',7,_binary '\0',2000,'ЭКСКУРСИЯ',2,NULL,NULL,0),(5,'Экскурсия в Египте, тур 2',8,_binary '',2800,'ЭКСКУРСИЯ',2,'2024-12-24',6,1),(6,'Гастрономический тур в Италии, тур 1',6,_binary '',3500,'ГАСТРОНОМИЧЕСКИЙ_ТУР',3,NULL,NULL,0),(7,'Гастрономический тур в Италии, тур 2',5,_binary '\0',3000,'ГАСТРОНОМИЧЕСКИЙ_ТУР',3,'2024-12-24',6,1),(8,'Шопинг в Корее, тур 1',4,_binary '\0',1800,'ШОПИНГ',4,NULL,NULL,0),(9,'Шопинг в Корее, тур 2',6,_binary '',2200,'ШОПИНГ',4,NULL,NULL,0),(10,'Экскурсия в Испании',7,_binary '',2400,'ЭКСКУРСИЯ',5,NULL,NULL,0),(11,'Отдых во Вьетнаме',5,_binary '\0',1800,'ОТДЫХ',6,NULL,NULL,0);
/*!40000 ALTER TABLE `tours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip_plans`
--

DROP TABLE IF EXISTS `trip_plans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip_plans` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `days` int NOT NULL,
  `places_selected` varchar(255) DEFAULT NULL,
  `additional_notes` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7nne14ecuqid84b5goq47gwv0` (`user_id`),
  CONSTRAINT `FK7nne14ecuqid84b5goq47gwv0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip_plans`
--

LOCK TABLES `trip_plans` WRITE;
/*!40000 ALTER TABLE `trip_plans` DISABLE KEYS */;
INSERT INTO `trip_plans` VALUES (1,'2024-12-06',5,'Эйфелева башня (Париж)','хочу покушать круассаны','2024-12-03 09:04:16',NULL),(2,'2024-12-19',15,'Бурдж-Халифа','хочу увидеть саму высокую башню в мире','2024-12-10 17:33:22',NULL),(3,'2024-12-24',8,'Колизей (Рим)','хочу посетить историческое место','2024-12-10 18:05:07',NULL);
/*!40000 ALTER TABLE `trip_plans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'sulismanaliev@gmail.com','$2a$10$XwAT0RuXvu./Ztx8WY1f9uS12E4WAUwjWRsPGVFG61.nMmVWW04ay','suli',1),(2,'123@gmail.com','$2a$10$nsDXjigZ7xH9hQQVGFvCmeA1POrN1szEf3Q6DZmOgh146T8HyWtiW','saida',1),(3,'zinglinde@gmail.com','$2a$10$dkxrJVEsiREdimsYLnq5PuSec/0pxkbWS/1Ihqg9Mq.5qNAgTz1S.','zinglinde',1),(4,'qwerty@gmail.com','$2a$10$IalBcfBYgz.Qpe0kQVDmzOHX3XpTDKxRXPmVja4ZaXEW96JPbEHRC','qwerty',1),(5,'saidaurmanbetova555@gmail.com','$2a$10$ByWjLx1SxhYep72Py61xIeEefIkEGf2YkfAdjWCMp2TC4uzI.p246','user',1),(6,'krepche878@gmail.com','$2a$10$.x8qCXWW4X4tJQd2qdCIKev1F//Qcs87SVhf0Cxx66wGlv1Qk4qly','krepche878',1),(7,'vtornik@gmail.com','$2a$10$sVXs7LkJXvk9lst0vB.3JO7NSwdjEIKgSL8AGI7v3NKQaJLhQDmMq','vtornik',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-24 20:31:49
