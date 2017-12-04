package pl.michal.olszewski.jooqsample.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class ConstantDateTimeService implements DateTimeService {

  @Override
  public LocalDateTime getCurrentDateTime() {
    return LocalDateTime.ofInstant(getCurrentTimestamp(), ZoneId.systemDefault());
  }

  @Override
  public Instant getCurrentTimestamp() {
    return Instant.parse("2000-01-01T10:00:55.000Z");
  }
}