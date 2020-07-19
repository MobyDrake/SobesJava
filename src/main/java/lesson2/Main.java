package lesson2;

import lesson2.Array.MyArrayList;
import lesson2.Array.MyArray;
import lesson2.Link.MyLinkList;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        MyArray<String> list = new MyArrayList<>();

        list.add("a");
        list.add("b");
        list.add("c");

        list.remove("b");

        list.display();

        var link = new MyLinkList<String>();
        link.add("a");
        link.add("b");
        link.add("c");
        System.out.println(link.size());


        link.removeFirst();

        link.display();
    }
}
