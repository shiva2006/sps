ALTER TABLE `ucm_mba`.`email_template` 
ADD COLUMN `STUDENT_ID` INT(10) NULL AFTER `EMAIL_TEMPLATE_ID`,
ADD INDEX `email_template_fk_std_id_idx` (`STUDENT_ID` ASC);
ALTER TABLE `ucm_mba`.`email_template` 
ADD CONSTRAINT `email_template_fk_std_id`
  FOREIGN KEY (`STUDENT_ID`)
  REFERENCES `ucm_mba`.`students` (`STUDENT_ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
