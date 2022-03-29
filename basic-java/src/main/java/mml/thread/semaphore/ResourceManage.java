package thread.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Semaphore 更重要的是保证线程间的互斥，保证执行过程只有限定数量，无法保证线程间资源的同步，
 *
 * @author meimengling
 * @version 1.0
 * @date 2021-6-16 18:24
 */
public class ResourceManage {
    private final Semaphore semaphore;
    private boolean resourceArray[];
    private final ReentrantLock lock;

    public ResourceManage() {
        this.semaphore = new Semaphore(10, true); // 控制10个共享资源的使用，使用先进先出的公平模式进行共享， fair = true 表示公平模式
        this.resourceArray = new boolean[10];
        for (int i = 0; i < 10; i++) {
            resourceArray[i] = true;    //初始化为资源可用的情况
        }
        this.lock = new ReentrantLock(true);  // 可重入互斥锁，fair = true 表示公平模式
    }

    public void useResource(int userId) {
        try {
            semaphore.acquire();
            int id = getResourceId();//占到一个坑
            System.out.println("userId:" + userId + " 正在使用资源，资源id:" + id);
            Thread.sleep(1000);//do something，相当于于使用资源
            resourceArray[id] = true;//退出这个坑

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private int getResourceId() {
        int id = -1;
        /**
         * 资源同步还是需要上锁
         */
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                if (resourceArray[i]) {
                    resourceArray[i] = false;
                    id = i;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return id;
    }
}
