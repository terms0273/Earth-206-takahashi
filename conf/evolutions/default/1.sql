# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user_information (
  id                        bigint auto_increment not null,
  user_id                   varchar(255),
  user_name                 varchar(255),
  password                  varchar(255),
  type                      varchar(255),
  delete_flag               integer,
  constraint pk_user_information primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table user_information;

SET FOREIGN_KEY_CHECKS=1;

