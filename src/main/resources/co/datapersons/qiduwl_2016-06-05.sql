# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.30)
# Database: qiduwl
# Generation Time: 2016-06-05 14:48:49 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table area
# ------------------------------------------------------------

DROP TABLE IF EXISTS `area`;

CREATE TABLE `area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `provincecode` int(11) DEFAULT NULL,
  `province` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `citycode` int(11) DEFAULT NULL,
  `city` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `areacode` int(11) DEFAULT NULL,
  `area` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;

INSERT INTO `area` (`id`, `provincecode`, `province`, `citycode`, `city`, `areacode`, `area`)
VALUES
	(1,10,'河北省',1011,'唐山市',101110,'金水区');

/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table historylineup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `historylineup`;

CREATE TABLE `historylineup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paytimecalc` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lineupnumber` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lineupmoney` double DEFAULT NULL,
  `outlineuptime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table lineup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `lineup`;

CREATE TABLE `lineup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paytimecalc` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `lineupnumber` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lineupmoney` double DEFAULT '100',
  `lineuptime` date DEFAULT NULL,
  `flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `lineup` WRITE;
/*!40000 ALTER TABLE `lineup` DISABLE KEYS */;

INSERT INTO `lineup` (`id`, `paytimecalc`, `userid`, `lineupnumber`, `lineupmoney`, `lineuptime`, `flag`)
VALUES
	(1,'2',NULL,NULL,100,NULL,0);

/*!40000 ALTER TABLE `lineup` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table manager
# ------------------------------------------------------------

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phonenumber` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `usertype` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `levante` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;

INSERT INTO `manager` (`id`, `userid`, `password`, `phonenumber`, `usertype`, `levante`)
VALUES
	(1,'qiduwl','202CB962AC59075B964B07152D234B70','13971615483','admin',NULL);

/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pay
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pay`;

CREATE TABLE `pay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shopid` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `paytime` date DEFAULT NULL,
  `paynumber` decimal(10,2) DEFAULT NULL,
  `shopname` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `paytimecalc` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `paytype` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `balance` decimal(8,2) DEFAULT '0.00',
  `flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `pay` WRITE;
/*!40000 ALTER TABLE `pay` DISABLE KEYS */;

INSERT INTO `pay` (`id`, `userid`, `shopid`, `paytime`, `paynumber`, `shopname`, `paytimecalc`, `paytype`, `balance`, `flag`)
VALUES
	(1,'1100010001','1','2016-04-07',47.00,NULL,'4547545514','支付宝',NULL,0),
	(2,'1100010001','1','2016-04-10',130.00,NULL,'4656564554','支付宝',NULL,0),
	(3,'1100010001','3','2016-04-11',212.00,NULL,'6545456454','微信',NULL,0),
	(4,'1100010002','1','2016-04-13',77.00,NULL,'4634445545','支付宝',NULL,0),
	(5,'1100010002','1','2016-04-14',45.00,NULL,'5454654656','支付宝',NULL,0),
	(6,'1100010002','1','2016-04-14',120.00,NULL,'6546544656','支付宝',NULL,0),
	(7,'1100010002','3','2016-04-15',240.00,NULL,'4545456646','支付宝',NULL,0),
	(8,'1100010011','2','2016-04-21',450.00,NULL,'7542454254','支付宝',NULL,0),
	(9,'1100010011','2','2016-03-14',320.00,NULL,'2452345245','支付宝',NULL,0),
	(10,'1100010011','2','2016-05-01',700.00,NULL,'4646546544','支付宝',NULL,0),
	(11,'1100010011','1','2016-05-01',400.00,NULL,'6556454654','支付宝',NULL,0),
	(12,'1100010002','1','2016-06-04',0.01,NULL,'????20160604235242','支付宝',NULL,0),
	(13,'1100010002','1','2016-06-04',0.01,NULL,'七度未来20160604235242','支付宝',NULL,0),
	(14,'1100010002','1','2016-06-05',0.01,NULL,'七度未来20160605004029','支付宝',NULL,0),
	(15,'1100010002','1','2016-06-05',0.01,NULL,'七度未来20160605005337','支付宝',NULL,0),
	(16,'1100010002','1','2016-06-05',0.01,NULL,'七度未来20160605010343','支付宝',NULL,0),
	(17,'1100010013','1','2016-06-05',1.00,NULL,'七度未来20160605143435','支付宝',NULL,0),
	(18,'1100010002','1','2016-06-05',0.01,NULL,'七度未来20160605175226','支付宝',NULL,0);

