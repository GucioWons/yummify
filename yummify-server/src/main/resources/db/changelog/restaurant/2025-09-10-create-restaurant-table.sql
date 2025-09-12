CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE restaurant
(
    id               UUID PRIMARY KEY     DEFAULT gen_random_uuid(),
    owner_id         UUID,
    name             VARCHAR(50) NOT NULL,
    description      JSONB       NOT NULL,
    default_language VARCHAR(2)  NOT NULL
);