package pl.michal.olszewski.jooqsample.finders;

import static pl.michal.olszewski.jooqsample.db.tables.Customer.CUSTOMER;
import static pl.michal.olszewski.jooqsample.db.tables.Product.PRODUCT;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import pl.michal.olszewski.jooqsample.dto.CustomerDTO;
import pl.michal.olszewski.jooqsample.dto.ProductDTO;

@Repository
public class CustomerFinder implements Finder<CustomerDTO, Long> {

  private final DSLContext dslContext;

  public CustomerFinder(DSLContext dslContext) {
    this.dslContext = dslContext;
  }

  public Optional<CustomerDTO> findByEmail(String email) {
    Map<Record, Result<Record>> recordResultMap = this.dslContext.select()
        .from(CUSTOMER)
        .leftJoin(PRODUCT)
        .on(CUSTOMER.ID.eq(PRODUCT.CUSTOMER_ID))
        .where(CUSTOMER.EMAIL.eq(email))
        .fetch()
        .intoGroups(CUSTOMER.fields());
    return recordResultMap
        .values()
        .stream()
        .map(v -> {
          CustomerDTO customerDTO = v.into(CUSTOMER.ID, CUSTOMER.EMAIL).get(0).into(CustomerDTO.class);
          customerDTO.setProducts(
              v.into(ProductDTO.class)
                  .stream().
                  filter(val -> val.getId() != null).collect(Collectors.toSet()));
          return customerDTO;
        }).findFirst();
  }

  public List<CustomerDTO> findAll() {
    Map<Record, Result<Record>> recordResultMap = this.dslContext.select().from(CUSTOMER)
        .leftJoin(PRODUCT)
        .on(CUSTOMER.ID.eq(PRODUCT.CUSTOMER_ID))
        .fetch()
        .intoGroups(CUSTOMER.fields());

    return recordResultMap
        .values()
        .stream()
        .map(v -> {
          CustomerDTO customerDTO = v.into(CUSTOMER.ID, CUSTOMER.EMAIL).get(0).into(CustomerDTO.class);
          customerDTO.setProducts(v.into(ProductDTO.class).stream().filter(val -> val.getId() != null).collect(Collectors.toSet()));
          return customerDTO;
        }).collect(Collectors.toList());
  }

  @Override
  public Optional<CustomerDTO> getById(Long aLong) {
    return null;
  }

  @Override
  public boolean exists(Long aLong) {
    return false;
  }

  @Override
  public long count() {
    return 0;
  }
}
