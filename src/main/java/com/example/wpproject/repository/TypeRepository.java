package com.example.wpproject.repository;

import com.example.wpproject.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    List<Type> findAllByNameLike(String text);
    void deleteByName(String name);
}
