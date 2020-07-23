package lesson3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class CounterThead implements Runnable {
        private Counter counter;
        private Lock lock;

        public CounterThead(Counter counter, Lock lock) {
            this.counter = counter;
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 5; i++) {
                    counter.increment();
                    System.out.println(Thread.currentThread().getName() + " Counter: " + counter.getCount());
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }