package com.mmling.cassandrademo;

import com.mmling.cassandrademo.entity.Person;
import com.mmling.cassandrademo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CassandraDemoApplicationTests {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void test() {
        System.out.println("==============");
        List<Person> byAge = personRepository.findByAge(23);
//        personRepository.findAllById()
        System.out.println(byAge);
    }

}
