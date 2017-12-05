package pl.michal.olszewski.jooqsample.finders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@Value
@AllArgsConstructor
public class SeekPagable {

  private final int pageSize;
  private final Long lastId;
}
