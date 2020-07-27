package lesson4.repository;

public interface MyRepository<T> {

    T getById(Long id);
    T saveOrUpdate(T t);

}
