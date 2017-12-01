package pl.michal.olszewski.jooqsample.finders;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import pl.michal.olszewski.jooqsample.db.tables.Customer;
import pl.michal.olszewski.jooqsample.db.tables.Product;
import pl.michal.olszewski.jooqsample.dto.CustomerDTO;
import pl.michal.olszewski.jooqsample.dto.ProductDTO;

@Repository
public class CustomerFinder {

  private final DSLContext dslContext;

  public CustomerFinder(DSLContext dslContext) {
    this.dslContext = dslContext;
  }

  public Collection<CustomerDTO> findAll() {
    Map<Record, Result<Record>> recordResultMap = this.dslContext.select().from(Customer.CUSTOMER)
        .leftJoin(Product.PRODUCT)
        .on(Customer.CUSTOMER.ID.eq(Product.PRODUCT.CUSTOMER_ID))
        .fetch()
        .intoGroups(Customer.CUSTOMER.fields());

    return recordResultMap
        .values()
        .stream()
        .map(v -> {
          CustomerDTO customerDTO = v.into(Customer.CUSTOMER.ID, Customer.CUSTOMER.EMAIL).get(0).into(CustomerDTO.class);
          customerDTO.setProducts(v.into(ProductDTO.class).stream().filter(val -> val.getId() != null).collect(Collectors.toSet()));
          return customerDTO;
        }).collect(Collectors.toSet());
  }
}
