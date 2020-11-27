package com.mmling.cassandrademo.controller;

import com.mmling.cassandrademo.entity.Person;
import com.mmling.cassandrademo.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author uuhnaut
 */
@RestController
@RequestMapping(value = "/v1/Person")
public class PersonController {

    private final PersonService PersonService;

    public PersonController(PersonService PersonService) {
        this.PersonService = PersonService;
    }

    @PostMapping("/create")
    public ResponseEntity<Person> create(@RequestBody Person req) {
        Person Person = PersonService.create(req);
        return new ResponseEntity<>(Person, HttpStatus.OK);
    }

    @DeleteMapping("/detele/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        PersonService.deleteById(id);
        return new ResponseEntity<>("Person has been deleted!", HttpStatus.OK);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<Person>> getByAge(@PathVariable int age) {
        List<Person> list = PersonService.getByAge(age);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
