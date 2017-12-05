package pl.michal.olszewski.jooqsample.config;

import java.time.Instant;
import java.time.LocalDateTime;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("application")
@Component
public class CurrentTimeDateTimeService implements DateTimeService {

  @Override
  public LocalDateTime getCurrentDateTime() {
    return LocalDateTime.now();
  }

  @Override
  public Instant getCurrentTimestamp() {
    return Instant.now();
  }
}