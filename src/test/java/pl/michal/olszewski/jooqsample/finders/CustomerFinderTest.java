package pl.michal.olszewski.jooqsample.finders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Collection;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import pl.michal.olszewski.jooqsample.dto.CustomerDTO;
import pl.michal.olszewski.jooqsample.entity.CustomerEntity;
import pl.michal.olszewski.jooqsample.entity.ProductEntity;
import pl.michal.olszewski.jooqsample.repository.CustomerRepository;

@SpringBootTest()
@RunWith(JUnitPlatform.class)
@ExtendWith(org.springframework.test.context.junit.jupiter.SpringExtension.class)
@Transactional
@ActiveProfiles(value = "test")
public class CustomerFinderTest {

  @Autowired
  protected CustomerRepository repository;

  @Autowired
  private CustomerFinder finder;

  @Test
  public void shouldFindAll() {
    //given
    CustomerEntity customerEntity = CustomerEntity.builder().email("email").build();
    customerEntity.addProduct(ProductEntity.builder().name("nazwa").build());
    customerEntity.addProduct(ProductEntity.builder().name("nazwa2").build());
    repository.save(customerEntity);
    //when
    Collection<CustomerDTO> all = finder.findAll();
    //then
    Optional<CustomerDTO> customerDTO = all.stream().findFirst();
    assertAll(
        () -> assertThat(all).isNotEmpty().hasSize(1),
        () -> assertThat(customerDTO.isPresent()).isTrue(),
        () -> assertThat(customerDTO.get().getProducts()).isNotEmpty().hasSize(2)
    );
  }
}