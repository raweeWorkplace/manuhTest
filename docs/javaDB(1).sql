-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 28, 2021 at 10:02 PM
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
  `id` varchar(255) NOT NULL,
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

-- --------------------------------------------------------

--
-- Table structure for table `number_data_value`
--

CREATE TABLE `number_data_value` (
  `id` varchar(255) NOT NULL,
  `data_value` bigint(20) DEFAULT NULL,
  `model_object_value_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `string_data_value`
--

CREATE TABLE `string_data_value` (
  `id` varchar(255) NOT NULL,
  `data_value` varchar(500) DEFAULT NULL,
  `model_object_value_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `text_data_value`
--

CREATE TABLE `text_data_value` (
  `id` varchar(255) NOT NULL,
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
  ADD PRIMARY KEY (`model_object_id`),
  ADD KEY `FKtdc1e913a4a3c7ni5b0df25jv` (`model_id`);

--
-- Indexes for table `data_model_object_value`
--
ALTER TABLE `data_model_object_value`
  ADD PRIMARY KEY (`model_object_value_id`),
  ADD KEY `FKh9tegv6dxwjdd5ud2hle0p9i9` (`model_object_id`),
  ADD KEY `FKpn7sbt4b4io8w9babc5v3ijb` (`model_attr_id`);

--
-- Indexes for table `date_data_value`
--
ALTER TABLE `date_data_value`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqscjg327gpgu356666c4kww71` (`model_object_value_id`);

--
-- Indexes for table `model_attribute`
--
ALTER TABLE `model_attribute`
  ADD PRIMARY KEY (`model_attr_id`),
  ADD KEY `FK8k5vhgues1q657xv457l0aga6` (`model_id`);

--
-- Indexes for table `model_name`
--
ALTER TABLE `model_name`
  ADD PRIMARY KEY (`model_id`),
  ADD KEY `FKeb43bh0nvsfawpp8uj1s0ygp4` (`parent_id`);

--
-- Indexes for table `number_data_value`
--
ALTER TABLE `number_data_value`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgucxrthlenk7c4j41cb3ox6ay` (`model_object_value_id`);

--
-- Indexes for table `string_data_value`
--
ALTER TABLE `string_data_value`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2a3qi93bhaf4270pslegbnap2` (`model_object_value_id`);

--
-- Indexes for table `text_data_value`
--
ALTER TABLE `text_data_value`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK72f78dkqe5g8go232h2vu7ryq` (`model_object_value_id`);

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
  MODIFY `model_attr_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `model_name`
--
ALTER TABLE `model_name`
  MODIFY `model_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `data_model_object`
--
ALTER TABLE `data_model_object`
  ADD CONSTRAINT `FKtdc1e913a4a3c7ni5b0df25jv` FOREIGN KEY (`model_id`) REFERENCES `model_name` (`model_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `data_model_object_value`
--
ALTER TABLE `data_model_object_value`
  ADD CONSTRAINT `FKh9tegv6dxwjdd5ud2hle0p9i9` FOREIGN KEY (`model_object_id`) REFERENCES `data_model_object` (`model_object_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FKpn7sbt4b4io8w9babc5v3ijb` FOREIGN KEY (`model_attr_id`) REFERENCES `model_attribute` (`model_attr_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `date_data_value`
--
ALTER TABLE `date_data_value`
  ADD CONSTRAINT `FKqscjg327gpgu356666c4kww71` FOREIGN KEY (`model_object_value_id`) REFERENCES `data_model_object_value` (`model_object_value_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `model_attribute`
--
ALTER TABLE `model_attribute`
  ADD CONSTRAINT `FK8k5vhgues1q657xv457l0aga6` FOREIGN KEY (`model_id`) REFERENCES `model_name` (`model_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `model_name`
--
ALTER TABLE `model_name`
  ADD CONSTRAINT `FKeb43bh0nvsfawpp8uj1s0ygp4` FOREIGN KEY (`parent_id`) REFERENCES `model_name` (`model_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `number_data_value`
--
ALTER TABLE `number_data_value`
  ADD CONSTRAINT `FKgucxrthlenk7c4j41cb3ox6ay` FOREIGN KEY (`model_object_value_id`) REFERENCES `data_model_object_value` (`model_object_value_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `string_data_value`
--
ALTER TABLE `string_data_value`
  ADD CONSTRAINT `FK2a3qi93bhaf4270pslegbnap2` FOREIGN KEY (`model_object_value_id`) REFERENCES `data_model_object_value` (`model_object_value_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `text_data_value`
--
ALTER TABLE `text_data_value`
  ADD CONSTRAINT `FK72f78dkqe5g8go232h2vu7ryq` FOREIGN KEY (`model_object_value_id`) REFERENCES `data_model_object_value` (`model_object_value_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
