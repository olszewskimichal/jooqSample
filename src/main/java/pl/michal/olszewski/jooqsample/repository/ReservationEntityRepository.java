package pl.michal.olszewski.jooqsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.olszewski.jooqsample.entity.ReservationEntity;

@Repository
public interface ReservationEntityRepository extends JpaRepository<ReservationEntity, Long> {

}
