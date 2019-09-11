package com.etcd.util;

import lombok.extern.slf4j.Slf4j;
import mousio.client.retry.RetryOnce;
import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.promises.EtcdResponsePromise;
import mousio.etcd4j.responses.EtcdAuthenticationException;
import mousio.etcd4j.responses.EtcdException;
import mousio.etcd4j.responses.EtcdKeysResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeoutException;

/**
 * @Author: meimengling
 * @Date: 2019/8/14 10:15
 */
@Component
@Scope("singleton")
@Slf4j
public class EtcdUtil {

    private EtcdClient etcdClient;

    @Autowired
    private Environment env;

    private final String serverName = env.getProperty("serverName"); // 自定义的服务名字，我定义成roomServer

    private final String dirString = "/roomServerList";

    private final String zoneId = env.getProperty("zeonId"); // 自定义的一个标识，我定义成1

    private final String etcdKey = dirString + "/" + zoneId + "/" + serverName; // 这里就是发布的节点

    public EtcdUtil() {
        int nodeCount = Integer.parseInt(env.getProperty("etcdGroupNodeCount"));

        URI[] uris = new URI[nodeCount]; // URI数组，对于集群，把所有集群节点地址加进来，etcd的代码里会轮询这些地址来发布节点，直到成功
        for (int iter = 0; iter < nodeCount; iter++) {
            String urlString = env.getProperty("etcdHost" + new Integer(iter).toString());
            System.out.println(urlString);
            uris[iter] = URI.create(urlString);
        }
        etcdClient = new EtcdClient(uris);
        etcdClient.setRetryHandler(new RetryOnce(20)); //retry策略
    }

    public void regist() { // 注册节点，放在程序启动的入口
        try { // 用put方法发布一个节点
            EtcdResponsePromise<EtcdKeysResponse> p = etcdClient
                    .putDir(etcdKey + "_" + env.getProperty("serverIp") + "_" + env.getProperty("serverPort"))
                    .ttl(60).send();
            p.get(); // 加上这个get()用来保证设置完成，走下一步，get会阻塞，由上面client的retry策略决定阻塞的方式

            new Thread(new GuardEtcd()).start(); // 启动一个守护线程来定时刷新节点

        } catch (Exception e) {
            // TODO: handle exception
            log.error("etcd Server not available.");
        }
    }

    public void destory() {
        try {
            EtcdResponsePromise<EtcdKeysResponse> p = etcdClient
                    .deleteDir(
                            etcdKey + "_" + env.getProperty("serverIp") + "_" + env.getProperty("serverPort"))
                    .recursive().send();
            p.get();
            etcdClient.close();
        } catch (IOException | EtcdException | EtcdAuthenticationException | TimeoutException e) {
            log.error(e.getMessage());
        }
    }

    private class GuardEtcd implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(40 * 1000l);

                    etcdClient.refresh(
                            etcdKey + "_" + env.getProperty("serverIp") + "_" + env.getProperty("serverPort"),
                            60).send();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
