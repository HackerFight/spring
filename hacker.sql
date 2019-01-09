/*
Navicat MySQL Data Transfer

Source Server         : dev
Source Server Version : 50722
Source Host           : 10.1.170.207:3306
Source Database       : hacker

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-01-09 13:40:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `login_id` varchar(255) DEFAULT NULL,
  `login_password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES ('1', '1', 'Go', '1001091181', 'abc');
INSERT INTO `t_admin_user` VALUES ('5', '2', 'Go', '1547003785514', '1547003785514');
INSERT INTO `t_admin_user` VALUES ('7', '3', 'Go', '1547004656732', '1547004656732');
INSERT INTO `t_admin_user` VALUES ('8', '4', 'nodeJs', '1547005255252', '1547005255252');

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `class_no` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_class
-- ----------------------------

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `stu_no` varchar(16) NOT NULL,
  `class_no` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
