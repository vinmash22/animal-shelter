-- liquibase formatted sql

-- changeset ZhdanovDM:3
CREATE TABLE users (
id bigserial primary key,
id_chat VARCHAR(255),
name VARCHAR(255),
age BIGINT,
phone VARCHAR(255),
role VARCHAR(255),
animal_id bigserial
)