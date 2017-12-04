package pl.michal.olszewski.jooqsample.dao;

import static pl.michal.olszewski.jooqsample.db.tables.Customer.CUSTOMER;
import static pl.michal.olszewski.jooqsample.db.tables.Product.PRODUCT;

import java.util.stream.Stream;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import pl.michal.olszewski.jooqsample.config.DateTimeService;
import pl.michal.olszewski.jooqsample.db.tables.records.CustomerRecord;
import pl.michal.olszewski.jooqsample.entity.CustomerEntity;

@Repository
public class CustomerDao implements Dao<CustomerEntity, Long> {

  private final DSLContext dslContext;
  private final DateTimeService dateTimeService;

  public CustomerDao(DSLContext dslContext, DateTimeService dateTimeService) {
    this.dslContext = dslContext;
    this.dateTimeService = dateTimeService;
  }

  public CustomerEntity save(CustomerEntity customerEntity) {
    CustomerRecord id = dslContext.insertInto(CUSTOMER, CUSTOMER.EMAIL)
        .values(customerEntity.getEmail())
        .returning(CUSTOMER.ID)
        .fetchOne();
    customerEntity.getProductList().forEach(
        v -> dslContext.insertInto(PRODUCT, PRODUCT.NAME, PRODUCT.CUSTOMER_ID)
            .values(v.getName(), id.getId()).execute()
    );
    customerEntity.setId(id.getId());
    return customerEntity;
  }

  @Override
  public Stream<CustomerEntity> save(Stream<CustomerEntity> entities) {
    return entities;
  }


  @Override
  public void delete(Long aLong) {

  }

  @Override
  public void delete(CustomerEntity entity) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public CustomerEntity update(CustomerEntity entity) {
    return null;
  }
}
