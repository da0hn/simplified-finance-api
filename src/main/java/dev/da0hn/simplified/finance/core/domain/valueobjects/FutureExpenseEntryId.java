package dev.da0hn.simplified.finance.core.domain.valueobjects;

public class FutureExpenseEntryId extends DomainEntityId {

  private final String value;

  private FutureExpenseEntryId(final String value) { this.value = value; }

  public static FutureExpenseEntryId newInstance() {
    return new FutureExpenseEntryId(DomainEntityId.nextId());
  }

  public static FutureExpenseEntryId of(final String value) {
    return new FutureExpenseEntryId(DomainEntityId.from(value));
  }

  public String value() {
    return this.value;
  }

}
