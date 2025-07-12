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

-- Insertion des customers (clé unique = email)
INSERT INTO customer (first_name, last_name, email) VALUES
    ('Alice', 'Martin', 'alice.martin@example.com'),
    ('Bob', 'Dupont', 'bob.dupont@example.com'),
    ('Chloe', 'Durand', 'chloe.durand@example.com')
ON CONFLICT (email) DO NOTHING;

-- Ajout d'une contrainte unique composite pour l'adresse
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM pg_indexes WHERE indexname = 'adresse_unique_rue_client'
  ) THEN
    CREATE UNIQUE INDEX adresse_unique_rue_client
    ON adresse (rue, code_postal, customer_id);
  END IF;
END $$;

-- Insertion d'adresses avec ON CONFLICT DO NOTHING
INSERT INTO adresse (rue, ville, code_postal, pays, customer_id) VALUES
    ('123 Rue Lafayette', 'Paris', '75010', 'France', (SELECT id FROM customer WHERE email = 'alice.martin@example.com')),
    ('15 Avenue des Champs-Élysées', 'Paris', '75008', 'France', (SELECT id FROM customer WHERE email = 'alice.martin@example.com')),
    ('22 Boulevard Haussmann', 'Lyon', '69006', 'France', (SELECT id FROM customer WHERE email = 'bob.dupont@example.com')),
    ('5 Rue de la République', 'Marseille', '13001', 'France', (SELECT id FROM customer WHERE email = 'chloe.durand@example.com'))
ON CONFLICT ON CONSTRAINT adresse_unique_rue_client DO NOTHING;
