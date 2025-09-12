CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE dish
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    restaurant_id UUID  NOT NULL,
    name          JSONB NOT NULL,
    description   JSONB,
);

CREATE TABLE dish_ingredient
(
    dish_id       UUID NOT NULL,
    ingredient_id UUID NOT NULL,
    PRIMARY KEY (dish_id, ingredient_id),
    CONSTRAINT fk_dish FOREIGN KEY (dish_id) REFERENCES dish (id),
    CONSTRAINT fk_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredient (id)
);