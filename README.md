# jooqSample
Testy biblioteki JOOQ do obsługi SQL

Aplikacja napisana z wykorzystaniem SpringBoota 1.5+ 
Struktura warstw dostepu do bazy została rozdzialona na tę ReadOnly czyli findery oraz WriteOnly czyli dao

Przykłady opierają się na 3 encjach
### Encje

* Reservation
```
  private Long id;
  private String name;
  private String description;
```
* Customer
```
  private Long id;
  private String email;
  private Set<ProductEntity> productList = new HashSet<>();
```
* Product
```
  private Long id;
  private String name;
  private CustomerEntity customerEntity;
```
### Findery
Zalożeniem bylo by każdy finder: 
- implementował interfejs generyczny Finder
- był @Transactional(readOnly = true)
- zwracał obiekty z warstwy transportowej DTO
oraz by finder od Customerów od razu robil JOINa do tabeli Product
```
  List<T> findAll();
  Optional<T> getById(ID id);
  boolean exists(ID id);
  long count();
  List<T> findAll(SeekPageable pageable);
```
### Dao 
Kazdy implementował interfejs generyczny Dao
```
  T save(T entity);
  Stream<T> save(Stream<T> entities);
  void delete(ID id);
  void delete(T entity);
  void deleteAll();
  T update(T entity);
```
### Testy
Dla każdej implementacji Dao i Finder są testy napisane z wykorzystaniem Junit5
