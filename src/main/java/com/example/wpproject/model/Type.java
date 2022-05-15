package com.example.wpproject.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2000)
    private String description;

    public Type(String name, String description) {
        this.name=name;
        this.description = description;
    }

    public Type() {

    }
}
