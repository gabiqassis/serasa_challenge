CREATE TABLE serasa_users (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    created_at TIMESTAMP(6) NOT NULL,
    updated_at TIMESTAMP(6),
    cpf VARCHAR(11) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);
