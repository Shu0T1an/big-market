-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: big_market
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `awards`
--

DROP TABLE IF EXISTS `award`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `award` (
  `id` int NOT NULL AUTO_INCREMENT,
  `award_id` int DEFAULT NULL,
  `award_key` varchar(255) DEFAULT NULL,
  `award_config` varchar(255) DEFAULT NULL,
  `award_desc` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `awards`
--

LOCK TABLES `award` WRITE;
/*!40000 ALTER TABLE `award` DISABLE KEYS */;
INSERT INTO `award` VALUES (1,101,'user_credit_random','1100','用户积分【优先透视图范围，如果没有则配置】','2023-12-09 11:07:06','2023-12-09 11:21:31'),(2,102,'openai_use_count','5','OpenAI 增加使用次数','2023-12-09 11:07:06','2023-12-09 11:22:59'),(3,103,'openai_use_count','10','OpenAI 增加使用次数','2023-12-09 11:07:06','2023-12-09 11:23:00'),(4,104,'openai_use_count','20','OpenAI 增加使用次数','2023-12-09 11:07:06','2023-12-09 11:24:58'),(5,105,'openai_model','gpt-4','OpenAI 增加模型','2023-12-09 11:07:06','2023-12-09 11:25:01'),(6,106,'openai_model','dall-e-2','OpenAI 增加模型','2023-12-09 11:07:06','2023-12-09 11:26:08'),(7,107,'openai_model','dall-e-3','OpenAI 增加模型','2023-12-09 11:07:06','2023-12-09 11:27:10'),(8,108,'openai_use_count','100','OpenAI 增加使用次数','2023-12-09 11:07:06','2023-12-09 11:28:55'),(9,109,'openai_model','gpt-4,dall-e-2,dall-e-3','OpenAI 增加模型','2023-12-09 11:07:06','2023-12-09 11:29:39');
/*!40000 ALTER TABLE `award` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-16 19:20:47
