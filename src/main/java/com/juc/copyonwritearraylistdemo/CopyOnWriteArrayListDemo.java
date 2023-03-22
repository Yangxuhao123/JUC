package main.java.com.juc.copyonwritearraylistdemo;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<String>();
        list.add("张三");
        list.set(0,"李四");
        list.remove(0);
        list.get(0);
        System.out.println(list);

        Iterator<String> iterator =  list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
