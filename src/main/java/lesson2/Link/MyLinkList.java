package lesson2.Link;

import java.util.LinkedList;

public class MyLinkList<T> implements MyLink<T> {

    private int size;
    private Entry<T> first;
    private Entry<T> last;


    @Override
    public void add(T value) {
        final Entry<T> l = last;
        Entry<T> entry = new Entry<>(value, l, null);
        last = entry;
        if(l == null) first = entry;
        else l.next = entry;
        size++;
    }

    @Override
    public boolean remove(T value) {
        Entry<T> prev = null;
        Entry<T> curr = first;
        while (curr != null) {
            if (curr.value.equals(value)) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }

        if (curr == null) return false;

        if (curr == first) first = curr.next;
        else prev.next = curr.next;

        size--;
        return true;
    }

    @Override
    public T get(int index) {
        return entry(index).value;
    }

    @Override
    public boolean contain(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public void insertFirst(T value) {
        Entry<T> f = first;
        Entry<T> entry = new Entry<>(value, null, f);

        f.prev = entry;
        first = entry;
        size++;
    }

    @Override
    public boolean removeFirst() {
        if (isEmpty()) return false;
        first = first.next;
        size--;
        return true;
    }

    @Override
    public T getFirst() {
        return first.value;
    }

    @Override
    public void display() {
        System.out.println("---------");
        Entry<T> current = first;
        while(current != null) {
            System.out.println(current.value);
            current = current.next;
        }
        System.out.println("---------");
    }

    @Override
    public int indexOf(T value) {
        if (value == null) return -1;

        int index = 0;
        for (Entry<T> entry = first; entry != null; entry = entry.next) {
            if (entry.value.equals(value)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private Entry<T> entry(int index) {
        if (index == 0) return first;
        if (index == size) return last;

        if (index < 0 || index > size) throw new ArrayIndexOutOfBoundsException();
        else {
            Entry<T> entry = first;
            for (int i = 0; i < index; i++) {
                entry = entry.next;
            }
            return entry;
        }
    }

    private static class Entry<E> {
        public final E value;
        public Entry<E> next;
        public Entry<E> prev;

        public Entry(E value, Entry<E> prev, Entry<E> next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
