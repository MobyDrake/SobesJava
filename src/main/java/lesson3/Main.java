package lesson3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Lock lock = new ReentrantLock();

        for (int i = 1; i < 6; i++) {
            Thread t = new Thread(new CounterThead(counter, lock));
            t.setName("Thread " + i);
            t.start();
        }
    }
}
