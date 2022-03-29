package thread;

import java.util.concurrent.TimeUnit;

/**
 * @author meimengling
 * @version 1.0
 * @date 2021-5-29 17:25
 */
public class DeadLock {
    private static Object lockA = new Object();
    private static Object lockB = new Object();

    private static void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + ": 获取A锁成功");
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + ": 尝试获取B锁");
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + ": 获取B锁成功");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void run2() {
        synchronized (lockB) {
            System.out.println(Thread.currentThread().getName() + ": 获取B锁成功");
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + ": 尝试获取A锁");
                synchronized (lockA) {
                    System.out.println(Thread.currentThread().getName() + ": 获取A锁成功");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void deadLock() {
        Thread threadA = new Thread(DeadLock::run);
        Thread threadB = new Thread(DeadLock::run2);
        threadA.start();
        threadB.start();
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        deadLock.deadLock();
    }
}
