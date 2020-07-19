package lesson2;

import lesson2.Array.MyArrayList;
import lesson2.Array.MyArray;

public class Main {
    public static void main(String[] args) {
        MyArray<String> list = new MyArrayList<>();

        list.add("a");
        list.add("b");
        list.add("c");

        list.remove("b");

        list.display();
    }
}
