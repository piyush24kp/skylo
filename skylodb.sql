/*
SQLyog - Free MySQL GUI v5.17
Host - 5.0.24-community-nt : Database - mobilestore
*********************************************************************
Server version : 5.0.24-community-nt
*/

SET NAMES utf8;

SET SQL_MODE='';

create database if not exists `mobilestore`;

USE `mobilestore`;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Table structure for table `brands` */

DROP TABLE IF EXISTS `brands`;

CREATE TABLE `brands` (
  `brandId` int(10) NOT NULL auto_increment,
  `brandName` varchar(20) default NULL,
  PRIMARY KEY  (`brandId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `brands` */

insert into `brands` (`brandId`,`brandName`) values (1,'STYLO -7 '),(13,'Arrow');

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `categoryId` int(11) NOT NULL auto_increment,
  `categoryName` varchar(45) default NULL,
  PRIMARY KEY  (`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

/*Table structure for table `models` */

DROP TABLE IF EXISTS `models`;

CREATE TABLE `models` (
  `modelId` int(10) NOT NULL auto_increment,
  `modelName` varchar(20) default NULL,
  `brandId` int(10) default NULL,
  `price` varchar(45) default NULL,
  `category` varchar(40) default NULL,
  `color` varbinary(10) default NULL,
  PRIMARY KEY  (`modelId`),
  KEY `FK_models` (`brandId`),
  CONSTRAINT `models_ibfk_1` FOREIGN KEY (`brandId`) REFERENCES `brands` (`brandId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `models` */

insert into `models` (`modelId`,`modelName`,`brandId`,`price`,`category`,`color`) values (1,'Linen',1,'10','asd','black'),(3,'Test Model',13,'100','Linen','Black');

/*Table structure for table `orderdetails` */

DROP TABLE IF EXISTS `orderdetails`;

CREATE TABLE `orderdetails` (
  `uId` int(20) NOT NULL auto_increment,
  `orderId` int(20) NOT NULL,
  `orderName` varchar(40) default NULL,
  `amount` int(5) default NULL,
  `quantity` int(3) default '0',
  `purchasePrice` int(11) default NULL,
  `sellPrice` int(11) default NULL,
  `brand` varchar(10) default NULL,
  `suppliedBy` int(10) default NULL,
  `orderDate` datetime default NULL,
  `model` varchar(10) default NULL,
  PRIMARY KEY  (`uId`),
  KEY `suppliedBy` (`suppliedBy`),
  CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`suppliedBy`) REFERENCES `supplier` (`supplierId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `orderdetails` */

insert into `orderdetails` (`uId`,`orderId`,`orderName`,`amount`,`quantity`,`purchasePrice`,`sellPrice`,`brand`,`suppliedBy`,`orderDate`,`model`) values (26,1499633754,'piyush patil',NULL,10,100,120,'1',13,'2017-07-10 00:00:00','1'),(27,1499636012,'piyush patil',NULL,5,100,120,'13',1,'2017-07-10 00:00:00','3');

/*Table structure for table `selldetail` */

DROP TABLE IF EXISTS `selldetail`;

CREATE TABLE `selldetail` (
  `orderId` bigint(10) NOT NULL auto_increment,
  `invoiceNo` varchar(45) default NULL,
  `customerName` varchar(45) default NULL,
  `brand` varchar(10) default NULL,
  `model` varchar(10) default NULL,
  `saleType` varchar(10) default NULL,
  `address` varchar(45) default NULL,
  `sellDate` date default NULL,
  `amount` bigint(10) default NULL,
  `contantNo` bigint(10) default NULL,
  `payment` varchar(20) default NULL,
  `quantity` bigint(10) default NULL,
  PRIMARY KEY  (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `selldetail` */

insert into `selldetail` (`orderId`,`invoiceNo`,`customerName`,`brand`,`model`,`saleType`,`address`,`sellDate`,`amount`,`contantNo`,`payment`,`quantity`) values (1,'STR-1499631774','piyush patil','1','1','Offline','141-a,vidhya nagar','2017-07-10',100,NULL,'Paid',1),(2,'STR-1499632059','piyush patil','1','1','Offline','141-a,vidhya nagar','2017-07-10',2000,7415786624,'Paid',1),(3,'STR-1499632263','piyush patil','1','1','Offline','141-a,vidhya nagar','2017-07-10',2000,741578662,'Due',1),(4,'STR-1499633309','piyush patil','1','1','Offline','141-a,vidhya nagar','2017-07-10',10,7415786624,'Due',2),(5,'STR-1499636231','piyush patil','13','3','Offline','141-a,vidhya nagar','2017-07-10',10000,7415786624,'Paid',5);

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `supplierId` int(10) NOT NULL auto_increment,
  `supplierName` varchar(20) default NULL,
  `contactNo` int(10) default NULL,
  `address` varchar(30) default NULL,
  `amountDue` varchar(10) default NULL,
  `amountPaid` varchar(10) default NULL,
  PRIMARY KEY  (`supplierId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `supplier` */

insert into `supplier` (`supplierId`,`supplierName`,`contactNo`,`address`,`amountDue`,`amountPaid`) values (1,'test',123456789,'1233456','1000','4000'),(2,'dj',123456789,'123123','500','2500'),(3,'AS',23,'123','123123','0'),(13,'test',123456,'indore','1000','4000');

/*Table structure for table `userdetails` */

DROP TABLE IF EXISTS `userdetails`;

CREATE TABLE `userdetails` (
  `uId` int(11) NOT NULL auto_increment,
  `userId` varchar(45) default NULL,
  `password` varchar(45) default NULL,
  `userName` varchar(45) default NULL,
  PRIMARY KEY  (`uId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userdetails` */

insert into `userdetails` (`uId`,`userId`,`password`,`userName`) values (1,'root','12345','Piyush Patil');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
