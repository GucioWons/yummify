CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE ingredient
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    restaurant_id UUID  NOT NULL,
    name          JSONB NOT NULL
);