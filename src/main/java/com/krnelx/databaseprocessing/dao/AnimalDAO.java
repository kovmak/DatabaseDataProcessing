package main.java.com.krnelx.databaseprocessing.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import main.java.com.krnelx.databaseprocessing.model.Animal;

public class AnimalDAO {

    // Establish connection with the SQLite database
    private Connection connect() {
        String url = "jdbc:sqlite:src/main/resources/db/zoo_database";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Add a new animal to the database
    public void addAnimal(Animal animal) {
        String sql = "INSERT INTO Animal(id, name, species, age, enclosure_id) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(animal.getId()));
            pstmt.setString(2, animal.getName());
            pstmt.setString(3, animal.getSpecies());
            pstmt.setInt(4, animal.getAge());
            pstmt.setInt(5, animal.getEnclosureId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Retrieve a list of all animals from the database
    public List<Animal> getAllAnimals() {
        String sql = "SELECT * FROM Animal";
        List<Animal> animals = new ArrayList<>();

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            // Iterate through the query results and create Animal objects
            while (rs.next()) {
                Animal animal = new Animal();
                animal.setId(UUID.fromString(rs.getString("id")));
                animal.setName(rs.getString("name"));
                animal.setSpecies(rs.getString("species"));
                animal.setAge(rs.getInt("age"));
                animal.setEnclosureId(rs.getInt("enclosure_id"));
                animals.add(animal);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return animals;
    }

    // Retrieve an animal by its UUID from the database
    public Animal getAnimalById(UUID id) {
        String sql = "SELECT * FROM Animal WHERE id = ?";
        Animal animal = null;

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String species = rs.getString("species");
                int age = rs.getInt("age");
                int enclosureId = rs.getInt("enclosure_id");

                animal = new Animal(id, name, species, age, enclosureId);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return animal;
    }

    // Update an existing animal in the database
    public void updateAnimal(Animal animal) {
        String sql = "UPDATE Animal SET name = ?, species = ?, age = ?, enclosure_id = ? WHERE id = ?";

        try (Connection conn = this.connect()) {
            if (conn != null) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, animal.getName());
                    pstmt.setString(2, animal.getSpecies());
                    pstmt.setInt(3, animal.getAge());
                    pstmt.setInt(4, animal.getEnclosureId());
                    pstmt.setString(5, animal.getId().toString());
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Connection to the database is null.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    // Delete an animal from the database by its UUID
    public void deleteAnimal(UUID id) {
        String sql = "DELETE FROM Animal WHERE id = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}