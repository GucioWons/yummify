CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE menu.menu
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    restaurant_id UUID    NOT NULL,
    is_active     BOOLEAN NOT NULL
);

CREATE TABLE menu.menu_section
(
    id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    menu_id  UUID NOT NULL,
    position INTEGER,
    name     JSONB,
    CONSTRAINT fk_menu_section_menu FOREIGN KEY (menu_id)
        REFERENCES menu.menu (id)
        ON DELETE CASCADE
);

CREATE TABLE menu.menu_entry
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    section_id UUID NOT NULL,
    dish_id    UUID,
    position   INTEGER,
    price      NUMERIC(10, 2),
    CONSTRAINT fk_menu_entry_section FOREIGN KEY (section_id)
        REFERENCES menu.menu_section (id)
        ON DELETE CASCADE
);