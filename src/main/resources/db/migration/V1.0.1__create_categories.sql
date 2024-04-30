CREATE TABLE `simplified-finance-db`.categories
(
    id VARCHAR(36) NOT NULL DEFAULT (UUID()),
    name VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);
