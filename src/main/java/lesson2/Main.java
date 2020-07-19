package lesson2;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MyList<String> list = new MyArrayList<>();

        list.add("a");
        list.add("b");
        list.add("c");

        list.remove("b");

        list.display();
    }
}
