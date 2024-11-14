package programmerzamannow.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

    @Test
    void testExecutorService() throws InterruptedException{
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Runnable in thread : " + Thread.currentThread().getName());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void testExecutorServiceFix() throws InterruptedException{
        // Hati hati ketika membuat executorservice dengan class Executor, karena mereka memiliki jumlah kapasitas
        // queue yang tidak terbatas
        ExecutorService executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Runnable in thread : " + Thread.currentThread().getName());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
