package programmerzamannow.thread;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    @Test
    void delayedJob() throws InterruptedException{
        //timeTask sama seperti runnable membutuhkan method run
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Delayed Task");
            }
        };


        //Timer seperti thread bisa jalankan shchedule
        Timer timer = new Timer();
        timer.schedule(task,2000);

        // timer tidak memiliki join sehingga threadnya kita sleep selama 3 detik
        Thread.sleep(3000L);
    }

    @Test
    void periodicJob() throws InterruptedException{
        //timeTask sama seperti runnable membutuhkan method run
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Delayed Task");
            }
        };


        //Timer seperti thread bisa jalankan shchedule
        // periodic memungkinkan untuk dijalankan setiap waktu yang di set di paramter periodic, jadi selama aplikasi
        // jalan maka setiap 2 (contoh) detik akan terus dijalankan
        // berguna untuk job yang membutuhkan dijalankan secara terjadwal tanpa harus menggunakan thread sleep
        Timer timer = new Timer();
        timer.schedule(task,2000,2000);

        // timer tidak memiliki join sehingga threadnya kita sleep selama 3 detik
        Thread.sleep(10000L);
    }
}
