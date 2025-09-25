CREATE TABLE customer_outbox (
                                 id UUID PRIMARY KEY,
                                 aggregate_id BIGINT NOT NULL,
                                 type VARCHAR(255) NOT NULL,
                                 payload TEXT NOT NULL,
                                 sent BOOLEAN DEFAULT FALSE,
                                 created_at TIMESTAMP DEFAULT NOW()
);

ALTER TABLE customer_outbox
    ADD COLUMN retry_count INT DEFAULT 0;

ALTER TABLE customer_outbox
    ADD COLUMN next_retry_at TIMESTAMP;