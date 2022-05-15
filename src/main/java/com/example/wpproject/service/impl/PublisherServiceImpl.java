package com.example.wpproject.service.impl;

import com.example.wpproject.model.Publisher;
import com.example.wpproject.repository.PublisherRepository;
import com.example.wpproject.service.PublisherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Optional<Publisher> findById(Long id) {
        return this.publisherRepository.findById(id);
    }

    @Override
    public List<Publisher> findAll() {
        return this.publisherRepository.findAll();
    }

    @Override
    public Optional<Publisher> save(String name, String surname, String nationality) {
        return Optional.of(this.publisherRepository.save(new Publisher(name, surname, nationality)));
    }

    @Override
    public void deleteById(Long id) {
        this.publisherRepository.deleteById(id);
    }
}
