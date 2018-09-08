/*
Navicat MySQL Data Transfer

Source Server         : mysql5.7
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : ssmww

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-09-07 13:41:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `addressid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL COMMENT '关联用户外键',
  `receivename` varchar(255) DEFAULT NULL,
  `receivephone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`addressid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goodsid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `goodsclass` varchar(255) DEFAULT NULL,
  `goodsname` varchar(255) DEFAULT NULL,
  `purchaseprice` decimal(10,2) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`goodsid`)
) ENGINE=InnoDB AUTO_INCREMENT=1016 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for goodsorders
-- ----------------------------
DROP TABLE IF EXISTS `goodsorders`;
CREATE TABLE `goodsorders` (
  `orderid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ordercode` varchar(255) DEFAULT NULL,
  `confirmdate` datetime DEFAULT NULL,
  `countprice` decimal(10,2) DEFAULT NULL,
  `saleid` int(11) DEFAULT NULL,
  `addressid` int(11) DEFAULT NULL,
  `orderstate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  UNIQUE KEY `ordercode` (`ordercode`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for orderdetails
-- ----------------------------
DROP TABLE IF EXISTS `orderdetails`;
CREATE TABLE `orderdetails` (
  `orderdetailsid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ordervode` varchar(255) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderdetailsid`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sale
-- ----------------------------
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale` (
  `saleid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `salename` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `salepassword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`saleid`),
  UNIQUE KEY `salename` (`salename`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
