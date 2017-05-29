# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user_information (
  id                        bigint not null,
  user_id                   varchar(255),
  user_name                 varchar(255),
  password                  varchar(255),
  type                      varchar(255),
  delete_flag               integer,
  constraint pk_user_information primary key (id))
;

create sequence user_information_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists user_information;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_information_seq;

