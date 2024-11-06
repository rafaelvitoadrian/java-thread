package programmerzamannow.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    @Test
    void create(){
        int minThread = 10;
        int maxThread = 100;
        //alive ini jika misal ada 15 thread dan tidak digunakan maka akan di kill oleh garbage memory java dan
        // dikembalikan lagi menjadi 10 sesuai dengan minimal yang sudah kita set. jika memang sudah 10 dan tidak
        // kepake 10 thrad maka tidak akan di kill karena sudah minTHread yang kita set
        long alive = 1;
        TimeUnit aliveTime = TimeUnit.MINUTES;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(minThread,maxThread,alive,aliveTime,queue);
    }

    @Test
    void executor() throws InterruptedException {
        int minThread = 10;
        int maxThread = 100;
        //alive ini jika misal ada 15 thread dan tidak digunakan maka akan di kill oleh garbage memory java dan
        // dikembalikan lagi menjadi 10 sesuai dengan minimal yang sudah kita set. jika memang sudah 10 dan tidak
        // kepake 10 thrad maka tidak akan di kill karena sudah minTHread yang kita set
        long alive = 1;
        TimeUnit aliveTime = TimeUnit.MINUTES;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(minThread,maxThread,alive,aliveTime,queue);

        Runnable runnable = () ->{
            try {
                Thread.sleep(5000);
                System.out.println("Runnable from thread : " +Thread.currentThread().getName());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };

        executor.execute(runnable);

        // menggunakan sleep karena JUnit async jadi akan ditinggal jika thread belum selesai
        Thread.sleep(6000);
    }

    @Test
    void shutdown() throws InterruptedException {
        int minThread = 10;
        int maxThread = 100;
        //alive ini jika misal ada 15 thread dan tidak digunakan maka akan di kill oleh garbage memory java dan
        // dikembalikan lagi menjadi 10 sesuai dengan minimal yang sudah kita set. jika memang sudah 10 dan tidak
        // kepake 10 thrad maka tidak akan di kill karena sudah minTHread yang kita set
        long alive = 1;
        TimeUnit aliveTime = TimeUnit.MINUTES;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1000);

        // ThreadPool tidak akan bertambah max jika queue masih belum penuh
//       ketika di run ini hanya 10 thread karena asumsi dari queue nya belum sampe 1000
        ThreadPoolExecutor executor = new ThreadPoolExecutor(minThread,maxThread,alive,aliveTime,queue);

        for (int i = 0; i < 1000; i++) {
            final int task = i;
            Runnable runnable = () ->{
                try {
                    Thread.sleep(1000);
                    // thread sleep punya behavior jika di shutdown now maka dia akan terinterput dan menampilkan error
                    System.out.println("Runnable from task : " + task + " thread : " +Thread.currentThread().getName());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            };
            executor.execute(runnable);
        }

        // mematikan namum menunggu selesai
//        executor.shutdown();
        // langsun mematikan dengan interups
//        executor.shutdownNow();
        // menunggu sampai seles ai dengan batas
        executor.awaitTermination(1,TimeUnit.DAYS);
    }

    @Test
    void RejectedHandler() throws InterruptedException {
        int minThread = 10;
        int maxThread = 100;
        long alive = 1;
        TimeUnit aliveTime = TimeUnit.MINUTES;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
        LogRejectedExecutionHandler rejectHandler = new LogRejectedExecutionHandler();

        // ditambah di paramter
//        FUngsi rejectedmethod adalah agar runnable tidak masuk ke queue dan mengambil banyak memori dengan kita
//        menanganinya maka kita dapat memanajemen memory kita tanpa takut kena over memory dan aplikasi down. di
//        rejectmethod bisa diarahkan ke halaman lain ataupun lainnya asal tidak menyimpan runnablenya di queue yang
//        akan memakan memory
        ThreadPoolExecutor executor = new ThreadPoolExecutor(minThread,maxThread,alive,aliveTime,queue,rejectHandler);

        for (int i = 0; i < 1000; i++) {
            final int task = i;
            Runnable runnable = () ->{
                try {
                    Thread.sleep(1000);
                    // thread sleep punya behavior jika di shutdown now maka dia akan terinterput dan menampilkan error
                    System.out.println("Runnable from task : " + task + " thread : " +Thread.currentThread().getName());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            };
            executor.execute(runnable);
        }

        executor.awaitTermination(1,TimeUnit.DAYS);
    }

    public static class LogRejectedExecutionHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("Task " + r + " is rejected");
        }
    }
}
