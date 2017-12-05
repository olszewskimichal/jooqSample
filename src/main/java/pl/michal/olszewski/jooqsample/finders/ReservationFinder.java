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

  public List<ReservationDTO> findAll() {
    return dslContext.selectFrom(RESERVATION)
        .fetchInto(ReservationRecord.class)
        .stream()
        .map(this::convertQueryResultToModelObject)
        .collect(Collectors.toList());
  }

  public Optional<ReservationDTO> getById(Long id) {
    Optional<ReservationRecord> record = dslContext.selectFrom(RESERVATION)
        .where(RESERVATION.ID.equal(id))
        .fetchOptional();
    return record.map(this::convertQueryResultToModelObject);
  }

  public boolean exists(Long id) {
    return dslContext.selectOne()
        .from(RESERVATION)
        .where(RESERVATION.ID.equal(id)).fetchOptional().isPresent();
  }

  public long count() {
    return dslContext.selectCount().from(RESERVATION).fetchOne(0, int.class);
  }

  @Override
  public List<ReservationDTO> findAll(SeekPagable pageable) {
    List<ReservationRecord> fetch = dslContext.selectFrom(RESERVATION)
        .orderBy(RESERVATION.ID.asc())
        .seek(pageable.getLastId() != null ? pageable.getLastId() : 0L)
        .limit(pageable.getPageSize())
        .fetchInto(ReservationRecord.class);
    return fetch.stream().map(this::convertQueryResultToModelObject).collect(Collectors.toList());
  }

  private ReservationDTO convertQueryResultToModelObject(ReservationRecord queryResult) {
    return ReservationDTO.builder()
        .name(queryResult.getName())
        .description(queryResult.getDescription())
        .build();
  }
}
