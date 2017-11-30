package pl.michal.olszewski.jooqsample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RESERVATION")
public class ReservationEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String description;
}
