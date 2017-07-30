/*
SQLyog - Free MySQL GUI v5.0
Host - 5.0.45-community-nt : Database - skylo
*********************************************************************
Server version : 5.0.45-community-nt
*/


create database if not exists `skylo`;

USE `skylo`;

/*Table structure for table `brands` */

DROP TABLE IF EXISTS `brands`;

CREATE TABLE `brands` (
  `brandId` int(10) NOT NULL auto_increment,
  `brandName` varchar(20) default NULL,
  PRIMARY KEY  (`brandId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `brands` */

insert into `brands` values 
(1,'STYLO');

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `categoryId` int(11) NOT NULL auto_increment,
  `categoryName` varchar(45) default NULL,
  PRIMARY KEY  (`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

/*Table structure for table `expense` */

DROP TABLE IF EXISTS `expense`;

CREATE TABLE `expense` (
  `uId` int(10) NOT NULL auto_increment,
  `expenseName` varchar(30) default NULL,
  `amount` int(10) default NULL,
  `expenseDate` datetime default NULL,
  `type` varchar(10) default NULL,
  PRIMARY KEY  (`uId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `expense` */

/*Table structure for table `history` */

DROP TABLE IF EXISTS `history`;

CREATE TABLE `history` (
  `id` int(10) NOT NULL auto_increment,
  `historyType` varchar(5) default NULL,
  `productDetail` varchar(15) default NULL,
  `createdDate` datetime default NULL,
  `status` varchar(2) default 'A',
  `size` int(10) default NULL,
  `amount` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `size` (`size`),
  CONSTRAINT `history_ibfk_1` FOREIGN KEY (`size`) REFERENCES `size` (`sizeId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `history` */

insert into `history` values 
(18,'STOCK','1501449559','2017-07-31 00:00:00','A',43,1890),
(20,'SELL','15','2017-07-31 00:00:00','A',45,0),
(21,'SELL','15','2017-07-31 00:00:00','A',45,5);

/*Table structure for table `history_master` */

DROP TABLE IF EXISTS `history_master`;

CREATE TABLE `history_master` (
  `historyId` int(10) NOT NULL auto_increment,
  `historyType` varchar(15) default NULL,
  PRIMARY KEY  (`historyId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `history_master` */

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `models` */

insert into `models` values 
(8,'Shorts',1,'105','Cotton','Black');

/*Table structure for table `orderdetails` */

DROP TABLE IF EXISTS `orderdetails`;

CREATE TABLE `orderdetails` (
  `uId` int(20) NOT NULL auto_increment,
  `orderId` int(20) NOT NULL,
  `orderName` varchar(40) default NULL,
  `amount` int(5) default NULL,
  `brand` varchar(10) default NULL,
  `suppliedBy` int(10) default NULL,
  `orderDate` datetime default NULL,
  `model` int(10) default NULL,
  `size` int(10) default NULL,
  PRIMARY KEY  (`uId`),
  KEY `suppliedBy` (`suppliedBy`),
  KEY `model` (`model`,`size`),
  KEY `FK_orderdetails` (`size`),
  CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`suppliedBy`) REFERENCES `supplier` (`supplierId`),
  CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`size`) REFERENCES `size` (`sizeId`),
  CONSTRAINT `orderdetails_ibfk_3` FOREIGN KEY (`model`) REFERENCES `models` (`modelId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

/*Data for the table `orderdetails` */

insert into `orderdetails` values 
(23,1501449559,'remark 1',1890,'1',1,'2017-07-31 00:00:00',8,43);

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
  `size` int(10) default NULL,
  `returnDate` datetime default NULL,
  `amountPaid` bigint(10) default NULL,
  `payment` varbinary(10) default NULL,
  PRIMARY KEY  (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `selldetail` */

insert into `selldetail` values 
(15,'S07-1501450057','cutomer 1','1','8',NULL,'Indore','2017-07-31',105,9898989898,45,NULL,5,'Due');

/*Table structure for table `size` */

DROP TABLE IF EXISTS `size`;

CREATE TABLE `size` (
  `sizeId` int(10) NOT NULL auto_increment,
  `s` int(5) default '0',
  `m` int(5) default '0',
  `l` int(5) default '0',
  `xl` int(5) default '0',
  `xxl` int(5) default '0',
  `xxxl` int(5) default '0',
  PRIMARY KEY  (`sizeId`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

/*Data for the table `size` */

insert into `size` values 
(43,1,4,2,4,2,4),
(45,1,0,0,0,0,0);

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `supplierId` int(10) NOT NULL auto_increment,
  `supplierName` varchar(30) default NULL,
  `contactNo` bigint(10) default NULL,
  `address` varchar(30) default NULL,
  `amountDue` varchar(10) default NULL,
  `amountPaid` varchar(10) default NULL,
  PRIMARY KEY  (`supplierId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `supplier` */

insert into `supplier` values 
(1,'Supplier 1',9999999999,'Indore',NULL,NULL);

/*Table structure for table `userdetails` */

DROP TABLE IF EXISTS `userdetails`;

CREATE TABLE `userdetails` (
  `uId` int(11) NOT NULL auto_increment,
  `userId` varchar(45) default NULL,
  `password` varchar(45) default NULL,
  `userName` varchar(45) default NULL,
  PRIMARY KEY  (`uId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `userdetails` */

insert into `userdetails` values 
(1,'root','12345','Piyush Patil');
