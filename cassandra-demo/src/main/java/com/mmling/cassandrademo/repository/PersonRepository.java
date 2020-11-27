package com.mmling.cassandrademo.repository;

import com.mmling.cassandrademo.entity.Person;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Meimengling
 * @date 2020-11-16 15:44
 */
@Repository
public interface PersonRepository extends CassandraRepository<Person, Integer> {

    @Query(value = "select * from person where age=?0 allow filtering")
    List<Person> findByAge(int age);

    @Query(value = "select * from person where name=?0 allow filtering")
    List<Person> findByName(String name);
}
