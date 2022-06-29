package com.mml.springbootgatewaydemo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableApolloConfig
@SpringBootApplication
public class SpringbootGatewayDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootGatewayDemoApplication.class, args);
    }

}
