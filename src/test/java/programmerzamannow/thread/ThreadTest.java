package programmerzamannow.thread;

import org.junit.jupiter.api.Test;
import org.springframework.ui.context.Theme;

public class ThreadTest {

    @Test
    void mainThread(){
        String name = Thread.currentThread().getName();
        System.out.println(name);
    }

    @Test
    void createThread(){
        Runnable runnable = () -> {
            System.out.println("Hello from thread :" + Thread.currentThread().getName());
        };

        runnable.run();
        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Program Selesei");
    }

    @Test
    void threadSleep() throws InterruptedException{
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000L);
                System.out.println("Hello from thread :" + Thread.currentThread().getName());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };

        runnable.run();
        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Program Selesei");
        Thread.sleep(3_000L);
    }

    @Test
    void threadJoin() throws InterruptedException{
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000L);
                System.out.println("Hello from thread :" + Thread.currentThread().getName());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };

        runnable.run();
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Menunggu selesai");
        thread.join();

        // dengan menggunakan join maka aplikasi menunggu sampai runnable selesai

        System.out.println("Program Selesei");
    }

    @Test
    void threadInterrupt() throws InterruptedException{
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable :" + i);
                try {
                    Thread.sleep(1_000L);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        // praktek salah karena ketika di interupt hanya di tambilkan catch saja, seharusnya di return atau di break
        // seperti function dibawah

        runnable.run();
        Thread thread = new Thread(runnable);
        thread.start();
        thread.sleep(5_000);
        thread.interrupt();
        System.out.println("Menunggu selesai");
        thread.join();


        System.out.println("Program Selesei");
    }

    @Test
    void threadInterruptCorrect() throws InterruptedException{
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable:" + i);
                try {
                    Thread.sleep(1_000);
                }catch (InterruptedException e){
//                    e.printStackTrace();
                    return;
                }
            }
        };

        runnable.run();
        Thread thread = new Thread(runnable);
        thread.start();
        thread.sleep(3_000);
        thread.interrupt();
        System.out.println("Menunggu selesai");
        thread.join();


        System.out.println("Program Selesei");
    }

    @Test
    void threadInterrupManual() throws InterruptedException{
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                if (Thread.interrupted()) {
                    return;
                }
                System.out.println("Runnable:" + i);
            }
        };

        runnable.run();
        Thread thread = new Thread(runnable);
        thread.start();
        thread.sleep(3_000);
        thread.interrupt();
        System.out.println("Menunggu selesai");
        thread.join();


        System.out.println("Program Selesei");
    }

    @Test
    void threadName(){
        Thread thread = new Thread(() -> {
            System.out.println("Run in thread :" + Thread.currentThread().getName());
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Run in thread :" +Thread.currentThread().getName());
            }
        });

        //Merubah nama thread

        thread.setName("Vito");
        thread.start();
        thread2.setName("Vito_2");
        thread2.start();
    }

    @Test
    void threadState() throws InterruptedException{
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getState());
            System.out.println("Run in thread :" + Thread.currentThread().getName());
        });

        thread.setName("Vito");
        System.out.println(thread.getState());
        thread.start();
        thread.join();
        System.out.println(thread.getState());

        //menampilkna status thread (statenya)
    }

    @Test
    void threadRuntime() throws InterruptedException{
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int threadPoolSize = Math.max(availableProcessors / 2, 1);
        System.out.println(String.format("Creating settlement engine executor service. Available processors [%d]. Thread pool size [%d].",
                Integer.valueOf(availableProcessors), Integer.valueOf(threadPoolSize)));

    }

}
