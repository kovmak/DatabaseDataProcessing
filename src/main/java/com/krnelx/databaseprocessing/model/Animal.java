package main.java.com.krnelx.databaseprocessing.model;

import java.util.Objects;
import java.util.UUID;

public class Animal {

    private UUID id;
    private String name;
    private String species;
    private int age;
    private int enclosureId;

    // Constructor with parameters
    public Animal(UUID id, String name, String species, int age, int enclosureId) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.enclosureId = enclosureId;
    }

    // Default constructor
    public Animal() {
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEnclosureId() {
        return enclosureId;
    }

    public void setEnclosureId(int enclosureId) {
        this.enclosureId = enclosureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age &&
            enclosureId == animal.enclosureId &&
            Objects.equals(id, animal.id) &&
            Objects.equals(name, animal.name) &&
            Objects.equals(species, animal.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, species, age, enclosureId);
    }
}