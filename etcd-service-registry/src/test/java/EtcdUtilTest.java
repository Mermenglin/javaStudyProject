import mousio.etcd4j.EtcdClient;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

/**
 * @Author: meimengling
 * @Date: 2019/8/15 14:00
 */
public class EtcdUtilTest {

    @Test
    public void test1() {
        try (EtcdClient etcd = new EtcdClient(URI.create("/192.168.59.20:4001"))) {
            // Logs etcd version
            System.out.println(etcd.getVersion());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
