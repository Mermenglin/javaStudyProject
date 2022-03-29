package thread;

import java.util.concurrent.TimeUnit;

/**
 * 多线程 唤醒-等待
 *
 * @author meimengling
 * @version 1.0
 * @date 2021-4-25 16:38
 */
public class ThreadWatiNotifyTest extends Thread {

    public static void main(String[] args) {
        final Object synObj = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (synObj) {
                    System.out.println("1.T1获取synObj的对象监视器，开始执行同步块");
                    try {
                        TimeUnit.SECONDS.sleep(10);//休息一分钟,不放弃锁
                        System.out.println("T1在 wait()时挂起了");
                        synObj.wait();
                        System.out.println("T1被其他线程唤醒后并重新获得synObj的对象监视器，继续执行");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("T1获取synObj的对象监视器，结束同步块");
                }
            }

            ;
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T2启动，但是因为有别的线程占用了synObj的对象监视器，则等待别的线程执行synObj.wait来释放它");
                synchronized (synObj) {
                    try {
                        System.out.println("T2获取synObj的对象监视器，进入同步块");
                        synObj.notify();
                        System.out.println("T2执行synObj.notify()");
                        System.out.println("T2在 wait()时挂起了");
                        synObj.wait();
                        System.out.println("T2 执行任务");
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println("T2结束同步块，释放synObj的对象监视器");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            ;
        });
        t2.start();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T3启动，但是因为有别的线程占用了synObj的对象监视器，则等待别的线程执行synObj.wait来释放它");
                synchronized (synObj) {
                    try {
                        System.out.println("T3获取synObj的对象监视器，进入同步块");
                        synObj.notify();
                        System.out.println("T3执行synObj.notify()");
                        System.out.println("T3在 wait()时挂起了");
                        synObj.wait();
                        System.out.println("T3 执行任务");
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println("T3结束同步块，释放synObj的对象监视器");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            ;
        });
        t3.start();
    }
}