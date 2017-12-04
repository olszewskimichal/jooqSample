package pl.michal.olszewski.jooqsample.dao;

import static pl.michal.olszewski.jooqsample.db.tables.Reservation.RESERVATION;

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
    ReservationRecord reservationRecord = dslContext.insertInto(RESERVATION, RESERVATION.NAME, RESERVATION.DESCRIPTION)
        .values(reservation.getName(), reservation.getDescription())
        .returning(RESERVATION.ID)
        .fetchOne();
    reservation.setId(reservationRecord.getId());
    return reservation;
  }

  @Override
  public <S extends ReservationEntity> Iterable<S> save(Iterable<S> entities) {
    return null;
  }

  @Override
  public void delete(Long aLong) {

  }

  @Override
  public void delete(ReservationEntity entity) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public ReservationEntity update(ReservationEntity entity) {
    return null;
  }

  private ReservationEntity convertQueryResultToModelObject(ReservationRecord queryResult) {
    return ReservationEntity.builder()
        .name(queryResult.getName())
        .description(queryResult.getDescription())
        .id(queryResult.getId())
        .build();
  }
}
