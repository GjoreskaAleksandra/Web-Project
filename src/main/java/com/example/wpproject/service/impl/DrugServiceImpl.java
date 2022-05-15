package com.example.wpproject.service.impl;

import com.example.wpproject.model.Publisher;
import com.example.wpproject.model.Drug;
import com.example.wpproject.model.Type;
import com.example.wpproject.model.exceptions.PublisherNotFoundException;
import com.example.wpproject.model.exceptions.DrugNotFoundException;
import com.example.wpproject.model.exceptions.TypeNotFoundException;
import com.example.wpproject.repository.PublisherRepository;
import com.example.wpproject.repository.DrugRepository;
import com.example.wpproject.repository.TypeRepository;
import com.example.wpproject.service.DrugService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DrugServiceImpl implements DrugService {

    private final DrugRepository drugRepository;
    private final PublisherRepository publisherRepository;
    private final TypeRepository typeRepository;

    public DrugServiceImpl(DrugRepository drugRepository, PublisherRepository publisherRepository, TypeRepository typeRepository) {
        this.drugRepository = drugRepository;
        this.publisherRepository = publisherRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public List<Drug> findAll() {
        return this.drugRepository.findAll();
    }

    @Override
    public Optional<Drug> findById(Long id) {
        return this.drugRepository.findById(id);
    }

    @Override
    public Optional<Drug> findByName(String name) {
        return this.drugRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Drug> save(String name, Long publisherId, String description, Long typeId, Double price) {
        Publisher publisher = this.publisherRepository.findById(publisherId)
                .orElseThrow(() -> new PublisherNotFoundException(publisherId));
        Type type = this.typeRepository.findById(typeId)
                .orElseThrow(() -> new TypeNotFoundException(typeId));

        this.drugRepository.deleteByName(name);
        return Optional.of(this.drugRepository.save(new Drug(name, description, price, publisher, type)));
    }

    @Override
    @Transactional
    public Optional<Drug> edit(Long id, String name, Long publisherId, String description, Long typeId, Double price) {
        Publisher publisher = this.publisherRepository.findById(publisherId)
                .orElseThrow(() -> new PublisherNotFoundException(publisherId));
        Type type = this.typeRepository.findById(typeId)
                .orElseThrow(() -> new TypeNotFoundException(typeId));
        Drug drug = this.drugRepository.findById(id).orElseThrow(() -> new DrugNotFoundException(id));

        drug.setName(name);
        drug.setDescription(description);
        drug.setType(type);
        drug.setPrice(price);
        drug.setPublisher(publisher);

        return Optional.of(this.drugRepository.save(new Drug(name, description, price, publisher, type)));
    }

    @Override
    public void deleteById(Long id) {
        this.drugRepository.deleteById(id);
    }
}
