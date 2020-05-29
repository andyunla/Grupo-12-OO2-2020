-- MySQL Script generated by MySQL Workbench
-- sáb 16 may 2020 16:53:28 -03
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Grupo-12-BDD-OO2-2020
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Grupo-12-BDD-OO2-2020` ;

-- -----------------------------------------------------
-- Schema Grupo-12-BDD-OO2-2020
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Grupo-12-BDD-OO2-2020` DEFAULT CHARACTER SET utf8 ;
USE `Grupo-12-BDD-OO2-2020` ;

-- -----------------------------------------------------
-- Table `Grupo-12-BDD-OO2-2020`.`persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Grupo-12-BDD-OO2-2020`.`persona` ;

CREATE TABLE IF NOT EXISTS `Grupo-12-BDD-OO2-2020`.`persona` (
  `id_persona` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `dni` INT NULL,
  `fecha_nacimiento` DATE NULL,
  PRIMARY KEY (`id_persona`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Grupo-12-BDD-OO2-2020`.`empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Grupo-12-BDD-OO2-2020`.`empleado` ;

CREATE TABLE IF NOT EXISTS `Grupo-12-BDD-OO2-2020`.`empleado` (
  `id_persona` INT(11) NOT NULL,
  `legajo` INT(11) NULL AUTO_INCREMENT,
  `horario_desde` TIME NULL,
  `horario_hasta` TIME NULL,
  `sueldo_basico` DOUBLE NULL,
  `id_local` INT(11) NOT NULL,
  `tipo_gerente` TINYINT(1) NULL DEFAULT 0,
  INDEX `fk_empleado_local1_idx` (`id_local` ASC),
  PRIMARY KEY (`id_persona`),
  UNIQUE INDEX `legajo_UNIQUE` (`legajo` ASC),
  CONSTRAINT `fk_empleado_local1`
    FOREIGN KEY (`id_local`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`locales` (`id_local`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_empleado_persona1`
    FOREIGN KEY (`id_persona`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`persona` (`id_persona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Grupo-12-BDD-OO2-2020`.`locales`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Grupo-12-BDD-OO2-2020`.`locales` ;

CREATE TABLE IF NOT EXISTS `Grupo-12-BDD-OO2-2020`.`locales` (
  `id_local` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre_local` VARCHAR(45) NULL,
  `latitud` DOUBLE NULL,
  `longitud` DOUBLE NULL,
  `direccion` VARCHAR(45) NULL,
  `telefono` INT NULL,
  `gerente_legajo` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_local`),
  INDEX `fk_locales_empleado1_idx` (`gerente_legajo` ASC),
  CONSTRAINT `fk_locales_empleado1`
    FOREIGN KEY (`gerente_legajo`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`empleado` (`legajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Grupo-12-BDD-OO2-2020`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Grupo-12-BDD-OO2-2020`.`cliente` ;

CREATE TABLE IF NOT EXISTS `Grupo-12-BDD-OO2-2020`.`cliente` (
  `id_persona` INT(11) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `nro_cliente` INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_persona`),
  UNIQUE INDEX `nro_cliente_UNIQUE` (`nro_cliente` ASC),
  CONSTRAINT `fk_cliente_persona1`
    FOREIGN KEY (`id_persona`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`persona` (`id_persona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Grupo-12-BDD-OO2-2020`.`producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Grupo-12-BDD-OO2-2020`.`producto` ;

CREATE TABLE IF NOT EXISTS `Grupo-12-BDD-OO2-2020`.`producto` (
  `id_producto` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `precio` DOUBLE NOT NULL,
  `talle` INT NOT NULL,
  PRIMARY KEY (`id_producto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Grupo-12-BDD-OO2-2020`.`pedido_stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Grupo-12-BDD-OO2-2020`.`pedido_stock` ;

CREATE TABLE IF NOT EXISTS `Grupo-12-BDD-OO2-2020`.`pedido_stock` (
  `id_pedido_stock` INT(11) NOT NULL AUTO_INCREMENT,
  `cantidad` INT NOT NULL,
  `aceptado` TINYINT(1) NOT NULL,
  `id_producto` INT(11) NOT NULL,
  `solicitante_legajo` INT(11) NOT NULL,
  `oferente_legajo` INT(11) NULL,
  PRIMARY KEY (`id_pedido_stock`),
  INDEX `fk_pedidostock_producto1_idx` (`id_producto` ASC),
  INDEX `fk_pedido_stock_empleado1_idx` (`solicitante_legajo` ASC),
  INDEX `fk_pedido_stock_empleado2_idx` (`oferente_legajo` ASC),
  CONSTRAINT `fk_pedidostock_producto1`
    FOREIGN KEY (`id_producto`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`producto` (`id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_stock_empleado1`
    FOREIGN KEY (`solicitante_legajo`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`empleado` (`legajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_stock_empleado2`
    FOREIGN KEY (`oferente_legajo`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`empleado` (`legajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Grupo-12-BDD-OO2-2020`.`chango`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Grupo-12-BDD-OO2-2020`.`chango` ;

CREATE TABLE IF NOT EXISTS `Grupo-12-BDD-OO2-2020`.`chango` (
  `id_chango` INT(11) NOT NULL AUTO_INCREMENT,
  `id_pedido_stock` INT(11) NULL DEFAULT NULL,
  `id_local` INT(11) NOT NULL,
  PRIMARY KEY (`id_chango`),
  INDEX `fk_chango_pedidostock1_idx` (`id_pedido_stock` ASC),
  INDEX `fk_chango_local1_idx` (`id_local` ASC),
  CONSTRAINT `fk_chango_pedidostock1`
    FOREIGN KEY (`id_pedido_stock`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`pedido_stock` (`id_pedido_stock`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chango_local1`
    FOREIGN KEY (`id_local`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`locales` (`id_local`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Grupo-12-BDD-OO2-2020`.`factura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Grupo-12-BDD-OO2-2020`.`factura` ;

CREATE TABLE IF NOT EXISTS `Grupo-12-BDD-OO2-2020`.`factura` (
  `id_factura` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha_factura` DATE NOT NULL,
  `coste_total` DOUBLE NOT NULL,
  `id_local` INT(11) NOT NULL,
  `empleado_legajo` INT(11) NOT NULL,
  `nro_cliente` INT(11) NOT NULL,
  `id_chango` INT(11) NOT NULL,
  INDEX `fk_factura_local1_idx` (`id_local` ASC),
  INDEX `fk_factura_cliente1_idx` (`nro_cliente` ASC),
  INDEX `fk_factura_empleado1_idx` (`empleado_legajo` ASC),
  PRIMARY KEY (`id_factura`),
  INDEX `fk_factura_chango1_idx` (`id_chango` ASC),
  CONSTRAINT `fk_factura_local1`
    FOREIGN KEY (`id_local`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`locales` (`id_local`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_factura_cliente1`
    FOREIGN KEY (`nro_cliente`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`cliente` (`nro_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_factura_empleado1`
    FOREIGN KEY (`empleado_legajo`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`empleado` (`legajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_factura_chango1`
    FOREIGN KEY (`id_chango`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`chango` (`id_chango`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Grupo-12-BDD-OO2-2020`.`lote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Grupo-12-BDD-OO2-2020`.`lote` ;

CREATE TABLE IF NOT EXISTS `Grupo-12-BDD-OO2-2020`.`lote` (
  `id_lote` INT(11) NOT NULL AUTO_INCREMENT,
  `cantidad_inicial` INT NOT NULL,
  `cantidad_actual` INT NOT NULL,
  `fecha_ingreso` DATE NOT NULL,
  `activo` TINYINT(1) NOT NULL DEFAULT 1,
  `id_producto` INT(11) NOT NULL,
  `id_local` INT(11) NOT NULL,
  PRIMARY KEY (`id_lote`),
  INDEX `fk_lote_producto1_idx` (`id_producto` ASC),
  INDEX `fk_lote_local1_idx` (`id_local` ASC),
  CONSTRAINT `fk_lote_producto1`
    FOREIGN KEY (`id_producto`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`producto` (`id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lote_local1`
    FOREIGN KEY (`id_local`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`locales` (`id_local`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Grupo-12-BDD-OO2-2020`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Grupo-12-BDD-OO2-2020`.`item` ;

CREATE TABLE IF NOT EXISTS `Grupo-12-BDD-OO2-2020`.`item` (
  `id_item` INT(11) NOT NULL AUTO_INCREMENT,
  `cantidad` INT NOT NULL,
  `id_chango` INT(11) NOT NULL,
  `id_producto` INT(11) NOT NULL,
  PRIMARY KEY (`id_item`),
  INDEX `fk_item_chango1_idx` (`id_chango` ASC),
  INDEX `fk_item_producto1_idx` (`id_producto` ASC),
  CONSTRAINT `fk_item_chango1`
    FOREIGN KEY (`id_chango`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`chango` (`id_chango`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_producto1`
    FOREIGN KEY (`id_producto`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`producto` (`id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- TABLA PARA MANEJO DE SESIONES DE USUARIO
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Grupo-12-BDD-OO2-2020`.`user` ;
CREATE TABLE IF NOT EXISTS `Grupo-12-BDD-OO2-2020`.`user` (
  `id_user` INT(11) NOT NULL AUTO_INCREMENT,
  `empleado_legajo` INT(11) NOT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(70) NOT NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT 1,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_user`),
  INDEX `fk_user_empleado1_idx` (`empleado_legajo` ASC),
  CONSTRAINT `fk_user_empleado1`
    FOREIGN KEY (`empleado_legajo`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`empleado` (`legajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

DROP TABLE IF EXISTS `Grupo-12-BDD-OO2-2020`.`user_role` ;
CREATE TABLE IF NOT EXISTS `Grupo-12-BDD-OO2-2020`.`user_role` (
  `id_user_role` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `role` VARCHAR(100) NOT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_user_role`),
  INDEX `fk_user_role_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_role_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `Grupo-12-BDD-OO2-2020`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET global TIME_ZONE='-3:00';