package thread.semaphore;

/**
 * @author meimengling
 * @version 1.0
 * @date 2021-6-16 18:38
 */
public class ResourceUser implements Runnable {
    private ResourceManage resourceManage;
    private int userId;

    public ResourceUser(ResourceManage resourceManage, int userId) {
        this.resourceManage = resourceManage;
        this.userId = userId;
    }

    @Override
    public void run() {
        System.out.println("userId:" + userId + " 准备使用资源....");
        resourceManage.useResource(userId);
        System.out.println("userId:" + userId + " 使用资源完毕....");
    }

    public static void main(String[] args) {
        ResourceManage resourceManage = new ResourceManage();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new ResourceUser(resourceManage, 10000 + i));
            threads[i] = thread;
        }
        for (int i = 0; i < 100; i++) {
            Thread thread = threads[i];
            try {
                thread.start();//启动线程
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
