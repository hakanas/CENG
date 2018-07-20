-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2018 at 03:46 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ceng443`
--

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `cID` int(11) NOT NULL,
  `cFirstNames` tinytext COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `cLastName` tinytext COLLATE utf8mb4_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`cID`, `cFirstNames`, `cLastName`) VALUES
(1, 'Umut Can', 'Erten'),
(2, 'Şevki', 'Kocadağ'),
(3, 'Ahmet', 'Kara'),
(4, 'Hakan', 'Aslan'),
(5, 'Mehmet', 'Akın'),
(6, 'Can', 'Erdoğar'),
(7, 'Bilal', 'Özlü'),
(8, 'Ulaş', 'Şahin'),
(9, 'Bilal', 'Yaylak'),
(10, 'Necip Fazıl', 'Yıldıran'),
(11, 'Fatih', 'Acun'),
(12, 'Kübra', 'Altın'),
(13, 'Münteha Nur', 'Bedir'),
(14, 'Abdullah', 'Dursun'),
(15, 'Murat', 'Kara'),
(16, 'Sinan', 'Köse'),
(17, 'Ülkü', 'Meteriz'),
(18, 'Canberk', 'Morelli'),
(19, 'Eda', 'Mutlu'),
(20, 'Egemen', 'Sarıkaya'),
(21, 'Murat', 'Topak'),
(22, 'İsmail', 'Tüzün'),
(23, 'Muhammed Yasin', 'Ulaş'),
(24, 'Berk', 'Yaşar'),
(25, 'Cansu Cemre', 'Yeşilçimen'),
(26, 'Yunus Emre', 'Zengin'),
(27, 'Ayşenur', 'Özmen'),
(28, 'Göksel', 'Şimşek'),
(29, 'Deniz', 'Sayın'),
(30, 'Hakan', 'Bostan'),
(31, 'Ekin', 'Dursun'),
(32, 'Hamza Faruk', 'Gelişgen'),
(33, 'Ali', 'Şimşek'),
(34, 'Zakiah', 'Utami'),
(35, 'Serhat', 'Şahin'),
(36, 'Sagynbek', 'Kenzhebaev'),
(37, 'Muhammet', 'Acar'),
(38, 'Mohammad Jaiwid', 'Jamee'),
(39, 'Yan', 'Melikyan');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`cID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `cID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
