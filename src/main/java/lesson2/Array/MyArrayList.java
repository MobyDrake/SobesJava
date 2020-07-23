package lesson2.Array;

import java.util.Arrays;

public class MyArrayList<T> implements MyArray<T> {

    private static final int DEFAULT_CAPACITY = 8;

    private T[] array;
    private int size = 0;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        this.array = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (value == null) throw new IllegalArgumentException("value == NULL");

        checkGrow();
        array[size++] = value;
    }

    @Override
    public boolean remove(T value) {
        return remove(indexOf(value));
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index >= size) return false;

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        array[--size] = null;

        return true;
    }

    @Override
    public T get(T value) {
        return get(indexOf(value));
    }

    @Override
    public T get(int index) {
        if (index > 0 && index < size) {
            return array[index];
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(T value) {
        if (value == null) return -1;

        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void display() {
        System.out.println("-----");
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
        System.out.println("-----");
    }

    @Override
    public boolean contain(T value) {
        return indexOf(value) != -1;
    }

    private void checkGrow() {
        if (size == array.length) {
            array = grow();
        }
    }

    private T[] grow() {
        return Arrays.copyOf(array, array.length * 2);
    }

}
