/*
 * This file is generated by jOOQ.
 */
package pl.michal.olszewski.jooqsample.db.tables.records;


import javax.annotation.Generated;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
import pl.michal.olszewski.jooqsample.db.tables.Product;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ProductRecord extends UpdatableRecordImpl<ProductRecord> implements Record3<Long, String, Long> {

  private static final long serialVersionUID = 918342722;

  /**
   * Create a detached ProductRecord
   */
  public ProductRecord() {
    super(Product.PRODUCT);
  }

  /**
   * Create a detached, initialised ProductRecord
   */
  public ProductRecord(Long id, String name, Long customerId) {
    super(Product.PRODUCT);

    set(0, id);
    set(1, name);
    set(2, customerId);
  }

  /**
   * Getter for <code>PUBLIC.PRODUCT.ID</code>.
   */
  public Long getId() {
    return (Long) get(0);
  }

  /**
   * Setter for <code>PUBLIC.PRODUCT.ID</code>.
   */
  public void setId(Long value) {
    set(0, value);
  }

  /**
   * Getter for <code>PUBLIC.PRODUCT.NAME</code>.
   */
  public String getName() {
    return (String) get(1);
  }

  /**
   * Setter for <code>PUBLIC.PRODUCT.NAME</code>.
   */
  public void setName(String value) {
    set(1, value);
  }

  // -------------------------------------------------------------------------
  // Primary key information
  // -------------------------------------------------------------------------

  /**
   * Getter for <code>PUBLIC.PRODUCT.CUSTOMER_ID</code>.
   */
  public Long getCustomerId() {
    return (Long) get(2);
  }

  // -------------------------------------------------------------------------
  // Record3 type implementation
  // -------------------------------------------------------------------------

  /**
   * Setter for <code>PUBLIC.PRODUCT.CUSTOMER_ID</code>.
   */
  public void setCustomerId(Long value) {
    set(2, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Record1<Long> key() {
    return (Record1) super.key();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Row3<Long, String, Long> fieldsRow() {
    return (Row3) super.fieldsRow();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Row3<Long, String, Long> valuesRow() {
    return (Row3) super.valuesRow();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Field<Long> field1() {
    return Product.PRODUCT.ID;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Field<String> field2() {
    return Product.PRODUCT.NAME;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Field<Long> field3() {
    return Product.PRODUCT.CUSTOMER_ID;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Long value1() {
    return getId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String value2() {
    return getName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Long value3() {
    return getCustomerId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ProductRecord value1(Long value) {
    setId(value);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ProductRecord value2(String value) {
    setName(value);
    return this;
  }

  // -------------------------------------------------------------------------
  // Constructors
  // -------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  @Override
  public ProductRecord value3(Long value) {
    setCustomerId(value);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ProductRecord values(Long value1, String value2, Long value3) {
    value1(value1);
    value2(value2);
    value3(value3);
    return this;
  }
}
