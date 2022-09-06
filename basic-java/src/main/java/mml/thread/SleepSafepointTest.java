/*
 * COPYRIGHT. ShenZhen JiMi Technology Co., Ltd. 2022.
 * ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system, or transmitted,
 * on any form or by any means, electronic, mechanical, photocopying, recording,
 * or otherwise, without the prior written permission of ShenZhen JiMi Network Technology Co., Ltd.
 *
 * Amendment History:
 *
 * Date                   By              Description
 * -------------------    -----------     -------------------------------------------
 * 2022-09-05             Mei.Mengling   Create the class
 * http://www.jimilab.com/
 */


package mml.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * sleep是一个本地方法，结束时，会检查其他线程有没有在安全点
 * <p>
 * int 时，for循环内部没有安全点， 主线程会等到for循环结束，进入安全点
 * int类型或范围更小的数据类型产生的循环被称为可数循环，默认是不会被放置安全点的。
 * <p>
 * long时，for循环内部会放置安全点，主线程在sleep结束后，就可以结束
 *
 * @author Mei.Mengling
 * @date 2022-09-05
 * @since 1.0.0
 */
public class SleepSafepointTest {

    public static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
//            for (int i = 0; i < 1000000000; i++) {
            for (long i = 0; i < 1000000000; i++) {
                num.getAndAdd(1);
            }
            System.out.println(Thread.currentThread().getName() + "执行结束!");
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("num = " + num);
    }

}
