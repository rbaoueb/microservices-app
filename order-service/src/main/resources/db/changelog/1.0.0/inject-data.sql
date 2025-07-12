INSERT INTO customer (id, name, email) VALUES
(1, 'Alice Dupont', 'alice@example.com'),
(2, 'Bob Martin', 'bob@example.com') ON CONFLICT DO NOTHING;

INSERT INTO product (id, name, price) VALUES
(1, 'Laptop', 1200.00),
(2, 'Phone', 800.00),
(3, 'Keyboard', 60.00) ON CONFLICT DO NOTHING;

-- Commande de Alice
INSERT INTO orders (id, customer_id, date) VALUES (1, 1, CURRENT_DATE);
INSERT INTO order_item (id, order_id, product_id, quantity) VALUES
(1, 1, 1, 1),
(2, 1, 3, 2)  ON CONFLICT DO NOTHING;

-- Commande de Bob
INSERT INTO orders (id, customer_id, date) VALUES (2, 2, CURRENT_DATE);
INSERT INTO order_item (id, order_id, product_id, quantity) VALUES
(3, 2, 2, 1) ON CONFLICT DO NOTHING;