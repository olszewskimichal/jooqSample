package pl.michal.olszewski.jooqsample.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
  private Long id;
  private String email;
  private Set<ProductDTO> products = new HashSet<>();
}
