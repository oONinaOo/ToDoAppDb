SET NAMES 'utf8' COLLATE 'utf8_general_ci';

DROP SCHEMA IF EXISTS `TodoApp`;

CREATE SCHEMA `TodoApp` CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `TodoApp` ;

CREATE TABLE `Todos` (
  `ID` INT NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR(60),
	`isActive` VARCHAR(10),
	PRIMARY KEY (`ID`)
	)
;

CREATE TABLE `Users` (
	`Username` VARCHAR(60),
	`Password` VARCHAR(25),
	PRIMARY KEY (`Username`)
	);

INSERT INTO `Users` (`Username`, `Password`) VALUES ('admin', 'admin');