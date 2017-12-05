package pl.michal.olszewski.jooqsample.dao;

import static pl.michal.olszewski.jooqsample.db.tables.Customer.CUSTOMER;
import static pl.michal.olszewski.jooqsample.db.tables.Product.PRODUCT;

import java.util.List;
import java.util.stream.Collectors;
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
    List<CustomerRecord> collect = entities.map(this::createRecord).collect(Collectors.toList());
    dslContext.batchInsert(collect).execute();
    return entities;
  }


  @Override
  public void delete(Long id) {
    dslContext.delete(CUSTOMER)
        .where(CUSTOMER.ID.equal(id))
        .execute();
  }

  @Override
  public void delete(CustomerEntity entity) {
    dslContext.delete(CUSTOMER)
        .where(CUSTOMER.ID.equal(entity.getId()))
        .execute();
  }

  @Override
  public void deleteAll() {
    dslContext.delete(CUSTOMER)
        .execute();
  }

  @Override
  public CustomerEntity update(CustomerEntity entity) {
    dslContext.update(CUSTOMER)
        .set(CUSTOMER.EMAIL, entity.getEmail())
        .where(CUSTOMER.ID.equal(entity.getId()))
        .execute();

    return entity;
  }

  private CustomerRecord createRecord(CustomerEntity entity) {
    return new CustomerRecord(entity.getId(), entity.getEmail());
  }

}
