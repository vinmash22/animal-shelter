-- liquibase formatted sql

-- changeset ZhdanovDM:1
CREATE table users (
id bigserial primary key,
name VARCHAR(255),
age BIGINT,
contacts VARCHAR(255),
passport BIGINT
)