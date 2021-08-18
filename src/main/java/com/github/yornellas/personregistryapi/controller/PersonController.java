package com.github.yornellas.personregistryapi.controller;

import com.github.yornellas.personregistryapi.dto.request.PersonDTO;
import com.github.yornellas.personregistryapi.entity.Person;

import com.github.yornellas.personregistryapi.exception.PersonNotFoundException;
import com.github.yornellas.personregistryapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll() {
        List<Person> list = personService.findAll();
        List<PersonDTO> listDTO = list.stream()
                .map(e -> new PersonDTO(e.getId(), e.getFirstName(), e.getLastName(), e.getCpf(), e.getBirthDate(), e.getPhones()))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) throws PersonNotFoundException {
        Person obj = personService.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        PersonDTO objDTO = new PersonDTO(obj.getId(), obj.getFirstName(), obj.getLastName(), obj.getCpf(), obj.getBirthDate(), obj.getPhones());
        return ResponseEntity.ok().body(objDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonDTO> create(@RequestBody @Valid PersonDTO personDTO) {
        Person obj = personService.fromDTO(personDTO);
        obj = personService.create(obj);
        return ResponseEntity.ok().body(personDTO);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody Person person) throws PersonNotFoundException {
        Person obj = personService.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        obj = personService.create(obj);
        PersonDTO objDTO = new PersonDTO(obj.getId(), obj.getFirstName(), obj.getLastName(), obj.getCpf(), obj.getBirthDate(), obj.getPhones());
        return ResponseEntity.ok().body(objDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<PersonDTO> deleteById(@PathVariable Long id) {
       personService.deleteById(id);
       return ResponseEntity.noContent().build();
    }
}
