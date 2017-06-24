# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table storage (
  id                            integer auto_increment not null,
  place                         varchar(255),
  type                          varchar(255),
  username                      varchar(255),
  password                      varchar(255),
  constraint pk_storage primary key (id)
);


# --- !Downs

drop table if exists storage;

