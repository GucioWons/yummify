CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE my_table.my_table
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    restaurant_id UUID NOT NULL,
    user_id UUID,
    name VARCHAR(25) NOT NULL
);