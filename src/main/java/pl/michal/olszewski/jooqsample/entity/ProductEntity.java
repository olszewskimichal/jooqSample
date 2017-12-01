package pl.michal.olszewski.jooqsample.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(exclude = "customerEntity")
public class ProductEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private CustomerEntity customerEntity;
}
