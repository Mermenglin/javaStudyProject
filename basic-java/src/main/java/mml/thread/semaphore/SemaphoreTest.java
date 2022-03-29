package thread.semaphore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * 简单使用 semaphore 信号量
 * <p>
 * 多个线程，同时5个线程在执行
 *
 * @author meimengling
 * @version 1.0
 * @date 2021-6-16 18:13
 */
public class SemaphoreTest {
    private int threadNum;
    private Semaphore semaphore;

    public SemaphoreTest(int threadNum, Semaphore semaphore) {
        this.threadNum = threadNum;
        this.semaphore = semaphore;
    }

    private void println(String msg) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[YYYY-MM-dd HH:mm:ss.SSS] ");
        System.out.println(simpleDateFormat.format(new Date()) + msg);
    }

    public void test() {
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    println(Thread.currentThread().getName() + " acquire");
                    Thread.sleep(5000);//模拟业务耗时
                    println(Thread.currentThread().getName() + " release");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        SemaphoreTest semaphoreTest = new SemaphoreTest(30, new Semaphore(5, true));
        semaphoreTest.test();
    }
}