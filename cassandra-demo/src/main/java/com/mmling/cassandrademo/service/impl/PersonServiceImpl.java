package com.mmling.cassandrademo.service.impl;

import com.mmling.cassandrademo.entity.Person;
import com.mmling.cassandrademo.repository.PersonRepository;
import com.mmling.cassandrademo.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author uuhnaut
 * @project spring-boot-cassandra-example
 */
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person create(Person Person) {
//        Person.setId(UUIDs.timeBased());
        return personRepository.save(Person);
    }

    @Override
    public List<Person> getByAge(int age) {
        return personRepository.findByAge(age);
    }

    @Override
    public void deleteById(Integer id) {
        personRepository.deleteById(id);
    }

}
