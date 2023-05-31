/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 5.6.12-log : Database - livingart
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`livingart` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `livingart`;

/*Table structure for table `auth_group` */

DROP TABLE IF EXISTS `auth_group`;

CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `auth_group` */

/*Table structure for table `auth_group_permissions` */

DROP TABLE IF EXISTS `auth_group_permissions`;

CREATE TABLE `auth_group_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `auth_group_permissions` */

/*Table structure for table `auth_permission` */

DROP TABLE IF EXISTS `auth_permission`;

CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8mb4;

/*Data for the table `auth_permission` */

insert  into `auth_permission`(`id`,`name`,`content_type_id`,`codename`) values 
(1,'Can add log entry',1,'add_logentry'),
(2,'Can change log entry',1,'change_logentry'),
(3,'Can delete log entry',1,'delete_logentry'),
(4,'Can view log entry',1,'view_logentry'),
(5,'Can add permission',2,'add_permission'),
(6,'Can change permission',2,'change_permission'),
(7,'Can delete permission',2,'delete_permission'),
(8,'Can view permission',2,'view_permission'),
(9,'Can add group',3,'add_group'),
(10,'Can change group',3,'change_group'),
(11,'Can delete group',3,'delete_group'),
(12,'Can view group',3,'view_group'),
(13,'Can add user',4,'add_user'),
(14,'Can change user',4,'change_user'),
(15,'Can delete user',4,'delete_user'),
(16,'Can view user',4,'view_user'),
(17,'Can add content type',5,'add_contenttype'),
(18,'Can change content type',5,'change_contenttype'),
(19,'Can delete content type',5,'delete_contenttype'),
(20,'Can view content type',5,'view_contenttype'),
(21,'Can add session',6,'add_session'),
(22,'Can change session',6,'change_session'),
(23,'Can delete session',6,'delete_session'),
(24,'Can view session',6,'view_session'),
(25,'Can add batch',7,'add_batch'),
(26,'Can change batch',7,'change_batch'),
(27,'Can delete batch',7,'delete_batch'),
(28,'Can view batch',7,'view_batch'),
(29,'Can add category',8,'add_category'),
(30,'Can change category',8,'change_category'),
(31,'Can delete category',8,'delete_category'),
(32,'Can view category',8,'view_category'),
(33,'Can add course',9,'add_course'),
(34,'Can change course',9,'change_course'),
(35,'Can delete course',9,'delete_course'),
(36,'Can view course',9,'view_course'),
(37,'Can add customer',10,'add_customer'),
(38,'Can change customer',10,'change_customer'),
(39,'Can delete customer',10,'delete_customer'),
(40,'Can view customer',10,'view_customer'),
(41,'Can add institute',11,'add_institute'),
(42,'Can change institute',11,'change_institute'),
(43,'Can delete institute',11,'delete_institute'),
(44,'Can view institute',11,'view_institute'),
(45,'Can add instrument',12,'add_instrument'),
(46,'Can change instrument',12,'change_instrument'),
(47,'Can delete instrument',12,'delete_instrument'),
(48,'Can view instrument',12,'view_instrument'),
(49,'Can add login',13,'add_login'),
(50,'Can change login',13,'change_login'),
(51,'Can delete login',13,'delete_login'),
(52,'Can view login',13,'view_login'),
(53,'Can add material',14,'add_material'),
(54,'Can change material',14,'change_material'),
(55,'Can delete material',14,'delete_material'),
(56,'Can view material',14,'view_material'),
(57,'Can add payment',15,'add_payment'),
(58,'Can change payment',15,'change_payment'),
(59,'Can delete payment',15,'delete_payment'),
(60,'Can view payment',15,'view_payment'),
(61,'Can add return_entry',16,'add_return_entry'),
(62,'Can change return_entry',16,'change_return_entry'),
(63,'Can delete return_entry',16,'delete_return_entry'),
(64,'Can view return_entry',16,'view_return_entry'),
(65,'Can add shop',17,'add_shop'),
(66,'Can change shop',17,'change_shop'),
(67,'Can delete shop',17,'delete_shop'),
(68,'Can view shop',17,'view_shop'),
(69,'Can add user',18,'add_user'),
(70,'Can change user',18,'change_user'),
(71,'Can delete user',18,'delete_user'),
(72,'Can view user',18,'view_user'),
(73,'Can add student',19,'add_student'),
(74,'Can change student',19,'change_student'),
(75,'Can delete student',19,'delete_student'),
(76,'Can view student',19,'view_student'),
(77,'Can add std_complaint',20,'add_std_complaint'),
(78,'Can change std_complaint',20,'change_std_complaint'),
(79,'Can delete std_complaint',20,'delete_std_complaint'),
(80,'Can view std_complaint',20,'view_std_complaint'),
(81,'Can add shop_gallery',21,'add_shop_gallery'),
(82,'Can change shop_gallery',21,'change_shop_gallery'),
(83,'Can delete shop_gallery',21,'delete_shop_gallery'),
(84,'Can view shop_gallery',21,'view_shop_gallery'),
(85,'Can add schedule',22,'add_schedule'),
(86,'Can change schedule',22,'change_schedule'),
(87,'Can delete schedule',22,'delete_schedule'),
(88,'Can view schedule',22,'view_schedule'),
(89,'Can add review',23,'add_review'),
(90,'Can change review',23,'change_review'),
(91,'Can delete review',23,'delete_review'),
(92,'Can view review',23,'view_review'),
(93,'Can add requst',24,'add_requst'),
(94,'Can change requst',24,'change_requst'),
(95,'Can delete requst',24,'delete_requst'),
(96,'Can view requst',24,'view_requst'),
(97,'Can add rent',25,'add_rent'),
(98,'Can change rent',25,'change_rent'),
(99,'Can delete rent',25,'delete_rent'),
(100,'Can view rent',25,'view_rent'),
(101,'Can add insti_gallery',26,'add_insti_gallery'),
(102,'Can change insti_gallery',26,'change_insti_gallery'),
(103,'Can delete insti_gallery',26,'delete_insti_gallery'),
(104,'Can view insti_gallery',26,'view_insti_gallery'),
(105,'Can add coursebooking',27,'add_coursebooking'),
(106,'Can change coursebooking',27,'change_coursebooking'),
(107,'Can delete coursebooking',27,'delete_coursebooking'),
(108,'Can view coursebooking',27,'view_coursebooking'),
(109,'Can add course_requst',28,'add_course_requst'),
(110,'Can change course_requst',28,'change_course_requst'),
(111,'Can delete course_requst',28,'delete_course_requst'),
(112,'Can view course_requst',28,'view_course_requst'),
(113,'Can add complaint',29,'add_complaint'),
(114,'Can change complaint',29,'change_complaint'),
(115,'Can delete complaint',29,'delete_complaint'),
(116,'Can view complaint',29,'view_complaint'),
(117,'Can add review_st',30,'add_review_st'),
(118,'Can change review_st',30,'change_review_st'),
(119,'Can delete review_st',30,'delete_review_st'),
(120,'Can view review_st',30,'view_review_st'),
(121,'Can add shopreview',31,'add_shopreview'),
(122,'Can change shopreview',31,'change_shopreview'),
(123,'Can delete shopreview',31,'delete_shopreview'),
(124,'Can view shopreview',31,'view_shopreview'),
(125,'Can add instireview',32,'add_instireview'),
(126,'Can change instireview',32,'change_instireview'),
(127,'Can delete instireview',32,'delete_instireview'),
(128,'Can view instireview',32,'view_instireview');

/*Table structure for table `auth_user` */

DROP TABLE IF EXISTS `auth_user`;

CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `auth_user` */

/*Table structure for table `auth_user_groups` */

DROP TABLE IF EXISTS `auth_user_groups`;

CREATE TABLE `auth_user_groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_group_id_97559544_fk_auth_group_id` (`group_id`),
  CONSTRAINT `auth_user_groups_group_id_97559544_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `auth_user_groups_user_id_6a12ed8b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `auth_user_groups` */

/*Table structure for table `auth_user_user_permissions` */

DROP TABLE IF EXISTS `auth_user_user_permissions`;

CREATE TABLE `auth_user_user_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `auth_user_user_permissions` */

