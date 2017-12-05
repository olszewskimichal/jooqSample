package pl.michal.olszewski.jooqsample.finders;

import static pl.michal.olszewski.jooqsample.db.tables.Customer.CUSTOMER;
import static pl.michal.olszewski.jooqsample.db.tables.Product.PRODUCT;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.michal.olszewski.jooqsample.dto.CustomerDTO;
import pl.michal.olszewski.jooqsample.dto.ProductDTO;

@Repository
@Transactional(readOnly = true)
public class CustomerFinder implements Finder<CustomerDTO, Long> {

  private final DSLContext dslContext;

  public CustomerFinder(DSLContext dslContext) {
    this.dslContext = dslContext;
  }

  public Optional<CustomerDTO> findByEmail(String email) {
    Map<CustomerDTO, List<ProductDTO>> map = this.dslContext.select()
        .from(CUSTOMER)
        .leftJoin(PRODUCT)
        .on(CUSTOMER.ID.eq(PRODUCT.CUSTOMER_ID))
        .where(CUSTOMER.EMAIL.eq(email))
        .fetchGroups(
            r -> r.into(CUSTOMER).into(CustomerDTO.class),
            r -> r.into(PRODUCT).into(ProductDTO.class)
        );
    return map.entrySet().stream()
        .map(v -> new CustomerDTO(v.getKey().getId(), v.getKey().getEmail(), new HashSet<ProductDTO>(v.getValue())))
        .findFirst();
  }

  public List<CustomerDTO> findAll() {
    Map<CustomerDTO, List<ProductDTO>> map = this.dslContext.select()
        .from(CUSTOMER)
        .leftJoin(PRODUCT)
        .on(CUSTOMER.ID.eq(PRODUCT.CUSTOMER_ID))
        .fetchGroups(
            r -> r.into(CUSTOMER).into(CustomerDTO.class),
            r -> r.into(PRODUCT).into(ProductDTO.class)
        );
    return map.entrySet().stream()
        .map(v -> new CustomerDTO(v.getKey().getId(), v.getKey().getEmail(), new HashSet<ProductDTO>(v.getValue())))
        .collect(Collectors.toList());
  }

  @Override
  public Optional<CustomerDTO> getById(Long id) {
    Map<CustomerDTO, List<ProductDTO>> map = this.dslContext.select()
        .from(CUSTOMER)
        .leftJoin(PRODUCT)
        .on(CUSTOMER.ID.eq(PRODUCT.CUSTOMER_ID))
        .where(CUSTOMER.ID.eq(id))
        .fetchGroups(
            r -> r.into(CUSTOMER).into(CustomerDTO.class),
            r -> r.into(PRODUCT).into(ProductDTO.class)
        );
    return map.entrySet().stream()
        .map(v -> new CustomerDTO(v.getKey().getId(), v.getKey().getEmail(), new HashSet<ProductDTO>(v.getValue())))
        .findFirst();
  }

  @Override
  public boolean exists(Long id) {
    return dslContext.selectOne()
        .from(CUSTOMER)
        .where(CUSTOMER.ID.equal(id)).fetchOptional().isPresent();
  }

  @Override
  public long count() {
    return dslContext.selectCount().from(CUSTOMER).fetchOne(0, int.class);
  }

  @Override
  public List<CustomerDTO> findAll(SeekPageable pageable) {
    return dslContext.select().from(CUSTOMER)
        .leftJoin(PRODUCT)
        .on(CUSTOMER.ID.eq(PRODUCT.CUSTOMER_ID))
        .orderBy(CUSTOMER.ID.asc())
        .seek(pageable.getLastId() != null ? pageable.getLastId() : 0L)
        .limit(pageable.getPageSize())
        .fetchGroups(
            r -> r.into(CUSTOMER).into(CustomerDTO.class),
            r -> r.into(PRODUCT).into(ProductDTO.class)
        ).entrySet()
        .stream()
        .map(v -> new CustomerDTO(v.getKey().getId(), v.getKey().getEmail(), new HashSet<ProductDTO>(v.getValue())))
        .collect(Collectors.toList());
  }


}
