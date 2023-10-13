- liquibase formatted sql

- changeset ZhdanovDM:1

CREATE TABLE users (
id bigserial primary key,
chatId BIGINT,
name VARCHAR(255),
age BIGINT,
phone VARCHAR(255),
role VARCHAR(255),
animal_id bigserial
);

- changeset ZhdanovDM:2

CREATE TABLE animals (
id bigserial primary key,
type VARCHAR(255),
breed VARCHAR(255),
sex VARCHAR(255),
age BIGINT,
color VARCHAR(255),
name VARCHAR(255),
date DATE,
chatId BIGINT,
report_text  VARCHAR(255)
);


