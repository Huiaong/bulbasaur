/*
 Navicat Premium Data Transfer

 Source Server         : mine
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 180.76.142.30:3306
 Source Schema         : bulbasaur

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 22/08/2020 23:59:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bulbasaur_favor_fund
-- ----------------------------
DROP TABLE IF EXISTS `bulbasaur_favor_fund`;
CREATE TABLE `bulbasaur_favor_fund` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `fund_code` varchar(6) NOT NULL COMMENT '基金代码',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` timestamp NOT NULL DEFAULT '1970-01-01 08:00:01' COMMENT '更新时间',
  `del_flag` smallint(1) unsigned NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for bulbasaur_user
-- ----------------------------
DROP TABLE IF EXISTS `bulbasaur_user`;
CREATE TABLE `bulbasaur_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `open_id` varchar(255) NOT NULL COMMENT 'openId',
  `status` smallint(2) NOT NULL COMMENT '状态',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` timestamp NOT NULL DEFAULT '1970-01-01 08:00:01' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` smallint(1) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
