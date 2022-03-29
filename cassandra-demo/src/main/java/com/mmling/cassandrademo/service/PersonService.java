package com.mmling.cassandrademo.service;


import com.mmling.cassandrademo.entity.Person;

import java.util.List;
import java.util.UUID;

/**
 * @author uuhnaut
 */
public interface PersonService {

    Person create(Person Person);

    List<Person> getByAge(int age);

    void deleteById(Integer id);
}
