ALTER TABLE `ucm_mba`.`students` 
CHANGE COLUMN `PHONE_NUMBER` `PHONE_NUMBER` BIGINT(14) NOT NULL ;

ALTER TABLE `ucm_mba`.`student_concentration` 
DROP COLUMN `ENTRY_DATE`;

ALTER TABLE `ucm_mba`.`student_concentration` 
DROP FOREIGN KEY `std_fk_std_id`;
