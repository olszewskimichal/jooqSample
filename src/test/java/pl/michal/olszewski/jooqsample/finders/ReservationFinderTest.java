package pl.michal.olszewski.jooqsample.finders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
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
import pl.michal.olszewski.jooqsample.repository.ReservationEntityRepository;

@SpringBootTest()
@RunWith(JUnitPlatform.class)
@ExtendWith(org.springframework.test.context.junit.jupiter.SpringExtension.class)
@Transactional
@ActiveProfiles(value = "test")
public class ReservationFinderTest {

  @Autowired
  protected ReservationEntityRepository entityRepository;

  @Autowired
  private ReservationFinder reservationFinder;

  @Test
  void shouldReturnReservationByNameWhenExists() {
    //given
    entityRepository.save(ReservationEntity.builder().name("name").description("desc").build());
    //when
    Optional<ReservationDTO> name = reservationFinder.findByName("name");
    //then
    assertAll(
        () -> assertThat(name.isPresent()).isTrue(),
        () -> assertThat(name.get().getName()).isEqualTo("name"),
        () -> assertThat(name.get().getDescription()).isEqualTo("desc")
    );
  }

  @Test
  void shouldReturnOptionalEmptyByNameWhenNotExists() {
    //given
    //when
    Optional<ReservationDTO> name = reservationFinder.findByName("name");
    //then
    assertAll(
        () -> assertThat(name.isPresent()).isFalse(),
        () -> assertThat(name).isEqualTo(Optional.empty())
    );
  }

}