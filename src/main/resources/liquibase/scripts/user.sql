-- liquibase formatted sql

-- changeset ZhdanovDM:1
CREATE TABLE users (
id bigserial primary key,
name VARCHAR(255),
age BIGINT,
contacts VARCHAR(255),
passport BIGINT
)

CREATE TABLE animals (
id bigserial primary key,
type VARCHAR(255),
breed VARCHAR(255),
sex VARCHAR(255),
age BIGINT,
color VARCHAR(255),
name VARCHAR(255),
date DATE,
id_chat VARCHAR(255),
report_text  VARCHAR(255)
)