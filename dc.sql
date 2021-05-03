-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ppbs
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `app`
--

DROP TABLE IF EXISTS `app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `router` varchar(255) DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app`
--

LOCK TABLES `app` WRITE;
/*!40000 ALTER TABLE `app` DISABLE KEYS */;
INSERT INTO `app` VALUES (0,'售后评价','evaluate',1),(1,'选择供应商审核','choiceSupplierAudit',0),(2,'选择供应商','choiceSupplier',1),(3,'竞价厅','BiddingRoom',NULL),(4,'申购审核','applyBuyAudit',0),(5,'我的申购单','applyBuySetting',1),(6,'资格审核配置','qualiAuditSetting',0),(7,'资格审核申请','qualiAuditApply',NULL),(8,'资格审核','qualiAudit',0),(9,'首页','home',NULL);
/*!40000 ALTER TABLE `app` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audit`
--

DROP TABLE IF EXISTS `audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `audit` (
  `auditid` int NOT NULL AUTO_INCREMENT,
  `audittype` int DEFAULT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `committerid` int DEFAULT NULL,
  `auditerid` int DEFAULT NULL,
  `fileid` int DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT '待审核',
  `orderid` int DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`auditid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit`
--

LOCK TABLES `audit` WRITE;
/*!40000 ALTER TABLE `audit` DISABLE KEYS */;
INSERT INTO `audit` VALUES (1,1,'2021-04-10 11:05:39',2,1,15,'测试','单位2','通过',NULL,NULL),(2,1,'2021-04-10 18:12:49',4,1,17,'供应商1单位','供应商1地址','通过',NULL,NULL),(3,2,'2021-04-10 20:59:07',2,1,21,NULL,NULL,'通过',1,NULL),(4,1,'2021-04-12 18:07:35',5,1,22,'供应商2的单位','单位地址2','通过',NULL,NULL),(5,3,'2021-04-13 17:47:03',2,1,NULL,NULL,NULL,'通过',1,'disicicscs');
/*!40000 ALTER TABLE `audit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `files` (
  `fileid` int NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) DEFAULT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `userid` int DEFAULT NULL,
  PRIMARY KEY (`fileid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
INSERT INTO `files` VALUES (10,'1_1618037221285_拨音.png','2021-04-10 06:47:01',1),(11,'1_1618040225180_拨音.png','2021-04-10 07:37:05',1),(12,'1_1618040510948_拨音2.png','2021-04-10 07:41:50',1),(13,'2_1618050296272_拗长音.png','2021-04-10 10:24:56',2),(14,'2_1618052634456_拨音.png','2021-04-10 11:03:54',2),(15,'2_1618074558396_测试.doc','2021-04-10 17:09:18',2),(16,'4_1618077530487_博丽灵梦.jpg','2021-04-10 17:58:50',4),(17,'4_1618078362763_浊音半浊音.png','2021-04-10 18:12:42',4),(18,'2_1618088029537_申购单.doc','2021-04-10 20:53:49',2),(19,'2_1618088262850_申购单.doc','2021-04-10 20:57:43',2),(20,'2_1618115787886_新的申购单.doc','2021-04-11 04:36:27',2),(21,'2_1618115839521_新的申购单.doc','2021-04-11 04:37:19',2),(22,'5_1618250851091_history.log','2021-04-12 18:07:31',5);
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msg`
--

DROP TABLE IF EXISTS `msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `msg` (
  `msgid` int NOT NULL AUTO_INCREMENT,
  `isev` int DEFAULT '0',
  `auditid` int DEFAULT NULL,
  `msgtype` int DEFAULT NULL,
  `msg` varchar(255) DEFAULT NULL,
  `use` int DEFAULT '1',
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`msgid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msg`
--

LOCK TABLES `msg` WRITE;
/*!40000 ALTER TABLE `msg` DISABLE KEYS */;
INSERT INTO `msg` VALUES (1,0,1,0,'测试退回',0,'2021-04-10 14:25:58'),(2,0,1,0,'th',1,'2021-04-10 17:04:26'),(3,0,1,1,'tg',1,'2021-04-10 17:31:17'),(4,0,2,0,'退回',1,'2021-04-10 18:13:46'),(5,0,2,0,'退回重新改',1,'2021-04-10 18:26:20'),(6,0,2,1,'通过这次应该可以',1,'2021-04-10 18:28:27'),(7,0,3,0,'tuihui',0,'2021-04-11 03:14:04'),(8,0,3,0,'再次退回，请重新上传文件',1,'2021-04-11 04:34:52'),(9,0,3,1,'这次没问题了，通过',1,'2021-04-11 04:39:59'),(10,0,4,1,'通过',1,'2021-04-12 18:08:22'),(11,0,5,0,'测试审核3退回',1,'2021-04-13 19:31:16'),(12,0,5,1,'tgcs',1,'2021-04-13 20:41:31'),(13,1,2,NULL,'服务很好',1,'2021-04-14 12:15:46');
/*!40000 ALTER TABLE `msg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer`
--

DROP TABLE IF EXISTS `offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offer` (
  `offerid` int NOT NULL AUTO_INCREMENT,
  `orderid` int DEFAULT NULL,
  `userid` int DEFAULT NULL,
  `money` decimal(18,2) DEFAULT '0.00',
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`offerid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer`
--

LOCK TABLES `offer` WRITE;
/*!40000 ALTER TABLE `offer` DISABLE KEYS */;
INSERT INTO `offer` VALUES (1,1,4,666.60,'2021-04-11 11:55:33'),(2,1,4,555.50,'2021-04-11 11:59:40'),(3,1,5,77.77,'2021-04-12 18:09:30');
/*!40000 ALTER TABLE `offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orderid` int NOT NULL AUTO_INCREMENT,
  `committerid` int DEFAULT NULL,
  `accepterid` int DEFAULT NULL,
  `auditerid` int DEFAULT NULL,
  `audittype` int DEFAULT NULL,
  `ordername` varchar(255) DEFAULT NULL,
  `technology` varchar(255) DEFAULT NULL,
  `minmoney` decimal(18,2) DEFAULT '0.00',
  `maxmoney` decimal(18,2) DEFAULT '0.00',
  `auditid` int DEFAULT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `enddate` datetime DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,2,4,1,5,'项目1','修改的技术描述1',5000.00,77.77,5,'2021-04-10 20:59:07','2021-04-13 10:43:00');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sets`
--

DROP TABLE IF EXISTS `sets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) DEFAULT NULL,
  `usertype` int DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'current_timestamp',
  `px` int DEFAULT NULL,
  `fileid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sets`
--

LOCK TABLES `sets` WRITE;
/*!40000 ALTER TABLE `sets` DISABLE KEYS */;
INSERT INTO `sets` VALUES (2,'测试',1,NULL,'2021-04-10 07:37:08',1,11),(3,'测试2',2,NULL,'2021-04-10 07:41:52',1,12);
/*!40000 ALTER TABLE `sets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `usertype` int DEFAULT NULL,
  `audit` int DEFAULT '0',
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'current_timestamp',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','123456',NULL,NULL,0,1,'2021-04-05 08:21:07'),(2,'test','rename5','123456','15888888888','abc@qq.com',1,1,'2021-04-05 08:21:15'),(4,'供应商1',NULL,'123456',NULL,NULL,2,1,'2021-04-10 17:49:03'),(5,'供应商2',NULL,'123456',NULL,NULL,2,1,'2021-04-12 18:05:53');
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

-- Dump completed on 2021-04-14 20:18:15
