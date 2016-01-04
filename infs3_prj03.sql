-- phpMyAdmin SQL Dump
-- version 4.2.12
-- http://www.phpmyadmin.net
--
-- Client :  mysql
-- Généré le :  Lun 30 Novembre 2015 à 14:25
-- Version du serveur :  5.5.32-MariaDB
-- Version de PHP :  5.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `infs3_prj03`
--

-- --------------------------------------------------------

--
-- Structure de la table `COMMANDER`
--

CREATE TABLE IF NOT EXISTS `COMMANDER` (
  `NUMTABLE` int(11) NOT NULL,
  `NUMMENU` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `COMPOSER`
--

CREATE TABLE IF NOT EXISTS `COMPOSER` (
  `NUMPLAT` int(11) NOT NULL,
  `NUMMENU` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `CONSTITUER`
--

CREATE TABLE IF NOT EXISTS `CONSTITUER` (
`NUMINGREDIENT` int(11) NOT NULL,
  `NUMPLAT` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `INGREDIENT`
--

CREATE TABLE IF NOT EXISTS `INGREDIENT` (
`NUMINGREDIENT` int(11) NOT NULL,
  `ETATSI` char(255) DEFAULT NULL,
  `PRIXU` float(11) DEFAULT 0,
  `STOCK` int(11) DEFAULT NULL,
  `NOM` char(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `INGREDIENT`
--

INSERT INTO `INGREDIENT` (`NUMINGREDIENT`, `ETATSI`, `PRIXU`, `STOCK`, `NOM`) VALUES
(1, 'bon', 10, 5, 'vide'),
(2, 'bon', 10, 5, 'steack');

-- --------------------------------------------------------

--
-- Structure de la table `MENU`
--

CREATE TABLE IF NOT EXISTS `MENU` (
`NUMMENU` int(11) NOT NULL,
  `NOM` char(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `MENU`
--

INSERT INTO `MENU` (`NUMMENU`, `COMPOSITION`, `NOM`) VALUES
(1, 'vide', 'vide');

-- --------------------------------------------------------

--
-- Structure de la table `PERSONNEL`
--

CREATE TABLE IF NOT EXISTS `PERSONNEL` (
`NUMPERSO` int(11) NOT NULL,
  `NUMRESTO` int(11) DEFAULT NULL,
  `NUMSALLE` int(11) DEFAULT NULL,
  `NOM` char(255) NOT NULL,
  `PRENOM` char(255) DEFAULT NULL,
  `POSTE` char(255) DEFAULT NULL,
  `ADRESSE` char(255) DEFAULT NULL,
  `NUMTEL` char(255) DEFAULT NULL,
  `ADRESSEMAIL` char(255) DEFAULT NULL,
  `HORAIRETRAV` text,
  `HORAIREPREV` text,
  `SALAIRE_H` float DEFAULT NULL,
  `DROITS` int(11) NOT NULL,
  `MDP` char(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `PERSONNEL`
--

INSERT INTO `PERSONNEL` (`NUMPERSO`, `NUMRESTO`, `NUMSALLE`, `NOM`, `PRENOM`, `POSTE`, `ADRESSE`, `NUMTEL`, `ADRESSEMAIL`, `HORAIRETRAV`, `HORAIREPREV`, `SALAIRE_H`, `DROITS`, `MDP`) VALUES
(1, 6, 2, 'Novak', 'Damien', 'Serveuse', '19 rue de la poire', '06 65 54 02 25', 'novak.damien@adresse.fr', NULL, NULL, 0.2, 1, 'motdepasse'),
(19, NULL, NULL, 'ROOT', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, '77e7e78b05578758626744dcdf57007c71797399');

-- --------------------------------------------------------

--
-- Structure de la table `PLAT`
--

CREATE TABLE IF NOT EXISTS `PLAT` (
`NUMPLAT` int(11) NOT NULL,
  `NOMPLAT` char(255) NOT NULL,
  `RECETTE` text,
  `PRIXU` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `PLAT`
--

INSERT INTO `PLAT` (`NUMPLAT`, `NOMPLAT`, `RECETTE`, `PRIXU`) VALUES
(1, 'frites', 'patate', 3),
(2, 'frites', 'patate', 3);

-- --------------------------------------------------------

--
-- Structure de la table `RESERVATION`
--

CREATE TABLE IF NOT EXISTS `RESERVATION` (
`NUMRESERVATION` int(11) NOT NULL,
  `DATERESERV` date NOT NULL,
  `PRENOMCLIENT` char DEFAULT NULL
  `NOMCLIENT` char(255) DEFAULT NULL,
  `NBPERS` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `RESERVER`
--

CREATE TABLE IF NOT EXISTS `RESERVER` (
  `NUMRESERVATION` int(11) NOT NULL,
  `NUMTABLE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `RESTAURANT`
--

CREATE TABLE IF NOT EXISTS `RESTAURANT` (
`NUMRESTO` int(11) NOT NULL,
  `NOMRESTO` char(255) NOT NULL,
  `MARGE` float NOT NULL,
  `NBSALLES` int(11) NOT NULL,
  `NBEMPLOYE` int(11) NOT NULL,
  `ADRESSE` char(255) NOT NULL,
  `PAYS` char(255) NOT NULL,
  `NUMTEL` char(10) NOT NULL,
  `VILLE` char(255) NOT NULL,
  `CP` char(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `RESTAURANT`
--

INSERT INTO `RESTAURANT` (`NUMRESTO`, `NOMRESTO`, `MARGE`, `NBSALLES`, `NBEMPLOYE`, `ADRESSE`, `PAYS`, `NUMTEL`, `VILLE`, `CP`) VALUES
(6, 'Les papilles', 5.32, 2, 5, '16 rue de la bouche', 'France', '0000000000', 'MaVille', '51500');

-- --------------------------------------------------------

--
-- Structure de la table `SALLE`
--

CREATE TABLE IF NOT EXISTS `SALLE` (
`NUMSALLE` int(11) NOT NULL,
  `NUMRESTO` int(11) NOT NULL,
  `NOMSALLE` char(255) NOT NULL,
  `ETATS` char(255) NOT NULL,
  `NOMBRETABLES` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `SALLE`
--

INSERT INTO `SALLE` (`NUMSALLE`, `NUMRESTO`, `NOMSALLE`, `ETATS`, `NOMBRETABLES`) VALUES
(2, 6, 'dents', 'horsService', 5);

-- --------------------------------------------------------

--
-- Structure de la table `SERVIR`
--

CREATE TABLE IF NOT EXISTS `SERVIR` (
  `NUMSALLE` int(11) NOT NULL,
  `NUMMENU` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `TABLES`
--

CREATE TABLE IF NOT EXISTS `TABLES` (
`NUMTABLE` int(11) NOT NULL,
  `NUMSALLE` int(11) NOT NULL,
  `ETATS` char(255) NOT NULL,
  `CAPACITE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `COMMANDER`
--
ALTER TABLE `COMMANDER`
 ADD PRIMARY KEY (`NUMTABLE`,`NUMMENU`), ADD KEY `COMMANDER_FK` (`NUMTABLE`), ADD KEY `COMMANDER2_FK` (`NUMMENU`);

--
-- Index pour la table `COMPOSER`
--
ALTER TABLE `COMPOSER`
 ADD PRIMARY KEY (`NUMPLAT`,`NUMMENU`), ADD KEY `COMPOSER_FK` (`NUMPLAT`), ADD KEY `COMPOSER2_FK` (`NUMMENU`);

--
-- Index pour la table `CONSTITUER`
--
ALTER TABLE `CONSTITUER`
 ADD PRIMARY KEY (`NUMINGREDIENT`,`NUMPLAT`), ADD KEY `CONSTITUER_FK` (`NUMINGREDIENT`), ADD KEY `CONSTITUER2_FK` (`NUMPLAT`);

--
-- Index pour la table `INGREDIENT`
--
ALTER TABLE `INGREDIENT`
 ADD PRIMARY KEY (`NUMINGREDIENT`);

--
-- Index pour la table `MENU`
--
ALTER TABLE `MENU`
 ADD PRIMARY KEY (`NUMMENU`);

--
-- Index pour la table `PERSONNEL`
--
ALTER TABLE `PERSONNEL`
 ADD PRIMARY KEY (`NUMPERSO`), ADD KEY `TRAVAILLER_FK` (`NUMSALLE`), ADD KEY `EMPLOYER_FK` (`NUMRESTO`);

--
-- Index pour la table `PLAT`
--
ALTER TABLE `PLAT`
 ADD PRIMARY KEY (`NUMPLAT`);

--
-- Index pour la table `RESERVATION`
--
ALTER TABLE `RESERVATION`
 ADD PRIMARY KEY (`NUMRESERVATION`);

--
-- Index pour la table `RESERVER`
--
ALTER TABLE `RESERVER`
 ADD PRIMARY KEY (`NUMRESERVATION`,`NUMTABLE`), ADD KEY `RESERVER_FK` (`NUMRESERVATION`), ADD KEY `RESERVER2_FK` (`NUMTABLE`);

--
-- Index pour la table `RESTAURANT`
--
ALTER TABLE `RESTAURANT`
 ADD PRIMARY KEY (`NUMRESTO`);

--
-- Index pour la table `SALLE`
--
ALTER TABLE `SALLE`
 ADD PRIMARY KEY (`NUMSALLE`), ADD KEY `POCEDER_FK` (`NUMRESTO`);

--
-- Index pour la table `SERVIR`
--
ALTER TABLE `SERVIR`
 ADD PRIMARY KEY (`NUMSALLE`,`NUMMENU`), ADD KEY `SERVIR_FK` (`NUMSALLE`), ADD KEY `SERVIR2_FK` (`NUMMENU`);

--
-- Index pour la table `TABLES`
--
ALTER TABLE `TABLES`
 ADD PRIMARY KEY (`NUMTABLE`), ADD KEY `SITUER_FK` (`NUMSALLE`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `CONSTITUER`
--
ALTER TABLE `CONSTITUER`
MODIFY `NUMINGREDIENT` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `INGREDIENT`
--
ALTER TABLE `INGREDIENT`
MODIFY `NUMINGREDIENT` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `MENU`
--
ALTER TABLE `MENU`
MODIFY `NUMMENU` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `PERSONNEL`
--
ALTER TABLE `PERSONNEL`
MODIFY `NUMPERSO` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT pour la table `PLAT`
--
ALTER TABLE `PLAT`
MODIFY `NUMPLAT` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `RESERVATION`
--
ALTER TABLE `RESERVATION`
MODIFY `NUMRESERVATION` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `RESTAURANT`
--
ALTER TABLE `RESTAURANT`
MODIFY `NUMRESTO` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `SALLE`
--
ALTER TABLE `SALLE`
MODIFY `NUMSALLE` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `TABLES`
--
ALTER TABLE `TABLES`
MODIFY `NUMTABLE` int(11) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `COMMANDER`
--
ALTER TABLE `COMMANDER`
ADD CONSTRAINT `FK_COMMANDE_COMMANDER_MENU` FOREIGN KEY (`NUMMENU`) REFERENCES `MENU` (`NUMMENU`),
ADD CONSTRAINT `FK_COMMANDE_COMMANDER_TABLE` FOREIGN KEY (`NUMTABLE`) REFERENCES `TABLES` (`NUMTABLE`);

--
-- Contraintes pour la table `COMPOSER`
--
ALTER TABLE `COMPOSER`
ADD CONSTRAINT `FK_COMPOSER_COMPOSER2_MENU` FOREIGN KEY (`NUMMENU`) REFERENCES `MENU` (`NUMMENU`),
ADD CONSTRAINT `FK_COMPOSER_COMPOSER_PLAT` FOREIGN KEY (`NUMPLAT`) REFERENCES `PLAT` (`NUMPLAT`);

--
-- Contraintes pour la table `CONSTITUER`
--
ALTER TABLE `CONSTITUER`
ADD CONSTRAINT `FK_CONSTITU_CONSTITUE_INGREDIE` FOREIGN KEY (`NUMINGREDIENT`) REFERENCES `INGREDIENT` (`NUMINGREDIENT`),
ADD CONSTRAINT `FK_CONSTITU_CONSTITUE_PLAT` FOREIGN KEY (`NUMPLAT`) REFERENCES `PLAT` (`NUMPLAT`);

--
-- Contraintes pour la table `PERSONNEL`
--
ALTER TABLE `PERSONNEL`
ADD CONSTRAINT `FK_PERSONNE_EMPLOYER_RESTAURA` FOREIGN KEY (`NUMRESTO`) REFERENCES `RESTAURANT` (`NUMRESTO`),
ADD CONSTRAINT `FK_PERSONNE_TRAVAILLE_SALLE` FOREIGN KEY (`NUMSALLE`) REFERENCES `SALLE` (`NUMSALLE`);

--
-- Contraintes pour la table `RESERVER`
--
ALTER TABLE `RESERVER`
ADD CONSTRAINT `FK_RESERVER_RESERVER2_TABLE` FOREIGN KEY (`NUMTABLE`) REFERENCES `TABLES` (`NUMTABLE`),
ADD CONSTRAINT `FK_RESERVER_RESERVER_RESERVAT` FOREIGN KEY (`NUMRESERVATION`) REFERENCES `RESERVATION` (`NUMRESERVATION`);

--
-- Contraintes pour la table `SALLE`
--
ALTER TABLE `SALLE`
ADD CONSTRAINT `FK_SALLE_POCEDER_RESTAURA` FOREIGN KEY (`NUMRESTO`) REFERENCES `RESTAURANT` (`NUMRESTO`);

--
-- Contraintes pour la table `SERVIR`
--
ALTER TABLE `SERVIR`
ADD CONSTRAINT `FK_SERVIR_SERVIR2_MENU` FOREIGN KEY (`NUMMENU`) REFERENCES `MENU` (`NUMMENU`),
ADD CONSTRAINT `FK_SERVIR_SERVIR_SALLE` FOREIGN KEY (`NUMSALLE`) REFERENCES `SALLE` (`NUMSALLE`);

--
-- Contraintes pour la table `TABLES`
--
ALTER TABLE `TABLES`
ADD CONSTRAINT `FK_TABLE_SITUER_SALLE` FOREIGN KEY (`NUMSALLE`) REFERENCES `SALLE` (`NUMSALLE`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
