--liquibase formatted sql

--changeset dev:004-init-customer-adresse-data

-- Création des tables
CREATE TABLE IF NOT EXISTS customer (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS adresse (
    id BIGSERIAL PRIMARY KEY,
    rue VARCHAR(255),
    ville VARCHAR(100),
    code_postal VARCHAR(20),
    pays VARCHAR(100),
    customer_id BIGINT NOT NULL,
    CONSTRAINT fk_adresse_customer FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);