package com.example.wpproject.service;

import com.example.wpproject.model.Type;

import java.util.List;
import java.util.Optional;

public interface TypeService {

    List<Type> findAll();
    Optional<Type> findById(Long id);
    Type create(String name, String description);
    Type update(String name, String description);
    void delete(String name);
    List<Type> searchType(String searchText);

}
