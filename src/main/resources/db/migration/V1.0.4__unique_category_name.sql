alter table `simplified-finance-db`.categories
    add constraint un_category_name
        unique (name);
