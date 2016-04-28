-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 28 Avril 2016 à 11:29
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `aeroclub`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

CREATE TABLE IF NOT EXISTS `adresse` (
  `idadr` int(4) NOT NULL,
  `rue` varchar(50) COLLATE utf8_bin NOT NULL,
  `ville` varchar(30) COLLATE utf8_bin NOT NULL,
  `codepostal` int(5) NOT NULL,
  `numero` int(3) NOT NULL,
  PRIMARY KEY (`idadr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `aerodrome`
--

CREATE TABLE IF NOT EXISTS `aerodrome` (
  `idaerodrome` char(4) COLLATE utf8_bin NOT NULL,
  `nom` varchar(30) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idaerodrome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `avion`
--

CREATE TABLE IF NOT EXISTS `avion` (
  `idavion` char(4) COLLATE utf8_bin NOT NULL,
  `nomavion` varchar(20) COLLATE utf8_bin NOT NULL,
  `typeavion` varchar(20) COLLATE utf8_bin NOT NULL,
  `immatriculation` char(5) COLLATE utf8_bin NOT NULL,
  `autonomie` int(3) NOT NULL,
  `capacitereservoir` int(3) NOT NULL,
  `nbplace` int(2) NOT NULL,
  `massemaximale` int(3) NOT NULL,
  `couthoraire` float(5,2) NOT NULL,
  `disponibilte` varchar(20) COLLATE utf8_bin NOT NULL,
  `vitessecroisiere` float(5,2) NOT NULL,
  PRIMARY KEY (`idavion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `membre`
--

CREATE TABLE IF NOT EXISTS `membre` (
  `idmembre` char(5) COLLATE utf8_bin NOT NULL,
  `nom` varchar(20) COLLATE utf8_bin NOT NULL,
  `prenom` varchar(20) COLLATE utf8_bin NOT NULL,
  `idadr` int(4) NOT NULL,
  `email` varchar(40) COLLATE utf8_bin NOT NULL,
  `numtel` int(10) NOT NULL,
  `datenaissance` date NOT NULL,
  `solde` float(6,2) NOT NULL,
  `isinstructeur` tinyint(1) NOT NULL,
  `ismecanicien` tinyint(1) NOT NULL,
  `ispilote` tinyint(1) NOT NULL,
  `isadministrateur` tinyint(1) NOT NULL,
  PRIMARY KEY (`idmembre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE IF NOT EXISTS `paiement` (
  `idpaiement` char(4) COLLATE utf8_bin NOT NULL,
  `idmembre` int(5) NOT NULL,
  `montant` float(6,2) NOT NULL,
  `datepaiement` date NOT NULL,
  `ischeque` tinyint(1) NOT NULL,
  `isespece` tinyint(1) NOT NULL,
  `nomemetteur` varchar(30) COLLATE utf8_bin NOT NULL,
  `banquedebiteur` varchar(30) COLLATE utf8_bin NOT NULL,
  `numerocheque` int(4) NOT NULL,
  PRIMARY KEY (`idpaiement`),
  KEY `fk_paimembre` (`idmembre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `vol`
--

CREATE TABLE IF NOT EXISTS `vol` (
  `idvol` char(4) COLLATE utf8_bin NOT NULL,
  `typevol` varchar(10) COLLATE utf8_bin NOT NULL,
  `nbpassagers` int(2) NOT NULL,
  `datevol` date NOT NULL,
  `tempsvol` time(4) NOT NULL,
  `aerodromedepart` char(4) COLLATE utf8_bin NOT NULL,
  `aerodromearrivee` char(4) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idvol`),
  KEY `fk_volaero1` (`aerodromedepart`),
  KEY `fk_volaero2` (`aerodromearrivee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `vol`
--
ALTER TABLE `vol`
  ADD CONSTRAINT `fk_volaero1` FOREIGN KEY (`aerodromedepart`) REFERENCES `aerodrome` (`idaerodrome`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_volaero2` FOREIGN KEY (`aerodromearrivee`) REFERENCES `aerodrome` (`idaerodrome`) ON DELETE CASCADE;
  
 ALTER TABLE `membre`
 ADD CONSTRAINT `fk_memadr` FOREIGN KEY (`idadr`) REFERENCES `membre`(`idadr`) ON DELETE CASCADE;
 
 ALTER TABLE `paiement`
 ADD CONSTRAINT `fk_paimembre` FOREIGN KEY (`idmembre`) REFERENCES `membre`(`idmembre`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
