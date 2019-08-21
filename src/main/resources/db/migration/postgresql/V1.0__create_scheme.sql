drop table if exists customer cascade;
drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start 1000 increment 1;

create table customer (
   id int8 not null,
    billing_city varchar(255) not null,
    billing_state varchar(255) not null,
    billing_street varchar(255) not null,
    billing_zip varchar(6) not null,
    code varchar(255) not null,
    name varchar(255) not null,
    shipping_city varchar(255) not null,
    shipping_state varchar(255) not null,
    shipping_street varchar(255) not null,
    shipping_zip varchar(6) not null,
    vat varchar(255) not null,
    version int4,
    primary key (id)
);
