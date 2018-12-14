/*
Navicat MySQL Data Transfer

Source Server         : DESKTOP-15N38RI
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : hrms

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-12-08 19:43:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `deptId` int(10) NOT NULL AUTO_INCREMENT,
  `dept1Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dept2Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`deptId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '办公室', '综合科');
INSERT INTO `dept` VALUES ('2', '办公室', '文秘科');
INSERT INTO `dept` VALUES ('3', '办公室', '机要科');
INSERT INTO `dept` VALUES ('4', '人事处', '人事科');
INSERT INTO `dept` VALUES ('5', '人事处', '师资科');
INSERT INTO `dept` VALUES ('6', '教务处', '教务科');
INSERT INTO `dept` VALUES ('7', '教务处', '注册中心');
INSERT INTO `dept` VALUES ('8', '信息办', '办公室');
INSERT INTO `dept` VALUES ('9', '信息办', '数据中心');
INSERT INTO `dept` VALUES ('10', '信息办', '网络中心');
INSERT INTO `dept` VALUES ('11', '信息办', '卡务中心');
INSERT INTO `dept` VALUES ('12', '测试1', '测试2');
INSERT INTO `dept` VALUES ('13', '测试22', '测试12');

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `histId` int(10) NOT NULL AUTO_INCREMENT,
  `histType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `oldInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `newInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `chgDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `chgNum` int(10) DEFAULT NULL,
  `personId` int(10) DEFAULT NULL,
  PRIMARY KEY (`histId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES ('1', '人员调动', '5', '6', '2018-10-20', '1', '1');
INSERT INTO `history` VALUES ('2', '人员调动', '6', '7', '2018-10-25', '2', '1');
INSERT INTO `history` VALUES ('3', '人员考核', '合格', '优秀', '2018-09-15', '1', '1');
INSERT INTO `history` VALUES ('4', '劳资分配', '5000', '6000', '2018-09-20', '1', '1');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `personId` int(10) NOT NULL AUTO_INCREMENT,
  `pName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `birth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `nat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deptId` int(10) DEFAULT NULL,
  `salary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `assess` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `other` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`personId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', '张三', '男', '1980-03-19', '汉族', '北京', '7', '6000', '优秀', null);
INSERT INTO `person` VALUES ('2', '李四', '女', '1981-09-17', '汉族', '北京', '5', '5500', '合格', null);
INSERT INTO `person` VALUES ('3', '王五', '男', '1979-12-21', '满族', '北京', '4', '5500', '合格', null);
INSERT INTO `person` VALUES ('4', '赵六', '女', '1985-06-23', '回族', '北京', '10', '4500', '未考核', '2010年工作');
INSERT INTO `person` VALUES ('5', '宋七', '女', '1986-10-21', '回族', '天津', '1', '3000', '未考核', null);
INSERT INTO `person` VALUES ('6', '柳八', '男', '1986-06-12', '汉族', '大连', '1', '3000', '不合格', '2010年入校');
