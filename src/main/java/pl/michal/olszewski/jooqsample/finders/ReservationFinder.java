package pl.michal.olszewski.jooqsample.finders;

import java.util.Optional;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import pl.michal.olszewski.jooqsample.db.tables.Reservation;
import pl.michal.olszewski.jooqsample.dto.ReservationDTO;

@Repository
public class ReservationFinder {

  private final DSLContext dslContext;

  public ReservationFinder(DSLContext dslContext) {
    this.dslContext = dslContext;
  }

  public Optional<ReservationDTO> findByName(String name) {
    return this.dslContext.select()
        .from(Reservation.RESERVATION)
        .where(Reservation.RESERVATION.NAME.eq(name)).fetch()
        .into(ReservationDTO.class).stream().findFirst();
  }
}
