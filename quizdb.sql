-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema quizdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `quizdb` ;

-- -----------------------------------------------------
-- Schema quizdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `quizdb` DEFAULT CHARACTER SET utf8 ;
USE `quizdb` ;

-- -----------------------------------------------------
-- Table `quiz`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quiz` ;

CREATE TABLE IF NOT EXISTS `quiz` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `question` ;

CREATE TABLE IF NOT EXISTS `question` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `quiz_id` INT UNSIGNED NOT NULL,
  `question_text` VARCHAR(300) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_question_quiz_id_idx` (`quiz_id` ASC),
  CONSTRAINT `fk_question_quiz_id`
    FOREIGN KEY (`quiz_id`)
    REFERENCES `quiz` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `answer` ;

CREATE TABLE IF NOT EXISTS `answer` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `question_id` INT UNSIGNED NOT NULL,
  `answer_text` VARCHAR(300) NOT NULL,
  `is_correct` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_answer_question_id_idx` (`question_id` ASC),
  CONSTRAINT `fk_answer_question_id`
    FOREIGN KEY (`question_id`)
    REFERENCES `question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `quiz`
-- -----------------------------------------------------
START TRANSACTION;
USE `quizdb`;
INSERT INTO `quiz` (`id`, `name`) VALUES (1, 'quiz1');
INSERT INTO `quiz` (`id`, `name`) VALUES (2, 'quiz2');
INSERT INTO `quiz` (`id`, `name`) VALUES (3, 'quiz3');

COMMIT;


-- -----------------------------------------------------
-- Data for table `question`
-- -----------------------------------------------------
START TRANSACTION;
USE `quizdb`;
INSERT INTO `question` (`id`, `quiz_id`, `question_text`) VALUES (1, 1, 'how do you see all questions?');

COMMIT;


-- -----------------------------------------------------
-- Data for table `answer`
-- -----------------------------------------------------
START TRANSACTION;
USE `quizdb`;
INSERT INTO `answer` (`id`, `question_id`, `answer_text`, `is_correct`) VALUES (1, 1, 'select * from question', 1);

COMMIT;

