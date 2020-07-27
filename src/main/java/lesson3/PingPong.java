package lesson3;

import java.util.concurrent.TimeUnit;

/**
 * Две версии решение задачки, не уверен что вторая версия правильная,
 * но она компактнее - красивее.
 * Меня смущает в V2 момет с вызовом notify() перед wait() - получается мы будем другие нити и только потом уходим
 * в спячку, и в итоге мы освобождаем монитор будучи ещё занятым. При наличии всего двух нитий ошибок не выявленно, но
 * при добавление третей нити одна из них вечно в ожидании - плохо.
 * И ещё минус заключается в старте - не учитывается что сначало должен быть "PING"
 */

public class PingPong {
    private static final Object monitor = new Object();
    private volatile String currentTurn = "PING";

    public static void main(String[] args) {
        //V1
        PingPong pong = new PingPong();
        new Thread(pong::pong).start();
        new Thread(pong::ping).start();


        //V2
        new Thread(() -> pong("PING")).start();
        new Thread(() -> pong("pong")).start();

    }

    //V1
    private void pong() {
        synchronized (monitor) {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    while (!currentTurn.equals("pong")) {
                        monitor.wait();
                    }
                    System.out.println(currentTurn);
                    currentTurn = "PING";
                    TimeUnit.SECONDS.sleep(1);
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void ping() {
        synchronized (monitor) {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    while (!currentTurn.equals("PING")) {
                        monitor.wait();
                    }
                    System.out.println(currentTurn);
                    currentTurn = "pong";
                    TimeUnit.SECONDS.sleep(1);
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //V2
    private static void pong(String msg) {
        synchronized (monitor) {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(msg);
                    TimeUnit.SECONDS.sleep(1);
                    monitor.notify();
                    monitor.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
