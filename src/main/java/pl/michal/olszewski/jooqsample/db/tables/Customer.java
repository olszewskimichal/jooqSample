/*
 * This file is generated by jOOQ.
*/
package pl.michal.olszewski.jooqsample.db.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;

import pl.michal.olszewski.jooqsample.db.Keys;
import pl.michal.olszewski.jooqsample.db.Public;
import pl.michal.olszewski.jooqsample.db.tables.records.CustomerRecord;


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
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Customer extends TableImpl<CustomerRecord> {

    private static final long serialVersionUID = 1833978202;

    /**
     * The reference instance of <code>PUBLIC.CUSTOMER</code>
     */
    public static final Customer CUSTOMER = new Customer();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CustomerRecord> getRecordType() {
        return CustomerRecord.class;
    }

    /**
     * The column <code>PUBLIC.CUSTOMER.ID</code>.
     */
    public final TableField<CustomerRecord, Long> ID = createField("ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.CUSTOMER.EMAIL</code>.
     */
    public final TableField<CustomerRecord, String> EMAIL = createField("EMAIL", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * Create a <code>PUBLIC.CUSTOMER</code> table reference
     */
    public Customer() {
        this("CUSTOMER", null);
    }

    /**
     * Create an aliased <code>PUBLIC.CUSTOMER</code> table reference
     */
    public Customer(String alias) {
        this(alias, CUSTOMER);
    }

    private Customer(String alias, Table<CustomerRecord> aliased) {
        this(alias, aliased, null);
    }

    private Customer(String alias, Table<CustomerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<CustomerRecord, Long> getIdentity() {
        return Keys.IDENTITY_CUSTOMER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CustomerRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_5;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CustomerRecord>> getKeys() {
        return Arrays.<UniqueKey<CustomerRecord>>asList(Keys.CONSTRAINT_5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Customer as(String alias) {
        return new Customer(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Customer rename(String name) {
        return new Customer(name, null);
    }
}