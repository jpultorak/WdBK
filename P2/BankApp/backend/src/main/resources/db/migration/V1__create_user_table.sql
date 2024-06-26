CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    balance NUMERIC(15, 4) DEFAULT 0,
    enabled BOOLEAN DEFAULT false,
    token VARCHAR(30),
    token_created_at TIMESTAMP,
    token_expires_at TIMESTAMP,
    token_validated_at TIMESTAMP

--     CHECK (
--         (token_created_at IS NULL AND token_expires_at IS NULL and token_validated_at is NULL) OR
--         (token_created_at IS NOT NULL AND token_expires_at IS NOT NULL and token_validated_at is NOT NULL)
--     )
);
