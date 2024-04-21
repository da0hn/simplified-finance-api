package dev.da0hn.simplified.finance.core.domain.valueobjects;

public class CategoryId extends DomainEntityId {

  private final String value;

  private CategoryId(final String value) { this.value = value; }

  public static CategoryId newInstance() {
    return new CategoryId(DomainEntityId.nextId());
  }

  public static CategoryId of(final String value) {
    return new CategoryId(DomainEntityId.from(value));
  }

  public String value() {
    return this.value;
  }

}
