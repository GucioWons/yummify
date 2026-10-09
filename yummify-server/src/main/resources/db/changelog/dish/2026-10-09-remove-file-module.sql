ALTER TABLE file.file SET SCHEMA dish;

ALTER TABLE dish.file RENAME TO dish_image;

ALTER TABLE dish.dish
    ADD CONSTRAINT fk_dish_image FOREIGN KEY (image_id) REFERENCES dish.dish_image (id);

ALTER TABLE dish.dish_image DROP COLUMN restaurant_id;