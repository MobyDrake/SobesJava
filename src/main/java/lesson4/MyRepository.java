package lesson4;

public interface MyRepository<T> {

    T getById(Long id);
    T saveOrUpdate(T t);

}
