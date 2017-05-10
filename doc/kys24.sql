-- MySQL dump 10.13  Distrib 5.5.19, for Win64 (x86)
--
-- Host: localhost    Database: kys24
-- ------------------------------------------------------
-- Server version	5.5.19

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
-- Table structure for table `tb_bank`
--

DROP TABLE IF EXISTS `tb_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_bank` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `cardid` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`bank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_bank`
--

LOCK TABLES `tb_bank` WRITE;
/*!40000 ALTER TABLE `tb_bank` DISABLE KEYS */;
INSERT INTO `tb_bank` VALUES (1,NULL,'杨子墨',123456789,'2017-05-09 01:49:20','2017-05-09 01:49:20'),(2,NULL,'杨子墨',123456789,'2017-05-09 01:54:41','2017-05-09 01:54:41');
/*!40000 ALTER TABLE `tb_bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_brand`
--

DROP TABLE IF EXISTS `tb_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_brand` (
  `brandID` int(11) NOT NULL AUTO_INCREMENT COMMENT '品牌编号',
  `varietyID` int(11) DEFAULT NULL COMMENT '商品种类编号',
  `brandname` varchar(8) DEFAULT NULL COMMENT '品牌名字',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`brandID`),
  KEY `brandname` (`brandname`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_brand`
--

LOCK TABLES `tb_brand` WRITE;
/*!40000 ALTER TABLE `tb_brand` DISABLE KEYS */;
INSERT INTO `tb_brand` VALUES (1,NULL,'自营','2017-05-01 04:36:53','2017-05-01 04:36:53');
/*!40000 ALTER TABLE `tb_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_commodity`
--

DROP TABLE IF EXISTS `tb_commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_commodity` (
  `commodity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `commodity_name` varchar(8) DEFAULT NULL COMMENT '商品名称',
  `commodity_brand` int(11) DEFAULT NULL COMMENT '品牌ID',
  `commodity_variety` int(11) DEFAULT NULL COMMENT '种类ID',
  `commodity_depict` varchar(200) DEFAULT NULL COMMENT '商品标签',
  `commodity_price` float(7,2) DEFAULT NULL COMMENT '商品价格(单价)',
  `commodity_amount` int(11) DEFAULT NULL COMMENT '库存',
  `commodity_leavenum` int(11) DEFAULT NULL COMMENT '商品剩余数量',
  `commodity_standard` float(5,2) DEFAULT NULL COMMENT '商品规格',
  `operator` int(11) DEFAULT NULL COMMENT '操作人编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `commodity_picture` varchar(40) DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`commodity_id`),
  KEY `commidityname` (`commodity_name`),
  KEY `commidity_brand` (`commodity_brand`),
  KEY `commidity_variety` (`commodity_variety`),
  KEY `commoditydepict` (`commodity_depict`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_commodity`
--

LOCK TABLES `tb_commodity` WRITE;
/*!40000 ALTER TABLE `tb_commodity` DISABLE KEYS */;
INSERT INTO `tb_commodity` VALUES (1,'鸡心',NULL,NULL,NULL,NULL,100,NULL,NULL,NULL,'2017-05-03 15:18:06','2017-05-03 15:18:06',NULL),(2,'鸡心',NULL,NULL,NULL,NULL,100,NULL,NULL,NULL,'2017-05-03 15:19:14','2017-05-03 15:19:14',NULL);
/*!40000 ALTER TABLE `tb_commodity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_order` (
  `order_id` varchar(40) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `order_address` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `total_count` int(11) DEFAULT NULL,
  `total_price` float(9,2) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order`
--

LOCK TABLES `tb_order` WRITE;
/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_orderitem`
--

DROP TABLE IF EXISTS `tb_orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_orderitem` (
  `orderitem_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(40) DEFAULT NULL,
  `commodity_id` int(11) DEFAULT NULL,
  `commodity_price` float(9,2) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`orderitem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_orderitem`
--

LOCK TABLES `tb_orderitem` WRITE;
/*!40000 ALTER TABLE `tb_orderitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_orderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `user_password` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `user_phone` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `user_address` varchar(100) DEFAULT NULL COMMENT '用户地址',
  `token` varchar(20) DEFAULT NULL COMMENT '密钥',
  `type` varchar(10) DEFAULT NULL COMMENT '类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  KEY `user_id` (`user_id`),
  KEY `user_name` (`user_name`),
  KEY `user_phone` (`user_phone`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'魏垚','226b22fc30d6de4560c5617d1b958301','15204696480',NULL,NULL,NULL,'2017-04-30 07:53:31','2017-04-30 07:21:05'),(2,'魏垚','226b22fc30d6de4560c5617d1b958301',NULL,NULL,NULL,NULL,'2017-04-30 07:26:55','2017-04-30 07:26:55'),(3,'魏垚','226b22fc30d6de4560c5617d1b958301',NULL,NULL,NULL,NULL,'2017-04-30 08:15:59','2017-04-30 08:15:59'),(4,'魏垚','226b22fc30d6de4560c5617d1b958301','12234566543','测试',NULL,'用户','2017-05-03 01:27:33','2017-05-03 01:24:11');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_variety`
--

DROP TABLE IF EXISTS `tb_variety`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_variety` (
  `variety_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品种类ID',
  `variety_name` varchar(8) DEFAULT NULL COMMENT '品种名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点ID',
  `type` varchar(10) DEFAULT NULL COMMENT '品种编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`variety_id`),
  KEY `varirtyname` (`variety_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_variety`
--

LOCK TABLES `tb_variety` WRITE;
/*!40000 ALTER TABLE `tb_variety` DISABLE KEYS */;
INSERT INTO `tb_variety` VALUES (1,'生鲜',0,NULL,'2017-05-01 03:52:51','2017-05-01 03:52:51');
/*!40000 ALTER TABLE `tb_variety` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-09 10:19:00