/*Table structure for table `django_admin_log` */

DROP TABLE IF EXISTS `django_admin_log`;

CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6_fk_auth_user_id` (`user_id`),
  CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `django_admin_log_user_id_c564eba6_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `django_admin_log` */

/*Table structure for table `django_content_type` */

DROP TABLE IF EXISTS `django_content_type`;

CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;

/*Data for the table `django_content_type` */

insert  into `django_content_type`(`id`,`app_label`,`model`) values 
(1,'admin','logentry'),
(3,'auth','group'),
(2,'auth','permission'),
(4,'auth','user'),
(5,'contenttypes','contenttype'),
(7,'myapp','batch'),
(8,'myapp','category'),
(29,'myapp','complaint'),
(9,'myapp','course'),
(27,'myapp','coursebooking'),
(28,'myapp','course_requst'),
(10,'myapp','customer'),
(32,'myapp','instireview'),
(11,'myapp','institute'),
(26,'myapp','insti_gallery'),
(12,'myapp','instrument'),
(13,'myapp','login'),
(14,'myapp','material'),
(15,'myapp','payment'),
(25,'myapp','rent'),
(24,'myapp','requst'),
(16,'myapp','return_entry'),
(23,'myapp','review'),
(30,'myapp','review_st'),
(22,'myapp','schedule'),
(17,'myapp','shop'),
(31,'myapp','shopreview'),
(21,'myapp','shop_gallery'),
(20,'myapp','std_complaint'),
(19,'myapp','student'),
(18,'myapp','user'),
(6,'sessions','session');

