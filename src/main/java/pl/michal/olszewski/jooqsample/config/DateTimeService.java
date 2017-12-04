package pl.michal.olszewski.jooqsample.config;

import java.time.Instant;
import java.time.LocalDateTime;

public interface DateTimeService {

  public LocalDateTime getCurrentDateTime();

  public Instant getCurrentTimestamp();
}