-- MySQL dump 10.13  Distrib 5.7.11, for Win32 (AMD64)
--
-- Host: localhost    Database: addrass_db
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
-- Table structure for table `contact_list`
--

DROP TABLE IF EXISTS `contact_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_list` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fk_user_main` bigint(20) NOT NULL,
  `fk_user_friend` bigint(20) NOT NULL,
  `fk_user_color` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`pk_id`),
  KEY `contact_list_user_main_pk_id_fk` (`fk_user_main`),
  KEY `contact_list_user_friend__fk` (`fk_user_friend`),
  KEY `contact_list_user_color_pk_id_fk` (`fk_user_color`),
  CONSTRAINT `contact_list_user_color_pk_id_fk` FOREIGN KEY (`fk_user_color`) REFERENCES `user_color` (`pk_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `contact_list_user_friend__fk` FOREIGN KEY (`fk_user_friend`) REFERENCES `user_data` (`pk_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `contact_list_user_main_pk_id_fk` FOREIGN KEY (`fk_user_main`) REFERENCES `user_data` (`pk_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event_member`
--

DROP TABLE IF EXISTS `event_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_member` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fk_event_id` bigint(20) NOT NULL,
  `fk_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`pk_id`),
  KEY `event_member_user_event_pk_id_fk` (`fk_event_id`),
  KEY `event_member_user_data_pk_id_fk` (`fk_user_id`),
  CONSTRAINT `event_member_user_data_pk_id_fk` FOREIGN KEY (`fk_user_id`) REFERENCES `user_data` (`pk_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `event_member_user_event_pk_id_fk` FOREIGN KEY (`fk_event_id`) REFERENCES `user_event` (`pk_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event_type`
--

DROP TABLE IF EXISTS `event_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_type` (
  `pk_id` tinyint(4) NOT NULL,
  `name` varchar(16) NOT NULL,
  PRIMARY KEY (`pk_id`),
  UNIQUE KEY `event_type_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `friend_list`
--

DROP TABLE IF EXISTS `friend_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_list` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fk_user_main` bigint(20) NOT NULL,
  `fk_user_friend` bigint(20) NOT NULL,
  `black_list` bit(1) NOT NULL DEFAULT b'0',
  `fk_user_color` tinyint(4) NOT NULL,
  `fk_user_group` tinyint(4) NOT NULL,
  PRIMARY KEY (`pk_id`),
  UNIQUE KEY `friend_list_fk_user_friend_fk_user_main_uindex` (`fk_user_friend`,`fk_user_main`),
  KEY `friend_list_user_color_pk_id_fk` (`fk_user_color`),
  KEY `friend_list_user_group_pk_id_fk` (`fk_user_group`),
  KEY `friend_list_user_main__fk` (`fk_user_main`),
  CONSTRAINT `friend_list_user_color_pk_id_fk` FOREIGN KEY (`fk_user_color`) REFERENCES `user_color` (`pk_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `friend_list_user_friend_pk_id_fk` FOREIGN KEY (`fk_user_friend`) REFERENCES `user_data` (`pk_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `friend_list_user_group_pk_id_fk` FOREIGN KEY (`fk_user_group`) REFERENCES `user_group` (`pk_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `friend_list_user_main__fk` FOREIGN KEY (`fk_user_main`) REFERENCES `user_data` (`pk_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_color`
--

DROP TABLE IF EXISTS `user_color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_color` (
  `pk_id` tinyint(4) NOT NULL,
  `color` int(11) NOT NULL,
  `name` varchar(15) NOT NULL,
  PRIMARY KEY (`pk_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `color_UNIQUE` (`color`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_data`
--

DROP TABLE IF EXISTS `user_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_data` (
  `user_login` varchar(32) DEFAULT NULL,
  `user_password` varchar(64) DEFAULT NULL,
  `user_name` varchar(64) NOT NULL,
  `user_phone_field` varchar(15) DEFAULT NULL,
  `user_email_field` varchar(32) DEFAULT NULL,
  `user_organization_field` varchar(32) DEFAULT NULL,
  `user_address_field` varchar(32) DEFAULT NULL,
  `user_notes_field` varchar(64) DEFAULT NULL,
  `fk_user_photo` bigint(20) DEFAULT '2',
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pk_id`),
  UNIQUE KEY `user_data_user_login_uindex` (`user_login`),
  KEY `fk_photo_user_icon` (`fk_user_photo`),
  CONSTRAINT `fk_photo_user_icon` FOREIGN KEY (`fk_user_photo`) REFERENCES `user_icon` (`pk_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_event`
--

DROP TABLE IF EXISTS `user_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_event` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `fk_event_owner` bigint(20) NOT NULL,
  `event_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fk_event_type` tinyint(4) DEFAULT NULL,
  `event_coordinate_x` double DEFAULT NULL,
  `event_coordinate_y` double DEFAULT NULL,
  PRIMARY KEY (`pk_id`),
  KEY `user_event_user_data_pk_id_fk` (`fk_event_owner`),
  KEY `fk_event_type_event_type_idx` (`fk_event_type`),
  CONSTRAINT `fk_event_type_event_type` FOREIGN KEY (`fk_event_type`) REFERENCES `event_type` (`pk_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `user_event_user_data_pk_id_fk` FOREIGN KEY (`fk_event_owner`) REFERENCES `user_data` (`pk_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `pk_id` tinyint(4) NOT NULL,
  `name` varchar(16) NOT NULL,
  PRIMARY KEY (`pk_id`),
  UNIQUE KEY `addrass_group_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Groups for contacts';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_icon`
--

DROP TABLE IF EXISTS `user_icon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_icon` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icon_bytes` longblob NOT NULL,
  `icon_name` varchar(20) NOT NULL,
  PRIMARY KEY (`pk_id`),
  UNIQUE KEY `user_icon_icon_name_uindex` (`icon_name`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-10 13:58:21
