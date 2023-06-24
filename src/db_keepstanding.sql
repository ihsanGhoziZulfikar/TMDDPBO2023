-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 19, 2023 at 03:16 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_keepstanding`
--

-- --------------------------------------------------------

--
-- Table structure for table `tscore`
--

CREATE TABLE `tscore` (
  `username` varchar(254) NOT NULL,
  `score` int(11) DEFAULT 0,
  `standing` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tscore`
--

INSERT INTO `tscore` (`username`, `score`, `standing`) VALUES
('a', 7740, 193),
('asep', 860, 26),
('Ihsan Ghozi Zulfikar', 380, 11),
('ujang', 620, 16);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tscore`
--
ALTER TABLE `tscore`
  ADD PRIMARY KEY (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