/*Table structure for table `django_migrations` */

DROP TABLE IF EXISTS `django_migrations`;

CREATE TABLE `django_migrations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

/*Data for the table `django_migrations` */

insert  into `django_migrations`(`id`,`app`,`name`,`applied`) values 
(1,'contenttypes','0001_initial','2023-04-26 04:00:09.072865'),
(2,'auth','0001_initial','2023-04-26 04:00:09.978901'),
(3,'admin','0001_initial','2023-04-26 04:00:10.181978'),
(4,'admin','0002_logentry_remove_auto_add','2023-04-26 04:00:10.197600'),
(5,'admin','0003_logentry_add_action_flag_choices','2023-04-26 04:00:10.213222'),
(6,'contenttypes','0002_remove_content_type_name','2023-04-26 04:00:10.306949'),
(7,'auth','0002_alter_permission_name_max_length','2023-04-26 04:00:10.338192'),
(8,'auth','0003_alter_user_email_max_length','2023-04-26 04:00:10.400678'),
(9,'auth','0004_alter_user_username_opts','2023-04-26 04:00:10.400678'),
(10,'auth','0005_alter_user_last_login_null','2023-04-26 04:00:10.447543'),
(11,'auth','0006_require_contenttypes_0002','2023-04-26 04:00:10.447543'),
(12,'auth','0007_alter_validators_add_error_messages','2023-04-26 04:00:10.463163'),
(13,'auth','0008_alter_user_username_max_length','2023-04-26 04:00:10.510026'),
(14,'auth','0009_alter_user_last_name_max_length','2023-04-26 04:00:10.556890'),
(15,'auth','0010_alter_group_name_max_length','2023-04-26 04:00:10.603754'),
(16,'auth','0011_update_proxy_permissions','2023-04-26 04:00:10.619376'),
(17,'auth','0012_alter_user_first_name_max_length','2023-04-26 04:00:10.666239'),
(18,'myapp','0001_initial','2023-04-26 04:00:14.040444'),
(19,'myapp','0002_course_course_image','2023-04-26 04:00:14.102929'),
(20,'myapp','0003_remove_course_course_image','2023-04-26 04:00:14.149793'),
(21,'myapp','0004_remove_batch_schedule','2023-04-26 04:00:14.243521'),
(22,'sessions','0001_initial','2023-04-26 04:00:14.384113'),
(23,'myapp','0005_auto_20230426_0950','2023-04-26 04:20:36.012930'),
(24,'myapp','0006_shopreview','2023-04-26 09:23:59.424814'),
(25,'myapp','0007_auto_20230427_1104','2023-04-27 05:34:52.637168');

/*Table structure for table `django_session` */

DROP TABLE IF EXISTS `django_session`;

CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `django_session` */

insert  into `django_session`(`session_key`,`session_data`,`expire_date`) values 
('h3lsnfb67ri1pl6v5mnvxq7f2gv44462','eyJsaWQiOjF9:1psFEp:ZICtYVrzGSqqqyn8olS6lBjb-rCqmkJmfV1wcVChVf4','2023-05-12 03:57:39.233836');

/*Table structure for table `myapp_batch` */

DROP TABLE IF EXISTS `myapp_batch`;

CREATE TABLE `myapp_batch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `batch_name` varchar(30) NOT NULL,
  `no_of_seats` varchar(10) NOT NULL,
  `date` varchar(20) NOT NULL,
  `time1` varchar(20) NOT NULL,
  `time2` varchar(20) NOT NULL,
  `COURSE_id` bigint(20) NOT NULL,
  `INSTITUTE_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_batch_COURSE_id_4f1df621_fk_myapp_course_id` (`COURSE_id`),
  KEY `myapp_batch_INSTITUTE_id_b9331875_fk_myapp_institute_id` (`INSTITUTE_id`),
  CONSTRAINT `myapp_batch_COURSE_id_4f1df621_fk_myapp_course_id` FOREIGN KEY (`COURSE_id`) REFERENCES `myapp_course` (`id`),
  CONSTRAINT `myapp_batch_INSTITUTE_id_b9331875_fk_myapp_institute_id` FOREIGN KEY (`INSTITUTE_id`) REFERENCES `myapp_institute` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_batch` */

/*Table structure for table `myapp_category` */

DROP TABLE IF EXISTS `myapp_category`;

CREATE TABLE `myapp_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_category` */

insert  into `myapp_category`(`id`,`category_name`) values 
(1,'musical instruments'),
(2,'drawings'),
(3,'writings');

/*Table structure for table `myapp_complaint` */

DROP TABLE IF EXISTS `myapp_complaint`;

CREATE TABLE `myapp_complaint` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(10) NOT NULL,
  `compalint` varchar(100) NOT NULL,
  `reply` varchar(100) NOT NULL,
  `LOGIN_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_complaint_LOGIN_id_57f588ca_fk_myapp_login_id` (`LOGIN_id`),
  CONSTRAINT `myapp_complaint_LOGIN_id_57f588ca_fk_myapp_login_id` FOREIGN KEY (`LOGIN_id`) REFERENCES `myapp_login` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_complaint` */

