package dev.da0hn.simplified.finance.infrastructure.db.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "FutureExpenseEntries")
@Table(name = "future_expense_entries")
public class FutureExpenseEntryEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 1250185594115222821L;

  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "total_installments", updatable = false)
  private Long installments;

  @Column(name = "total_amount", updatable = false)
  private BigDecimal totalAmount;

  @Column(name = "partial_amount", updatable = false)
  private BigDecimal partialAmount;

  @Column(name = "issued_at", updatable = false)
  private LocalDate issuedAt;

  @Column(name = "created_at", updatable = false)
  private LocalDate createdAt;

  @OneToMany(mappedBy = "futureExpenseEntry", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<EntryEntity> children;

}
