-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: securities_account
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `corporate_account`
--

DROP TABLE IF EXISTS `corporate_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `corporate_account` (
  `securities_id` int(11) NOT NULL AUTO_INCREMENT,
  `register_no` varchar(20) DEFAULT NULL,
  `business_license_no` varchar(20) DEFAULT NULL,
  `legal_representative_id` varchar(20) DEFAULT NULL,
  `legal_representative_name` varchar(40) DEFAULT NULL,
  `legal_representative_phone_no` varchar(20) DEFAULT NULL,
  `legal_representative_add` varchar(100) DEFAULT NULL,
  `authorizer_name` varchar(40) DEFAULT NULL,
  `authorizer_id` varchar(20) DEFAULT NULL,
  `authorizer_phone_no` varchar(20) DEFAULT NULL,
  `authorizer_add` varchar(100) DEFAULT NULL,
  `state` int(11) DEFAULT '0',
  PRIMARY KEY (`securities_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fund_account`
--

DROP TABLE IF EXISTS `fund_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fund_account` (
  `fund_id` int(11) NOT NULL AUTO_INCREMENT,
  `interest` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `fund_priv` bit(1) DEFAULT NULL,
  PRIMARY KEY (`fund_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `personal_account`
--

DROP TABLE IF EXISTS `personal_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `personal_account` (
  `securities_id` int(11) NOT NULL AUTO_INCREMENT,
  `register_date` date DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `id_no` varchar(20) DEFAULT NULL,
  `family_add` varchar(100) DEFAULT NULL,
  `career` varchar(40) DEFAULT NULL,
  `education` varchar(40) DEFAULT NULL,
  `organization` varchar(40) DEFAULT NULL,
  `phone_no` varchar(20) DEFAULT NULL,
  `agent_id_no` varchar(20) DEFAULT NULL,
  `state` int(11) DEFAULT '0',
  PRIMARY KEY (`securities_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `securities_fund`
--

DROP TABLE IF EXISTS `securities_fund`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `securities_fund` (
  `securities_id` int(11) NOT NULL,
  `fund_id` int(11) NOT NULL,
  PRIMARY KEY (`fund_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `securities_stock`
--

DROP TABLE IF EXISTS `securities_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `securities_stock` (
  `securities_id` int(11) NOT NULL,
  `stock_code` varchar(10) NOT NULL,
  PRIMARY KEY (`stock_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-20 10:09:57
