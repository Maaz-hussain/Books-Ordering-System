/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.9-log : Database - online_book_store
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`online_book_store` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `online_book_store`;

/*Table structure for table `tb_author` */

DROP TABLE IF EXISTS `tb_author`;

CREATE TABLE `tb_author` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `author_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_author` */

insert  into `tb_author`(`id`,`author_name`) values (1,'Mark Twin'),(2,' J.K. Rowling');

/*Table structure for table `tb_books` */

DROP TABLE IF EXISTS `tb_books`;

CREATE TABLE `tb_books` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `iban_no` varchar(100) DEFAULT NULL,
  `author_id` int(100) DEFAULT NULL,
  `price` int(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `tb_books_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `tb_author` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_books` */

insert  into `tb_books`(`id`,`title`,`iban_no`,`author_id`,`price`) values (1,'Oliver Twist','123486',1,450),(2,'Harry Potter','null',2,930);

/*Table structure for table `tb_order` */

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `item_qty` int(100) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `order_time` time DEFAULT NULL,
  `staff_id` int(100) DEFAULT NULL,
  `amount` int(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `staff_id` (`staff_id`),
  CONSTRAINT `tb_order_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `tb_staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tb_order` */

insert  into `tb_order`(`id`,`item_qty`,`order_date`,`order_time`,`staff_id`,`amount`) values (3,3,'2020-06-10','03:31:09',1,1350);

/*Table structure for table `tb_order_details` */

DROP TABLE IF EXISTS `tb_order_details`;

CREATE TABLE `tb_order_details` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `order_id` int(100) DEFAULT NULL,
  `qty` int(100) DEFAULT NULL,
  `item_id` int(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `food_item_id` (`item_id`),
  CONSTRAINT `tb_order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`id`),
  CONSTRAINT `tb_order_details_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `tb_books` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_order_details` */

insert  into `tb_order_details`(`id`,`order_id`,`qty`,`item_id`) values (2,3,3,1);

/*Table structure for table `tb_staff` */

DROP TABLE IF EXISTS `tb_staff`;

CREATE TABLE `tb_staff` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_staff` */

insert  into `tb_staff`(`id`,`name`,`email`,`username`,`password`) values (1,'khizar','khizar@gmail.com','k','123'),(2,'1','2','1','1@gmail.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
