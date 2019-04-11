/*
Navicat MySQL Data Transfer

Source Server         : 39.96.211.124
Source Server Version : 50725
Source Host           : 39.96.211.124:3306
Source Database       : sms

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-04-11 11:06:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for branch_school
-- ----------------------------
DROP TABLE IF EXISTS `branch_school`;
CREATE TABLE `branch_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `school_id` int(11) NOT NULL,
  `address` varchar(64) DEFAULT NULL,
  `telephone_number` varchar(16) DEFAULT NULL,
  `monitor_open_days_of_week` varchar(256) DEFAULT '["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"]',
  `monitor_start_time` time DEFAULT '09:00:00',
  `monitor_end_time` time DEFAULT '17:00:00',
  PRIMARY KEY (`id`),
  KEY `index_branch_school_id` (`id`) USING BTREE,
  KEY `fk_branch_school_to_school_id` (`school_id`),
  CONSTRAINT `fk_branch_school_to_school_id` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `description` varchar(128) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `photo_url` varchar(128) DEFAULT NULL,
  `management_status_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `index_course_id` (`id`) USING BTREE,
  KEY `fk_course_to_user_id` (`user_id`),
  KEY `fk_course_to_subject_id` (`subject_id`),
  KEY `fk_course_to_management_status_dictionary_id` (`management_status_id`),
  CONSTRAINT `fk_course_to_management_status_dictionary_id` FOREIGN KEY (`management_status_id`) REFERENCES `management_status_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_course_to_subject_id` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_course_to_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for courseware
-- ----------------------------
DROP TABLE IF EXISTS `courseware`;
CREATE TABLE `courseware` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `description` varchar(128) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `photo_url` varchar(128) DEFAULT NULL,
  `is_combo_type` tinyint(4) DEFAULT '0',
  `audio_url` varchar(128) DEFAULT NULL,
  `video_url` varchar(128) DEFAULT NULL,
  `combo_data` json DEFAULT NULL,
  `is_displayed_on_subject_page` tinyint(4) DEFAULT '0',
  `is_displayed_on_main_page` tinyint(4) DEFAULT '0',
  `management_status_id` int(11) DEFAULT '0',
  `created_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_courseware_id` (`id`) USING BTREE,
  KEY `index_courseware_published_and_displayed_on_main_page` (`is_displayed_on_main_page`,`management_status_id`),
  KEY `index_courseware_published_and_displayed_on_subject_page` (`is_displayed_on_subject_page`,`management_status_id`,`course_id`),
  KEY `fk_courseware_to_course_id` (`course_id`),
  KEY `fk_courseware_to_management_status_dictionary_id` (`management_status_id`),
  CONSTRAINT `fk_courseware_to_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_courseware_to_management_status_dictionary_id` FOREIGN KEY (`management_status_id`) REFERENCES `management_status_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for fee
-- ----------------------------
DROP TABLE IF EXISTS `fee`;
CREATE TABLE `fee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `fee_type_id` int(11) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `school_id` int(11) DEFAULT NULL,
  `branch_school_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `management_status_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE,
  KEY `fk_fee_to_fee_type_id` (`fee_type_id`),
  KEY `fk_fee_to_course_id` (`course_id`),
  KEY `fk_fee_to_school_id` (`school_id`),
  KEY `fk_fee_to_branch_school_id` (`branch_school_id`),
  KEY `fk_fee_to_group_id` (`group_id`),
  KEY `fk_fee_to_member_id` (`member_id`),
  KEY `fk_fee_to_management_status_dictionary_id` (`management_status_id`),
  CONSTRAINT `fk_fee_to_branch_school_id` FOREIGN KEY (`branch_school_id`) REFERENCES `branch_school` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fee_to_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fee_to_fee_type_id` FOREIGN KEY (`fee_type_id`) REFERENCES `fee_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fee_to_group_id` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fee_to_management_status_dictionary_id` FOREIGN KEY (`management_status_id`) REFERENCES `management_status_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fee_to_member_id` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fee_to_school_id` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for fee_type
-- ----------------------------
DROP TABLE IF EXISTS `fee_type`;
CREATE TABLE `fee_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `branch_school_id` int(11) NOT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_group_id` (`id`) USING BTREE,
  KEY `fk_group_to_branch_school_id` (`branch_school_id`),
  CONSTRAINT `fk_group_to_branch_school_id` FOREIGN KEY (`branch_school_id`) REFERENCES `branch_school` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for information
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `information_type_id` int(11) NOT NULL,
  `information_subtype_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `created_time` datetime NOT NULL,
  `content` text,
  `photo_url` varchar(128) DEFAULT NULL,
  `school_id` int(11) DEFAULT NULL,
  `branch_school_id` int(11) DEFAULT NULL,
  `management_status_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE,
  KEY `index_information_type_and_created_time` (`information_type_id`,`created_time`),
  KEY `fk_information_to_information_subtype_dictionary_id` (`information_subtype_id`),
  KEY `fk_information_to_user_id` (`user_id`),
  KEY `fk_information_to_school_id` (`school_id`),
  KEY `fk_information_to_branch_school_id` (`branch_school_id`),
  KEY `fk_information_to_management_status_dictionary_id` (`management_status_id`),
  CONSTRAINT `fk_information_to_branch_school_id` FOREIGN KEY (`branch_school_id`) REFERENCES `branch_school` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_information_to_management_status_dictionary_id` FOREIGN KEY (`management_status_id`) REFERENCES `management_status_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_information_to_school_id` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_information_to_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for information_subtype_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `information_subtype_dictionary`;
CREATE TABLE `information_subtype_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=405 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for information_type_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `information_type_dictionary`;
CREATE TABLE `information_type_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for management_status_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `management_status_dictionary`;
CREATE TABLE `management_status_dictionary` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_name` varchar(128) NOT NULL,
  `log_password` varchar(128) NOT NULL,
  `telephone_number` varchar(16) NOT NULL,
  `name` varchar(128) NOT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `gender` tinyint(4) NOT NULL,
  `birthday` date NOT NULL,
  `ethnicity` varchar(128) NOT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `home_address` varchar(128) DEFAULT NULL,
  `father_name` varchar(128) DEFAULT NULL,
  `father_telephone_number` varchar(16) DEFAULT NULL,
  `mother_name` varchar(128) DEFAULT NULL,
  `mother_telephone_number` varchar(16) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `management_status_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `index_member_id` (`id`) USING BTREE,
  KEY `fk_member_to_group_id` (`group_id`),
  KEY `fk_member_to_management_status_dictionary_id` (`management_status_id`),
  CONSTRAINT `fk_member_to_group_id` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_member_to_management_status_dictionary_id` FOREIGN KEY (`management_status_id`) REFERENCES `management_status_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for monitor
-- ----------------------------
DROP TABLE IF EXISTS `monitor`;
CREATE TABLE `monitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `group_id` int(11) NOT NULL,
  `camera_address` varchar(128) NOT NULL,
  `photo_url` varchar(128) DEFAULT NULL,
  `install_date` date DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_monitor_id` (`id`) USING BTREE,
  KEY `fk_monitor_to_group_id` (`group_id`),
  CONSTRAINT `fk_monitor_to_group_id` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `fee_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE,
  KEY `fk_payment_to_member_id` (`member_id`),
  KEY `fk_payment_to_fee_id` (`fee_id`),
  CONSTRAINT `fk_payment_to_fee_id` FOREIGN KEY (`fee_id`) REFERENCES `fee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_to_member_id` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for role_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `role_dictionary`;
CREATE TABLE `role_dictionary` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `telephone_number` varchar(16) DEFAULT NULL,
  `school_type_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `index_school_id` (`id`) USING BTREE,
  KEY `fk_school_to_school_type_dictionary_id` (`school_type_id`),
  CONSTRAINT `fk_school_to_school_type_dictionary_id` FOREIGN KEY (`school_type_id`) REFERENCES `school_type_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for school_information
-- ----------------------------
DROP TABLE IF EXISTS `school_information`;
CREATE TABLE `school_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `school_id` int(11) DEFAULT NULL,
  `information_type_id` int(11) NOT NULL,
  `information_subtype_id` int(11) DEFAULT NULL,
  `title` varchar(128) NOT NULL,
  `introduction` varchar(256) DEFAULT NULL,
  `photo_url` varchar(128) DEFAULT NULL,
  `content` text,
  `created_time` datetime(3) NOT NULL,
  `management_status_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE,
  KEY `index_school_info_info_type_and_created_time` (`information_type_id`,`created_time`),
  KEY `index_school_info_created_time` (`created_time`),
  KEY `fk_school_info_to_info_subtype_dictionary_id` (`information_subtype_id`),
  KEY `fk_school_info_to_school_id` (`school_id`),
  KEY `fk_school_info_to_management_status_dictionary_id` (`management_status_id`),
  CONSTRAINT `fk_school_info_to_management_status_dictionary_id` FOREIGN KEY (`management_status_id`) REFERENCES `management_status_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_school_info_to_school_id` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for school_type_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `school_type_dictionary`;
CREATE TABLE `school_type_dictionary` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for student_report
-- ----------------------------
DROP TABLE IF EXISTS `student_report`;
CREATE TABLE `student_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `title` varchar(128) DEFAULT NULL,
  `report_type_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_time` datetime NOT NULL,
  `content_file_path` varchar(128) DEFAULT NULL,
  `photo_url` varchar(128) DEFAULT NULL,
  `management_status_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE,
  KEY `index_report_type_and_created_time` (`report_type_id`,`created_time`),
  KEY `fk_student_report_to_member_id` (`member_id`),
  KEY `fk_student_report_to_user_id` (`user_id`),
  KEY `fk_student_report_to_management_status_dictionary_id` (`management_status_id`),
  CONSTRAINT `fk_student_report_to_istudent_report_type_dictionary_id` FOREIGN KEY (`report_type_id`) REFERENCES `student_report_type_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_report_to_management_status_dictionary_id` FOREIGN KEY (`management_status_id`) REFERENCES `management_status_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_report_to_member_id` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_report_to_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for student_report_type_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `student_report_type_dictionary`;
CREATE TABLE `student_report_type_dictionary` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `school_id` int(11) NOT NULL,
  `management_status_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `index_subject_id` (`id`) USING BTREE,
  KEY `fk_subject_to_school_id` (`school_id`),
  KEY `fk_subject_to_management_status_dictionary_id` (`management_status_id`),
  CONSTRAINT `fk_subject_to_management_status_dictionary_id` FOREIGN KEY (`management_status_id`) REFERENCES `management_status_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_subject_to_school_id` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `telephone_number` varchar(16) DEFAULT NULL,
  `branch_school_id` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `index_teach_id` (`id`) USING BTREE,
  KEY `fk_teacher_to_branch_school_id` (`branch_school_id`),
  CONSTRAINT `fk_teacher_to_branch_school_id` FOREIGN KEY (`branch_school_id`) REFERENCES `branch_school` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for upgrade_history
-- ----------------------------
DROP TABLE IF EXISTS `upgrade_history`;
CREATE TABLE `upgrade_history` (
  `upgrade_script` varchar(64) NOT NULL,
  `upgrade_time` datetime DEFAULT NULL,
  PRIMARY KEY (`upgrade_script`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_name` varchar(128) NOT NULL,
  `log_password` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `telephone_number` varchar(16) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for user_role_association
-- ----------------------------
DROP TABLE IF EXISTS `user_role_association`;
CREATE TABLE `user_role_association` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `school_id` int(11) DEFAULT NULL,
  `branch_school_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  KEY `index_user_id` (`user_id`) USING BTREE,
  KEY `fk_school_group_association_to_school_id` (`school_id`),
  KEY `fk_school_group_association_to_branch_school_id` (`branch_school_id`),
  KEY `fk_school_group_association_to_group_id` (`group_id`),
  KEY `fk_user_role_association_to_role_dictionary_id` (`role_id`),
  CONSTRAINT `fk_school_group_association_to_branch_school_id` FOREIGN KEY (`branch_school_id`) REFERENCES `branch_school` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_school_group_association_to_group_id` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_school_group_association_to_school_id` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_school_group_association_to_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_association_to_role_dictionary_id` FOREIGN KEY (`role_id`) REFERENCES `role_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
