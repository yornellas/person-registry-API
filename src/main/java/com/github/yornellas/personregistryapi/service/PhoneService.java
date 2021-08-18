package com.github.yornellas.personregistryapi.service;

import com.github.yornellas.personregistryapi.dto.request.PhoneDTO;
import com.github.yornellas.personregistryapi.entity.Phone;
import com.github.yornellas.personregistryapi.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PhoneService {

    @Autowired
    PhoneRepository phoneRepository;

    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    public Optional<Phone> findById(Long id) {
        return phoneRepository.findById(id);
    }

    public Phone create(Phone phone) {
        return phoneRepository.save(phone);
    }

    public Phone update(Long id, Phone phone) {
        Optional<Phone> obj = phoneRepository.findById(id);
        return phoneRepository.save(obj.orElseThrow(NoSuchElementException::new));
    }

    public void deleteById(Long id) {
        phoneRepository.deleteById(id);
    }

    public Phone fromDTO(PhoneDTO phoneDTO) {
        return new Phone(phoneDTO.getId(), phoneDTO.getType(), phoneDTO.getNumber());
    }
}
