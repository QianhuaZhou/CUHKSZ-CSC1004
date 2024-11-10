/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : im

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 03/05/2023 10:08:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msg` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `send` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `to` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '1111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:07:20');
INSERT INTO `message` VALUES (2, '1111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:09:54');
INSERT INTO `message` VALUES (3, '1111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:10:55');
INSERT INTO `message` VALUES (4, '111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:12:10');
INSERT INTO `message` VALUES (5, '21212123', 'archerForzkx', 'group1', 1, '2023-05-02 23:23:29');
INSERT INTO `message` VALUES (6, '12121212', 'archerForzkx', 'group1', 1, '2023-05-02 23:24:19');
INSERT INTO `message` VALUES (7, '12123123123123', 'archerForzkx', 'group1', 1, '2023-05-02 23:24:22');
INSERT INTO `message` VALUES (8, '123123123123', 'archerForzkx', 'group1', 1, '2023-05-02 23:24:24');
INSERT INTO `message` VALUES (9, '123123123123', 'archerForzkx', 'group1', 1, '2023-05-02 23:24:27');
INSERT INTO `message` VALUES (10, '12331231231', 'archerForzkx', 'group1', 1, '2023-05-02 23:24:29');
INSERT INTO `message` VALUES (11, '你好呀', 'archerForzkx', 'group1', 1, '2023-05-02 23:29:36');
INSERT INTO `message` VALUES (12, '11111111111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:31:41');
INSERT INTO `message` VALUES (13, '11111', 'archerForzkx', 'group1', 1, '2023-05-02 23:33:18');
INSERT INTO `message` VALUES (14, '111111111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:34:58');
INSERT INTO `message` VALUES (15, '111111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:35:01');
INSERT INTO `message` VALUES (16, '1111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:36:10');
INSERT INTO `message` VALUES (17, '1111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:40:07');
INSERT INTO `message` VALUES (18, '111111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:40:10');
INSERT INTO `message` VALUES (19, '111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:40:13');
INSERT INTO `message` VALUES (20, '11111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:40:16');
INSERT INTO `message` VALUES (21, '111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:40:18');
INSERT INTO `message` VALUES (22, '1111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:40:20');
INSERT INTO `message` VALUES (23, '1111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:40:46');
INSERT INTO `message` VALUES (24, '1111111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:42:54');
INSERT INTO `message` VALUES (25, '121212122', 'archerForzmy', 'group1', 1, '2023-05-02 23:43:30');
INSERT INTO `message` VALUES (26, '请求', 'archerForzkx', 'group1', 1, '2023-05-02 23:44:06');
INSERT INTO `message` VALUES (27, '112121', 'archerForzkx', 'group1', 1, '2023-05-02 23:44:11');
INSERT INTO `message` VALUES (28, '1212212', 'archerForzkx', 'group1', 1, '2023-05-02 23:44:14');
INSERT INTO `message` VALUES (29, '12121212', 'archerForzkx', 'group1', 1, '2023-05-02 23:44:17');
INSERT INTO `message` VALUES (30, '121212', 'archerForzkx', 'group1', 1, '2023-05-02 23:44:18');
INSERT INTO `message` VALUES (31, '12121221', 'archerForzkx', 'group1', 1, '2023-05-02 23:44:20');
INSERT INTO `message` VALUES (32, '12121221', 'archerForzkx', 'group1', 1, '2023-05-02 23:44:22');
INSERT INTO `message` VALUES (33, '121212', 'archerForzkx', 'group1', 1, '2023-05-02 23:44:24');
INSERT INTO `message` VALUES (34, '122112', 'archerForzmy', 'group1', 1, '2023-05-02 23:44:28');
INSERT INTO `message` VALUES (35, '112121212', 'archerForzmy', 'group1', 1, '2023-05-02 23:47:08');
INSERT INTO `message` VALUES (36, '122222222222222222', 'archerForzmy', 'group1', 1, '2023-05-02 23:47:18');
INSERT INTO `message` VALUES (37, '1212121212', 'archerForzkx', 'group1', 1, '2023-05-02 23:47:25');
INSERT INTO `message` VALUES (38, '1212121212', 'archerForzkx', 'group1', 1, '2023-05-02 23:47:29');
INSERT INTO `message` VALUES (39, '1111111111111111111111', 'archerForzmy', 'group1', 1, '2023-05-02 23:48:22');
INSERT INTO `message` VALUES (40, '1111111111111111111', 'archerForzmy', 'group1', 1, '2023-05-02 23:48:25');
INSERT INTO `message` VALUES (41, '121212121212', 'archerForzkx', 'group1', 1, '2023-05-02 23:49:17');
INSERT INTO `message` VALUES (42, '123123123123123', 'archerForzkx', 'group1', 1, '2023-05-02 23:49:23');
INSERT INTO `message` VALUES (43, '121231231', 'archerForzkx', 'group1', 1, '2023-05-02 23:49:44');
INSERT INTO `message` VALUES (44, '1111111111111111', 'archerForzmy', 'group1', 1, '2023-05-02 23:52:22');
INSERT INTO `message` VALUES (45, '1111111111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:52:31');
INSERT INTO `message` VALUES (46, '3232323', 'archerForzkx', 'group1', 1, '2023-05-02 23:53:16');
INSERT INTO `message` VALUES (47, '12112', 'archerForzkx', 'group1', 1, '2023-05-02 23:54:42');
INSERT INTO `message` VALUES (48, '1111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:56:46');
INSERT INTO `message` VALUES (49, '11111', 'archerForzmy', 'group1', 1, '2023-05-02 23:56:49');
INSERT INTO `message` VALUES (50, '111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:56:54');
INSERT INTO `message` VALUES (51, '111111111', 'archerForzkx', 'group1', 1, '2023-05-02 23:59:50');
INSERT INTO `message` VALUES (52, '1111111', 'archerForzkx', 'group1', 1, '2023-05-03 00:00:00');
INSERT INTO `message` VALUES (53, '1111111', 'archerForzmy', 'group1', 1, '2023-05-03 00:00:06');
INSERT INTO `message` VALUES (54, '111111', 'archerForzmy', 'group1', 1, '2023-05-03 00:00:09');
INSERT INTO `message` VALUES (55, '12222222222', 'archerForzmy', 'group1', 1, '2023-05-03 00:05:22');
INSERT INTO `message` VALUES (56, '1111111111111', 'archerForzmy', 'group1', 1, '2023-05-03 00:05:27');
INSERT INTO `message` VALUES (57, '111111111111111', 'archerForzkx', 'group1', 1, '2023-05-03 00:05:29');
INSERT INTO `message` VALUES (58, '111111111111', 'archerForzkx', 'group1', 1, '2023-05-03 00:08:51');
INSERT INTO `message` VALUES (59, '11111111111111111', 'archerForzmy', 'group1', 1, '2023-05-03 00:08:54');
INSERT INTO `message` VALUES (60, '111111111111', 'archerForzmy', 'group1', 1, '2023-05-03 00:08:56');
INSERT INTO `message` VALUES (61, '1111111111', 'archerForzkx', 'group1', 1, '2023-05-03 00:08:59');
INSERT INTO `message` VALUES (62, '1111111111111111', 'archerForzmy', 'group1', 1, '2023-05-03 00:10:25');
INSERT INTO `message` VALUES (63, '111111111111', 'archerForzkx', 'group1', 1, '2023-05-03 00:10:28');
INSERT INTO `message` VALUES (64, '111111111111111', 'archerForzkx', 'group1', 1, '2023-05-03 00:10:30');
INSERT INTO `message` VALUES (65, '1111111111111111', 'archerForzkx', 'group1', 1, '2023-05-03 00:10:33');
INSERT INTO `message` VALUES (66, '111111111111', 'archerForzkx', 'group1', 1, '2023-05-03 00:10:35');
INSERT INTO `message` VALUES (67, '1111111111111', 'archerForzmy', 'group1', 1, '2023-05-03 00:10:38');
INSERT INTO `message` VALUES (68, '11111111111', 'archerForzkx', 'group1', 1, '2023-05-03 00:12:41');
INSERT INTO `message` VALUES (69, '11111111111111', 'archerForzmy', 'group1', 1, '2023-05-03 00:12:46');
INSERT INTO `message` VALUES (70, '11111111111111', 'archerForzmy', 'group1', 1, '2023-05-03 00:12:50');
INSERT INTO `message` VALUES (71, '1111111111111', 'archerForzkx', 'group1', 1, '2023-05-03 00:35:47');
INSERT INTO `message` VALUES (72, '11111111111111', 'archerForzmy', 'group1', 1, '2023-05-03 00:35:52');
INSERT INTO `message` VALUES (73, '1111111111111111', 'archerForzmy', 'group1', 1, '2023-05-03 00:36:55');
INSERT INTO `message` VALUES (74, '111111111111', 'archerForzkx', 'group1', 1, '2023-05-03 00:37:01');
INSERT INTO `message` VALUES (76, ':grinning:', 'archerForzkx', 'group1', 1, '2023-05-03 09:56:18');
INSERT INTO `message` VALUES (77, ':sweat_smile::sweat_smile::sweat_smile:', 'archerForzkx', 'group1', 1, '2023-05-03 09:56:20');
INSERT INTO `message` VALUES (78, '121212:wink:', 'archerForzkx', 'group1', 1, '2023-05-03 09:56:24');
INSERT INTO `message` VALUES (79, '3123123123123:grinning:', 'archerForzkx', 'group1', 1, '2023-05-03 10:00:22');
INSERT INTO `message` VALUES (80, '2313123123123:blush:', 'archerForzkx', 'group1', 1, '2023-05-03 10:00:29');
INSERT INTO `message` VALUES (81, ':yum::yum:', 'archerForzkx', 'group1', 1, '2023-05-03 10:00:32');
INSERT INTO `message` VALUES (82, ':grinning:121212', 'archerForzkx', 'group1', 1, '2023-05-03 10:03:04');
INSERT INTO `message` VALUES (83, '1222222222222222222:blush:', 'archerForzkx', 'group1', 1, '2023-05-03 10:03:09');
INSERT INTO `message` VALUES (84, '122222222222222222222222222222222222', 'archerForzkx', 'group1', 1, '2023-05-03 10:03:21');
INSERT INTO `message` VALUES (85, ':grinning::grinning::grinning::grinning:', 'archerForzkx', 'group1', 1, '2023-05-03 10:05:24');
INSERT INTO `message` VALUES (86, '112131213123123', 'archerForzkx', 'group1', 1, '2023-05-03 10:05:28');
INSERT INTO `message` VALUES (87, ':wink:123123123', 'archerForzkx', 'group1', 1, '2023-05-03 10:05:32');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (8, 'archerForzmy', '111111', '18720520869', '1835060443@qq.com');
INSERT INTO `user` VALUES (9, 'archerForzkx', '111111', '18720520869', '1835060443@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