/*Table structure for table `myapp_course` */

DROP TABLE IF EXISTS `myapp_course`;

CREATE TABLE `myapp_course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(50) NOT NULL,
  `course_fee` varchar(30) NOT NULL,
  `duration` varchar(20) NOT NULL,
  `available_seats` varchar(10) NOT NULL,
  `INSTITUTE_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_course_INSTITUTE_id_4a5ef796_fk_myapp_institute_id` (`INSTITUTE_id`),
  CONSTRAINT `myapp_course_INSTITUTE_id_4a5ef796_fk_myapp_institute_id` FOREIGN KEY (`INSTITUTE_id`) REFERENCES `myapp_institute` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_course` */

/*Table structure for table `myapp_course_requst` */

DROP TABLE IF EXISTS `myapp_course_requst`;

CREATE TABLE `myapp_course_requst` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `requ` varchar(200) NOT NULL,
  `date` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `STUDENT_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_course_requst_STUDENT_id_47c0a1b7_fk_myapp_student_id` (`STUDENT_id`),
  CONSTRAINT `myapp_course_requst_STUDENT_id_47c0a1b7_fk_myapp_student_id` FOREIGN KEY (`STUDENT_id`) REFERENCES `myapp_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_course_requst` */

/*Table structure for table `myapp_coursebooking` */

DROP TABLE IF EXISTS `myapp_coursebooking`;

CREATE TABLE `myapp_coursebooking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `booking_date` varchar(20) NOT NULL,
  `payment_type` varchar(20) NOT NULL,
  `COURSE_id` bigint(20) NOT NULL,
  `PAYMENT_id` bigint(20) NOT NULL,
  `STUDENT_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_coursebooking_COURSE_id_783e3ecc_fk_myapp_course_id` (`COURSE_id`),
  KEY `myapp_coursebooking_PAYMENT_id_9cb85c05_fk_myapp_payment_id` (`PAYMENT_id`),
  KEY `myapp_coursebooking_STUDENT_id_392584b7_fk_myapp_student_id` (`STUDENT_id`),
  CONSTRAINT `myapp_coursebooking_COURSE_id_783e3ecc_fk_myapp_course_id` FOREIGN KEY (`COURSE_id`) REFERENCES `myapp_course` (`id`),
  CONSTRAINT `myapp_coursebooking_PAYMENT_id_9cb85c05_fk_myapp_payment_id` FOREIGN KEY (`PAYMENT_id`) REFERENCES `myapp_payment` (`id`),
  CONSTRAINT `myapp_coursebooking_STUDENT_id_392584b7_fk_myapp_student_id` FOREIGN KEY (`STUDENT_id`) REFERENCES `myapp_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_coursebooking` */

/*Table structure for table `myapp_customer` */

DROP TABLE IF EXISTS `myapp_customer`;

CREATE TABLE `myapp_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `house_name` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `district` varchar(50) NOT NULL,
  `pin` varchar(10) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `LOGIN_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_customer_LOGIN_id_23d9edf9_fk_myapp_login_id` (`LOGIN_id`),
  CONSTRAINT `myapp_customer_LOGIN_id_23d9edf9_fk_myapp_login_id` FOREIGN KEY (`LOGIN_id`) REFERENCES `myapp_login` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_customer` */

/*Table structure for table `myapp_insti_gallery` */

DROP TABLE IF EXISTS `myapp_insti_gallery`;

CREATE TABLE `myapp_insti_gallery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image1` varchar(500) NOT NULL,
  `image2` varchar(500) NOT NULL,
  `image3` varchar(500) NOT NULL,
  `INSTITUTE_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_insti_gallery_INSTITUTE_id_2bff230f_fk_myapp_institute_id` (`INSTITUTE_id`),
  CONSTRAINT `myapp_insti_gallery_INSTITUTE_id_2bff230f_fk_myapp_institute_id` FOREIGN KEY (`INSTITUTE_id`) REFERENCES `myapp_institute` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_insti_gallery` */

