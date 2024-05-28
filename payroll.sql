CREATE DATABASE payroll;
USE payroll;

INSERT INTO `user` (`username`,`password`,`enabled`) VALUES 
	('employee','$2a$12$TR2Fpy7.HjsdolPPDe9SJugg.F4qUpeRMPyMlDJN0n411qfM0XtWm',1),
	('accountant','$2a$12$q00ycEz8c8UGDME6r8GMOOly/Cn1j5u980R2VfmGhsDtX.vJTjvtO',1),
	('manager','$2a$12$LK6QqxMBvBf9rOkWSKSsTuENFGq.rmhu.djwtl7.Uin..N7S8XgD.',1);
	
INSERT INTO `role` (NAME) VALUES 
	('ROLE_EMPLOYEE'),('ROLE_ACCOUNTANT'),('ROLE_MANAGER');
	
INSERT INTO `users_roles` (user_id,role_id) VALUES 
	(1, 1),
	(2, 1),
	(2, 2),
	(3, 1),
	(3, 2),
	(3, 3);
	
	
#PASSWORD AKUN
#employee, karyawan123
#accountant, akuntan456
#manager, manager000

	
DROP TABLE payroll;
DROP TABLE absent_summary;
DROP TABLE employee;
DROP TABLE POSITION;

DROP DATABASE payroll;


#https://cdns.klimg.com/kapanlagi.com/g/2019/04/11/r/pas_foto_idol-20190411-002-rita.jpg
#https://i.pinimg.com/736x/f9/e1/1a/f9e11a06eecc1c266aaee7c23b3fb66e.jpg