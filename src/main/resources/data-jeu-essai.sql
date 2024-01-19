INSERT INTO category (name) VALUES ("Cafes"),("Gateaux");

INSERT INTO tag (name) VALUES ("Promo"),("Best seller"),("Organic"),("Alergen");

INSERT INTO product (name, price, category_id)
VALUES ("Expresso",1.5, 1) , ("Latte", 2.5, 1), ("Speculoos", 0.5, 2);

INSERT INTO tag_product (product_id, tag_id) VALUES
(1, 1), (1, 2), (3, 1);