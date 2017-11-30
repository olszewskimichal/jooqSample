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

import pl.michal.olszewski.jooqsample.db.tables.Reservation;


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
public class ReservationRecord extends UpdatableRecordImpl<ReservationRecord> implements Record3<Long, String, String> {

    private static final long serialVersionUID = 1315510740;

    /**
     * Setter for <code>PUBLIC.RESERVATION.ID</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.RESERVATION.ID</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>PUBLIC.RESERVATION.DESCRIPTION</code>.
     */
    public void setDescription(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.RESERVATION.DESCRIPTION</code>.
     */
    public String getDescription() {
        return (String) get(1);
    }

    /**
     * Setter for <code>PUBLIC.RESERVATION.NAME</code>.
     */
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>PUBLIC.RESERVATION.NAME</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Long, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Long, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Reservation.RESERVATION.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Reservation.RESERVATION.DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Reservation.RESERVATION.NAME;
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
        return getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReservationRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReservationRecord value2(String value) {
        setDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReservationRecord value3(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReservationRecord values(Long value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ReservationRecord
     */
    public ReservationRecord() {
        super(Reservation.RESERVATION);
    }

    /**
     * Create a detached, initialised ReservationRecord
     */
    public ReservationRecord(Long id, String description, String name) {
        super(Reservation.RESERVATION);

        set(0, id);
        set(1, description);
        set(2, name);
    }
}