package com.github.yornellas.personregistryapi.controller;

import com.github.yornellas.personregistryapi.entity.Person;
import com.github.yornellas.personregistryapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Person> findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @PutMapping(path = "/{id}")
    public Person update(@PathVariable Long id, @RequestBody Person person) {
        Optional<Person> obj = personService.findById(id);
        return personService.update(id, obj.orElseThrow(NoSuchElementException::new));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable Long id) {
       personService.deleteById(id);
    }
}
