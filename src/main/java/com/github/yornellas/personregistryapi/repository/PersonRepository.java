package com.github.yornellas.personregistryapi.repository;

import com.github.yornellas.personregistryapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
