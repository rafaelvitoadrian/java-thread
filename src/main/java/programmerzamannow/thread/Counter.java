package programmerzamannow.thread;

public class Counter {

    private Long value = 0L;

    // dengan menambahkan synchronized maka function ini hanya bisa di akses oleh 1 thread dalam 1 waktu
//    public synchronized void increment(){
//        value++;
//    }


    // ini artinya hanya didalam isi synchronized saja yang dapat diakes dalam 1 waktu sisanya akan menunggu (this)
    // bisa diganti value atau data lainnya
    public void increment(){
        synchronized (this){
            value++;
        }

    }

    public Long getValue(){
        return value;
    }




}
