package pl.michal.olszewski.jooqsample.dao;

import static pl.michal.olszewski.jooqsample.db.tables.Reservation.RESERVATION;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import pl.michal.olszewski.jooqsample.config.DateTimeService;
import pl.michal.olszewski.jooqsample.db.tables.records.ReservationRecord;
import pl.michal.olszewski.jooqsample.entity.ReservationEntity;

@Repository
public class ReservationDao implements Dao<ReservationEntity, Long> {

  private final DSLContext dslContext;
  private final DateTimeService dateTimeService;


  public ReservationDao(DSLContext dslContext, DateTimeService dateTimeService) {
    this.dslContext = dslContext;
    this.dateTimeService = dateTimeService;
  }

  public ReservationEntity save(ReservationEntity reservation) {
    ReservationRecord reservationRecord = dslContext.insertInto(RESERVATION)
        .set(createRecord(reservation))
        .returning().fetchOne();
    return convertQueryResultToModelObject(reservationRecord);
  }

  @Override
  public Stream<ReservationEntity> save(Stream<ReservationEntity> entities) {
    List<ReservationRecord> collect = entities.map(this::createRecord).collect(Collectors.toList());
    dslContext.batchInsert(collect).execute();
    return entities;
  }

  @Override
  public void delete(Long id) {
    dslContext.delete(RESERVATION)
        .where(RESERVATION.ID.equal(id))
        .execute();
  }

  @Override
  public void delete(ReservationEntity entity) {
    dslContext.delete(RESERVATION)
        .where(RESERVATION.ID.equal(entity.getId()))
        .execute();
  }

  @Override
  public void deleteAll() {
    dslContext.delete(RESERVATION)
        .execute();
  }

  @Override
  public ReservationEntity update(ReservationEntity entity) {
    dslContext.update(RESERVATION)
        .set(RESERVATION.NAME, entity.getName())
        .set(RESERVATION.DESCRIPTION, entity.getDescription())
        .where(RESERVATION.ID.equal(entity.getId()))
        .execute();

    return entity;
  }

  private ReservationRecord createRecord(ReservationEntity entity) {
    return new ReservationRecord(entity.getId(), entity.getDescription(), entity.getName());
  }

  private ReservationEntity convertQueryResultToModelObject(ReservationRecord queryResult) {
    return ReservationEntity.builder()
        .name(queryResult.getName())
        .description(queryResult.getDescription())
        .id(queryResult.getId())
        .build();
  }
}
