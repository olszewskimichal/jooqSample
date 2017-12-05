package pl.michal.olszewski.jooqsample.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;
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
import pl.michal.olszewski.jooqsample.finders.CustomerFinder;

@SpringBootTest()
@RunWith(JUnitPlatform.class)
@ExtendWith(org.springframework.test.context.junit.jupiter.SpringExtension.class)
@Transactional
@ActiveProfiles(value = "test")
public class CustomerDaoTest {


  @Autowired
  private CustomerFinder finder;

  @Autowired
  private CustomerDao dao;

  @Test
  public void shouldCreateCustomer() {
    //given
    CustomerEntity customerEntity = CustomerEntity.builder().email("email").build();
    customerEntity.addProduct(ProductEntity.builder().name("nazwa").build());
    customerEntity.addProduct(ProductEntity.builder().name("nazwa2").build());
    dao.save(customerEntity);
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

  @Test
  void shouldSaveStreamOfCustomers() {
    //given
    Stream<CustomerEntity> stream = Stream.of(
        CustomerEntity.builder().email("test").build(),
        CustomerEntity.builder().email("test2").build()
    );
    //when
    dao.save(stream);
    //then
    assertThat(finder.count()).isEqualTo(2);
  }

  @Test
  void shouldDeleteById() {
    CustomerEntity saved = dao.save(CustomerEntity.builder().email("test").build());

    dao.delete(saved);

    assertThat(finder.count()).isEqualTo(0);
  }

  @Test
  void shouldDeleteAllCustomers() {
    //given
    dao.save(Stream.of(
        CustomerEntity.builder().email("test").build(),
        CustomerEntity.builder().email("test2").build()
    ));
    //when
    dao.deleteAll();
    //then
    assertThat(finder.count()).isEqualTo(0);
  }

  @Test
  void shouldUpdateCustomer() {
    //given
    CustomerEntity saved = dao.save(CustomerEntity.builder().email("test").build());
    saved.setEmail("nowyOpis");
    //when
    dao.update(saved);
    //then
    Optional<CustomerDTO> optional = finder.getById(saved.getId());
    assertThat(optional).isPresent();
    assertThat(optional.get().getEmail()).isNotNull().isEqualTo("nowyOpis");
  }
}
