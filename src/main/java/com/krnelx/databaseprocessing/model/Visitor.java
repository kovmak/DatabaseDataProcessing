package main.java.com.krnelx.databaseprocessing.model;

import java.util.UUID;

public class Visitor {

    private UUID id;
    private String name;
    private int age;

    // Constructor with parameters
    public Visitor(UUID id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Default constructor
    public Visitor() {
        this.id = UUID.randomUUID();
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}