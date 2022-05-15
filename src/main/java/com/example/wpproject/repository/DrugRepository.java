package com.example.wpproject.repository;

import com.example.wpproject.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
    Optional<Drug> findByName(String name);
    void deleteByName(String name);

}
