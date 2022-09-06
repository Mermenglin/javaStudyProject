package com.jimi.onlinestoresupport;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableApolloConfig
@SpringBootApplication
public class OnlineStoreSupportApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreSupportApplication.class, args);
    }

}
