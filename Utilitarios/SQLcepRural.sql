-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cep_rural
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cep_rural
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cep_rural` DEFAULT CHARACTER SET utf8 ;
USE `cep_rural` ;

-- -----------------------------------------------------
-- Table `cep_rural`.`estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cep_rural`.`estado` (
  `estcodigo` INT(11) NOT NULL AUTO_INCREMENT,
  `estnome` VARCHAR(45) NOT NULL,
  `estsigla` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`estcodigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cep_rural`.`comunidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cep_rural`.`comunidade` (
  `comidcodigo` INT(11) NOT NULL AUTO_INCREMENT,
  `comnome` VARCHAR(45) NULL DEFAULT NULL,
  `comqtdprop` INT(11) NULL DEFAULT NULL,
  `estestcodigo` INT(11) NOT NULL,
  PRIMARY KEY (`comidcodigo`, `estestcodigo`),
  INDEX `fk_comunidade_estado1_idx` (`estestcodigo` ASC),
  CONSTRAINT `fk_comunidade_estado1`
    FOREIGN KEY (`estestcodigo`)
    REFERENCES `cep_rural`.`estado` (`estcodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cep_rural`.`tipoprop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cep_rural`.`tipoprop` (
  `tprcodigo` INT(11) NOT NULL AUTO_INCREMENT,
  `tprtipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tprcodigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cep_rural`.`cep`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cep_rural`.`cep` (
  `cepidcodigo` INT(11) NOT NULL AUTO_INCREMENT,
  `cepcodigo` VARCHAR(45) NOT NULL,
  `ceplat` VARCHAR(45) NOT NULL,
  `ceplongi` VARCHAR(45) NOT NULL,
  `cepnomeprop` VARCHAR(45) NULL DEFAULT NULL,
  `cepcomcodigo` INT(11) NOT NULL,
  `ceptprcodigo` INT(11) NOT NULL,
  PRIMARY KEY (`cepidcodigo`, `cepcomcodigo`, `ceptprcodigo`),
  INDEX `fk_cep_comunidade1_idx` (`cepcomcodigo` ASC),
  INDEX `fk_cep_tipoprop1_idx` (`ceptprcodigo` ASC),
  CONSTRAINT `fk_cep_comunidade1`
    FOREIGN KEY (`cepcomcodigo`)
    REFERENCES `cep_rural`.`comunidade` (`comidcodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cep_tipoprop1`
    FOREIGN KEY (`ceptprcodigo`)
    REFERENCES `cep_rural`.`tipoprop` (`tprcodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cep_rural`.`permissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cep_rural`.`permissao` (
  `percodigo` INT(11) NOT NULL AUTO_INCREMENT,
  `perstatus` CHAR(1) NOT NULL,
  PRIMARY KEY (`percodigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cep_rural`.`grupo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cep_rural`.`grupo` (
  `grucodigo` INT(11) NOT NULL AUTO_INCREMENT,
  `grudescricao` VARCHAR(45) NOT NULL,
  `grupercodigo` INT(11) NOT NULL,
  PRIMARY KEY (`grucodigo`, `grupercodigo`),
  INDEX `fk_Grupo_Permissao1_idx` (`grupercodigo` ASC),
  CONSTRAINT `fk_Grupo_Permissao1`
    FOREIGN KEY (`grupercodigo`)
    REFERENCES `cep_rural`.`permissao` (`percodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cep_rural`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cep_rural`.`usuarios` (
  `usucodigo` INT(11) NOT NULL AUTO_INCREMENT,
  `usulogin` VARCHAR(45) NOT NULL,
  `ususenha` VARCHAR(45) NOT NULL,
  `usugrucodigo` INT(11) NOT NULL,
  PRIMARY KEY (`usucodigo`, `usugrucodigo`),
  INDEX `fk_Usuarios_Grupo_idx` (`usugrucodigo` ASC),
  CONSTRAINT `fk_Usuarios_Grupo`
    FOREIGN KEY (`usugrucodigo`)
    REFERENCES `cep_rural`.`grupo` (`grucodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cep_rural`.`logs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cep_rural`.`logs` (
  `logcodigo` INT(11) NOT NULL AUTO_INCREMENT,
  `logusuario` VARCHAR(45) NOT NULL,
  `logusucodigo` INT(11) NOT NULL,
  PRIMARY KEY (`logcodigo`, `logusucodigo`),
  INDEX `logusucodigo` (`logusucodigo` ASC),
  CONSTRAINT `logs_ibfk_1`
    FOREIGN KEY (`logusucodigo`)
    REFERENCES `cep_rural`.`usuarios` (`usucodigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cep_rural`.`usucep`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cep_rural`.`usucep` (
  `usucepusucodigo` INT(11) NOT NULL,
  `usucepcepcomcodigo` INT(11) NOT NULL,
  PRIMARY KEY (`usucepusucodigo`, `usucepcepcomcodigo`),
  INDEX `fk_usuarios_has_cep_cep1_idx` (`usucepcepcomcodigo` ASC),
  INDEX `fk_usuarios_has_cep_usuarios1_idx` (`usucepusucodigo` ASC),
  CONSTRAINT `fk_usuarios_has_cep_cep1`
    FOREIGN KEY (`usucepcepcomcodigo`)
    REFERENCES `cep_rural`.`cep` (`cepcomcodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_has_cep_usuarios1`
    FOREIGN KEY (`usucepusucodigo`)
    REFERENCES `cep_rural`.`usuarios` (`usucodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
