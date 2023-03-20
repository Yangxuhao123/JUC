package main.java.com.juc.exchangedemo;

import java.util.concurrent.Exchanger;

public class ExchangeDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(){
            @Override
            public void run() {
                try {
                    String data = exchanger.exchange("线程1的数据");
                    System.out.println("线程1获取到线程2交换过来的数据："+data);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    String data = exchanger.exchange("线程2的数据");
                    System.out.println("线程2获取到线程1交换过来的数据："+data);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }
}
