package com.mmling.graphqltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class GraphqlTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlTestApplication.class, args);
    }

}
