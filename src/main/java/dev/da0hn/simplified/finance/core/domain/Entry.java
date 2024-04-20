package dev.da0hn.simplified.finance.core.domain;

import java.time.LocalDateTime;
import java.util.Set;

public class Entry {

  private Long id;

  private String title;

  private String description;

  private EntryType type;

  private EntryStatus status;

  private Amount amount;

  private PaymentMethod paymentMethod;

  private LocalDateTime createdAt;

  private LocalDateTime issuedAt;

  private Set<Category> categories;

}
