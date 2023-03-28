SET GLOBAL time_zone = '+00:00';

DROP DATABASE IF EXISTS API_APPLICATION;
CREATE DATABASE API_APPLICATION;
USE API_APPLICATION;

ALTER DATABASE COLLATE 'utf8mb4_bin';

DROP TABLE IF EXISTS `USER`;
CREATE TABLE IF NOT EXISTS `USER`
(
    `ID`          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,

    `USERNAME`    VARCHAR(100) NOT NULL,
    `PASSWORD`    VARCHAR(100) NOT NULL,
    `ACTOR`       INT          NOT NULL,
    `RESET_CODE`  VARCHAR(100)          DEFAULT NULL,
    `RESET_UNTIL` TIMESTAMP             DEFAULT NULL,

    `CREATED_AT`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY`  VARCHAR(20)  NOT NULL,
    `UPDATED_AT`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `UPDATED_BY`  VARCHAR(20)  NOT NULL,
    `DELETED_AT`  TIMESTAMP    NULL,
    `DELETED_BY`  VARCHAR(20)  NULL,
    UNIQUE KEY `UK01_USER` (`USERNAME`)
);

DROP TABLE IF EXISTS `USER_TOKEN`;
CREATE TABLE IF NOT EXISTS `USER_TOKEN`
(
    `ID`            INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,

    `USER_ID`       INT          NOT NULL,
    `ACCESS_TOKEN`  VARCHAR(256) NOT NULL,
    `REFRESH_TOKEN` VARCHAR(64)  NOT NULL,
    `EXPIRES_AT`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `CREATED_AT`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY`    VARCHAR(20)  NOT NULL,
    `DELETED_AT`    TIMESTAMP    NULL,
    `DELETED_BY`    VARCHAR(20)  NULL,
    CONSTRAINT `FK01_USER_TOKEN` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`ID`)
);

DROP TABLE IF EXISTS `SUPER_USER`;
CREATE TABLE IF NOT EXISTS `SUPER_USER`
(
    `ID`             INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,

    `SUPER_USER_ID`  INT         NOT NULL,
    `TARGET_USER_ID` INT         NOT NULL,
    `EXPIRES_AT`     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `CREATED_AT`     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY`     VARCHAR(20) NOT NULL,
    `UPDATED_AT`     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `UPDATED_BY`     VARCHAR(20) NOT NULL,
    `DELETED_AT`     TIMESTAMP   NULL,
    `DELETED_BY`     VARCHAR(20) NULL,
    CONSTRAINT `FK01_SUPER_USER` FOREIGN KEY (`SUPER_USER_ID`) REFERENCES `USER` (`ID`),
    CONSTRAINT `FK02_SUPER_USER` FOREIGN KEY (`TARGET_USER_ID`) REFERENCES `USER` (`ID`)
);

DROP TABLE IF EXISTS `ROLE`;
CREATE TABLE IF NOT EXISTS `ROLE`
(
    `ID`         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,

    `ACTOR`      INT          NOT NULL,
    `NAME`       VARCHAR(100) NOT NULL,

    `CREATED_AT` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY` VARCHAR(20)  NOT NULL,
    `DELETED_AT` TIMESTAMP    NULL,
    `DELETED_BY` VARCHAR(20)  NULL
);

DROP TABLE IF EXISTS `USER_ROLE`;
CREATE TABLE IF NOT EXISTS `USER_ROLE`
(
    `ID`         INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,

    `USER_ID`    INT         NOT NULL,
    `ROLE_ID`    INT         NOT NULL,

    `CREATED_AT` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY` VARCHAR(20) NOT NULL,
    `DELETED_AT` TIMESTAMP   NULL,
    `DELETED_BY` VARCHAR(20) NULL,
    CONSTRAINT `FK01_USER_ROLE` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`ID`),
    CONSTRAINT `FK02_USER_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `ROLE` (`ID`)
);

DROP TABLE IF EXISTS `POLICY`;
CREATE TABLE IF NOT EXISTS `POLICY`
(
    `ID`         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,

    `ACTOR`      INT          NOT NULL,
    `SERVICE`    INT          NOT NULL,
    `ACTION`     VARCHAR(100) NOT NULL,

    `CREATED_AT` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY` VARCHAR(20)  NOT NULL,
    `DELETED_AT` TIMESTAMP    NULL,
    `DELETED_BY` VARCHAR(20)  NULL
);

DROP TABLE IF EXISTS `ROLE_POLICY`;
CREATE TABLE IF NOT EXISTS `ROLE_POLICY`
(
    `ID`         INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,

    `ROLE_ID`    INT         NOT NULL,
    `POLICY_ID`  INT         NOT NULL,

    `CREATED_AT` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY` VARCHAR(20) NOT NULL,
    `DELETED_AT` TIMESTAMP   NULL,
    `DELETED_BY` VARCHAR(20) NULL,
    CONSTRAINT `FK01_ROLE_POLICY` FOREIGN KEY (`ROLE_ID`) REFERENCES `ROLE` (`ID`),
    CONSTRAINT `FK02_ROLE_POLICY` FOREIGN KEY (`POLICY_ID`) REFERENCES `POLICY` (`ID`)
);

DROP TABLE IF EXISTS `API_LOG`;
CREATE TABLE IF NOT EXISTS `API_LOG`
(
    `ID_API_LOG`            INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `OPERATION_TIME`        TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `OPERATOR`              VARCHAR(20) NOT NULL,
    `METHOD`                VARCHAR(10)          DEFAULT NULL,
    `PATH`                  VARCHAR(256)         DEFAULT NULL,
    `QUERY`                 VARCHAR(2000)        DEFAULT NULL,
    `REQUEST_BODY`          LONGTEXT,
    `STATUS`                INT         NOT NULL,
    `REMOTE_IP`             VARCHAR(40) NOT NULL,
    `EXECUTION_DURATION_MS` LONG        NOT NULL,
    `EXCEPTION`             LONGTEXT
);


DROP DATABASE IF EXISTS YOUR_APPLICATION;
CREATE DATABASE YOUR_APPLICATION;
USE YOUR_APPLICATION;
/*
 below are tables for sample
 change them to the tables you need
 */

DROP TABLE IF EXISTS `SAMPLE`;
CREATE TABLE IF NOT EXISTS `SAMPLE`
(
    `ID`          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `NAME`        VARCHAR(400) NOT NULL,
    `BIRTHDAY`    TIMESTAMP    NOT NULL,
    `IS_JAPANESE` BOOLEAN      NOT NULL
);