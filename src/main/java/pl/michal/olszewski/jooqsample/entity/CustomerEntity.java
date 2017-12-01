package pl.michal.olszewski.jooqsample.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "CUSTOMER")
@Builder
public class CustomerEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String email;

  @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private Set<ProductEntity> productList = new HashSet<>();

  public void addProduct(ProductEntity item) {
    productList.add(item);
    item.setCustomerEntity(this);
  }
}
