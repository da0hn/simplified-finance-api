package dev.da0hn.simplified.finance.infrastructure.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Category")
@Table(name = "categories")
public class CategoryEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = -4405829484923436237L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

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
    final CategoryEntity that = (CategoryEntity) o;
    return this.id != null && Objects.equals(this.id, that.id);
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "(" +
           "id = " + this.id + ", " +
           "description = " + this.description + ", " +
           "name = " + this.name + ")";
  }

}
