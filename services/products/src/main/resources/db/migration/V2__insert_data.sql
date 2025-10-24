-- Insert sample categories
INSERT INTO category (id, description, name)
VALUES
    (nextval('category_seq'), 'Electronic devices and gadgets', 'Electronics'),
    (nextval('category_seq'), 'Household and kitchen utilities', 'Home Appliances'),
    (nextval('category_seq'), 'Books, novels, and educational material', 'Books'),
    (nextval('category_seq'), 'Fashion and clothing for men and women', 'Clothing'),
    (nextval('category_seq'), 'Sports and fitness equipment', 'Sports');

-- Insert sample products
INSERT INTO product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 100, 'Latest smartphone with 5G support', 'Smartphone X', 799.99,
     (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 50, 'Lightweight laptop with SSD storage', 'Laptop Pro', 1199.49,
     (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 200, 'Automatic coffee maker with timer', 'Coffee Maker', 149.99,
     (SELECT id FROM category WHERE name = 'Home Appliances')),
    (nextval('product_seq'), 120, 'Air fryer with 5L capacity', 'Air Fryer', 89.50,
     (SELECT id FROM category WHERE name = 'Home Appliances')),
    (nextval('product_seq'), 300, 'Bestselling science fiction novel', 'Sci-Fi Novel', 19.99,
     (SELECT id FROM category WHERE name = 'Books')),
    (nextval('product_seq'), 500, 'Classic cotton t-shirt', 'Cotton T-Shirt', 14.95,
     (SELECT id FROM category WHERE name = 'Clothing')),
    (nextval('product_seq'), 250, 'Running shoes for men', 'Running Shoes', 59.99,
     (SELECT id FROM category WHERE name = 'Clothing')),
    (nextval('product_seq'), 75, 'Professional basketball', 'Basketball Pro', 29.90,
     (SELECT id FROM category WHERE name = 'Sports')),
    (nextval('product_seq'), 40, 'Yoga mat non-slip', 'Yoga Mat', 24.50,
     (SELECT id FROM category WHERE name = 'Sports'));
