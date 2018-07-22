alter table user_profile disable trigger all;
insert into user_profile (id, created_dt, created_by, updated_dt, updated_by,fname,mname,lname,email) values (1, localtimestamp,3,localtimestamp,3,'Admin',null,'Admin','admin@aspex.com');
insert into user_profile (id, created_dt, created_by, updated_dt, updated_by,fname,mname,lname,email) values (2, localtimestamp,3,localtimestamp,3,'Michael','B.','Stoeckel','mstoeckel@aspex.com');
insert into users (id, created_dt, created_by, updated_dt, updated_by,username, user_profile_id) values (3,localtimestamp,3,localtimestamp,3,'admin', 1);
insert into users (id, created_dt, created_by, updated_dt, updated_by,username, user_profile_id) values (4,localtimestamp,3,localtimestamp,3,'mstoeckel', 2);
alter table user_profile enable trigger all;

insert into groups(id, created_dt, created_by, updated_dt, updated_by,name, description) values(5,localtimestamp,3,localtimestamp,3,'admin', 'Administration Group');
insert into user_group_xref(user_id, group_id) values(3,5);
insert into user_group_xref(user_id, group_id) values(4,5);

insert into organization(id, created_dt, created_by, updated_dt, updated_by,name, abbrev, parent_id) values(10,localtimestamp,3,localtimestamp,3,'Department of Justice', 'DoJ', null);
insert into organization(id, created_dt, created_by, updated_dt, updated_by,name, abbrev, parent_id) values(11,localtimestamp,3,localtimestamp,3,'Federal Bureau of Investigation', 'FBI', 10);
insert into organization(id, created_dt, created_by, updated_dt, updated_by,name, abbrev, parent_id) values(12,localtimestamp,3,localtimestamp,3,'Drug Enforcement Administration', 'DEA', 10);
insert into organization(id, created_dt, created_by, updated_dt, updated_by,name, abbrev, parent_id) values(13,localtimestamp,3,localtimestamp,3,'US Attorneys', 'USA', 10);
insert into organization(id, created_dt, created_by, updated_dt, updated_by,name, abbrev, parent_id) values(14,localtimestamp,3,localtimestamp,3,'Information Technlogy Branch', 'ITB', 11);
insert into organization(id, created_dt, created_by, updated_dt, updated_by,name, abbrev, parent_id) values(15,localtimestamp,3,localtimestamp,3,'Information Technlogy and Application Division', 'ITAD', 14);
insert into organization(id, created_dt, created_by, updated_dt, updated_by,name, abbrev, parent_id) values(16,localtimestamp,3,localtimestamp,3,'Enterprise Data Analytics Section', 'EDAS', 15);
insert into organization(id, created_dt, created_by, updated_dt, updated_by,name, abbrev, parent_id) values(17,localtimestamp,3,localtimestamp,3,'Data Analytics Framework Unit', 'DAFU', 16);

select setval('example_seq', 100);