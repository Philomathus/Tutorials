/*
 Navicat Premium Data Transfer

  Source Server         : db
 Source Server Type    : MySQL
  Source Server Version : 80022
 Source Host           : localhost:3306
  Source Schema         : db_silverscent_ordering_system

    Target Server Version : 80022
 File Encoding         : 65001
 
 Date: 16/11/2022 17:33:14
 */

 SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
 
-- ----------------------------
 -- Table structure for customers
-- ----------------------------
 DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`  (
  `customer_id` int NOT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `first_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `middle_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `mobile_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;
	
-- ----------------------------
	-- ----------------------------
INSERT INTO `customers` VALUES (1, 'Rahim', 'Kei', 'Liyu', '+63969999666', 'keimar@qq.com');
	INSERT INTO `customers` VALUES (2, 'Lee', 'John', 'Ris', '+63232323232', 'jri@qq.com');

	-- ----------------------------
-- Table structure for inventory
	-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
	CREATE TABLE `inventory`  (
  `id` int NOT NULL AUTO_INCREMENT,
    `item_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
    ) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

	-- ----------------------------
-- ----------------------------
INSERT INTO `inventory` VALUES (1, 'sspf_wa_b', 50);
INSERT INTO `inventory` VALUES (2, 'sspf_wa_s', 100);
INSERT INTO `inventory` VALUES (3, 'sspf_wn_b', 50);
INSERT INTO `inventory` VALUES (4, 'sspf_wn_s', 100);
INSERT INTO `inventory` VALUES (5, 'sspf_m_b', 50);
INSERT INTO `inventory` VALUES (6, 'sspf_m_s', 100);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` int NULL DEFAULT NULL,
    `item_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `size` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `quantity` int NULL DEFAULT NULL,
  `cost` int NULL DEFAULT NULL,
    `order_date` datetime NULL DEFAULT NULL,
  `is_delivered` int NULL DEFAULT 0,
    `date_delivered` datetime NULL DEFAULT NULL,
  ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

  -- ----------------------------
-- Records of orders
  -- ----------------------------
INSERT INTO `orders` VALUES (1, 2, 'sspf_wa_b', '250ml', 2, 500, '2022-11-16 17:30:12', 0, NULL);
  
-- ----------------------------
  -- Table structure for perfume_variants
-- ----------------------------
  DROP TABLE IF EXISTS `perfume_variants`;
CREATE TABLE `perfume_variants`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `item_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `fragrance` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `size` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `price` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `item_code`(`item_code`) USING BTREE,
  INDEX `fragrance`(`fragrance`) USING BTREE,
    ) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

	-- ----------------------------
-- ----------------------------
INSERT INTO `perfume_variants` VALUES (1, 'sspf_wa_b', 'whisky angel', '250ml', 250);
INSERT INTO `perfume_variants` VALUES (2, 'sspf_wa_s', 'whisky angel', '100ml', 100);
INSERT INTO `perfume_variants` VALUES (3, 'sspf_wn_b', 'white nectarine', '250ml', 250);
INSERT INTO `perfume_variants` VALUES (4, 'sspf_wn_s', 'white nectarine', '100ml', 100);
INSERT INTO `perfume_variants` VALUES (5, 'sspf_m_b', 'marrakesh', '250ml', 250);
INSERT INTO `perfume_variants` VALUES (6, 'sspf_m_s', 'marrakesh', '100ml', 100);

SET FOREIGN_KEY_CHECKS = 1;
-- Records of perfume_variants	INDEX `size`(`size`) USING BTREE)  PRIMARY KEY (`order_id`) USING BTREE	-- Records of inventory	PRIMARY KEY (`id`) USING BTREE-- Records of customers) Target Server Type    : MySQL