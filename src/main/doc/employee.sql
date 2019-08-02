/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : testzhang

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2019-08-02 17:11:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `emp_name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `emp_no` varchar(100) DEFAULT NULL COMMENT '员工编号',
  `emp_sex` varchar(100) DEFAULT NULL COMMENT '性别',
  `make_time` datetime DEFAULT NULL,
  `sal` varchar(100) DEFAULT NULL COMMENT '工资',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '333', '333', '333', '2019-07-30 08:20:12', '333');
INSERT INTO `employee` VALUES ('2', '3332', '3332', '3332', '2018-10-25 23:16:04', '3332');
INSERT INTO `employee` VALUES ('3', '111', '111', '111', '2019-08-05 09:52:38', '111');
INSERT INTO `employee` VALUES ('4', 'ss1', 'ss2', 'ss3', '2019-08-02 14:21:51', 'ss5');
INSERT INTO `employee` VALUES ('5', 'string', 'string', 'string', '2019-08-01 09:55:48', 'string');
INSERT INTO `employee` VALUES ('6', 'string6-1', 'string6-2', 'string6-3', '2019-10-01 09:55:48', 'string6-4');
