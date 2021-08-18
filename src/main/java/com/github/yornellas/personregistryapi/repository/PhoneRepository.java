package com.github.yornellas.personregistryapi.repository;

import com.github.yornellas.personregistryapi.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
