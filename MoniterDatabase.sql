drop database if exists MoniterData;
create database MoniterData;
use MoniterData;
create table 事件记录
(
时间 datetime not null,
IP地址 varchar(64) not null,
设备型号 varchar(64) not null,
`SESSION编号` varchar(64),
所在页面 varchar(255) not null,
用户 varchar(255),
事件类型 varchar(255) not null,
事件描述 varchar(255)
);
drop user if exists MoniterData@localhost;
create user MoniterData@localhost identified by 'MoniterData';
grant create,select,update,insert on MoniterData.* to MoniterData@localhost;
flush privileges;