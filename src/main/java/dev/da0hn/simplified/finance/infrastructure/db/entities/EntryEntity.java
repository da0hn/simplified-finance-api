package dev.da0hn.simplified.finance.infrastructure.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Entries")
@Table(name = "entries")
public class EntryEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = -425032786865013388L;

  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false, updatable = false)
  private EntryTypeEntityEnum type;

  @Column(name = "amount", nullable = false)
  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_method", nullable = false, updatable = false)
  private PaymentMethodEntityEnum paymentMethod;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private EntryStatusEntityEnum status;

  @Column(name = "issued_at", nullable = false, updatable = false)
  private LocalDateTime issuedAt;

  @Column(name = "reference_month", nullable = false, updatable = false)
  private YearMonth referenceMonth;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "future_expense_entry_id", foreignKey = @ForeignKey(name = "fk_entries_on_future_expense_entries"))
  private FutureExpenseEntryEntity futureExpenseEntry;

  @Column(name = "installment_number")
  private Long installmentNumber;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "entry_categories",
    joinColumns = @JoinColumn(name = "entry_id", foreignKey = @ForeignKey(name = "fk_entry_categories_on_entries")),
    inverseJoinColumns = @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_entry_categories_on_categories"))
  )
  private Set<CategoryEntity> categories = new HashSet<>();

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() :
      this.getClass().hashCode();
  }

  @Override
  public final boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null) return false;
    final Class<?> oEffectiveClass = o instanceof HibernateProxy ?
      ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    final Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
      ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    final EntryEntity that = (EntryEntity) o;
    return this.id != null && Objects.equals(this.id, that.id);
  }

  @Override
  public String toString() {
    return "EntryEntity{" +
           "id='" + this.id + '\'' +
           ", title='" + this.title + '\'' +
           ", description='" + this.description + '\'' +
           ", type=" + this.type +
           ", amount=" + this.amount +
           ", paymentMethod=" + this.paymentMethod +
           ", status=" + this.status +
           ", issuedAt=" + this.issuedAt +
           ", referenceMonth=" + this.referenceMonth +
           ", createdAt=" + this.createdAt +
           ", futureExpenseEntry=" + this.futureExpenseEntry +
           ", categories=" + this.categories +
           '}';
  }

}
