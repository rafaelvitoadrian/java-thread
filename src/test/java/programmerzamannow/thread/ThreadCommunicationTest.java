package programmerzamannow.thread;

import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.defaultanswers.GloballyConfiguredAnswer;

public class ThreadCommunicationTest {

    private String message;

    @Test
    void Manual() throws InterruptedException{
        Thread thread1 = new Thread(() -> {
           while (message == null){
               //wait
           }
            System.out.println(message);
        });

        Thread thread2 = new Thread(() -> {
            message = "Vito";
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    @Test
    void LockNotify() throws InterruptedException{
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock){
                try {
                    // ketika lock.wait() maka synchronized (lock) akan dilepas sehingga lock bisa diakses thread lain
                    lock.wait();
                    System.out.println(message);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock){
                message = "Vito";
                lock.notify();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    @Test
    void LockNotifyAll() throws InterruptedException{
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock){
                try {
                    // ketika lock.wait() maka synchronized (lock) akan dilepas sehingga lock bisa diakses thread lain
                    lock.wait();
                    System.out.println(message);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        });

        Thread thread3 = new Thread(() -> {
            synchronized (lock){
                try {
                    // ketika lock.wait() maka synchronized (lock) akan dilepas sehingga lock bisa diakses thread lain
                    lock.wait();
                    System.out.println(message);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock){
                message = "Vito";
                lock.notifyAll();
                // memberikan ke lebih dari 1 thread yang menunggu message diisi
            }
        });

        thread1.start();
        thread3.start();
        thread2.start();

        thread1.join();
        thread3.join();
        thread2.join();
    }
}
