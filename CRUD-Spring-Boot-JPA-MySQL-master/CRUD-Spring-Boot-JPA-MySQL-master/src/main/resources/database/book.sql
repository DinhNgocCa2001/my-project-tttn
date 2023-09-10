-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 06, 2017 at 07:55 PM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `bookstore`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pages` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `publication` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Table structure for table `book`
--

CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` longtext DEFAULT NULL,
  `linkUrl` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT 0,
  `discount` smallint DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id`, `author`, `category`, `name`, `pages`, `price`, `publication`) VALUES
(1, 'Santosh Kumar', 'Programming', 'Extensive Multithreading', 100, 50, 'Mcgraw Publication'),
(3, 'Suchitra', 'Programming', 'Java', 1000, 200, 'Mcgraw Publication'),
(5, 'Nitesh', 'Computer Programming', 'C++', 1500, 240, 'Rajput Publication');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


INSERT INTO `product` (`id`, `name`, `price`, `discount`, `linkUrl`, `description`) VALUES
(1, 'dinh', 50, 50, 'https://cdn-images.kooding.com/productListingImage/478779-2/253cc620f6d1bd45d12473d5a22d79541d278850.jpg', 50),
(2, 'ngoc', 50, 50, 'https://cdn-images.kooding.com/productListingImage/478459-2/06c90c8476777de2984fb4d9b2d4e0dc834b4aab.jpg', 50),
(3, 'ca', 50, 50, 'https://cdn-images.kooding.com/productListingImage/479689-2/f74263fa9753d5f84d880a88dff8509602781939.jpg', 50),
(4, 'dinh', 50, 50, 'https://cdn-images.kooding.com/productListingImage/479692-2/3c1cfd4b9e53537d0cc1214705a7071a9d5ed539.jpg', 50);

