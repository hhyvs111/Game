/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : game

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-05-21 18:46:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `rank`
-- ----------------------------
DROP TABLE IF EXISTS `rank`;
CREATE TABLE `rank` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `score` int(10) NOT NULL,
  `time` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rank
-- ----------------------------
INSERT INTO `rank` VALUES ('2', '2', '2', '2017-03-07');
INSERT INTO `rank` VALUES ('3', '谭文波', '120', '2017-04-13');
INSERT INTO `rank` VALUES ('11', '里里', '150', '2017-04-15');
INSERT INTO `rank` VALUES ('14', '456', '0', '2017-04-16');
INSERT INTO `rank` VALUES ('16', '213', '0', '2017-04-16');
INSERT INTO `rank` VALUES ('17', '312', '0', '2017-04-16');
INSERT INTO `rank` VALUES ('18', '456', '30', '2017-04-16');
INSERT INTO `rank` VALUES ('19', '213', '160', '2017-04-16');
INSERT INTO `rank` VALUES ('20', '213', '120', '2017-04-16');
INSERT INTO `rank` VALUES ('22', '123', '60', '2017-04-16');
INSERT INTO `rank` VALUES ('23', '213', '120', '2017-04-16');
INSERT INTO `rank` VALUES ('29', '123', '100', '2017-04-18');
INSERT INTO `rank` VALUES ('30', '123', '70', '2017-04-18');
INSERT INTO `rank` VALUES ('34', '123', '120', '2017-04-18');
INSERT INTO `rank` VALUES ('50', 'TWB', '280', '2017-04-18');
INSERT INTO `rank` VALUES ('51', 'test', '1980', '2017-05-06');
INSERT INTO `rank` VALUES ('52', 'test', '0', '2017-05-06');
INSERT INTO `rank` VALUES ('53', 'hhyvs520', '390', '2017-05-08');
INSERT INTO `rank` VALUES ('54', 'hhyvs520', '0', '2017-05-08');
INSERT INTO `rank` VALUES ('55', 'hhyvs520', '300', '2017-05-08');
INSERT INTO `rank` VALUES ('56', 'hhyvs520', '60', '2017-05-08');
INSERT INTO `rank` VALUES ('57', 'hhyvs520', '0', '2017-05-08');
INSERT INTO `rank` VALUES ('58', 'hhyvs520', '0', '2017-05-08');
INSERT INTO `rank` VALUES ('59', 'hhyvs520', '60', '2017-05-08');
INSERT INTO `rank` VALUES ('60', 'hhyvs520', '90', '2017-05-08');
INSERT INTO `rank` VALUES ('61', 'hhyvs520', '150', '2017-05-08');
INSERT INTO `rank` VALUES ('62', 'hhyvs520', '70', '2017-05-09');
INSERT INTO `rank` VALUES ('63', '111', '30', '2017-05-18');
INSERT INTO `rank` VALUES ('64', '111', '90', '2017-05-19');
INSERT INTO `rank` VALUES ('65', '111', '1850', '2017-05-19');
INSERT INTO `rank` VALUES ('66', '123', '1150', '2017-05-19');
INSERT INTO `rank` VALUES ('67', '123', '920', '2017-05-19');
INSERT INTO `rank` VALUES ('68', '111', '1040', '2017-05-19');
INSERT INTO `rank` VALUES ('69', '111', '60', '2017-05-19');
INSERT INTO `rank` VALUES ('70', '111', '60', '2017-05-19');
INSERT INTO `rank` VALUES ('71', '111', '200', '2017-05-20');
INSERT INTO `rank` VALUES ('72', '111', '0', '2017-05-20');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '111', '222', '13007351861');
INSERT INTO `user` VALUES ('2', 'admin', 'admin', '18833556644');
INSERT INTO `user` VALUES ('3', 'hhyvs111', '123456', '18607329625');
INSERT INTO `user` VALUES ('4', 'haha', '123', '14567891233');
INSERT INTO `user` VALUES ('5', 'hehe', '123456', '13807329966');
INSERT INTO `user` VALUES ('6', 'test', '123', '15007351234');
INSERT INTO `user` VALUES ('7', 'hhyvs520', 'a13007351861', '18607329625');
INSERT INTO `user` VALUES ('8', '123', '123', '12345678936');
INSERT INTO `user` VALUES ('9', 'tanwenbo', 'a13007351861', '18607329625');
