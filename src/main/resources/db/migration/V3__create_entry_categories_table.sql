CREATE TABLE  `simplified-finance-db`.entry_categories
(
    category_id BIGINT NOT NULL,
    entry_id BIGINT NOT NULL,
    CONSTRAINT pk_entry_category PRIMARY KEY (category_id, entry_id)
);

ALTER TABLE `simplified-finance-db`.entry_categories
ADD CONSTRAINT fk_entry_categories_on_categories FOREIGN KEY (category_id)
        REFERENCES `simplified-finance-db`.categories (id);

ALTER TABLE `simplified-finance-db`.entry_categories
ADD CONSTRAINT fk_entry_categories_on_entries FOREIGN KEY (entry_id)
        REFERENCES `simplified-finance-db`.entries (id);
