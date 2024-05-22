package main.java.com.krnelx.databaseprocessing.service;

import java.util.List;
import java.util.UUID;

import main.java.com.krnelx.databaseprocessing.dao.AnimalDAO;
import main.java.com.krnelx.databaseprocessing.dao.EmployeeDAO;
import main.java.com.krnelx.databaseprocessing.dao.EnclosureDAO;
import main.java.com.krnelx.databaseprocessing.dao.VisitorDAO;
import main.java.com.krnelx.databaseprocessing.model.Animal;
import main.java.com.krnelx.databaseprocessing.model.Employee;
import main.java.com.krnelx.databaseprocessing.model.Enclosure;
import main.java.com.krnelx.databaseprocessing.model.Visitor;

public class DatabaseService {

    private final AnimalDAO animalDAO;
    private final EmployeeDAO employeeDAO;
    private final EnclosureDAO enclosureDAO;
    private final VisitorDAO visitorDAO;

    public DatabaseService() {
        this.animalDAO = new AnimalDAO();
        this.employeeDAO = new EmployeeDAO();
        this.enclosureDAO = new EnclosureDAO();
        this.visitorDAO = new VisitorDAO();
    }

    // Animal CRUD operations
    public void addAnimal(UUID id, String name, String species, int age, int enclosureId) {
        Animal animal = new Animal(id, name, species, age, enclosureId);
        animalDAO.addAnimal(animal);
    }

    public List<Animal> getAllAnimals() {
        return animalDAO.getAllAnimals();
    }

    public Animal getAnimalById(UUID id) {
        return animalDAO.getAnimalById(id);
    }

    public void updateAnimal(UUID id, String name, String species, int age, int enclosureId) {
        Animal animal = new Animal(id, name, species, age, enclosureId);
        animalDAO.updateAnimal(animal);
    }

    public void deleteAnimal(UUID id) {
        animalDAO.deleteAnimal(id);
    }

    // Employee CRUD operations
    public void addEmployee(UUID id, String name, String position, double salary) {
        Employee employee = new Employee(id, name, position, salary);
        employeeDAO.addEmployee(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public Employee getEmployeeById(UUID id) {
        return employeeDAO.getEmployeeById(id);
    }

    public void updateEmployee(UUID id, String name, String position, double salary) {
        Employee employee = new Employee(id, name, position, salary);
        employeeDAO.updateEmployee(employee);
    }

    public void deleteEmployee(UUID id) {
        employeeDAO.deleteEmployee(id);
    }

    // Enclosure CRUD operations
    public void addEnclosure(UUID id, String name, String type, int capacity) {
        Enclosure enclosure = new Enclosure(id, name, type, capacity);
        enclosureDAO.addEnclosure(enclosure);
    }

    public List<Enclosure> getAllEnclosures() {
        return enclosureDAO.getAllEnclosures();
    }

    public Enclosure getEnclosureById(UUID id) {
        return enclosureDAO.getEnclosureById(id);
    }

    public void updateEnclosure(UUID id, String name, String type, int capacity) {
        Enclosure enclosure = new Enclosure(id, name, type, capacity);
        enclosureDAO.updateEnclosure(enclosure);
    }

    public void deleteEnclosure(UUID id) {
        enclosureDAO.deleteEnclosure(id);
    }

    // Visitor CRUD operations
    public void addVisitor(UUID id, String name, int age) {
        Visitor visitor = new Visitor(id, name, age);
        visitorDAO.addVisitor(visitor);
    }

    public List<Visitor> getAllVisitors() {
        return visitorDAO.getAllVisitors();
    }

    public Visitor getVisitorById(UUID id) {
        return visitorDAO.getVisitorById(id);
    }

    public void updateVisitor(UUID id, String name, int age) {
        Visitor visitor = new Visitor(id, name, age);
        visitorDAO.updateVisitor(visitor);
    }

    public void deleteVisitor(UUID id) {
        visitorDAO.deleteVisitor(id);
    }
}