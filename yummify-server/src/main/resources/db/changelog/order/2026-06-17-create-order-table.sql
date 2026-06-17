CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE order.order
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    restaurant_id UUID        NOT NULL,
    table_id      UUID        NOT NULL,
    status        VARCHAR(50) NOT NULL
);

CREATE TABLE order.order_item
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    order_id      UUID,
    dish_id       UUID           NOT NULL,
    name_snapshot JSONB          NOT NULL,
    price         NUMERIC(10, 2) NOT NULL,
    CONSTRAINT fk_order_item_order FOREIGN KEY (order_id)
            REFERENCES order.order (id)
            ON DELETE CASCADE
);