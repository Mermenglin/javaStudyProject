package thread;

import java.util.concurrent.TimeUnit;

/**
 * 三个线程，打印 ABCABCABCABCABCABCABCABCABCABC
 *
 * @author meimengling
 * @version 1.0
 * @date 2021-4-25 17:30
 */
public class ThreadWatiNotifyTest2 {
    public void main(String[] args) {

        // 第一个，不执行等待
        Thread t1 = new Thread(new test("A", "B", false));
        t1.start();

        Thread t2 = new Thread(new test("B", "C", true));
        t2.start();

        Thread t3 = new Thread(new test("C", "A", true));
        t3.start();
    }

    class test implements Runnable {

        private String name;
        private Object next;
        private boolean flag;

        public test(String name, Object next, boolean flag) {
            this.name = name;
            this.next = next;
            this.flag = flag;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    if (flag) {
                        // 进入等待，等待上一个线程唤醒
                        synchronized (name) {
                            name.wait();
                        }
                    } else {
                        flag = true;
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                    System.out.print(name);
                    synchronized (next) {
                        next.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

