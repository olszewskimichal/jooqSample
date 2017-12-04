package pl.michal.olszewski.jooqsample.dao;

import java.io.Serializable;
import java.util.stream.Stream;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface Dao<T, ID extends Serializable> {

  T save(T entity);

  Stream<T> save(Stream<T> entities);

  void delete(ID id);

  void delete(T entity);

  void deleteAll();

  T update(T entity);


}
