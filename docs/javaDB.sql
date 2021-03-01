-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 01, 2021 at 10:51 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javaDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_model_object`
--

CREATE TABLE `data_model_object` (
  `model_object_id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `model_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `data_model_object_value`
--

CREATE TABLE `data_model_object_value` (
  `model_object_value_id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `model_attr_id` bigint(20) DEFAULT NULL,
  `model_object_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `date_data_value`
--

CREATE TABLE `date_data_value` (
  `id` varchar(75) NOT NULL,
  `data_value` datetime DEFAULT NULL,
  `model_object_value_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `model_attribute`
--

CREATE TABLE `model_attribute` (
  `model_attr_id` bigint(20) NOT NULL,
  `attribute_name` varchar(255) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `data_type` varchar(30) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `has_child` bit(1) DEFAULT NULL,
  `mandatory` bit(1) DEFAULT NULL,
  `is_unique` tinyint(1) NOT NULL DEFAULT 1,
  `is_visibility` bit(1) DEFAULT NULL,
  `model_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `model_attribute`
--

INSERT INTO `model_attribute` (`model_attr_id`, `attribute_name`, `created_by`, `created_date`, `data_type`, `description`, `display_name`, `has_child`, `mandatory`, `is_unique`, `is_visibility`, `model_id`) VALUES
(1, 'EMP_NAME', 1010, '2021-03-01 14:53:23', 'STRING', 'NAME OF DEPARTMENT', 'EMPLOYEE_NAME', b'0', b'1', 0, b'0', 2),
(3, 'EMP_JOIN_DT', 1010, '2021-03-01 14:53:23', 'DATE', 'Joining Date', 'EMPLOYEE_JOIN_DATE', b'0', b'1', 0, b'0', 2),
(4, 'EMP_ADDRESS', 1010, '2021-03-01 14:53:23', 'TEXT', 'Address of Employee', 'EMPLOYEE_ADDRESS', b'0', b'0', 0, b'0', 2),
(5, 'DEPT_ID', 1010, '2021-03-01 14:53:23', 'NUMBER', 'Department of Employee', 'DEPARTMENT_ID', b'1', b'1', 0, b'0', 2),
(7, 'DEPT_NAME', 1010, '2021-03-01 14:53:53', 'STRING', 'NAME OF DEPATMENT', 'DEPARTMENT_NAME', b'0', b'1', 1, b'0', 3);

-- --------------------------------------------------------

--
-- Table structure for table `model_name`
--

CREATE TABLE `model_name` (
  `model_id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `description` longtext DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `entity_name` varchar(255) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `parent_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `model_name`
--

INSERT INTO `model_name` (`model_id`, `created_by`, `created_date`, `description`, `display_name`, `entity_name`, `status`, `parent_id`) VALUES
(2, 1010, '2021-03-01 14:53:23', 'MEMBER OF EACH DEPARTMENT', 'EMPLOYEE', 'EMP_TBL', 1, NULL),
(3, 7, '2021-03-01 14:53:45', 'A TABLE WHICH WILL STORE \n ALL THE DEPARTMENT VALUE', 'DEPARTMENT', 'DEPT_TBL', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `number_data_value`
--

CREATE TABLE `number_data_value` (
  `id` varchar(75) NOT NULL,
  `data_value` bigint(20) DEFAULT NULL,
  `model_object_value_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `string_data_value`
--

CREATE TABLE `string_data_value` (
  `id` varchar(75) NOT NULL,
  `data_value` varchar(500) DEFAULT NULL,
  `model_object_value_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `text_data_value`
--

CREATE TABLE `text_data_value` (
  `id` varchar(75) NOT NULL,
  `data_value` longtext DEFAULT NULL,
  `model_object_value_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_model_object`
--
ALTER TABLE `data_model_object`
  ADD PRIMARY KEY (`model_object_id`);

--
-- Indexes for table `data_model_object_value`
--
ALTER TABLE `data_model_object_value`
  ADD PRIMARY KEY (`model_object_value_id`);

--
-- Indexes for table `date_data_value`
--
ALTER TABLE `date_data_value`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `model_attribute`
--
ALTER TABLE `model_attribute`
  ADD PRIMARY KEY (`model_attr_id`);

--
-- Indexes for table `model_name`
--
ALTER TABLE `model_name`
  ADD PRIMARY KEY (`model_id`);

--
-- Indexes for table `number_data_value`
--
ALTER TABLE `number_data_value`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `string_data_value`
--
ALTER TABLE `string_data_value`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `text_data_value`
--
ALTER TABLE `text_data_value`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_model_object`
--
ALTER TABLE `data_model_object`
  MODIFY `model_object_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `data_model_object_value`
--
ALTER TABLE `data_model_object_value`
  MODIFY `model_object_value_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `model_attribute`
--
ALTER TABLE `model_attribute`
  MODIFY `model_attr_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `model_name`
--
ALTER TABLE `model_name`
  MODIFY `model_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
