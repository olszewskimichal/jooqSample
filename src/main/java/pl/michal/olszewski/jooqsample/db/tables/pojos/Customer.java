/*
 * This file is generated by jOOQ.
 */
package pl.michal.olszewski.jooqsample.db.tables.pojos;


import java.io.Serializable;
import javax.annotation.Generated;


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
public class Customer implements Serializable {

  private static final long serialVersionUID = -2065773234;

  private Long id;
  private String email;

  public Customer() {
  }

  public Customer(Customer value) {
    this.id = value.id;
    this.email = value.email;
  }

  public Customer(
      Long id,
      String email
  ) {
    this.id = id;
    this.email = email;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Customer (");

    sb.append(id);
    sb.append(", ").append(email);

    sb.append(")");
    return sb.toString();
  }
}
