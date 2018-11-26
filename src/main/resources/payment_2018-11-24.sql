# ************************************************************
# Sequel Pro SQL dump
# Version 5420
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 8.0.12)
# Database: payment
# Generation Time: 2018-11-24 03:39:14 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table payment_record
# ------------------------------------------------------------

DROP TABLE IF EXISTS `payment_record`;

CREATE TABLE `payment_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `money` double NOT NULL,
  `day` date NOT NULL,
  `record` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `payment_record` WRITE;
/*!40000 ALTER TABLE `payment_record` DISABLE KEYS */;

INSERT INTO `payment_record` (`id`, `money`, `day`, `record`)
VALUES
	(5,10000,'2018-10-13','{\"leftMoney\":0.00000,\"renters\":{1:2761.00000,2:1432.00000,3:793.00000,4:716.00000,5:524.00000,6:511.00000,7:473.00000,8:460.00000,9:384.00000,10:332.00000,11:332.00000,12:266.00000,13:256.00000,14:256.00000,15:153.00000,16:128.00000,17:115.00000,18:82.00000,19:26.00000}}'),
	(6,8000,'2018-10-14','{\"leftMoney\":0.00000,\"renters\":{1:2208.80000,2:1145.60000,3:634.40000,4:572.80000,5:419.20000,6:408.80000,7:378.40000,8:368.00000,9:307.20000,10:265.60000,11:265.60000,12:212.80000,13:204.80000,14:204.80000,15:122.40000,16:102.40000,17:92.00000,18:65.60000,19:20.80000}}');

/*!40000 ALTER TABLE `payment_record` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table renter
# ------------------------------------------------------------

DROP TABLE IF EXISTS `renter`;

CREATE TABLE `renter` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `money` double NOT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `renter` WRITE;
/*!40000 ALTER TABLE `renter` DISABLE KEYS */;

INSERT INTO `renter` (`id`, `name`, `money`, `end_date`)
VALUES
	(1,'金亮平',108000,NULL),
	(2,'朱伟友',56000,NULL),
	(3,'邹凡奇',31000,NULL),
	(4,'吴鑫',28000,NULL),
	(5,'钟建文',20500,NULL),
	(6,'蔡豪',20000,NULL),
	(7,'彭求诚',18500,NULL),
	(8,'胡威',18000,NULL),
	(9,'刘将',15000,NULL),
	(10,'谢称发',13000,NULL),
	(11,'赖毅',13000,NULL),
	(12,'陈明',10400,NULL),
	(13,'罗鹏',10000,NULL),
	(14,'欧阳效程',10000,NULL),
	(15,'杨春英',6000,NULL),
	(16,'江员红',5000,NULL),
	(17,'刘德财',4500,NULL),
	(18,'孔志杰',3200,NULL),
	(19,'吕志强',1000,NULL);

/*!40000 ALTER TABLE `renter` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table token
# ------------------------------------------------------------

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
	 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	 `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
	 `start_date` date NOT NULL,
	 `day` int(2) NOT NULL COMMENT '今天有效：0',
	 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
