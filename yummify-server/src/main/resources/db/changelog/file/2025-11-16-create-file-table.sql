CREATEEXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE file.file
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    restaurant_id UUID         NOT NULL,
    storage_key   VARCHAR(255) NOT NULL
);