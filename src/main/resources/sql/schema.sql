SET GLOBAL time_zone = '+00:00';

DROP DATABASE IF EXISTS API_APPLICATION;
CREATE DATABASE API_APPLICATION CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE API_APPLICATION;

DROP TABLE IF EXISTS `USER`;
CREATE TABLE IF NOT EXISTS `USER`
(
    `ID`          CHAR(36)     NOT NULL PRIMARY KEY,

    `USERNAME`    VARCHAR(100) NOT NULL,
    `PASSWORD`    VARCHAR(100) NOT NULL,
    `ACTOR`       INT          NOT NULL,
    `RESET_CODE`  VARCHAR(100)          DEFAULT NULL,
    `RESET_UNTIL` TIMESTAMP             DEFAULT NULL,

    `CREATED_AT`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY`  VARCHAR(36)  NOT NULL,
    `UPDATED_AT`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `UPDATED_BY`  VARCHAR(36)  NOT NULL,
    `DELETED_AT`  TIMESTAMP    NULL,
    `DELETED_BY`  VARCHAR(36)  NULL,
    UNIQUE KEY `UK01_USER` (`USERNAME`)
);

DROP TABLE IF EXISTS `USER_TOKEN`;
CREATE TABLE IF NOT EXISTS `USER_TOKEN`
(
    `ID`            CHAR(36)     NOT NULL PRIMARY KEY,

    `USER_ID`       CHAR(36)     NOT NULL,
    `ACCESS_TOKEN`  VARCHAR(256) NOT NULL,
    `REFRESH_TOKEN` VARCHAR(64)  NOT NULL,
    `EXPIRES_AT`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `CREATED_AT`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY`    VARCHAR(36)  NOT NULL,
    `DELETED_AT`    TIMESTAMP    NULL,
    `DELETED_BY`    VARCHAR(36)  NULL,
    UNIQUE KEY `UK01_USER_TOKEN` (`ACCESS_TOKEN`)
);

DROP TABLE IF EXISTS `API_LOG`;
CREATE TABLE IF NOT EXISTS `API_LOG`
(
    `ID`                    CHAR(36)     NOT NULL PRIMARY KEY,
    `OPERATOR`              VARCHAR(36)  NOT NULL,
    `SESSION_ID`            VARCHAR(40)  NOT NULL,
    `REMOTE_ADDRESS`        VARCHAR(40)  NOT NULL,
    `OPERATION_TIME`        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `EXECUTION_DURATION_MS` LONG                  DEFAULT NULL,
    `METHOD`                VARCHAR(10)  NOT NULL,
    `PATH`                  VARCHAR(256) NOT NULL,
    `QUERY`                 VARCHAR(1024)         DEFAULT NULL,
    `BODY`                  LONGTEXT              DEFAULT NULL,
    `STATUS`                INT                   DEFAULT NULL,
    `RESPONSE`              LONGTEXT              DEFAULT NULL,
    `EXCEPTION`             LONGTEXT              DEFAULT NULL
);

DROP DATABASE IF EXISTS YOUR_APPLICATION;
CREATE DATABASE YOUR_APPLICATION CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE YOUR_APPLICATION;

/*
 below are tables for sample
 change them to the tables you need
 */

DROP TABLE IF EXISTS `SAMPLE`;
CREATE TABLE IF NOT EXISTS `SAMPLE`
(
    `ID`          CHAR(36)     NOT NULL PRIMARY KEY,
    `NAME`        VARCHAR(400) NOT NULL,
    `BIRTHDAY`    TIMESTAMP    NOT NULL,
    `IS_JAPANESE` BOOLEAN      NOT NULL,
    `CREATED_AT`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `UPDATED_AT`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `IS_DELETED`  BOOLEAN      NOT NULL DEFAULT FALSE,
    `DELETED_AT`  TIMESTAMP    NULL
);