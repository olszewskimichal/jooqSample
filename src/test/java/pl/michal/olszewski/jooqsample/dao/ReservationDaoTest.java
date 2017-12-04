package pl.michal.olszewski.jooqsample.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import pl.michal.olszewski.jooqsample.dto.ReservationDTO;
import pl.michal.olszewski.jooqsample.entity.ReservationEntity;
import pl.michal.olszewski.jooqsample.finders.ReservationFinder;

@SpringBootTest()
@RunWith(JUnitPlatform.class)
@ExtendWith(org.springframework.test.context.junit.jupiter.SpringExtension.class)
@Transactional
@ActiveProfiles(value = "test")
public class ReservationDaoTest {

  @Autowired
  private ReservationFinder finder;

  @Autowired
  private ReservationDao dao;

  @Test
  void shouldCreateNewReservation() {
    //given
    ReservationEntity entity = ReservationEntity.builder().description("description").name("test").build();
    //when
    dao.save(entity);
    //then
    Optional<ReservationDTO> test = finder.findByName("test");
    assertThat(test).isPresent();
    assertThat(test.get().getDescription()).isEqualTo("description");
  }

}
