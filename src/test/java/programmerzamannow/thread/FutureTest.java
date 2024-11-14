package programmerzamannow.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
//import java.util.concurrent.Executors;

public class FutureTest {

    @Test
    void FutureTest()throws InterruptedException,ExecutionException {
//        ExecutorService executor = new Executors.newSingleThreadExecutor();
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // deklarasi runnable yang dapat return suatu hal sesuai yang kita deklarasikan
        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "Hi";
        };


        //deklarasi hasilnya sesuai yang kita tentukan untuk nilainya
        Future<String> future = executor.submit(callable);
        System.out.println("Selesai Future");

        // untuk cek apakah sudah selesai atau belom
        while (!future.isDone()){
            System.out.println("Waiting...");
            Thread.sleep(1000);
        }





        // untuk mendapatkan hasilnya dari runnable callable, dimana jika belum ada hasilnya maka akan terus ditunggu
        // sampai hasilnya ada
        System.out.println(future.get());


    }

    @Test
    void FutureCancelTest()throws InterruptedException,ExecutionException {
//        ExecutorService executor = new Executors.newSingleThreadExecutor();
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // deklarasi runnable yang dapat return suatu hal sesuai yang kita deklarasikan
        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "Hi";
        };


        //deklarasi hasilnya sesuai yang kita tentukan untuk nilainya
        Future<String> future = executor.submit(callable);
        System.out.println("Selesai Future");

        Thread.sleep(2000);
        future.cancel(true);
        // untuk mendapatkan hasilnya dari runnable callable, dimana jika belum ada hasilnya maka akan terus ditunggu
        // sampai hasilnya ada
        System.out.println(future.get());


    }
}
