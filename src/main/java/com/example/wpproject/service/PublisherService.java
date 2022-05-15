package com.example.wpproject.service;

import com.example.wpproject.model.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
    Optional<Publisher> findById(Long id);
    List<Publisher> findAll();
    Optional<Publisher> save(String name, String surname, String nationality);
    void deleteById(Long id);

}
