-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: ucm_mba
-- ------------------------------------------------------
-- Server version	5.6.22-log

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
-- Table structure for table `concentration_faculty`
--

DROP TABLE IF EXISTS `concentration_faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concentration_faculty` (
  `CONCENTRATION_SERIAL_NO` int(10) NOT NULL AUTO_INCREMENT,
  `CONCENTRATION_ID` int(10) NOT NULL,
  `FACULTY_ID` int(10) NOT NULL,
  `CREATED_BY` int(10) NOT NULL,
  `CREATED_ON` datetime NOT NULL,
  `UPDATED_BY` int(10) NOT NULL,
  `UPDATED_ON` datetime NOT NULL,
  PRIMARY KEY (`CONCENTRATION_SERIAL_NO`),
  KEY `con_faclty_fk_userid_idx` (`FACULTY_ID`),
  KEY `con_faclty_fk_conid` (`CONCENTRATION_ID`),
  CONSTRAINT `con_faclty_fk_conid` FOREIGN KEY (`CONCENTRATION_ID`) REFERENCES `concentrations` (`CONCENTRATION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `con_faclty_fk_userid` FOREIGN KEY (`FACULTY_ID`) REFERENCES `sps_users` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concentration_faculty`
--

LOCK TABLES `concentration_faculty` WRITE;
/*!40000 ALTER TABLE `concentration_faculty` DISABLE KEYS */;
INSERT INTO `concentration_faculty` VALUES (1,5,22,1,'2016-08-12 14:50:14',1,'2016-08-12 14:50:14'),(2,6,25,1,'2016-08-12 15:00:31',1,'2016-08-12 15:00:31'),(3,7,26,1,'2016-08-12 15:34:30',1,'2016-08-12 15:34:30'),(4,8,27,1,'2016-08-12 15:34:47',1,'2016-08-12 15:34:47'),(5,9,28,1,'2016-08-12 15:35:05',1,'2016-08-12 15:35:05'),(6,10,30,1,'2016-08-12 15:35:21',1,'2016-08-12 15:35:21');
/*!40000 ALTER TABLE `concentration_faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concentrations`
--

DROP TABLE IF EXISTS `concentrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concentrations` (
  `CONCENTRATION_ID` int(10) NOT NULL AUTO_INCREMENT,
  `CONCENTRATION_NAME` varchar(128) NOT NULL,
  `DEGREE_CODE` varchar(45) NOT NULL,
  `ACTIVE` int(1) DEFAULT NULL,
  `CREATED_BY` int(10) DEFAULT NULL,
  `CREATED_ON` datetime DEFAULT NULL,
  `UPDATED_BY` int(10) DEFAULT NULL,
  `UPDATED_ON` datetime DEFAULT NULL,
  PRIMARY KEY (`CONCENTRATION_ID`),
  UNIQUE KEY `CONCENTRATION_NAME_UNIQUE` (`CONCENTRATION_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concentrations`
--

LOCK TABLES `concentrations` WRITE;
/*!40000 ALTER TABLE `concentrations` DISABLE KEYS */;
INSERT INTO `concentrations` VALUES (5,'General','GEN',1,1,'2016-08-12 14:50:14',1,'2016-08-12 14:50:14'),(6,'Finance','FIN',1,1,'2016-08-12 15:00:31',1,'2016-08-12 15:00:31'),(7,'Accounting','ACC',1,1,'2016-08-12 15:34:30',1,'2016-08-12 15:34:30'),(8,'Information Systems','IS',1,1,'2016-08-12 15:34:47',1,'2016-08-12 15:34:47'),(9,'Marketing','MRK',1,1,'2016-08-12 15:35:05',1,'2016-08-12 15:35:05'),(10,'Sports Business','SB',1,1,'2016-08-12 15:35:21',1,'2016-08-12 15:35:21');
/*!40000 ALTER TABLE `concentrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_template`
--

DROP TABLE IF EXISTS `email_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_template` (
  `EMAIL_TEMPLATE_ID` int(10) NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` int(10) DEFAULT NULL,
  `DOCUMENT` text NOT NULL,
  `SEND_TO` varchar(128) NOT NULL,
  `PASSWORD` varchar(256) NOT NULL,
  `GENERATED_ON` datetime NOT NULL,
  `ACTIVE` int(1) NOT NULL,
  `CREATED_BY` int(10) NOT NULL,
  `CREATED_ON` datetime NOT NULL,
  `UPDATED_BY` int(10) NOT NULL,
  `UPDATED_ON` datetime NOT NULL,
  PRIMARY KEY (`EMAIL_TEMPLATE_ID`),
  KEY `email_template_fk_std_id_idx` (`STUDENT_ID`),
  CONSTRAINT `email_template_fk_std_id` FOREIGN KEY (`STUDENT_ID`) REFERENCES `students` (`STUDENT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_template`
--

LOCK TABLES `email_template` WRITE;
/*!40000 ALTER TABLE `email_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `email_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (33);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_requests`
--

DROP TABLE IF EXISTS `password_reset_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `password_reset_requests` (
  `REQUEST_ID` int(10) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) NOT NULL,
  `COMMENTS` varchar(256) NOT NULL,
  `PROCESSED_ON` datetime DEFAULT NULL,
  `PROCESSED_BY` int(10) DEFAULT NULL,
  `ACTIVE` int(1) NOT NULL,
  `CREATED_BY` int(10) NOT NULL,
  `CREATED_ON` datetime NOT NULL,
  `UPDATED_BY` int(10) NOT NULL,
  `UPDATED_ON` datetime NOT NULL,
  PRIMARY KEY (`REQUEST_ID`),
  KEY `request_fk_userid_idx` (`USER_ID`),
  CONSTRAINT `request_fk_userid` FOREIGN KEY (`USER_ID`) REFERENCES `sps_users` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_requests`
--

LOCK TABLES `password_reset_requests` WRITE;
/*!40000 ALTER TABLE `password_reset_requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `password_reset_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `QUESTION_ID` int(10) NOT NULL AUTO_INCREMENT,
  `CONCENTRATION_ID` int(10) DEFAULT NULL,
  `DESCRIPTION` text,
  `OPTION_1` varchar(255) DEFAULT NULL,
  `OPTION_2` varchar(255) DEFAULT NULL,
  `OPTION_3` varchar(255) DEFAULT NULL,
  `OPTION_4` varchar(255) DEFAULT NULL,
  `ANSWER` varchar(1) DEFAULT NULL,
  `ACTIVE` int(1) DEFAULT NULL,
  `CREATED_BY` int(10) DEFAULT NULL,
  `CREATED_ON` datetime DEFAULT NULL,
  `UPDATED_BY` int(10) DEFAULT NULL,
  `UPDATED_ON` datetime DEFAULT NULL,
  `tempAns` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`QUESTION_ID`),
  KEY `con_fk_question_idx` (`CONCENTRATION_ID`),
  CONSTRAINT `con_fk_question` FOREIGN KEY (`CONCENTRATION_ID`) REFERENCES `concentrations` (`CONCENTRATION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,5,'How is CEO of Google','Sunder pichai','Sundar kumar','Sundar Shyam','Sunder Kumar','1',1,-1,'2016-08-12 15:00:31',-1,'2016-08-12 15:00:31',NULL),(2,5,'Each year World Red Cross and Red Crescent Day is celebrated on','May 8','May 18','May 26','June 23','1',1,-1,'2016-08-12 15:00:31',-1,'2016-08-12 15:00:31',NULL),(3,5,'The ozone layer restricts','Visible light','Infrared radiation','X-rays and gamma rays','Ultraviolet radiation','4',1,-1,'2016-08-12 15:00:31',-1,'2016-08-12 15:00:31',NULL),(4,5,'Filaria is caused by','Bacteria','Mosquito','Protozoa','Virus','2',1,-1,'2016-08-12 15:00:31',-1,'2016-08-12 15:00:31',NULL),(5,5,'Headquarters of UNO are situated at','New York, USA','Hague (Netherlands)','Geneva','Paris','1',1,-1,'2016-08-12 15:00:31',-1,'2016-08-12 15:00:31',NULL),(6,8,'Java was founded by','James Gosling','Cameron','stephen','Stanly','1',1,-1,'2016-08-12 15:00:31',-1,'2016-08-12 15:00:31',NULL),(7,8,'Java Latest version','1.4','1.5','1.6','1.7','4',1,-1,'2016-08-12 15:00:31',-1,'2016-08-12 15:00:31',NULL),(8,8,'What is CSE ?','Common Science','Computer Science','Custom style','Configuration system','2',1,-1,'2016-08-12 15:00:31',-1,'2016-08-12 15:00:31',NULL),(9,8,'What is IT ?','Information Technology','Info Tea','In Taly','Indonesia Team','1',1,-1,'2016-08-12 15:00:31',-1,'2016-08-12 15:00:31',NULL),(10,8,'OS computer abbreviation usually means ?','Order of Significance','Open Software','Operating System','Optical Sensor','3',1,-1,'2016-08-12 15:00:31',-1,'2016-08-12 15:00:31',NULL);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review_notes`
--

DROP TABLE IF EXISTS `review_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review_notes` (
  `REVIEW_NOTE_ID` int(10) NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` int(10) NOT NULL,
  `NOTES` varchar(1024) DEFAULT NULL,
  `REVIEW_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`REVIEW_NOTE_ID`),
  KEY `FK_oeghtq4v5fxusg0coj6yus6ys` (`STUDENT_ID`),
  CONSTRAINT `FK_oeghtq4v5fxusg0coj6yus6ys` FOREIGN KEY (`STUDENT_ID`) REFERENCES `students` (`STUDENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000003 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_notes`
--

LOCK TABLES `review_notes` WRITE;
/*!40000 ALTER TABLE `review_notes` DISABLE KEYS */;
INSERT INTO `review_notes` VALUES (1000000001,20,'Oka',NULL),(1000000002,20,'oka2','2016-08-20 10:57:57');
/*!40000 ALTER TABLE `review_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sps_users`
--

DROP TABLE IF EXISTS `sps_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sps_users` (
  `USER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(127) NOT NULL,
  `LOGIN_ID` varchar(64) NOT NULL,
  `ROLE` varchar(45) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `ACTIVE` int(1) DEFAULT NULL,
  `CREATED_BY` int(10) NOT NULL,
  `CREATED_ON` datetime NOT NULL,
  `UPDATED_BY` int(10) NOT NULL,
  `UPDATED_ON` datetime NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000002 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sps_users`
--

LOCK TABLES `sps_users` WRITE;
/*!40000 ALTER TABLE `sps_users` DISABLE KEYS */;
INSERT INTO `sps_users` VALUES (9,'dsa','sdf34','program director','sdf','sps@123',1,10001,'2016-08-06 15:03:50',10001,'2016-08-09 21:58:53'),(11,'sadf','454tr','sf','fdsg','sps@123',1,10001,'2016-08-06 15:09:55',10001,'2016-08-06 15:09:55'),(12,'sadfa','45asfdas','asdf','asdf','sps@123',1,10001,'2016-08-06 15:12:31',10001,'2016-08-06 15:12:31'),(13,'sad','sdare','sadf','ere','sps@123',1,10001,'2016-08-06 15:14:28',10001,'2016-08-06 15:14:28'),(14,'e','sadf','saf','sadf','sps@123',1,10001,'2016-08-06 15:18:34',10001,'2016-08-06 15:18:34'),(15,'asd','fsafd','sfd','sfdasf','sps@123',1,10001,'2016-08-06 15:19:34',10001,'2016-08-06 15:19:34'),(16,'reyt','hgfh','hgfd','yftrt','sps@123',1,10001,'2016-08-06 22:34:43',10001,'2016-08-06 22:34:43'),(18,'sdf','asd','ASD','ASDFG','sps@123',1,10001,'2016-08-06 23:01:19',10001,'2016-08-06 23:01:19'),(19,'dsf','dsf','dsf','sdf','sps@123',1,10001,'2016-08-06 23:21:23',10001,'2016-08-06 23:21:23'),(20,'','','','','sps@123',1,10001,'2016-08-08 12:42:56',10001,'2016-08-08 12:42:56'),(21,'dfa','sdf','asdf','asfdafds','sps@123',1,10001,'2016-08-08 12:43:09',10001,'2016-08-08 12:43:09'),(22,'shiva_advisor','shiva_advisor','advisor','blabla','sps@123',1,10001,'2016-08-11 12:28:44',10001,'2016-08-11 12:28:44'),(23,'nilesh','nilesh_pd','Program Director','nilish','sps@123',1,10001,'2016-08-12 14:51:29',10001,'2016-08-12 14:51:29'),(24,'nilesh','nilesh_advisor','Program Director','NA','sps@123',1,10001,'2016-08-12 15:07:05',10001,'2016-08-12 15:07:05'),(25,'jhon paul','john_paul','Advisor','NA','sps@123',1,10001,'2016-08-12 15:07:36',10001,'2016-08-12 15:07:36'),(26,'smith','smith_adron','Advisor','NA','sps@123',1,10001,'2016-08-12 15:08:56',10001,'2016-08-12 15:08:56'),(27,'kennady','kennady_paul','Advisor','NA','sps@123',1,10001,'2016-08-12 15:24:08',10001,'2016-08-12 15:24:08'),(28,'joshna','Joshna_iva','Advisor','NA','sps@123',1,10001,'2016-08-12 15:31:26',10001,'2016-08-12 15:31:26'),(29,'cameron','cameron_putin','Advisor','NA','sps@123',1,10001,'2016-08-12 15:31:48',10001,'2016-08-12 15:31:48'),(30,'amritha','amritha_ad','Advisor','NA','sps@123',1,10001,'2016-08-12 15:33:01',10001,'2016-08-12 15:33:01'),(32,'Shiva','Shiva','Student','Shiv.Gangadhar@laurusis.com','sps@123',1,10001,'2016-08-22 19:54:40',10001,'2016-08-22 19:54:41'),(10001,'Shiva Gangadhar','shiva_pd','Program Director','shiva.rsgr@gmai.com','Mes@123',1,-1,'2016-07-31 10:20:30',-1,'2016-07-31 10:20:30'),(1000000001,'sdf','safd','asdf','asdf','sadf',1,-1,'2016-07-31 10:20:30',-1,'2016-07-31 10:20:30');
/*!40000 ALTER TABLE `sps_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_concentration`
--

DROP TABLE IF EXISTS `student_concentration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_concentration` (
  `STU_CON_ID` int(10) NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` int(10) NOT NULL,
  `CONCENTRATION_ID` int(10) NOT NULL,
  `ADVISOR` int(10) DEFAULT NULL,
  `EMAIL_TEMPLATE_ID` int(10) DEFAULT NULL,
  `STATUS` varchar(45) DEFAULT NULL,
  `STATUS_CHANGE_DATE` datetime DEFAULT NULL,
  `CONDITIONS` text,
  `EXAM_STATUS` varchar(45) DEFAULT NULL,
  `ACTIVE` int(1) DEFAULT NULL,
  `CREATED_BY` int(10) DEFAULT NULL,
  `CREATED_ON` datetime DEFAULT NULL,
  `UPDATED_BY` int(10) DEFAULT NULL,
  `UPDATED_ON` datetime DEFAULT NULL,
  PRIMARY KEY (`STU_CON_ID`),
  KEY `std_con_fk_con_id_idx` (`CONCENTRATION_ID`),
  KEY `std_con_fk_template_id_idx` (`EMAIL_TEMPLATE_ID`),
  KEY `FK_8dp77g0liuyqwfvl3gj0chq6w` (`STUDENT_ID`),
  CONSTRAINT `FK_8dp77g0liuyqwfvl3gj0chq6w` FOREIGN KEY (`STUDENT_ID`) REFERENCES `students` (`STUDENT_ID`),
  CONSTRAINT `std_con_fk_con_id` FOREIGN KEY (`CONCENTRATION_ID`) REFERENCES `concentrations` (`CONCENTRATION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `std_con_fk_template_id` FOREIGN KEY (`EMAIL_TEMPLATE_ID`) REFERENCES `email_template` (`EMAIL_TEMPLATE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_concentration`
--

LOCK TABLES `student_concentration` WRITE;
/*!40000 ALTER TABLE `student_concentration` DISABLE KEYS */;
INSERT INTO `student_concentration` VALUES (4,19,6,25,NULL,'PreReqs met','2016-08-20 13:59:42',NULL,NULL,0,10001,'2016-08-14 12:40:34',10001,'2016-08-14 12:40:33'),(5,20,6,25,NULL,'Withdrawn','2016-08-20 13:59:09',NULL,NULL,0,10001,'2016-08-20 13:10:57',10001,'2016-08-20 13:10:57'),(6,14,8,26,NULL,'Accepted','2016-08-20 14:00:41',NULL,'Completed',0,10001,'2016-08-20 13:10:40',10001,'2016-08-20 13:10:40'),(7,21,5,NULL,NULL,NULL,NULL,NULL,NULL,0,10001,'2016-08-24 10:39:10',10001,'2016-08-24 10:39:10');
/*!40000 ALTER TABLE `student_concentration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_exam`
--

DROP TABLE IF EXISTS `student_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_exam` (
  `STD_QUN_ID` int(10) NOT NULL AUTO_INCREMENT,
  `STD_CON_ID` int(10) NOT NULL,
  `QUESTION_ID` int(10) NOT NULL,
  `STD_ANSWER` varchar(1) DEFAULT NULL,
  `ACTIVE` int(1) NOT NULL DEFAULT '1',
  `CREATED_BY` int(10) NOT NULL,
  `CREATED_ON` datetime NOT NULL,
  `UPDATED_BY` int(10) NOT NULL,
  `UPDATED_ON` datetime NOT NULL,
  PRIMARY KEY (`STD_QUN_ID`),
  KEY `question_fk_std_con_idx` (`QUESTION_ID`),
  KEY `FK_2dpsh4ka0jdldvl6r51cej3p7` (`STD_CON_ID`),
  CONSTRAINT `question_fk_std_con` FOREIGN KEY (`QUESTION_ID`) REFERENCES `questions` (`QUESTION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `std_exam_fk_std_con` FOREIGN KEY (`STD_CON_ID`) REFERENCES `concentrations` (`CONCENTRATION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_exam`
--

LOCK TABLES `student_exam` WRITE;
/*!40000 ALTER TABLE `student_exam` DISABLE KEYS */;
INSERT INTO `student_exam` VALUES (8,8,6,'1',1,32,'2016-08-23 09:31:49',32,'2016-08-23 09:31:49'),(9,8,7,'3',1,32,'2016-08-23 09:31:49',32,'2016-08-23 09:31:49'),(10,8,8,'1',1,32,'2016-08-23 09:31:50',32,'2016-08-23 09:31:50'),(11,8,9,'2',1,32,'2016-08-23 09:31:50',32,'2016-08-23 09:31:50'),(12,8,10,'1',1,32,'2016-08-23 09:31:50',32,'2016-08-23 09:31:50');
/*!40000 ALTER TABLE `student_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `STUDENT_ID` int(10) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(45) NOT NULL,
  `LAST_NAME` varchar(45) NOT NULL,
  `MAILING_ADDRESS` varchar(1024) NOT NULL,
  `PHONE_NUMBER` bigint(14) NOT NULL,
  `GRE_SCORE` int(10) DEFAULT NULL,
  `UCM_EMAIL` varchar(45) DEFAULT NULL,
  `OTHER_EMAIL` varchar(45) DEFAULT NULL,
  `ACTIVE` varchar(45) NOT NULL,
  `CREATED_BY` int(10) DEFAULT NULL,
  `CREATED_ON` datetime DEFAULT NULL,
  `UPDATED_BY` int(10) DEFAULT NULL,
  `UPDATED_ON` datetime DEFAULT NULL,
  `ENTRY_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`STUDENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (14,'Shiva','Gangadhar','Shiv.Gangadhar@laurusis.com',1234567890,33,'shiva.blab','Shiv.Gangadhar@laurusis.com','1',10001,'2016-08-14 12:26:33',10001,'2016-08-14 12:26:32','2016-08-26 12:00:00'),(19,'sadf','sadf','sadf',12345,333,'sadf','sadfasd','1',10001,'2016-08-14 12:40:30',10001,'2016-08-14 12:40:29','2016-08-31 12:00:00'),(20,'Harshal','Kalavadiya','NA',876522339,333,'harsha.12@ucm.com','677@gg','1',10001,'2016-08-15 11:01:51',10001,'2016-08-15 11:01:51','2016-08-31 12:00:00'),(21,'Shanth','Raj','Hoskote',8880083588,350,'NA','shanthraj92@gmail.com','1',10001,'2016-08-24 10:39:10',10001,'2016-08-24 10:39:10','2016-08-24 12:00:00');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ucm_mba'
--

--
-- Dumping routines for database 'ucm_mba'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-24 11:01:54
