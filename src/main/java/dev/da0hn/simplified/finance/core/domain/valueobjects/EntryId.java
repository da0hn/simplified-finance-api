package dev.da0hn.simplified.finance.core.domain.valueobjects;

public class EntryId extends DomainEntityId {

  private final String value;

  private EntryId(final String value) { this.value = value; }

  public static EntryId newInstance() {
    return new EntryId(DomainEntityId.nextId());
  }

  public static EntryId of(final String value) {
    return new EntryId(DomainEntityId.from(value));
  }

  public String value() {
    return this.value;
  }

}
