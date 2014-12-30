-- MySQL dump 10.13  Distrib 5.5.33, for Linux (i686)
--
-- Host: localhost    Database: ibooking
-- ------------------------------------------------------
-- Server version	5.5.33-log

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
-- Table structure for table `ib_menu`
--

DROP TABLE IF EXISTS `ib_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ib_menu` (
  `menu_id` int(4) NOT NULL AUTO_INCREMENT,
  `menu_name` char(255) NOT NULL,
  `menu_price` int(16) NOT NULL,
  `menu_pic_addr` char(255) NOT NULL,
  `menu_type_id` int(4) NOT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ib_menu`
--

LOCK TABLES `ib_menu` WRITE;
/*!40000 ALTER TABLE `ib_menu` DISABLE KEYS */;
INSERT INTO `ib_menu` VALUES (1,'剁椒鱼头',15,'res/pic/剁椒鱼头.jpg',1),(2,'红烧肉',16,'res/pic/红烧肉.jpg',1),(3,'香芹香干肉丝',12,'res/pic/香芹香干肉丝.jpg',1),(4,'蒜蓉菠菜',10,'res/pic/蒜蓉菠菜.jpg',1),(5,'米饭',2,'res/pic/米饭.jpg',2),(6,'青椒牛肉',16,'res/pic/青椒牛肉.jpg',1),(7,'凉瓜炒蛋',12,'res/pic/凉瓜炒蛋.jpg',1),(8,'凉拌三丝',12,'res/pic/凉拌三丝.jpg',3);
/*!40000 ALTER TABLE `ib_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ib_menu_type`
--

DROP TABLE IF EXISTS `ib_menu_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ib_menu_type` (
  `menu_type_id` int(4) NOT NULL AUTO_INCREMENT,
  `menu_type_name` char(255) NOT NULL,
  PRIMARY KEY (`menu_type_id`),
  KEY `menu_type_name` (`menu_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ib_menu_type`
--

LOCK TABLES `ib_menu_type` WRITE;
/*!40000 ALTER TABLE `ib_menu_type` DISABLE KEYS */;
INSERT INTO `ib_menu_type` VALUES (2,'主食'),(3,'凉菜'),(1,'炒菜');
/*!40000 ALTER TABLE `ib_menu_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ib_option`
--

DROP TABLE IF EXISTS `ib_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ib_option` (
  `option_id` int(4) NOT NULL AUTO_INCREMENT,
  `option_name` char(255) NOT NULL,
  `option_value` char(255) NOT NULL,
  PRIMARY KEY (`option_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ib_option`
--

LOCK TABLES `ib_option` WRITE;
/*!40000 ALTER TABLE `ib_option` DISABLE KEYS */;
INSERT INTO `ib_option` VALUES (1,'title','我的小店'),(2,'idx_menu_lines','3');
/*!40000 ALTER TABLE `ib_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ib_order`
--

DROP TABLE IF EXISTS `ib_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ib_order` (
  `order_id` int(16) NOT NULL AUTO_INCREMENT,
  `order_user_name` char(255) NOT NULL,
  `order_time` datetime NOT NULL,
  `order_admin_name` char(255) NOT NULL,
  `order_accept` int(1) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ib_order`
--

LOCK TABLES `ib_order` WRITE;
/*!40000 ALTER TABLE `ib_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `ib_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ib_order_detail`
--

DROP TABLE IF EXISTS `ib_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ib_order_detail` (
  `detail_id` int(16) NOT NULL AUTO_INCREMENT,
  `detail_order_id` int(16) NOT NULL,
  `detail_menu_name` char(255) NOT NULL,
  `detail_menu_price` int(16) NOT NULL,
  `detail_amount` int(16) NOT NULL,
  `detail_remark` char(255) NOT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ib_order_detail`
--

LOCK TABLES `ib_order_detail` WRITE;
/*!40000 ALTER TABLE `ib_order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `ib_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ib_shopping`
--

DROP TABLE IF EXISTS `ib_shopping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ib_shopping` (
  `shopping_id` int(16) NOT NULL AUTO_INCREMENT,
  `shopping_user_name` char(255) NOT NULL,
  `shopping_menu_name` char(255) NOT NULL,
  `shopping_menu_price` int(16) NOT NULL,
  `shopping_amount` int(16) NOT NULL,
  `shopping_remark` char(255) NOT NULL,
  PRIMARY KEY (`shopping_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ib_shopping`
--

LOCK TABLES `ib_shopping` WRITE;
/*!40000 ALTER TABLE `ib_shopping` DISABLE KEYS */;
/*!40000 ALTER TABLE `ib_shopping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ib_user`
--

DROP TABLE IF EXISTS `ib_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ib_user` (
  `user_id` int(16) NOT NULL AUTO_INCREMENT,
  `user_name` char(255) NOT NULL,
  `user_passwd` char(255) NOT NULL,
  `user_auth` enum('admin','customer') NOT NULL,
  `user_tel` char(255) NOT NULL,
  `user_addr` char(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ib_user`
--

LOCK TABLES `ib_user` WRITE;
/*!40000 ALTER TABLE `ib_user` DISABLE KEYS */;
INSERT INTO `ib_user` VALUES (1,'admin','123456','admin','12345678901','蛇口'),(30,'weigy','123456','customer','13760209518','宝安西乡大益广场2栋16座2B');
/*!40000 ALTER TABLE `ib_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ibooking'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-30 15:45:42
