package com.github.yornellas.personregistryapi.controller;

import com.github.yornellas.personregistryapi.dto.request.PhoneDTO;
import com.github.yornellas.personregistryapi.entity.Phone;
import com.github.yornellas.personregistryapi.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/phones")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping
    public ResponseEntity<List<PhoneDTO>> findAll() {
        List<Phone> list = phoneService.findAll();
        List<PhoneDTO> listDTO = list.stream()
                .map(e -> new PhoneDTO(e.getId(), e.getType(), e.getNumber()))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PhoneDTO> findById(@PathVariable Long id) {
        Phone obj = phoneService.findById(id).orElseThrow(NoSuchElementException::new);
        PhoneDTO objDTO = new PhoneDTO(obj.getId(), obj.getType(), obj.getNumber());
        return ResponseEntity.ok().body(objDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PhoneDTO> create(@RequestBody @Valid PhoneDTO phoneDTO) {
        Phone obj = phoneService.fromDTO(phoneDTO);
        obj = phoneService.create(obj);
        PhoneDTO objDTO = new PhoneDTO(obj.getId(), obj.getType(), obj.getNumber());
        return ResponseEntity.ok().body(objDTO);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PhoneDTO> update(@PathVariable Long id, @RequestBody Phone phone) {
        Phone obj = phoneService.findById(id).orElseThrow(NoSuchElementException::new);
        obj = phoneService.create(obj);
        PhoneDTO objDTO = new PhoneDTO(obj.getId(), obj.getType(), obj.getNumber());
        return ResponseEntity.ok().body(objDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<PhoneDTO> deleteById(@PathVariable Long id) {
        phoneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
