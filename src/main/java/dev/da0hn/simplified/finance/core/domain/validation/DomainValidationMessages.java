package dev.da0hn.simplified.finance.core.domain.validation;

public final class DomainValidationMessages {

  public static final String NON_NULL_ARGUMENT = "%s must not be null";

  public static final String ENTRY_TITLE_NOT_BLANK = "Title must not be blank";

  public static final String ENTRY_DESCRIPTION_NOT_BLANK = "Description must not be blank";

  public static final String ENTRY_TYPE_NOT_NULL = "Type must not be null";

  public static final String ENTRY_STATUS_NOT_NULL = "Status must not be null";

  public static final String ENTRY_AMOUNT_NOT_NULL = "Amount must not be null";

  public static final String ENTRY_PAYMENT_METHOD_NOT_NULL = "Payment method must not be null";

  public static final String ENTRY_CREATED_AT_NOT_NULL = "Created at must not be null";

  public static final String ENTRY_ISSUED_AT_NOT_NULL = "Issued at must not be null";

  public static final String ENTRY_INSTALLMENT_DETAILS_NOT_NULL = "Installment details must not be null";

  public static final String ENTRY_CATEGORIES_NOT_NULL = "Categories must not be null";

  public static final String ENTRY_CATEGORIES_NOT_EMPTY = "Categories must not be empty";

  public static final String CATEGORY_ID_NOT_NULL = "Category ID must not be null";

  public static final String CATEGORY_NAME_NOT_BLANK = "Name must not be blank";

  public static final String CATEGORY_DESCRIPTION_NOT_BLANK = "Description must not be blank";

  private DomainValidationMessages() { }

}
