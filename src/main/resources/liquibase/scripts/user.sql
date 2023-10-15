-- liquibase formatted sql
-- changeset Mariya:1

CREATE TABLE users (
id bigserial primary key,
chatId BIGINT,
name VARCHAR(255),
age INT,
phone VARCHAR(255),
role VARCHAR(255),
animalId BIGINT
);

CREATE TABLE animals (
id bigserial primary key,
type VARCHAR(255),
breed VARCHAR(255),
sex VARCHAR(255),
age INT,
color VARCHAR(255),
name VARCHAR(255),
date DATE,
chatId BIGINT,
reportText  VARCHAR(255)
);
