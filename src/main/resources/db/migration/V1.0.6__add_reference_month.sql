ALTER TABLE `simplified-finance-db`.entries
ADD reference_month date NULL;

ALTER TABLE `simplified-finance-db`.entries
    MODIFY reference_month date NOT NULL;
