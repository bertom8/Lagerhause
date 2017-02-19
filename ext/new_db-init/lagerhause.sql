-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Hoszt: localhost
-- Létrehozás ideje: 2015. Nov 12. 14:17
-- Szerver verzió: 5.5.44-0ubuntu0.14.04.1
-- PHP verzió: 5.5.9-1ubuntu4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Adatbázis: `lagerhause`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `house` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `export`
--

CREATE TABLE IF NOT EXISTS `export` (
  `id` bigint(20) NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `quantity` int(11) DEFAULT NULL,
  `success` bit(1) NOT NULL DEFAULT b'0',
  `time` datetime DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `ware_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8pgc1k3nwh744brci7loo4607` (`customer_id`),
  KEY `FKops5bl8fl73pnnqtym79qajly` (`ware_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- A tábla adatainak kiíratása `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(23),
(23),
(23),
(23),
(23),
(23),
(23);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `import`
--

CREATE TABLE IF NOT EXISTS `import` (
  `id` bigint(20) NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `quantity` int(11) DEFAULT NULL,
  `success` bit(1) DEFAULT b'0',
  `time` datetime DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  `ware_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn1st8vkkohxq361ndreneuko1` (`supplier_id`),
  KEY `FK8q0o9x6t8unnhyq9bp70t76kv` (`ware_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `log`
--

CREATE TABLE IF NOT EXISTS `log` (
  `id` bigint(20) NOT NULL,
  `action` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `typeid` varchar(255) DEFAULT NULL,
  `user_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl163easojv1n5lir29mjrrx86` (`user_user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `storage`
--

CREATE TABLE IF NOT EXISTS `storage` (
  `id` bigint(20) NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- A tábla adatainak kiíratása `storage`
--

INSERT INTO `storage` (`id`, `deleted`, `name`) VALUES
(15, b'0', 'Tároló1');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `supplier`
--

CREATE TABLE IF NOT EXISTS `supplier` (
  `id` bigint(20) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `house` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_name` varchar(255) NOT NULL,
  `admin` bit(1) NOT NULL DEFAULT b'0',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `password` varchar(255) DEFAULT NULL,
  `picture` longblob,
  `statistics` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- A tábla adatainak kiíratása `user`
--

INSERT INTO `user` (`user_name`, `admin`, `deleted`, `password`, `picture`, `statistics`) VALUES
('admin', b'1', b'0', '9311089187a9308509c09443224e5097', NULL, b'1');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `ware`
--

CREATE TABLE IF NOT EXISTS `ware` (
  `id` bigint(20) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `name` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `serial` varchar(255) DEFAULT NULL,
  `storage_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcg8muwuis1oddv8ghj8bbmh92` (`storage_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `export`
--
ALTER TABLE `export`
  ADD CONSTRAINT `FKops5bl8fl73pnnqtym79qajly` FOREIGN KEY (`ware_id`) REFERENCES `ware` (`id`),
  ADD CONSTRAINT `FK8pgc1k3nwh744brci7loo4607` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);

--
-- Megkötések a táblához `import`
--
ALTER TABLE `import`
  ADD CONSTRAINT `FK8q0o9x6t8unnhyq9bp70t76kv` FOREIGN KEY (`ware_id`) REFERENCES `ware` (`id`),
  ADD CONSTRAINT `FKn1st8vkkohxq361ndreneuko1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`);

--
-- Megkötések a táblához `log`
--
ALTER TABLE `log`
  ADD CONSTRAINT `FKl163easojv1n5lir29mjrrx86` FOREIGN KEY (`user_user_name`) REFERENCES `user` (`user_name`);

--
-- Megkötések a táblához `ware`
--
ALTER TABLE `ware`
  ADD CONSTRAINT `FKcg8muwuis1oddv8ghj8bbmh92` FOREIGN KEY (`storage_id`) REFERENCES `storage` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
