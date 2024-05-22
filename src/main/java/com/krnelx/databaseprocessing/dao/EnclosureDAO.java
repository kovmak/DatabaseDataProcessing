package main.java.com.krnelx.databaseprocessing.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import main.java.com.krnelx.databaseprocessing.model.Enclosure;

public class EnclosureDAO {

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

    // Add a new enclosure to the database
    public void addEnclosure(Enclosure enclosure) {
        String sql = "INSERT INTO Enclosure(id, name, type, capacity) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(enclosure.getId()));
            pstmt.setString(2, enclosure.getName());
            pstmt.setString(3, enclosure.getType());
            pstmt.setInt(4, enclosure.getCapacity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Retrieve a list of all enclosures from the database
    public List<Enclosure> getAllEnclosures() {
        String sql = "SELECT * FROM Enclosure";
        List<Enclosure> enclosures = new ArrayList<>();

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            // Iterate through the query results and create Enclosure objects
            while (rs.next()) {
                Enclosure enclosure = new Enclosure();
                enclosure.setId(UUID.fromString(rs.getString("id")));
                enclosure.setName(rs.getString("name"));
                enclosure.setType(rs.getString("type"));
                enclosure.setCapacity(rs.getInt("capacity"));
                enclosures.add(enclosure);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return enclosures;
    }

    // Retrieve an enclosure by its UUID from the database
    public Enclosure getEnclosureById(UUID id) {
        String sql = "SELECT * FROM Enclosure WHERE id = ?";
        Enclosure enclosure = null;

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int capacity = rs.getInt("capacity");
                String type = rs.getString("type");

                enclosure = new Enclosure(id, name, type, capacity);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return enclosure;
    }

    // Update an existing enclosure in the database
    public void updateEnclosure(Enclosure enclosure) {
        String sql = "UPDATE Enclosure SET name = ?, capacity = ? WHERE id = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, enclosure.getName());
            pstmt.setDouble(2, enclosure.getCapacity());
            pstmt.setString(3, enclosure.getId().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete an enclosure from the database by its UUID
    public void deleteEnclosure(UUID id) {
        String sql = "DELETE FROM Enclosure WHERE id = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}