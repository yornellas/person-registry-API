package com.github.yornellas.personregistryapi.service;

import com.github.yornellas.personregistryapi.dto.request.PersonDTO;
import com.github.yornellas.personregistryapi.entity.Person;
import com.github.yornellas.personregistryapi.exception.PersonNotFoundException;
import com.github.yornellas.personregistryapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(Long id) throws PersonNotFoundException {
        Optional<Person> obj = personRepository.findById(id);
        if(obj.isEmpty()) {
            throw new PersonNotFoundException(id);
        }
        return personRepository.findById(id);
    }

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Person update(Long id, Person person) throws PersonNotFoundException {
        Optional<Person> obj = personRepository.findById(id);
        if(obj.isEmpty()) {
            throw new PersonNotFoundException(id);
        }
        return personRepository.save(obj.orElseThrow(() -> new PersonNotFoundException(id)));
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        personRepository.deleteById(id);
    }

    public Person fromDTO(PersonDTO personDTO) {
        return new Person(personDTO.getId(), personDTO.getFirstName(), personDTO.getLastName(), personDTO.getCpf(), personDTO.getBirthDate(), personDTO.getPhones());
    }
}
