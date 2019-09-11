package com.redis;

import com.etcd.util.EtcdUtil;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class SpringbootEtcdApplication {

//    @Autowired
//    private RoomServer roomServer;
//    @Autowired
//    private EtcdUtil etcdUtil;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEtcdApplication.class, args);
    }

    /*@Bean
    public RoomServer roomServer() {
        return new RoomServer(Integer.parseInt(Property.getProperty("serverPort")));
    }

    @Override
    public void run(String... args) throws Exception {
        etcdUtil.regist();
        ChannelFuture future = roomServer.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                roomServer.destroy();
            }
        });

        future.channel().closeFuture().syncUninterruptibly();
    }

    @PreDestroy
    public void destory() {
        etcdUtil.destory();
    }*/

}
