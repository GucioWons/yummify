CREATE EXTENSION IF NOT EXISTS "pgcrypto";

ALTER TABLE dish.dish ADD image_id uuid NULL;
