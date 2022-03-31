package mml;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Meimengling
 * @date 2022-3-31 14:35
 */
public class TimerTest {

    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("当前时间: " + new Date() + "n" +
                        "线程名称: " + Thread.currentThread().getName());
            }
        };
        System.out.println("当前时间: " + new Date() + "n" +
                "线程名称: " + Thread.currentThread().getName());
        Timer timer = new Timer("Timer");
        long delay = 1000L;
        timer.schedule(task, delay);
    }

}
