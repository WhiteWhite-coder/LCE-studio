/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - db_shopping
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_shopping` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_shopping`;

/*Table structure for table `tb_address` */

DROP TABLE IF EXISTS `tb_address`;

CREATE TABLE `tb_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detail` text,
  `name` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_address_id` (`uid`),
  CONSTRAINT `fk_address_id` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `tb_address` */

insert  into `tb_address`(`id`,`detail`,`name`,`phone`,`uid`,`level`) values (1,'2','吴淼杰','15815256636',7,0),(8,'1','吴淼杰','15815256636',7,1);

/*Table structure for table `tb_cart` */

DROP TABLE IF EXISTS `tb_cart`;

CREATE TABLE `tb_cart` (
  `id` int(11) NOT NULL,
  `pid` int(11) NOT NULL DEFAULT '0',
  `num` int(11) DEFAULT NULL,
  `money` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`id`,`pid`),
  KEY `fk_cart_pid` (`pid`),
  CONSTRAINT `fk_cart_id` FOREIGN KEY (`id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `fk_cart_pid` FOREIGN KEY (`pid`) REFERENCES `tb_goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_cart` */

insert  into `tb_cart`(`id`,`pid`,`num`,`money`) values (4,1,3,'3.00'),(4,2,3,'69.00'),(4,3,3,'234.00'),(7,1,3,'3.00');

/*Table structure for table `tb_goods` */

DROP TABLE IF EXISTS `tb_goods`;

CREATE TABLE `tb_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `pubdate` date DEFAULT NULL,
  `picture` varchar(255) NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `star` tinyint(4) NOT NULL DEFAULT '0',
  `intro` text,
  `typeid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_typeid` (`typeid`),
  CONSTRAINT `fk_typeid` FOREIGN KEY (`typeid`) REFERENCES `tb_goods_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `tb_goods` */

insert  into `tb_goods`(`id`,`name`,`pubdate`,`picture`,`price`,`star`,`intro`,`typeid`) values (1,'java','2020-03-05','8\\7\\8bba858c-79da-4cdc-a974-eb4526d7408b_696673-1_w.jpg','1.00',5,'第一',1),(2,'疯狂java讲义','2020-02-06','8\\7\\8bba858c-79da-4cdc-a974-eb4526d7408b_696673-1_w.jpg','23.00',5,'第二',1),(3,'第一行代码','2020-03-20','8\\7\\8b607da8e9-8421-47c2-bc61-424c22c50697_696673-1_w.jpg','78.00',4,'第三',1),(4,'安卓','2020-03-20','b\\8\\46e944eb-3589-4dc3-aafa-24227ff0323c_','89.00',3,'第四',1),(5,'小米9','2020-02-13','d\\a\\3b673ed6-9dd9-4f34-8920-c4da39af94fd_liebiao_xiaomi6.jpg','2499.00',3,'第五',1),(6,'小米10pro','2020-03-06','8\\5\\86c72bfa-67c2-4ab1-97ec-a82d6b2168d0_liebiao_xiaomi6.jpg','1.00',2,'第六',1),(7,'小米10','2020-04-18','f\\0\\c5da94db-0d2e-42df-a4ab-2610e9b02df0_liebiao_xiaomimix.jpg','1588.00',3,'第七',1),(8,'辣椒','2020-05-23','0','2000.00',5,NULL,1),(9,'大米','2020-05-06','0','2000.00',0,NULL,1),(10,'球鞋','2020-05-20','0','1999.00',0,NULL,1),(11,'苹果','2020-05-23','0','1999.00',0,NULL,1),(12,'梨子','2020-05-20','0','1999.00',0,NULL,1);

/*Table structure for table `tb_goods_type` */

DROP TABLE IF EXISTS `tb_goods_type`;

CREATE TABLE `tb_goods_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `level` int(11) DEFAULT NULL,
  `Parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `tb_goods_type` */

insert  into `tb_goods_type`(`id`,`name`,`level`,`Parent`) values (1,'计算机',1,0),(2,'小米手机',1,0),(3,'笔记本',1,0),(4,'电视盒子',1,0),(5,'智能家电',1,0),(6,'其他',1,0),(7,'平衡车',1,0);

/*Table structure for table `tb_order` */

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `id` varchar(100) NOT NULL,
  `uid` int(11) DEFAULT NULL,
  `money` decimal(10,2) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_uid` (`uid`),
  KEY `fk_order_aid` (`aid`),
  CONSTRAINT `fk_order_aid` FOREIGN KEY (`aid`) REFERENCES `tb_address` (`id`),
  CONSTRAINT `fk_order_uid` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_order` */

/*Table structure for table `tb_orderdetail` */

DROP TABLE IF EXISTS `tb_orderdetail`;

CREATE TABLE `tb_orderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Oid` varchar(100) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `money` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_pid` (`pid`),
  KEY `fk_order_id` (`Oid`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`Oid`) REFERENCES `tb_order` (`id`),
  CONSTRAINT `fk_order_pid` FOREIGN KEY (`pid`) REFERENCES `tb_goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_orderdetail` */

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `flag` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`username`,`password`,`email`,`gender`,`flag`,`role`,`code`) values (1,'admin','e10adc3949ba59abbe56e057f20f883e','964069134@qq.com','男',1,0,'20200521033029585ea'),(4,'lee','e10adc3949ba59abbe56e057f20f883e','964069134@qq.com','男',1,1,'20200521033029585ea'),(7,'wuwu','e10adc3949ba59abbe56e057f20f883e','964069134@qq.com','男',1,1,'20200521033029585ea'),(8,'wuwuwu','e10adc3949ba59abbe56e057f20f883e','964069134@qq.com','男',1,1,'2020052103365560518c'),(9,'wuwuwuwu','e10adc3949ba59abbe56e057f20f883e','964069134@qq.com','男',1,1,'2020052104082050823e'),(10,'wuwuwuwuwu','e10adc3949ba59abbe56e057f20f883e','964069134@qq.com','男',1,1,'20200521094143374200'),(11,'leewu','e10adc3949ba59abbe56e057f20f883e','964069134@qq.com','男',1,1,'20200523120753585192');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
