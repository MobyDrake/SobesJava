package lesson2.Link;

import lesson2.ICollection;

public interface MyLink<T> extends ICollection {

    void add(T value);
    boolean remove(T value);
    T get(int index);
    boolean contain(T value);

    void insertFirst(T v);
    boolean removeFirst();
    T getFirst();

    void display();
    int indexOf(T value);
}
