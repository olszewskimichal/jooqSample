package pl.michal.olszewski.jooqsample.finders;

import static pl.michal.olszewski.jooqsample.db.tables.Reservation.RESERVATION;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import pl.michal.olszewski.jooqsample.db.tables.records.ReservationRecord;
import pl.michal.olszewski.jooqsample.dto.ReservationDTO;

@Repository
public class ReservationFinder implements Finder<ReservationDTO, Long> {

  private final DSLContext dslContext;

  public ReservationFinder(DSLContext dslContext) {
    this.dslContext = dslContext;
  }

  public Optional<ReservationDTO> findByName(String name) {
    Optional<ReservationRecord> record = this.dslContext
        .selectFrom(RESERVATION)
        .where(RESERVATION.NAME.eq(name))
        .fetchOptional();

    return record.map(this::convertQueryResultToModelObject);
  }

  @Override
  public List<ReservationDTO> findAll() {
    return dslContext.selectFrom(RESERVATION)
        .fetchInto(ReservationRecord.class)
        .stream()
        .map(this::convertQueryResultToModelObject)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<ReservationDTO> getById(Long id) {
    ReservationRecord record = dslContext.selectFrom(RESERVATION)
        .where(RESERVATION.ID.equal(id))
        .fetchOne();
    if (record == null) {
      return Optional.empty();
    }
    return Optional.ofNullable(convertQueryResultToModelObject(record));
  }

  @Override
  public boolean exists(Long id) {
    return dslContext.fetchExists(
        dslContext.selectOne()
            .from(RESERVATION)
            .where(RESERVATION.ID.equal(id)));
  }

  @Override
  public long count() {
    return dslContext.selectCount().from(RESERVATION).fetchOne(0, int.class);
  }

  private ReservationDTO convertQueryResultToModelObject(ReservationRecord queryResult) {
    return ReservationDTO.builder()
        .name(queryResult.getName())
        .description(queryResult.getDescription())
        .build();
  }
}
