package com.example.wpproject.service;

import com.example.wpproject.model.Drug;

import java.util.List;
import java.util.Optional;

public interface DrugService {

    List<Drug> findAll();
    Optional<Drug> findById(Long id);
    Optional<Drug> findByName(String name);
    Optional<Drug> save(String name, Long publisherId, String description, Long typeId, Double price);
    Optional<Drug> edit(Long id, String name, Long publisherId, String description, Long typeId, Double price);
    void deleteById(Long id);

}
