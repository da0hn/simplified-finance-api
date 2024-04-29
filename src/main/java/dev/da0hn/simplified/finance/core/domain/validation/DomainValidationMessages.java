package dev.da0hn.simplified.finance.core.domain.validation;

public final class DomainValidationMessages {

  public static final String NON_NULL_ARGUMENT = "%s must not be null";

  public static final String ENTRY_ID_NOT_NULL = "Entry ID must not be null";

  public static final String ENTRY_TITLE_NOT_BLANK = "Title must not be blank";

  public static final String ENTRY_DESCRIPTION_NOT_BLANK = "Description must not be blank";

  public static final String ENTRY_TYPE_NOT_NULL = "Type must not be null";

  public static final String ENTRY_STATUS_NOT_NULL = "Status must not be null";

  public static final String ENTRY_AMOUNT_NOT_NULL = "Amount must not be null";

  public static final String ENTRY_PAYMENT_METHOD_NOT_NULL = "Payment method must not be null";

  public static final String ENTRY_CREATED_AT_NOT_NULL = "Created at must not be null";

  public static final String ENTRY_ISSUED_AT_NOT_NULL = "Issued at must not be null";

  public static final String ENTRY_REFERENCE_MONTH_NOT_NULL = "Reference month must not be null";

  public static final String ENTRY_FUTURE_EXPENSE = "Future expense entry must not be null";

  public static final String ENTRY_CATEGORIES_NOT_NULL = "Categories must not be null";

  public static final String ENTRY_CATEGORIES_NOT_EMPTY = "Categories must not be empty";

  public static final String CATEGORY_ID_NOT_NULL = "Category ID must not be null";

  public static final String CATEGORY_NAME_NOT_BLANK = "Name must not be blank";

  public static final String CATEGORY_DESCRIPTION_NOT_BLANK = "Description must not be blank";

  public static final String FUTURE_EXPENSE_ENTRY_ID_NOT_NULL = "Future expense entry ID must not be null";

  public static final String FUTURE_EXPENSE_ENTRY_TITLE_NOT_BLANK = "Title must not be blank";

  public static final String FUTURE_EXPENSE_ENTRY_DESCRIPTION_NOT_BLANK = "Description must not be blank";

  public static final String FUTURE_EXPENSE_ENTRY_TOTAL_AMOUNT_NOT_NULL = "Total amount must not be null";

  public static final String FUTURE_EXPENSE_ENTRY_PARTIAL_AMOUNT_NOT_NULL = "Partial amount must not be null";

  public static final String FUTURE_EXPENSE_ENTRY_ISSUED_AT_NOT_NULL = "Issued at must not be null";

  public static final String FUTURE_EXPENSE_ENTRY_CREATED_AT_NOT_NULL = "Created at must not be null";

  public static final String FUTURE_EXPENSE_ENTRY_CHILDREN_NOT_NULL = "Children must not be null";

  public static final String FUTURE_EXPENSE_ENTRY_CATEGORIES_NOT_EMPTY = "Entry categories must not be empty";

  public static final String FUTURE_EXPENSE_ENTRY_INSTALLMENT_QUANTITY_NOT_NULL = "Installment quantity must not be null";

  public static final String INSTALLMENT_DETAIL_NUMBER_NOT_NULL = "Installment number must not be null";

  public static final String INSTALLMENT_FUTURE_EXPENSE_ENTRY_NOT_NULL = "Future expense entry must not be null";

  public static final String INSTALLMENT_POSITIVE = "Installment must be non-zero positive";

  public static final String INSTALLMENT_NOT_NULL = "Installment must not be null";

  private DomainValidationMessages() { }

}
