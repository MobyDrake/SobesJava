package lesson2;

public interface MyList<T> extends ICollection {

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
