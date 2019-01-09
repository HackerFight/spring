/*
Navicat MySQL Data Transfer

Source Server         : test2
Source Server Version : 50723
Source Host           : mysql.test2.hzzh.xyz:3306
Source Database       : energy_management

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-01-08 15:10:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_app_feedback
-- ----------------------------
DROP TABLE IF EXISTS `t_app_feedback`;
CREATE TABLE `t_app_feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_code` tinyint(3) NOT NULL COMMENT '1：知能APP',
  `account_id` varchar(255) NOT NULL COMMENT '登陆账号ID',
  `feedback_content` varchar(200) DEFAULT NULL COMMENT '意见和问题',
  `feedback_image_url` text COMMENT '图片url',
  `phone_number` varchar(15) DEFAULT NULL COMMENT '手机号码',
  `contact_way` varchar(32) DEFAULT NULL COMMENT '联系方式',
  `sys_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `sys_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='App意见反馈表';

-- ----------------------------
-- Records of t_app_feedback
-- ----------------------------
INSERT INTO `t_app_feedback` VALUES ('5', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:20', '2019-01-08 11:33:20');
INSERT INTO `t_app_feedback` VALUES ('6', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:21', '2019-01-08 11:33:21');
INSERT INTO `t_app_feedback` VALUES ('7', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:21', '2019-01-08 11:33:21');
INSERT INTO `t_app_feedback` VALUES ('8', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:21', '2019-01-08 11:33:21');
INSERT INTO `t_app_feedback` VALUES ('9', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:37', '2019-01-08 11:33:37');
INSERT INTO `t_app_feedback` VALUES ('10', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:38', '2019-01-08 11:33:38');
INSERT INTO `t_app_feedback` VALUES ('11', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:38', '2019-01-08 11:33:38');
INSERT INTO `t_app_feedback` VALUES ('12', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:39', '2019-01-08 11:33:39');
INSERT INTO `t_app_feedback` VALUES ('13', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:39', '2019-01-08 11:33:39');
INSERT INTO `t_app_feedback` VALUES ('14', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:40', '2019-01-08 11:33:40');
INSERT INTO `t_app_feedback` VALUES ('15', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:40', '2019-01-08 11:33:40');
INSERT INTO `t_app_feedback` VALUES ('16', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:41', '2019-01-08 11:33:41');
INSERT INTO `t_app_feedback` VALUES ('17', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:41', '2019-01-08 11:33:41');
INSERT INTO `t_app_feedback` VALUES ('18', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:42', '2019-01-08 11:33:42');
INSERT INTO `t_app_feedback` VALUES ('19', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:46', '2019-01-08 11:33:46');
INSERT INTO `t_app_feedback` VALUES ('20', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:46', '2019-01-08 11:33:46');
INSERT INTO `t_app_feedback` VALUES ('21', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:46', '2019-01-08 11:33:46');
INSERT INTO `t_app_feedback` VALUES ('22', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:59', '2019-01-08 11:33:59');
INSERT INTO `t_app_feedback` VALUES ('23', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:33:59', '2019-01-08 11:33:59');
INSERT INTO `t_app_feedback` VALUES ('24', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:34:00', '2019-01-08 11:34:00');
INSERT INTO `t_app_feedback` VALUES ('25', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:34:00', '2019-01-08 11:34:00');
INSERT INTO `t_app_feedback` VALUES ('26', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:35:12', '2019-01-08 11:35:12');
INSERT INTO `t_app_feedback` VALUES ('27', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:35:12', '2019-01-08 11:35:12');
INSERT INTO `t_app_feedback` VALUES ('28', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:35:56', '2019-01-08 11:35:56');
INSERT INTO `t_app_feedback` VALUES ('29', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:35:57', '2019-01-08 11:35:57');
INSERT INTO `t_app_feedback` VALUES ('30', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:43:23', '2019-01-08 11:43:23');
INSERT INTO `t_app_feedback` VALUES ('31', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:43:29', '2019-01-08 11:43:29');
INSERT INTO `t_app_feedback` VALUES ('32', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:43:30', '2019-01-08 11:43:30');
INSERT INTO `t_app_feedback` VALUES ('33', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:43:30', '2019-01-08 11:43:30');
INSERT INTO `t_app_feedback` VALUES ('34', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 11:43:30', '2019-01-08 11:43:30');
INSERT INTO `t_app_feedback` VALUES ('35', '1', '5zz28c76960a7fa0', '我的', '1', '13689754582', null, '2019-01-08 11:49:50', '2019-01-08 11:49:50');
INSERT INTO `t_app_feedback` VALUES ('36', '1', '5zz28c76960a7fa0', '我的', '1', '13689754582', null, '2019-01-08 11:49:52', '2019-01-08 11:49:52');
INSERT INTO `t_app_feedback` VALUES ('37', '1', '5zz28c76960a7fa0', '我呢联系', null, '13689754582', '', '2019-01-08 14:31:17', '2019-01-08 14:31:17');
INSERT INTO `t_app_feedback` VALUES ('38', '1', '5zz28c76960a7fa0', '我的小伙伴', 'http://img.oss.powercloud.cn/21E8B90F93D6253B255A9E5C6ADCE0F7,http://img.oss.powercloud.cn/0F249C4DF042526D9704787253B1EEA5,http://img.oss.powercloud.cn/4C976E2895B8B2EC22F82642DBF07AE8', '13689754582', '18668241888', '2019-01-08 14:31:53', '2019-01-08 14:31:53');
INSERT INTO `t_app_feedback` VALUES ('39', '1', '5zz28c76960a7fa0', '我的建议', 'http://img.oss.powercloud.cn/1ED0C45B74FC11AD506AA4C25EFFE352', '13689754582', '', '2019-01-08 14:33:56', '2019-01-08 14:33:56');
INSERT INTO `t_app_feedback` VALUES ('40', '1', '5zz28c76960a7fa0', '联系方式', 'http://img.oss.powercloud.cn/4C976E2895B8B2EC22F82642DBF07AE8,http://img.oss.powercloud.cn/F81683A8A29C2D7AE3725629333E3339,http://img.oss.powercloud.cn/1ED0C45B74FC11AD506AA4C25EFFE352', '13689754582', '18668248888', '2019-01-08 14:42:58', '2019-01-08 14:42:58');
INSERT INTO `t_app_feedback` VALUES ('41', '1', '5zz28c76960a7fa0', '我的资料', 'http://img.oss.powercloud.cn/4C976E2895B8B2EC22F82642DBF07AE8,http://img.oss.powercloud.cn/1ED0C45B74FC11AD506AA4C25EFFE352', '13689754582', '18668548888', '2019-01-08 14:46:54', '2019-01-08 14:46:54');
INSERT INTO `t_app_feedback` VALUES ('42', '1', '5zz28c76960a7fa0', '我们资料', null, '13689754582', '18898242288', '2019-01-08 14:47:17', '2019-01-08 14:47:17');
INSERT INTO `t_app_feedback` VALUES ('43', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 14:54:19', '2019-01-08 14:54:19');
INSERT INTO `t_app_feedback` VALUES ('44', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 14:54:20', '2019-01-08 14:54:20');
INSERT INTO `t_app_feedback` VALUES ('45', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 14:54:20', '2019-01-08 14:54:20');
INSERT INTO `t_app_feedback` VALUES ('46', '1', '5zz28c76960a7fa0', '我的', null, '13689754582', null, '2019-01-08 14:55:35', '2019-01-08 14:55:35');