/*Table structure for table `myapp_instireview` */

DROP TABLE IF EXISTS `myapp_instireview`;

CREATE TABLE `myapp_instireview` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `review` varchar(100) NOT NULL,
  `INSTITUTE_id` bigint(20) NOT NULL,
  `USER_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_instireview_INSTITUTE_id_ec0257e4_fk_myapp_institute_id` (`INSTITUTE_id`),
  KEY `myapp_instireview_USER_id_590b6430_fk_myapp_user_id` (`USER_id`),
  CONSTRAINT `myapp_instireview_INSTITUTE_id_ec0257e4_fk_myapp_institute_id` FOREIGN KEY (`INSTITUTE_id`) REFERENCES `myapp_institute` (`id`),
  CONSTRAINT `myapp_instireview_USER_id_590b6430_fk_myapp_user_id` FOREIGN KEY (`USER_id`) REFERENCES `myapp_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_instireview` */

/*Table structure for table `myapp_institute` */

DROP TABLE IF EXISTS `myapp_institute`;

CREATE TABLE `myapp_institute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `city` varchar(50) NOT NULL,
  `district` varchar(50) NOT NULL,
  `pin` varchar(10) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `image` varchar(200) NOT NULL,
  `status` varchar(10) NOT NULL,
  `LOGIN_id` bigint(20) NOT NULL,
  `manager` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_institute_LOGIN_id_a1f5cbf9_fk_myapp_login_id` (`LOGIN_id`),
  CONSTRAINT `myapp_institute_LOGIN_id_a1f5cbf9_fk_myapp_login_id` FOREIGN KEY (`LOGIN_id`) REFERENCES `myapp_login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_institute` */

insert  into `myapp_institute`(`id`,`name`,`email`,`city`,`district`,`pin`,`phone`,`image`,`status`,`LOGIN_id`,`manager`) values 
(1,'kala keli','kala@gmail.com','mavoor','kozhikode','678935','97564421','/media/Photo1102454-27042023.jpg','approved',3,'1'),
(2,'kalaravm','kalaravm@gmail.com','vadakara','kozhikode','678953','887346842','/media/Photo1111856-27042023.jpg','pending',4,'ragavan');

/*Table structure for table `myapp_instrument` */

DROP TABLE IF EXISTS `myapp_instrument`;

CREATE TABLE `myapp_instrument` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `rent_amount` varchar(30) NOT NULL,
  `desc` varchar(50) NOT NULL,
  `imageinstru` varchar(200) NOT NULL,
  `CATEGORY_id` bigint(20) NOT NULL,
  `SHOP_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_instrument_SHOP_id_802ff4a9_fk_myapp_shop_id` (`SHOP_id`),
  KEY `myapp_instrument_CATEGORY_id_78a120b6_fk_myapp_category_id` (`CATEGORY_id`),
  CONSTRAINT `myapp_instrument_CATEGORY_id_78a120b6_fk_myapp_category_id` FOREIGN KEY (`CATEGORY_id`) REFERENCES `myapp_category` (`id`),
  CONSTRAINT `myapp_instrument_SHOP_id_802ff4a9_fk_myapp_shop_id` FOREIGN KEY (`SHOP_id`) REFERENCES `myapp_shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_instrument` */

/*Table structure for table `myapp_login` */

DROP TABLE IF EXISTS `myapp_login`;

