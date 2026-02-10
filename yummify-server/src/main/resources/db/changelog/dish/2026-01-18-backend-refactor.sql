ALTER TABLE dish.dish_ingredients RENAME TO dish_ingredient_id;

ALTER TABLE dish.dish ALTER COLUMN id DROP DEFAULT;

ALTER TABLE dish.dish_ingredient_id DROP CONSTRAINT fk_ingredient;

ALTER TABLE dish.ingredient SET SCHEMA ingredient;