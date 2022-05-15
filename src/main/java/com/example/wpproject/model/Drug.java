package com.example.wpproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @ManyToOne
    private Publisher publisher;

    @Column(length = 2000)
    private String description;

    @ManyToOne
    private Type type;

    private Double price;

    public Drug(){

    }
    public Drug(String name, String description, Double price, Publisher publisher, Type type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.publisher = publisher;
        this.type = type;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
