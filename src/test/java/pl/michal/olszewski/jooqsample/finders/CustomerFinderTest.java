package pl.michal.olszewski.jooqsample.finders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Collection;
import java.util.List;
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
    repository.save(customerEntity);
    //when
    Collection<CustomerDTO> all = finder.findAll();
    //then
    Optional<CustomerDTO> customerDTO = all.stream().findFirst();
    assertAll(
        () -> assertThat(all).isNotEmpty().hasSize(1),
        () -> assertThat(customerDTO.isPresent()).isTrue(),
        () -> assertThat(customerDTO.get().getProducts()).isNotEmpty().hasSize(1)
    );
  }

  @Test
  void shouldReturnCustomerByEmailWhenExists() {
    //given
    CustomerEntity customerEntity = CustomerEntity.builder().email("email").build();
    customerEntity.addProduct(ProductEntity.builder().name("nazwa").build());
    customerEntity.addProduct(ProductEntity.builder().name("nazwa2").build());
    CustomerEntity customerEntity2 = CustomerEntity.builder().email("email2").build();
    repository.save(customerEntity);
    repository.save(customerEntity2);
    //when
    Optional<CustomerDTO> name = finder.findByEmail("email");
    //then
    assertAll(
        () -> assertThat(name.isPresent()).isTrue(),
        () -> assertThat(name.get().getEmail()).isEqualTo("email"),
        () -> assertThat(name.get().getProducts()).isNotEmpty().hasSize(2)
    );
  }

  @Test
  void shouldReturnOptionalEmptyByNameWhenNotExists() {
    //given
    //when
    Optional<CustomerDTO> customerDTO = finder.findByEmail("name");
    //then
    assertAll(
        () -> assertThat(customerDTO.isPresent()).isFalse(),
        () -> assertThat(customerDTO).isEqualTo(Optional.empty())
    );
  }

  @Test
  void shouldReturnCustomerByIdWhenExists() {
    //given
    CustomerEntity save = repository.save(CustomerEntity.builder().email("name").build());
    //when
    Optional<CustomerDTO> name = finder.getById(save.getId());
    //then
    assertAll(
        () -> assertThat(name.isPresent()).isTrue(),
        () -> assertThat(name.get().getEmail()).isEqualTo("name"));
  }

  @Test
  void shouldReturnOptionalEmptyByIdWhenNotExists() {
    //given
    //when
    Optional<CustomerDTO> name = finder.getById(Long.MIN_VALUE);
    //then
    assertAll(
        () -> assertThat(name.isPresent()).isFalse(),
        () -> assertThat(name).isEqualTo(Optional.empty())
    );
  }

  @Test
  void shouldReturnTrueWhenExists() {
    //given
    CustomerEntity save = repository.save(CustomerEntity.builder().email("name").build());
    //when
    boolean exists = finder.exists(save.getId());
    //then
    assertThat(exists).isTrue();
  }

  @Test
  void shouldReturnFalseWhenNotExists() {
    //given
    //when
    boolean exists = finder.exists(Long.MIN_VALUE);
    //then
    assertThat(exists).isFalse();
  }

  @Test
  void shouldCountAllCustomers() {
    assertThat(finder.count()).isEqualTo(0);
    repository.save(CustomerEntity.builder().email("name").build());
    assertThat(finder.count()).isEqualTo(1);
  }

  @Test
  void shouldCorrectReturnPageableValues() {
    Long id = null;
    for (int i = 0; i < 30; i++) {
      id = repository.save(CustomerEntity.builder().email("name" + i).build()).getId();
    }

    List<CustomerDTO> all = finder.findAll(new SeekPagable(10, null));
    assertThat(all).isNotEmpty().hasSize(10);

    List<CustomerDTO> anotherAll = finder.findAll(new SeekPagable(10, id - 10));
    assertThat(anotherAll).isNotEmpty().hasSize(10);

    assertThat(all).isNotEqualTo(anotherAll);
  }
}