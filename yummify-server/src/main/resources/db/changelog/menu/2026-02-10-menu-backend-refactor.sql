ALTER TABLE menu.menu RENAME TO menu_version;

ALTER TABLE menu.menu_version DROP COLUMN active;

ALTER TABLE menu.menu_version ADD COLUMN version INT NOT NULL DEFAULT 1;

ALTER TABLE menu.menu_version ADD COLUMN status VARCHAR(32) NOT NULL DEFAULT 'DRAFT';

ALTER TABLE menu.menu_section DROP CONSTRAINT fk_menu_section_menu;

ALTER TABLE menu.menu_section RENAME COLUMN menu_id TO menu_version_id;

ALTER TABLE menu.menu_section
    ADD CONSTRAINT fk_menu_section_menu_version
        FOREIGN KEY (menu_version_id)
            REFERENCES menu.menu_version(id)
            ON DELETE CASCADE;

ALTER TABLE menu.menu_entry DROP COLUMN "position";