CREATE TABLE `myapp_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `type` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_login` */

insert  into `myapp_login`(`id`,`username`,`password`,`type`) values 
(1,'a@gmail.com','123','shop'),
(2,'admin','123','admin'),
(3,'kala@gmail.com','kala','institute'),
(4,'kalaravm@gmail.com','987','institute');

/*Table structure for table `myapp_material` */

DROP TABLE IF EXISTS `myapp_material`;

CREATE TABLE `myapp_material` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `rent_amount` varchar(30) NOT NULL,
  `description` varchar(50) NOT NULL,
  `image` varchar(100) NOT NULL,
  `CATEGORY_id` bigint(20) NOT NULL,
  `SHOP_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_material_SHOP_id_5fc13c19_fk_myapp_shop_id` (`SHOP_id`),
  KEY `myapp_material_CATEGORY_id_f850b3de_fk_myapp_category_id` (`CATEGORY_id`),
  CONSTRAINT `myapp_material_CATEGORY_id_f850b3de_fk_myapp_category_id` FOREIGN KEY (`CATEGORY_id`) REFERENCES `myapp_category` (`id`),
  CONSTRAINT `myapp_material_SHOP_id_5fc13c19_fk_myapp_shop_id` FOREIGN KEY (`SHOP_id`) REFERENCES `myapp_shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_material` */

/*Table structure for table `myapp_payment` */

DROP TABLE IF EXISTS `myapp_payment`;

CREATE TABLE `myapp_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(10) NOT NULL,
  `amount` varchar(20) NOT NULL,
  `payment_type` varchar(20) NOT NULL,
  `CUSTOMER_id` bigint(20) NOT NULL,
  `STUDENT_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_payment_STUDENT_id_85503ad5_fk_myapp_student_id` (`STUDENT_id`),
  KEY `myapp_payment_CUSTOMER_id_1928e5af_fk_myapp_customer_id` (`CUSTOMER_id`),
  CONSTRAINT `myapp_payment_CUSTOMER_id_1928e5af_fk_myapp_customer_id` FOREIGN KEY (`CUSTOMER_id`) REFERENCES `myapp_customer` (`id`),
  CONSTRAINT `myapp_payment_STUDENT_id_85503ad5_fk_myapp_student_id` FOREIGN KEY (`STUDENT_id`) REFERENCES `myapp_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_payment` */

/*Table structure for table `myapp_rent` */

DROP TABLE IF EXISTS `myapp_rent`;

CREATE TABLE `myapp_rent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `issue_date` varchar(20) NOT NULL,
  `return_date` varchar(20) NOT NULL,
  `amount` varchar(20) NOT NULL,
  `INSTRUMENT_id` bigint(20) NOT NULL,
  `MATERIAL_id` bigint(20) NOT NULL,
  `PAYMENT_id` bigint(20) NOT NULL,
  `USER_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_rent_INSTRUMENT_id_8fbbac4a_fk_myapp_instrument_id` (`INSTRUMENT_id`),
  KEY `myapp_rent_MATERIAL_id_391a3a2a_fk_myapp_material_id` (`MATERIAL_id`),
  KEY `myapp_rent_PAYMENT_id_8bb0031f_fk_myapp_payment_id` (`PAYMENT_id`),
  KEY `myapp_rent_USER_id_bd5c154f_fk_myapp_user_id` (`USER_id`),
  CONSTRAINT `myapp_rent_INSTRUMENT_id_8fbbac4a_fk_myapp_instrument_id` FOREIGN KEY (`INSTRUMENT_id`) REFERENCES `myapp_instrument` (`id`),
  CONSTRAINT `myapp_rent_MATERIAL_id_391a3a2a_fk_myapp_material_id` FOREIGN KEY (`MATERIAL_id`) REFERENCES `myapp_material` (`id`),
  CONSTRAINT `myapp_rent_PAYMENT_id_8bb0031f_fk_myapp_payment_id` FOREIGN KEY (`PAYMENT_id`) REFERENCES `myapp_payment` (`id`),
  CONSTRAINT `myapp_rent_USER_id_bd5c154f_fk_myapp_user_id` FOREIGN KEY (`USER_id`) REFERENCES `myapp_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_rent` */

/*Table structure for table `myapp_requst` */

DROP TABLE IF EXISTS `myapp_requst`;

