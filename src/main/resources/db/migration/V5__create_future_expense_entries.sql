ALTER TABLE `simplified-finance-db`.entries
DROP FOREIGN KEY fk_entries_on_parent_entry;

ALTER TABLE `simplified-finance-db`.entries
DROP COLUMN installment_amount;

ALTER TABLE `simplified-finance-db`.entries
DROP COLUMN installment_date;

ALTER TABLE `simplified-finance-db`.entries
DROP COLUMN installments;

ALTER TABLE `simplified-finance-db`.entries
DROP COLUMN parent_entry_id;

CREATE TABLE `simplified-finance-db`.future_expense_entries
(
    id VARCHAR(36) NOT NULL,
    title VARCHAR(255) NOT NULL, `description` VARCHAR(255) NOT NULL,
    total_installments BIGINT NULL,
    total_amount DECIMAL NULL,
    partial_amount DECIMAL NULL,
    due_date date NULL,
    created_at date NULL,
    CONSTRAINT pk_future_expense_entries PRIMARY KEY (id)
);

ALTER TABLE `simplified-finance-db`.entries
ADD future_expense_entry_id VARCHAR(36) NULL;

ALTER TABLE `simplified-finance-db`.entries
ADD CONSTRAINT FK_ENTRIES_ON_FUTURE_EXPENSE_ENTRIES FOREIGN KEY (future_expense_entry_id)
        REFERENCES `simplified-finance-db`.future_expense_entries (id);
