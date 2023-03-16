package main.java.com.juc.threadlocaldemo;

public class ThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocal<Long> requestId = new ThreadLocal<>();
        ThreadLocal<Long> txid = new ThreadLocal<>();

        new Thread(){
            @Override
            public void run() {
                requestId.set(1L);
                txid.set(1L);
                System.out.println("线程1："+requestId.get());
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                requestId.set(2L);
                System.out.println("线程2："+requestId.get());
            }
        }.start();
    }
}
