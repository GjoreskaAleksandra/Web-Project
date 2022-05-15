package com.example.wpproject.service.impl;

import com.example.wpproject.model.Type;
import com.example.wpproject.repository.TypeRepository;
import com.example.wpproject.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public List<Type> findAll() {
        return this.typeRepository.findAll();
    }

    @Override
    public Optional<Type> findById(Long id) {
        return this.typeRepository.findById(id);
    }

    @Override
    public Type create(String name, String description) {
        Type type = new Type(name, description);
        return this.typeRepository.save(type);
    }

    @Override
    public Type update(String name, String description) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }

        Type type = new Type(name, description);
        return this.typeRepository.save(type);
    }

    @Override
    public void delete(String name) {
        this.typeRepository.deleteByName(name);
    }

    @Override
    public List<Type> searchType(String searchText) {
        return this.typeRepository.findAllByNameLike(searchText);
    }
}
