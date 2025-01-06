CREATE TABLE serasa_orders (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    item_description VARCHAR(255) NOT NULL,
    item_quantity INT NOT NULL,
    item_price DECIMAL(10, 2) NOT NULL,
    total_value DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL,
    updated_at TIMESTAMP(6) NULL
);
