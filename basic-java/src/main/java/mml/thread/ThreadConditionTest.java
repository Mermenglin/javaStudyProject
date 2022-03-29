package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author meimengling
 * @version 1.0
 * @date 2021-4-25 17:58
 */
public class ThreadConditionTest {
    public static Lock lock = new ReentrantLock();
    public static int count = 0;

    public static Condition conditionA = lock.newCondition();
    public static Condition conditionB = lock.newCondition();
    public static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {
        Thread t1 = new Thread(new test(lock, conditionA, conditionB, "A", false));
        t1.start();
        Thread t2 = new Thread(new test(lock, conditionB, conditionC, "B", true));
        t2.start();
        Thread t3 = new Thread(new test(lock, conditionC, conditionA, "C", true));
        t3.start();
    }

    static class test implements Runnable {
        private Lock lock;
        private Condition condition;
        private Condition conditionNext;
        private String pr;
        private boolean flag;

        public test(Lock lock, Condition condition, Condition conditionNext, String pr, boolean flag) {
            this.lock = lock;
            this.condition = condition;
            this.conditionNext = conditionNext;
            this.pr = pr;
            this.flag = flag;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    if (flag) {
                        condition.await();
                    } else {
                        flag = true;
                        condition.await(100, TimeUnit.MILLISECONDS);
                    }
                    System.out.print(pr);
                    conditionNext.signal();
                }
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }

        }
    }
}
