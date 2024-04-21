CREATE TABLE `simplified-finance-db`.entries
(
    id VARCHAR(36) NOT NULL DEFAULT (UUID()),
    title VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    type VARCHAR(255) NOT NULL,
    amount DECIMAL NOT NULL,
    payment_method VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    issued_at datetime NOT NULL,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    parent_entry_id VARCHAR(36) NULL,
    installments BIGINT NULL,
    installment_amount DECIMAL NULL,
    installment_date date NULL,
    CONSTRAINT pk_entry PRIMARY KEY (id)
);

ALTER TABLE `simplified-finance-db`.entries
    ADD CONSTRAINT fk_entries_on_parent_entry FOREIGN KEY (parent_entry_id)
        REFERENCES `simplified-finance-db`.entries (id);
