package pl.michal.olszewski.jooqsample.finders;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Finder<T, ID extends Serializable> {

  List<T> findAll();

  Optional<T> getById(ID id);

  boolean exists(ID id);

  long count();

  List<T> findAll(SeekPagable pageable);

}
