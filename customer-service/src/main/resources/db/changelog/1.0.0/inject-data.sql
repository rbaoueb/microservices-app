
-- Insertion des customers (clé unique = email)
INSERT INTO customer (first_name, last_name, email) VALUES
    ('Alice', 'Martin', 'alice.martin@example.com'),
    ('Bob', 'Dupont', 'bob.dupont@example.com'),
    ('Chloe', 'Durand', 'chloe.durand@example.com')
ON CONFLICT DO NOTHING;

-- Insertion d'adresses avec ON CONFLICT DO NOTHING
INSERT INTO adresse (rue, ville, code_postal, pays, customer_id) VALUES
    ('123 Rue Lafayette', 'Paris', '75010', 'France', (SELECT id FROM customer WHERE email = 'alice.martin@example.com')),
    ('15 Avenue des Champs-Élysées', 'Paris', '75008', 'France', (SELECT id FROM customer WHERE email = 'alice.martin@example.com')),
    ('22 Boulevard Haussmann', 'Lyon', '69006', 'France', (SELECT id FROM customer WHERE email = 'bob.dupont@example.com')),
    ('5 Rue de la République', 'Marseille', '13001', 'France', (SELECT id FROM customer WHERE email = 'chloe.durand@example.com'))
ON CONFLICT DO NOTHING;
