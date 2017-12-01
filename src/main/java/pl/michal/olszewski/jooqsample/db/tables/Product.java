/*
 * This file is generated by jOOQ.
*/
package pl.michal.olszewski.jooqsample.db.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;

import pl.michal.olszewski.jooqsample.db.Keys;
import pl.michal.olszewski.jooqsample.db.Public;
import pl.michal.olszewski.jooqsample.db.tables.records.ProductRecord;


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
public class Product extends TableImpl<ProductRecord> {

    private static final long serialVersionUID = 1665465334;

    /**
     * The reference instance of <code>PUBLIC.PRODUCT</code>
     */
    public static final Product PRODUCT = new Product();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProductRecord> getRecordType() {
        return ProductRecord.class;
    }

    /**
     * The column <code>PUBLIC.PRODUCT.ID</code>.
     */
    public final TableField<ProductRecord, Long> ID = createField("ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.PRODUCT.NAME</code>.
     */
    public final TableField<ProductRecord, String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>PUBLIC.PRODUCT.CUSTOMER_ID</code>.
     */
    public final TableField<ProductRecord, Long> CUSTOMER_ID = createField("CUSTOMER_ID", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>PUBLIC.PRODUCT</code> table reference
     */
    public Product() {
        this("PRODUCT", null);
    }

    /**
     * Create an aliased <code>PUBLIC.PRODUCT</code> table reference
     */
    public Product(String alias) {
        this(alias, PRODUCT);
    }

    private Product(String alias, Table<ProductRecord> aliased) {
        this(alias, aliased, null);
    }

    private Product(String alias, Table<ProductRecord> aliased, Field<?>[] parameters) {
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
    public Identity<ProductRecord, Long> getIdentity() {
        return Keys.IDENTITY_PRODUCT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ProductRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ProductRecord>> getKeys() {
        return Arrays.<UniqueKey<ProductRecord>>asList(Keys.CONSTRAINT_1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<ProductRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ProductRecord, ?>>asList(Keys.FKJ80N6400WNFQRT86QIMF9K6YS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product as(String alias) {
        return new Product(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Product rename(String name) {
        return new Product(name, null);
    }
}
