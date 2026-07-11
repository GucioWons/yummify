CREATE TABLE auth.role
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    restaurant_id UUID  NOT NULL,
    name          JSONB NOT NULL,
    permissions   JSONB NOT NULL
);