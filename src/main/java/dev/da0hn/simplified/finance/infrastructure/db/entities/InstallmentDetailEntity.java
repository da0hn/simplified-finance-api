package dev.da0hn.simplified.finance.infrastructure.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Objects;

@Builder
@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class InstallmentDetailEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 1250185594115222821L;

  @Column(name = "installments", nullable = false, updatable = false)
  private Long installments;

  @Column(name = "installment_amount", nullable = false, updatable = false)
  private BigDecimal installmentAmount;

  @Column(name = "installment_date", nullable = false, updatable = false)
  private YearMonth installmentDate;

  @Override
  public final int hashCode() {
    return Objects.hash(this.installments, this.installmentAmount, this.installmentDate);
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
    final InstallmentDetailEntity that = (InstallmentDetailEntity) o;
    return this.installments != null && Objects.equals(this.installments, that.installments)
           && this.installmentAmount != null && Objects.equals(this.installmentAmount, that.installmentAmount)
           && this.installmentDate != null && Objects.equals(this.installmentDate, that.installmentDate);
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "(" +
           "installments = " + this.installments + ", " +
           "installmentAmount = " + this.installmentAmount + ", " +
           "installmentDate = " + this.installmentDate + ")";
  }

}
