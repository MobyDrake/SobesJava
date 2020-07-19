package lesson2.Array;

import lesson2.ICollection;

public interface MyArray<T> extends ICollection {

    void add(T value);
    boolean remove(T value);
    boolean remove(int index);
    T get(T value);
    T get(int index);
    int size();
    int indexOf(T value);
    void display();
    boolean contain(T value);


    default void addAll(T... values) {
        for(T value : values) {
            add(value);
        }
    }
}
