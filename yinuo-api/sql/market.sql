CREATE DATABASE  IF NOT EXISTS `market` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `market`;
-- MySQL dump 10.13  Distrib 5.6.11, for osx10.6 (i386)
--
-- Host: 123.57.141.203    Database: market
-- ------------------------------------------------------
-- Server version	5.1.73-log

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


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账户id',
  `username` varchar(15) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `sex` tinyint(3) NOT NULL DEFAULT '0' COMMENT '性别',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `qq` varchar(15) NOT NULL DEFAULT '' COMMENT 'qq账户',
  `creat_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `role` tinyint(3) NOT NULL DEFAULT '0' COMMENT '权限等级',
  `fk_file_id` bigint(20) NOT NULL COMMENT '头像图片',
  `money` double(9,2) NOT NULL DEFAULT '0.00' COMMENT '余额',
  `address` String NOT NULL DEFAULT ' COMMENT '收货地址',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_shop
-- ----------------------------
DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '店铺id自生成',
  `name` varchar(15) NOT NULL DEFAULT '' COMMENT '店铺名称',
  `create_time` datetime NOT NULL COMMENT '店铺创建时间',
  `open_time` int(11) NOT NULL COMMENT '开始营业时间(h*60+m)',
  `stop_time` int(11) NOT NULL COMMENT '结束营业时间(h*60+m)',
  `state` tinyint(3) NOT NULL COMMENT '店铺营业状态',
  `fk_file_id` bigint(20) NOT NULL COMMENT '店铺图片',
  `fk_user_id` bigint(20) NOT NULL COMMENT '店铺的创建用户的id',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_commodity
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity`;
CREATE TABLE `t_commodity` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(15) NOT NULL COMMENT '商品名',
  `type` tinyint(3) NOT NULL COMMENT '商品类型',
  `state` tinyint(3) NOT NULL COMMENT '状态（1上架，0下架）',
  `sale` int(11) NOT NULL COMMENT '商品销量',
  `price` double(9,2) NOT NULL COMMENT '商品单价',
  `fk_file_id` bigint(20) NOT NULL COMMENT '商品图片',
  `detail` varchar(63) NOT NULL COMMENT '商品简介',
  `stock` int(11) NOT NULL COMMENT '库存',
  `fk_shop_id` bigint(20) NOT NULL COMMENT '代表是那个店铺',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单号',
  `fk_user_id` bigint(20) NOT NULL COMMENT '下单的用户'
  `create_time` datetime NOT NULL COMMENT '订单创建时间(下单)',
  `state` tinyint(3) NOT NULL COMMENT '订单状态（已下单，已接单，派送中，已接收，结束）',
  `address` varchar(127) NOT NULL COMMENT '收货地址',
  `total` double NOT NULL COMMENT '订单总价',
  `user_mobile` int NOT NULL COMMENT '顾客电话',
  `shop_moblile` int NOT NULL COMMENT '店铺联系方式',
  `order_time` datetime NOT NULL COMMENT '订单修改时间(接单)',
  `delivery_time` datetime NOT NULL COMMENT '订单修改时间(派送)',
  `confirm_time` datetime NOT NULL COMMENT '订单修改时间(确认)',
  `stop_time` datetime NOT NULL COMMENT '订单修改时间(结束)',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_order_detail`;
CREATE TABLE `t_order_detail` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,COMMENT '主键'
  `fk_order_id` bigint(20) NOT NULL COMMENT '代表是哪个订单',
  `fk_commodity_id` bigint(20) NOT NULL COMMENT '代表是买的那个商品',
  `number` int(11) NOT NULL COMMENT '购买商品数量',
  `price` double(9,2) NOT NULL COMMENT '商品单价',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `t_evaluate`;
CREATE TABLE `t_evaluate` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评价表的id',
  `content` varchar(255) NOT NULL COMMENT '评价内容',
  `content_append` varchar(255) NOT NULL COMMENT '追加评价内容',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `append_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '追加时间',
  `grade` tinyint(3) NOT NULL COMMENT '本次消费的等级评价的星级（1~5）',
  `fk_order_id` bigint(20) NOT NULL COMMENT '对本次消费（订单）的pkid',

  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_transaction
-- ----------------------------
DROP TABLE IF EXISTS `t_deal`;
CREATE TABLE `t_deal` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fk_user_id` bigint(20) NOT NULL COMMENT '用户',
  `type` tinyint(3) NOT NULL COMMENT '（存 or 取） ',
  `no` varchar(32) NOT NULL COMMENT '交易流水号',
  `money` double(9,2) NOT NULL COMMENT '金额',
  `state` tinyint(3) NOT NULL COMMENT '交易状态',
  `visible` tinyint(3) NOT NULL COMMENT '账单是否可见',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`pk_id`,`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `fk_user_id` bigint(20) NOT NULL COMMENT '用户id，对应谁的购物车',
  `fk_commodity_id` bigint(20) NOT NULL COMMENT '购买商品id',
  `number` int(11) NOT NULL COMMENT '购买商品数量',
  `total` double(9,2) NOT NULL COMMENT '购物车总价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

Date: 2017-07-19 15:25:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(63) NOT NULL COMMENT '保存文件的路径',
  `type` tinyint(3) NOT NULL COMMENT '表示文件的分类（用户头像，店铺头像.....）',
  PRIMARY KEY (`pk_id`)
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

-- Dump completed on 2017-07-07  0:49:40

