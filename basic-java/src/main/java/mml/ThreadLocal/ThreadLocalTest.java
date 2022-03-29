package mml.ThreadLocal;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocalMap在内部的set，get和扩容时遇到 Entry不为空key为空，都会清理掉泄漏的Entry
 * hash碰撞时，直接将entry向后移一位（线性探测），清除泄露的entry时，会将后面的entry进行重新判断位置
 *
 * @author Meimengling
 * @date 2022-2-10 16:34
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> THREAD_LOCAL1 = new ThreadLocal<>();
    private static ThreadLocal<String> THREAD_LOCAL2 = new ThreadLocal<>();


    @Test
    public void test1() {
        THREAD_LOCAL1.set("test1");
        THREAD_LOCAL2.set("test2");

        // test1
        System.out.println(THREAD_LOCAL1.get());
        THREAD_LOCAL1.remove();
        // null
        System.out.println(THREAD_LOCAL1.get());
        // test2
        System.out.println(THREAD_LOCAL2.get());
    }

    @Test
    public void test2() {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 2, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(6),
                new ThreadPoolExecutor.DiscardPolicy());

        executorService.execute(() -> THREAD_LOCAL1.set("local1"));

        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> System.out.println(THREAD_LOCAL1.get()));
        }
        System.out.println("end");
        executorService.shutdown();
    }
}
