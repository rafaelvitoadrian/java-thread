package programmerzamannow.thread;

public class DaemonApp {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("Run Thread");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.setDaemon(true);
        thread.start();

        // Daemon bisa berfungsi untuk
        // background jobs seperti menghapus garbage memory atau sampah yang sudah tidak digunakan menggunakan daemon
        // thread


    }
}
