import com.juc.waitnotifydemo.MyQueue;

public class QueueTest {

    public static void main(String[] args) throws InterruptedException {
        MyQueue myQueue = new MyQueue();
        Thread threadA = new Thread(()->{
            myQueue.offer("hello1");
        },"threadA");
        threadA.start();

        Thread threadB = new Thread(()->{
            myQueue.offer("hello2");
        },"threadB");
        threadB.start();



        Thread.sleep(10000);
        Thread threadC = new Thread(()->{
            String res1 = myQueue.take();
            System.out.println(res1);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String res2 = myQueue.take();
            System.out.println(res2);
        });
        threadC.start();

        //  threadAwaiting...
        //  10s
        //  hello1
        //  threadBwaiting
        //  threadBwaiting
        //  10s
        //  hello2

    }
}
