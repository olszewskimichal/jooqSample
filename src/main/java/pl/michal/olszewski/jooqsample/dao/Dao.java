package pl.michal.olszewski.jooqsample.dao;

import java.io.Serializable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface Dao<T, ID extends Serializable> {

  <S extends T> S save(S entity);

  <S extends T> Iterable<S> save(Iterable<S> entities);

  void delete(ID id);

  void delete(T entity);

  void deleteAll();

  T update(T entity);


}
