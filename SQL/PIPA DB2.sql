DROP DATABASE IF EXISTS new_personnel;
CREATE DATABASE new_personnel;
USE new_personnel;

CREATE TABLE DEPT(DEPTNO INT(2), DNAME VARCHAR(14), LOC VARCHAR(14), PRIMARY KEY(DEPTNO));
INSERT INTO DEPT VALUES (50, 'SALES', 'ATHENS');
INSERT INTO DEPT VALUES (60, 'ACCOUNTING', 'ATHENS');
INSERT INTO DEPT VALUES (70, 'PAYROLL', 'VOLOS');

CREATE TABLE EMP(EMPNO INT(2), ENAME VARCHAR(14), JOBCODE VARCHAR(14), DEPTNO INT(2), COMM FLOAT(5,2), PRIMARY KEY(EMPNO), FOREIGN KEY(DEPTNO) REFERENCES DEPT(DEPTNO));
INSERT INTO EMP VALUES (10, 'CODD', 100, 50, NULL);
INSERT INTO EMP VALUES (20, 'NAVATHE', 200, 50, 450.0);
INSERT INTO EMP VALUES (30, 'ELMASRI', 300, 60, NULL);
INSERT INTO EMP VALUES (40, 'DATE', 100, 50, NULL);

CREATE TABLE JOB(JOBCODE INT(3) NOT NULL,JOB_DESCR VARCHAR(14), SAL FLOAT(6,2), PRIMARY KEY(JOBCODE));
INSERT INTO JOB VALUES (100,'SALESMAN',2000);
INSERT INTO JOB VALUES (200,'ANALYST',2000);
INSERT INTO JOB VALUES (300,'DBA',3000);

create table Accounts ( acctID integer not null primary key,
Balance integer not null);
insert into Accounts (acctID, Balance) values (101, 1000);
insert into Accounts (acctID, Balance) values (202, 2000);
insert into Accounts (acctID, Balance) values (303, 2500);
insert into Accounts (acctID, Balance) values (404, 3000); 

create table CUSTOMERS(CUSTNO integer, CUST_NAME varchar(30));
show tables;
insert into CUSTOMERS values (10,101), (20,202);
select * from CUSTOMERS;
describe CUSTOMERS;

alter table Accounts add CUSTNO integer;
alter table Accounts add foreign key(CUSTNO) references CUSTOMERS(CUSTNO);
update Accounts set CUSTNO='10' where acctID='101';
update Accounts set CUSTNO='10' where acctID='303';
update Accounts set CUSTNO='10' where acctID='404';
update Accounts set CUSTNO='20' where acctID='202';
select * from Accounts;
describe Accounts; 

select CUSTNO, count(*), sum(Balance)
from Accounts
where CUSTNO not in (20)
group by CUSTNO;

