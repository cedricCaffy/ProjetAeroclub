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
-- Base de données :  aeroclub
--

DROP TABLE IF EXISTS adresse, avion, aeroclub, instructeur, brevet, pilote, droits, paiement, vol, membre, espece, cheque;

-- --------------------------------------------------------

--
-- Structure de la table adresse
--

CREATE TABLE IF NOT EXISTS adresse (
  idadr int PRIMARY KEY AUTO_INCREMENT,
  rue varchar(50) COLLATE utf8_bin NOT NULL,
  ville varchar(30) COLLATE utf8_bin NOT NULL,
  codepostal char(5) NOT NULL,
  numero int(4)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table aerodrome
--

CREATE TABLE IF NOT EXISTS aeroclub (
  idaeroclub int PRIMARY KEY AUTO_INCREMENT,
  nom varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table avion
--

CREATE TABLE IF NOT EXISTS avion (
  idavion int PRIMARY KEY AUTO_INCREMENT,
  nomavion varchar(20) COLLATE utf8_bin NOT NULL,
  typeavion varchar(20) COLLATE utf8_bin NOT NULL,
  immatriculation char(6) COLLATE utf8_bin NOT NULL,
  consommation int(3) NOT NULL,
  capacitereservoir int(3) NOT NULL,
  nbplace int(2) NOT NULL,
  massemaximale int(3) NOT NULL,
  couthoraire float(5,2) NOT NULL,
  disponibilite varchar(20) COLLATE utf8_bin NOT NULL,
  vitessecroisiere float(5,2) NOT NULL,
  idaeroclub int NOT NULL,
  CONSTRAINT fk_avionaero FOREIGN KEY (idaeroclub) REFERENCES aeroclub(idaeroclub) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table membre
--

CREATE TABLE IF NOT EXISTS membre (
  idmembre int PRIMARY KEY AUTO_INCREMENT,
  nom varchar(20) COLLATE utf8_bin NOT NULL,
  prenom varchar(20) COLLATE utf8_bin NOT NULL,
  idadr int NOT NULL,
  email varchar(40) COLLATE utf8_bin NOT NULL,
  numtel char(10) COLLATE utf8_bin NOT NULL,
  nummobile char(10) COLLATE utf8_bin NOT NULL,
  datenaissance date NOT NULL,
  solde float(6,2) NOT NULL,
  login varchar(20) COLLATE utf8_bin UNIQUE NOT NULL,
  mdp varchar(20) COLLATE utf8_bin NOT NULL,
  idaeroclub int NOT NULL,
  CONSTRAINT fk_membreadr FOREIGN KEY (idadr) REFERENCES adresse(idadr),
  CONSTRAINT fk_membreaero FOREIGN KEY (idaeroclub) REFERENCES aeroclub(idaeroclub) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table paiement
--

CREATE TABLE IF NOT EXISTS paiement (
  idpaiement int PRIMARY KEY AUTO_INCREMENT,
  idmembre int NOT NULL,
  montant float(6,2) NOT NULL,
  datepaiement date NOT NULL,
  CONSTRAINT fk_paimembre FOREIGN KEY (idmembre) REFERENCES membre(idmembre) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS espece(
	idpaiement INT PRIMARY KEY,
	CONSTRAINT fk_espece FOREIGN KEY (idpaiement) REFERENCES paiement(idpaiement) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS cheque(
	idpaiement INT PRIMARY KEY,
	nomemetteur varchar(30),
	banquedebiteur varchar(30),
	numerocheque int(4) NOT NULL,
	CONSTRAINT fk_cheque FOREIGN KEY (idpaiement) REFERENCES paiement(idpaiement) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TRIGGER after_insert_paiement AFTER INSERT
ON paiement FOR EACH ROW
UPDATE membre m SET m.solde = m.solde + NEW.montant WHERE m.idmembre = NEW.idmembre;

CREATE TRIGGER after_delete_paiement AFTER DELETE
ON paiement FOR EACH ROW
UPDATE membre m SET m.solde = m.solde - OLD.montant WHERE m.idmembre = OLD.idmembre;

-- --------------------------------------------------------

--
-- Structure de la table pilote
--

CREATE TABLE IF NOT EXISTS pilote (
  idpilote int PRIMARY KEY AUTO_INCREMENT,
  idmembre int NOT NULL,
  datevaliditevisitemedicale date NOT NULL,
  CONSTRAINT fk_pilotemembre FOREIGN KEY (idmembre) REFERENCES membre(idmembre) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Structure de la table instructeur
--

CREATE TABLE IF NOT EXISTS instructeur (
  numeroinstructeur varchar(9) COLLATE utf8_bin PRIMARY KEY,
  couthoraire float,
  idpilote int NOT NULL,
  CONSTRAINT fk_instrucpilote FOREIGN KEY (idpilote) REFERENCES pilote(idpilote) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Structure de la table vol
--

CREATE TABLE IF NOT EXISTS vol (
  idvol int PRIMARY KEY AUTO_INCREMENT,
  typevol varchar(10) COLLATE utf8_bin NOT NULL,
  nbpassagers int(2) NOT NULL,
  datevol date NOT NULL,
  tempsvol time(4) NOT NULL,
  aeroclubdepart VARCHAR(6),
  aeroclubarrivee VARCHAR(6),
  idavion int NOT NULL,
  idpilote int NOT NULL,
  numeroinstructeur varchar(9),
  couttotal float(6,2) NOT NULL,
  CONSTRAINT fk_volavion FOREIGN KEY (idavion) REFERENCES avion(idavion) ON DELETE CASCADE,
  CONSTRAINT fk_volpilote FOREIGN KEY (idpilote) REFERENCES pilote(idpilote) ON DELETE CASCADE,
  CONSTRAINT fk_volinstruct FOREIGN KEY (numeroinstructeur) REFERENCES instructeur(numeroinstructeur) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TRIGGER after_insert_vol AFTER INSERT
ON vol FOR EACH ROW
UPDATE membre m, pilote p SET m.solde = m.solde - NEW.couttotal WHERE p.idpilote = NEW.idpilote AND m.idmembre = p.idmembre;

--
-- Structure de la table droits
--

CREATE TABLE IF NOT EXISTS droits (
  idmembre int PRIMARY KEY,
  instructeur VARCHAR(20),
  administrateur VARCHAR(20),
  mecanicien VARCHAR(20),
  pilote VARCHAR(20), 
  CONSTRAINT fk_droitsmembre FOREIGN KEY (idmembre) REFERENCES membre(idmembre) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Structure de la table instructeur
--

CREATE TABLE IF NOT EXISTS brevet (
  idbrevet int PRIMARY KEY AUTO_INCREMENT,
  idpilote int,
  nombrevet varchar(10) COLLATE utf8_bin NOT NULL,
  datevalidite date NOT NULL,
  dateobtention date NOT NULL,
  CONSTRAINT fk_pilote FOREIGN KEY (idpilote) REFERENCES pilote(idpilote) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
