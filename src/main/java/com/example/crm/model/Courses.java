package com.example.crm.model;

import jakarta.persistence.*;

@Entity
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int price;

    public Courses() {}

    public Courses(Long id, String name, String description, int price) {
        this.id = id; this.name = name; this.description = description; this.price = price;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}