/*!40000 ALTER TABLE `pay` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table proxy
# ------------------------------------------------------------

DROP TABLE IF EXISTS `proxy`;

CREATE TABLE `proxy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proxyid` int(11) DEFAULT NULL,
  `area` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `proxyshare` double DEFAULT '0.01',
  `areausernumber` int(11) DEFAULT NULL,
  `areashopnumber` int(12) DEFAULT NULL,
  `monthtransac` double DEFAULT NULL,
  `monthlypayment` double DEFAULT NULL,
  `whetherproxy` varchar(2) COLLATE utf8_unicode_ci DEFAULT '无',
  `verifytime` date DEFAULT NULL,
  `checkpeople` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `undonecause` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `verifystatus` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `proxy` WRITE;
/*!40000 ALTER TABLE `proxy` DISABLE KEYS */;

INSERT INTO `proxy` (`id`, `proxyid`, `area`, `proxyshare`, `areausernumber`, `areashopnumber`, `monthtransac`, `monthlypayment`, `whetherproxy`, `verifytime`, `checkpeople`, `undonecause`, `verifystatus`)
VALUES
	(1,1100010001,'42-4201-420106',0.01,NULL,NULL,NULL,NULL,'无',NULL,NULL,NULL,NULL),
	(2,1100010001,'42-4201-420111',0.01,NULL,NULL,NULL,NULL,'无',NULL,NULL,NULL,NULL),
	(3,1100010012,'42-4201-420102',0.01,NULL,NULL,NULL,NULL,'无',NULL,NULL,NULL,NULL),
	(4,1100010012,'42-4201-420104',0.01,NULL,NULL,NULL,NULL,'无',NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `proxy` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table recommend
# ------------------------------------------------------------

DROP TABLE IF EXISTS `recommend`;

CREATE TABLE `recommend` (
  `recommendId` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `registTime` datetime DEFAULT NULL,
  `userType` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `accountSum` decimal(10,0) DEFAULT NULL,
  `profitReturn` decimal(10,0) DEFAULT NULL,
  `royalty` decimal(10,0) DEFAULT NULL,
  `mobile` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`recommendId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table shalllineup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `shalllineup`;

CREATE TABLE `shalllineup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paytimecalc` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `lineupnumber` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lineupmoney` double DEFAULT '100',
  `lineuptime` date DEFAULT NULL,
  `flag` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table shop
# ------------------------------------------------------------

DROP TABLE IF EXISTS `shop`;

CREATE TABLE `shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `area` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shopaddress` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shopbrand` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `applytime` datetime DEFAULT NULL,
  `shopname` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orgnumber` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `incorporator` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `verifytime` date DEFAULT NULL,
  `checkpeople` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `undonecause` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `verifystatus` int(11) DEFAULT '0',
  `reducepoint` decimal(10,2) DEFAULT NULL,
  `image` text COLLATE utf8_unicode_ci,
  `shoptype` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;

INSERT INTO `shop` (`id`, `userid`, `area`, `shopaddress`, `shopbrand`, `applytime`, `shopname`, `orgnumber`, `incorporator`, `phone`, `verifytime`, `checkpeople`, `undonecause`, `verifystatus`, `reducepoint`, `image`, `shoptype`)
VALUES
	(1,1100010001,'42-4201-420106','武汉市武昌区螃蟹甲6号',NULL,'2016-04-01 12:24:45','大野体育器材',NULL,NULL,NULL,'2016-04-10','张飞','0',1,0.09,'../attached/201605/20160523231054_246.jpg','超市'),
	(2,1100010001,'42-4201-420106','武汉市武昌区水果湖二路',NULL,'2016-04-02 07:12:34','晨星文具',NULL,NULL,NULL,NULL,NULL,'0',1,0.09,'../attached/201605/20160523231646_747.jpg','超市'),
	(3,1100010002,'42-4201-420111','武汉市洪山区广八路',NULL,'2016-04-02 10:45:12','金石服饰',NULL,NULL,NULL,'2016-04-12','王冕','0',1,0.09,NULL,'餐饮'),
	(4,1100010009,'42-4201-420111','八一路465号','新百伦','2016-05-11 10:45:12','全力百货','dsfadsfasdf','大野','787544545',NULL,NULL,'0',1,0.09,NULL,'超市'),
	(5,1100010009,'42-4201-420111','广八路3号',NULL,'2016-05-02 10:45:12','凯西五金','afaf','大野','74654684','2016-06-02',NULL,'0',2,0.00,NULL,'超市'),
	(6,1100010009,'42-4201-420106','大野服饰','大野服饰','2016-05-10 10:45:12','大野服饰','asdfasdf','大野','546434543',NULL,NULL,NULL,0,0.09,NULL,'超市'),
	(8,1100010016,'42-4201-420111','武汉市洪山区八一路465号','乐凡特软件','2016-05-07 10:45:12','乐凡特软件工作室','','李俊','13971615483','2016-05-28',NULL,NULL,1,0.10,NULL,'超市'),
	(7,1100010001,'42-4201-420106','中山路4号','弹力皮鞋','2016-05-15 10:45:12','弹力皮鞋','asdfasdfasd','大野','542312212',NULL,NULL,NULL,0,0.09,NULL,'超市'),
	(9,1100010009,'42-4201-420106','asdfad','ajsdfas',NULL,'asdfa','','adfa','adf',NULL,NULL,NULL,0,0.09,NULL,'超市'),
	(10,1100010013,'42-4201-420102','武汉江岸区二七路航天双城','圣美阳光',NULL,'武汉圣美阳光咨询有限公司','','王崇阳','18963988320',NULL,NULL,NULL,0,NULL,NULL,NULL),
	(11,1100010014,'42-4201-420103','武汉市江汉区新华下路169号大唐新都一期B座1608','武汉菩提树科技有限公司',NULL,'武汉菩提树科技有限公司','','夏其美','18538273844','2016-05-30',NULL,NULL,1,0.00,NULL,NULL);

/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table transfee
# ------------------------------------------------------------

DROP TABLE IF EXISTS `transfee`;

CREATE TABLE `transfee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `transTime` datetime DEFAULT NULL,
  `fee` decimal(10,0) DEFAULT NULL,
  `pid` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `transType` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `account` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `zfbTransNum` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table transfer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `transfer`;

CREATE TABLE `transfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transferid` int(11) DEFAULT NULL,
  `transfertime` date DEFAULT NULL,
  `transfermoney` double DEFAULT NULL,
  `transactionnum` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `account` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table userall
# ------------------------------------------------------------

DROP TABLE IF EXISTS `userall`;

CREATE TABLE `userall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `password` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `refereeid` int(11) DEFAULT NULL,
  `registime` date NOT NULL,
  `perfecttime` date DEFAULT NULL,
  `landtime` int(10) DEFAULT NULL,
  `lastlandtime` date DEFAULT NULL,
  `name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `identitycard` varchar(18) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `cardattributive` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `usertype` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `phonenumber` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `phoneattributive` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bodyattributive` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `paytreasurable` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `smallletter` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `businessbank` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mailaddress` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `usershare` double DEFAULT NULL,
  `shopshare` double DEFAULT NULL,
  `payintegral` int(11) DEFAULT NULL,
  `distribution` int(11) DEFAULT NULL,
  `payover` double DEFAULT NULL,
  `paytotal` double DEFAULT NULL,
  `paynumber` int(11) DEFAULT NULL,
  `tradetotal` double DEFAULT NULL,
  `tradenumber` int(11) DEFAULT NULL,
  `recommendtotal` double DEFAULT NULL,
  `recommendshopnum` int(11) DEFAULT NULL,
  `recommendusernum` int(11) DEFAULT NULL,
  `areanumber` int(11) DEFAULT NULL,
  `areatotal` double DEFAULT NULL,
  `alllineup` int(11) DEFAULT NULL,
  `newlineup` int(11) DEFAULT NULL,
  `historylineup` int(11) DEFAULT NULL,
  `shalllineup` int(11) DEFAULT NULL,
  `landnumber` int(11) DEFAULT NULL,
  `landlasttime` datetime DEFAULT NULL,
  `vipstatus` int(11) DEFAULT NULL,
  `returnfeeto` decimal(10,2) DEFAULT NULL,
  `balance` decimal(8,2) DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci MIN_ROWS=1100010001;

LOCK TABLES `userall` WRITE;
/*!40000 ALTER TABLE `userall` DISABLE KEYS */;

INSERT INTO `userall` (`id`, `userid`, `password`, `refereeid`, `registime`, `perfecttime`, `landtime`, `lastlandtime`, `name`, `identitycard`, `sex`, `age`, `birthday`, `cardattributive`, `usertype`, `phonenumber`, `phoneattributive`, `bodyattributive`, `paytreasurable`, `smallletter`, `businessbank`, `mailaddress`, `usershare`, `shopshare`, `payintegral`, `distribution`, `payover`, `paytotal`, `paynumber`, `tradetotal`, `tradenumber`, `recommendtotal`, `recommendshopnum`, `recommendusernum`, `areanumber`, `areatotal`, `alllineup`, `newlineup`, `historylineup`, `shalllineup`, `landnumber`, `landlasttime`, `vipstatus`, `returnfeeto`, `balance`)
VALUES
	(1,1100010001,'202CB962AC59075B964B07152D234B70',NULL,'2016-04-01','2016-04-02',NULL,NULL,'李克勤','4433434',NULL,NULL,NULL,NULL,'shop','13207179943',NULL,'42-4201-420105','ffadf','afadf','cercr','7646',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,248,'2016-05-03 20:39:01',NULL,0.02,0.00),
	(2,1100010002,'202CB962AC59075B964B07152D234B70',1100010001,'2016-04-02','2016-04-05',NULL,NULL,'张学友',NULL,NULL,NULL,NULL,NULL,'user','13200170017',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.02,0.01),
	(3,1100010003,'202CB962AC59075B964B07152D234B70',NULL,'2016-04-01','2016-04-02',NULL,NULL,'刘德华',NULL,NULL,NULL,NULL,NULL,'user','15933333333',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.02,0.00),
	(4,1100010004,'202CB962AC59075B964B07152D234B70',1100010001,'2016-04-02','2016-04-03',NULL,NULL,'郭富城',NULL,NULL,NULL,NULL,NULL,'user','13900000001',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.02,0.00),
	(5,1100010005,'202CB962AC59075B964B07152D234B70',1100010001,'2016-04-03','2016-04-07',NULL,NULL,'黎明',NULL,NULL,NULL,NULL,NULL,'user','13354545454',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.02,0.00),
	(6,1100010006,'202CB962AC59075B964B07152D234B70',1100010001,'2016-04-08','2016-04-11',NULL,NULL,'谭咏麟',NULL,NULL,NULL,NULL,NULL,'user','13755555555',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.02,0.00),
	(7,1100010007,'202CB962AC59075B964B07152D234B70',1100010003,'2016-04-11','2016-04-12',NULL,NULL,'周润发',NULL,NULL,NULL,NULL,NULL,'user','13299999998',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.02,0.00),
	(8,1100010008,'202CB962AC59075B964B07152D234B70',1100010003,'2016-04-12','2016-04-13',NULL,NULL,'吴奇隆',NULL,NULL,NULL,NULL,NULL,'user','13244544454',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.02,0.00),
	(9,1100010009,'202CB962AC59075B964B07152D234B70',NULL,'2016-04-02','2016-04-05',NULL,NULL,'aasas','adfas',NULL,NULL,NULL,NULL,'shop','13977777777',NULL,'42-4201-420106','adfa','asdfasd','asdfasdf','asdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,114,'2016-05-01 03:23:05',NULL,0.02,0.00),
	(10,1100010010,'202CB962AC59075B964B07152D234B70',NULL,'2016-04-05','2016-04-07',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'shop','19811111111',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.02,0.00),
	(14,1100010011,'E10ADC3949BA59ABBE56E057F20F883E',NULL,'2016-04-18',NULL,NULL,NULL,'李俊','420502197604211117',NULL,NULL,NULL,NULL,'user','13971615484',NULL,'42-4201-420106',NULL,NULL,'777777777777777777','38274390@qq.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,130,'2016-04-27 16:02:29',NULL,0.02,0.00),
	(15,1100010012,'202CB962AC59075B964B07152D234B70',NULL,'2016-04-26',NULL,NULL,NULL,'1223123','123123',NULL,NULL,NULL,NULL,'user','15527464650',NULL,'42-4202-420202','123123','123','123','123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2016-04-26 23:43:51',NULL,0.02,0.00),
	(16,1100010013,'3B0F796E863A9AC1228B11758874AF70',NULL,'2016-04-26',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'user','13719367326',NULL,NULL,'13719367326','13719367326',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,53,'2016-04-29 00:15:01',NULL,0.02,0.00),
	(17,1100010014,'43674F87503B58F9FC1E01128E425F21',NULL,'2016-04-26',NULL,NULL,NULL,'夏其美','420123197712214534',NULL,NULL,NULL,NULL,'shop','18538273844',NULL,'42-4201-420116','18538273844','18538273844','6222083202012201683','54464146@qq.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,18,'2016-04-27 19:26:39',NULL,0.02,0.00),
	(18,1100010015,'E10ADC3949BA59ABBE56E057F20F883E',NULL,'2016-04-27',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'user','15623587179',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,33,'2016-04-27 15:27:10',NULL,0.02,0.00),
	(23,1100010016,'202CB962AC59075B964B07152D234B70',0,'2016-05-09',NULL,NULL,NULL,'李俊','23423413414141234',NULL,NULL,NULL,NULL,'shop','13971615483',NULL,'42-4201-420111','24123413413','3413412341','412341234','38274390@qq.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.02,0.00),
	(24,1100010017,'E10ADC3949BA59ABBE56E057F20F883E',1100010013,'2016-05-26',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'user','18627174029',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.00),
	(25,1100010018,'AA5A9BCF37A4D7B6BE268ADA11D9839D',1100010013,'2016-05-28',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'shop','15703825932',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.00),
	(26,1100010019,'E10ADC3949BA59ABBE56E057F20F883E',1100010013,'2016-05-30',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'user','18963988320',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.00);

/*!40000 ALTER TABLE `userall` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
