-- MySQL Script generated by MySQL Workbench
-- Fri Dec 25 11:42:45 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sdek
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sdek
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sdek` DEFAULT CHARACTER SET utf8 ;
USE `sdek` ;

-- -----------------------------------------------------
-- Table `sdek`.`type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sdek`.`type` (
                                             `id` INT NOT NULL AUTO_INCREMENT,
                                             `type_of_client` TINYINT(5) NOT NULL,
                                             PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sdek`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sdek`.`client` (
                                               `id` INT NOT NULL AUTO_INCREMENT,
                                               `name` VARCHAR(45) NULL DEFAULT NULL,
                                               `data` VARCHAR(45) NULL DEFAULT NULL,
                                               `type_id` INT NOT NULL,
                                               PRIMARY KEY (`id`),
                                               INDEX `fk_client_type1_idx` (`type_id` ASC) VISIBLE,
                                               CONSTRAINT `fk_client_type1`
                                                   FOREIGN KEY (`type_id`)
                                                       REFERENCES `sdek`.`type` (`id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sdek`.`manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sdek`.`manager` (
                                                `id` INT NOT NULL AUTO_INCREMENT,
                                                `name` VARCHAR(45) NOT NULL,
                                                `office` VARCHAR(45) NOT NULL,
                                                PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sdek`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sdek`.`user` (
                                             `id` BIGINT NOT NULL AUTO_INCREMENT,
                                             `login` VARCHAR(25) NOT NULL,
                                             `password` VARCHAR(50) NOT NULL,
                                             `email` VARCHAR(25) NOT NULL,
                                             `role` ENUM('MANAGER', 'CLIENT') NULL DEFAULT NULL,
                                             `client_id` INT NULL,
                                             `manager_id` INT NULL,
                                             PRIMARY KEY (`id`),
                                             UNIQUE INDEX (`login` ASC) VISIBLE,
                                             UNIQUE INDEX (`email` ASC) VISIBLE,
                                             INDEX `fk_user_client1_idx` (`client_id` ASC) VISIBLE,
                                             INDEX `fk_user_manager1_idx` (`manager_id` ASC) VISIBLE,
                                             CONSTRAINT `fk_user_client1`
                                                 FOREIGN KEY (`client_id`)
                                                     REFERENCES `sdek`.`client` (`id`)
                                                     ON DELETE NO ACTION
                                                     ON UPDATE NO ACTION,
                                             CONSTRAINT `fk_user_manager1`
                                                 FOREIGN KEY (`manager_id`)
                                                     REFERENCES `sdek`.`manager` (`id`)
                                                     ON DELETE NO ACTION
                                                     ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sdek`.`truck`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sdek`.`truck` (
                                              `id` INT NOT NULL AUTO_INCREMENT,
                                              `truck_no` VARCHAR(45) NOT NULL,
                                              `length_capasity(cm)` MEDIUMINT(4) NOT NULL,
                                              `width_capasity(cm)` SMALLINT(3) NOT NULL,
                                              `height_capasity(cm)` MEDIUMINT(3) NOT NULL,
                                              `weight_capasity(kg)` MEDIUMINT(5) NOT NULL,
                                              `isbusy` TINYINT NOT NULL,
                                              `manager_id` INT NOT NULL,
                                              PRIMARY KEY (`id`),
                                              INDEX `fk_truck_manager_idx` (`manager_id` ASC) VISIBLE,
                                              CONSTRAINT `fk_truck_manager`
                                                  FOREIGN KEY (`manager_id`)
                                                      REFERENCES `sdek`.`manager` (`id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sdek`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sdek`.`order` (
                                              `id` INT NOT NULL AUTO_INCREMENT,
                                              `pickup adress` VARCHAR(45) NULL DEFAULT NULL,
                                              `city_pickup` VARCHAR(45) NOT NULL,
                                              `city_delivery` VARCHAR(45) NOT NULL,
                                              `unloading adress` VARCHAR(45) NULL DEFAULT NULL,
                                              `length_cm` MEDIUMINT(4) NOT NULL,
                                              `width_cm` SMALLINT(3) NOT NULL,
                                              `height_cm` MEDIUMINT(3) NOT NULL,
                                              `weight_kg` DOUBLE(7,2) NOT NULL,
                                              `date` DATE NOT NULL,
                                              `isactive` TINYINT NOT NULL,
                                              `price` DECIMAL(15,2) NULL DEFAULT NULL,
                                              `ordercol` VARCHAR(45) NULL DEFAULT NULL,
                                              `truck_id` INT NULL,
                                              `manager_id` INT NULL,
                                              `client_id` INT NOT NULL,
                                              PRIMARY KEY (`id`),
                                              INDEX `fk_order_truck1_idx` (`truck_id` ASC) VISIBLE,
                                              INDEX `fk_order_manager1_idx` (`manager_id` ASC) VISIBLE,
                                              INDEX `fk_order_client1_idx` (`client_id` ASC) VISIBLE,
                                              CONSTRAINT `fk_order_truck1`
                                                  FOREIGN KEY (`truck_id`)
                                                      REFERENCES `sdek`.`truck` (`id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION,
                                              CONSTRAINT `fk_order_manager1`
                                                  FOREIGN KEY (`manager_id`)
                                                      REFERENCES `sdek`.`manager` (`id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION,
                                              CONSTRAINT `fk_order_client1`
                                                  FOREIGN KEY (`client_id`)
                                                      REFERENCES `sdek`.`client` (`id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sdek`.`driver`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sdek`.`driver` (
                                               `id` INT NOT NULL AUTO_INCREMENT,
                                               `name` VARCHAR(45) NOT NULL,
                                               `licenseNo` VARCHAR(45) NOT NULL,
                                               `isbusy` TINYINT NOT NULL,
                                               `medical_aprovement` DATE NULL DEFAULT NULL,
                                               PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sdek`.`driver_has_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sdek`.`driver_has_order` (
                                                         `id_truck_driver` INT NOT NULL AUTO_INCREMENT,
                                                         `driver_id` INT NOT NULL,
                                                         `order_id` INT NOT NULL,
                                                         INDEX `fk_truck_has_driver_driver1_idx` (`driver_id` ASC) VISIBLE,
                                                         PRIMARY KEY (`id_truck_driver`),
                                                         INDEX `fk_driver_has_order_order1_idx` (`order_id` ASC) VISIBLE,
                                                         CONSTRAINT `fk_truck_has_driver_driver1`
                                                             FOREIGN KEY (`driver_id`)
                                                                 REFERENCES `sdek`.`driver` (`id`)
                                                                 ON DELETE NO ACTION
                                                                 ON UPDATE NO ACTION,
                                                         CONSTRAINT `fk_driver_has_order_order1`
                                                             FOREIGN KEY (`order_id`)
                                                                 REFERENCES `sdek`.`order` (`id`)
                                                                 ON DELETE NO ACTION
                                                                 ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;