CREATE TABLE `myapp_requst` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `requ` varchar(200) NOT NULL,
  `date` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `CUSTOMER_id` bigint(20) NOT NULL,
  `INSTRUMENT_id` bigint(20) NOT NULL,
  `MATERIAL_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_requst_CUSTOMER_id_572eef06_fk_myapp_customer_id` (`CUSTOMER_id`),
  KEY `myapp_requst_INSTRUMENT_id_e4a76cae_fk_myapp_instrument_id` (`INSTRUMENT_id`),
  KEY `myapp_requst_MATERIAL_id_20ead851_fk_myapp_material_id` (`MATERIAL_id`),
  CONSTRAINT `myapp_requst_CUSTOMER_id_572eef06_fk_myapp_customer_id` FOREIGN KEY (`CUSTOMER_id`) REFERENCES `myapp_customer` (`id`),
  CONSTRAINT `myapp_requst_INSTRUMENT_id_e4a76cae_fk_myapp_instrument_id` FOREIGN KEY (`INSTRUMENT_id`) REFERENCES `myapp_instrument` (`id`),
  CONSTRAINT `myapp_requst_MATERIAL_id_20ead851_fk_myapp_material_id` FOREIGN KEY (`MATERIAL_id`) REFERENCES `myapp_material` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_requst` */

/*Table structure for table `myapp_return_entry` */

DROP TABLE IF EXISTS `myapp_return_entry`;

CREATE TABLE `myapp_return_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(100) NOT NULL,
  `issue_date` varchar(20) NOT NULL,
  `return_date` varchar(20) NOT NULL,
  `amount` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_return_entry` */

/*Table structure for table `myapp_review` */

DROP TABLE IF EXISTS `myapp_review`;

CREATE TABLE `myapp_review` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `review` varchar(100) NOT NULL,
  `USER_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_review_USER_id_0e923f15_fk_myapp_user_id` (`USER_id`),
  CONSTRAINT `myapp_review_USER_id_0e923f15_fk_myapp_user_id` FOREIGN KEY (`USER_id`) REFERENCES `myapp_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_review` */

/*Table structure for table `myapp_review_st` */

DROP TABLE IF EXISTS `myapp_review_st`;

CREATE TABLE `myapp_review_st` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `review` varchar(100) NOT NULL,
  `STUDENT_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_review_st_STUDENT_id_09137c6c_fk_myapp_student_id` (`STUDENT_id`),
  CONSTRAINT `myapp_review_st_STUDENT_id_09137c6c_fk_myapp_student_id` FOREIGN KEY (`STUDENT_id`) REFERENCES `myapp_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_review_st` */

/*Table structure for table `myapp_schedule` */

DROP TABLE IF EXISTS `myapp_schedule`;

CREATE TABLE `myapp_schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(10) NOT NULL,
  `time` varchar(10) NOT NULL,
  `COURSE_id` bigint(20) NOT NULL,
  `INSTITUTE_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_schedule_COURSE_id_edc12277_fk_myapp_course_id` (`COURSE_id`),
  KEY `myapp_schedule_INSTITUTE_id_f92145c3_fk_myapp_institute_id` (`INSTITUTE_id`),
  CONSTRAINT `myapp_schedule_COURSE_id_edc12277_fk_myapp_course_id` FOREIGN KEY (`COURSE_id`) REFERENCES `myapp_course` (`id`),
  CONSTRAINT `myapp_schedule_INSTITUTE_id_f92145c3_fk_myapp_institute_id` FOREIGN KEY (`INSTITUTE_id`) REFERENCES `myapp_institute` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_schedule` */

/*Table structure for table `myapp_shop` */

DROP TABLE IF EXISTS `myapp_shop`;

CREATE TABLE `myapp_shop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(30) NOT NULL,
  `city` varchar(30) NOT NULL,
  `district` varchar(30) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `image` varchar(500) NOT NULL,
  `status` varchar(10) NOT NULL,
  `LOGIN_id` bigint(20) NOT NULL,
  `owner` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_shop_LOGIN_id_0b42e874_fk_myapp_login_id` (`LOGIN_id`),
  CONSTRAINT `myapp_shop_LOGIN_id_0b42e874_fk_myapp_login_id` FOREIGN KEY (`LOGIN_id`) REFERENCES `myapp_login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_shop` */

insert  into `myapp_shop`(`id`,`shop_name`,`city`,`district`,`phone`,`email`,`image`,`status`,`LOGIN_id`,`owner`) values 
(1,'art gallery','vadakara','kozhikode','987654211','a@gmail.com','/media/20230427100447.jpg','approved',1,'1');

/*Table structure for table `myapp_shop_gallery` */

DROP TABLE IF EXISTS `myapp_shop_gallery`;

CREATE TABLE `myapp_shop_gallery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image1` varchar(500) NOT NULL,
  `image2` varchar(500) NOT NULL,
  `image3` varchar(500) NOT NULL,
  `SHOP_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_shop_gallery_SHOP_id_0ada4460_fk_myapp_shop_id` (`SHOP_id`),
  CONSTRAINT `myapp_shop_gallery_SHOP_id_0ada4460_fk_myapp_shop_id` FOREIGN KEY (`SHOP_id`) REFERENCES `myapp_shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_shop_gallery` */

/*Table structure for table `myapp_shopreview` */

DROP TABLE IF EXISTS `myapp_shopreview`;

CREATE TABLE `myapp_shopreview` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `review` varchar(100) NOT NULL,
  `SHOP_id` bigint(20) NOT NULL,
  `USER_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_shopreview_SHOP_id_215a64da_fk_myapp_shop_id` (`SHOP_id`),
  KEY `myapp_shopreview_USER_id_d1cb9654_fk_myapp_user_id` (`USER_id`),
  CONSTRAINT `myapp_shopreview_SHOP_id_215a64da_fk_myapp_shop_id` FOREIGN KEY (`SHOP_id`) REFERENCES `myapp_shop` (`id`),
  CONSTRAINT `myapp_shopreview_USER_id_d1cb9654_fk_myapp_user_id` FOREIGN KEY (`USER_id`) REFERENCES `myapp_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_shopreview` */

/*Table structure for table `myapp_std_complaint` */

DROP TABLE IF EXISTS `myapp_std_complaint`;

CREATE TABLE `myapp_std_complaint` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(10) NOT NULL,
  `compalint` varchar(100) NOT NULL,
  `reply` varchar(100) NOT NULL,
  `STUDENT_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_std_complaint_STUDENT_id_fd610f49_fk_myapp_student_id` (`STUDENT_id`),
  CONSTRAINT `myapp_std_complaint_STUDENT_id_fd610f49_fk_myapp_student_id` FOREIGN KEY (`STUDENT_id`) REFERENCES `myapp_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_std_complaint` */

/*Table structure for table `myapp_student` */

DROP TABLE IF EXISTS `myapp_student`;

CREATE TABLE `myapp_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `house_name` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `district` varchar(50) NOT NULL,
  `pin` varchar(10) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `BATCH_id` bigint(20) NOT NULL,
  `COURSE_id` bigint(20) NOT NULL,
  `INSTITUTE_id` bigint(20) NOT NULL,
  `USER_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_student_BATCH_id_9b66aa82_fk_myapp_batch_id` (`BATCH_id`),
  KEY `myapp_student_COURSE_id_5fe98c90_fk_myapp_course_id` (`COURSE_id`),
  KEY `myapp_student_INSTITUTE_id_d681223b_fk_myapp_institute_id` (`INSTITUTE_id`),
  KEY `myapp_student_USER_id_1082b5fc_fk_myapp_user_id` (`USER_id`),
  CONSTRAINT `myapp_student_BATCH_id_9b66aa82_fk_myapp_batch_id` FOREIGN KEY (`BATCH_id`) REFERENCES `myapp_batch` (`id`),
  CONSTRAINT `myapp_student_COURSE_id_5fe98c90_fk_myapp_course_id` FOREIGN KEY (`COURSE_id`) REFERENCES `myapp_course` (`id`),
  CONSTRAINT `myapp_student_INSTITUTE_id_d681223b_fk_myapp_institute_id` FOREIGN KEY (`INSTITUTE_id`) REFERENCES `myapp_institute` (`id`),
  CONSTRAINT `myapp_student_USER_id_1082b5fc_fk_myapp_user_id` FOREIGN KEY (`USER_id`) REFERENCES `myapp_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_student` */

/*Table structure for table `myapp_user` */

DROP TABLE IF EXISTS `myapp_user`;

CREATE TABLE `myapp_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `district` varchar(50) NOT NULL,
  `pin` varchar(10) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `LOGIN_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `myapp_user_LOGIN_id_da832ded_fk_myapp_login_id` (`LOGIN_id`),
  CONSTRAINT `myapp_user_LOGIN_id_da832ded_fk_myapp_login_id` FOREIGN KEY (`LOGIN_id`) REFERENCES `myapp_login` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `myapp_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
