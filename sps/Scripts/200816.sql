CREATE TABLE `mes_maint`.`review_notes` (
  `REVIEW_NOTE_ID` INT(10) NOT NULL,
  `STUDENT_ID` INT(10) NOT NULL,
  `NOTES` VARCHAR(1024) NULL,
  PRIMARY KEY (`REVIEW_NOTE_ID`));
  
  
ALTER TABLE `ucm_mba`.`student_concentration` 
DROP COLUMN `ACCEPTED`,
DROP COLUMN `WITHDRAWN`,
DROP COLUMN `APPROVED`;

ALTER TABLE `ucm_mba`.`student_concentration` 
ADD COLUMN `STATUS` VARCHAR(45) NULL AFTER `EMAIL_TEMPLATE_ID`;
