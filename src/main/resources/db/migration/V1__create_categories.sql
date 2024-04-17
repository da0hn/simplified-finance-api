CREATE TABLE `simplified-finance-db`.categories
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL, `description` VARCHAR(255) NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);
