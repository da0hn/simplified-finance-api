package dev.da0hn.simplified.finance.core.domain.valueobjects;

import java.util.UUID;

public abstract class DomainEntityId {

  public static String nextId() {
    return UUID.randomUUID().toString();
  }

  public static String from(final String value) {
    return UUID.fromString(value).toString();
  }

}
