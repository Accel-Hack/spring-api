USE API_APPLICATION;

ALTER TABLE API_APPLICATION.USER_TOKEN
    ADD CONSTRAINT `FK01_USER_TOKEN` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`ID`);

USE YOUR_APPLICATION;