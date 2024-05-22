package main.java.com.krnelx.databaseprocessing.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import main.java.com.krnelx.databaseprocessing.model.Visitor;

public class VisitorDAO {

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

    // Add a new visitor to the database
    public void addVisitor(Visitor visitor) {
        String sql = "INSERT INTO Visitor(id, name, age) VALUES(?,?,?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(visitor.getId()));
            pstmt.setString(2, visitor.getName());
            pstmt.setInt(3, visitor.getAge());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Retrieve a list of all visitors from the database
    public List<Visitor> getAllVisitors() {
        String sql = "SELECT * FROM Visitor";
        List<Visitor> visitors = new ArrayList<>();

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            // Iterate through the query results and create Visitor objects
            while (rs.next()) {
                Visitor visitor = new Visitor();
                visitor.setId(UUID.fromString(rs.getString("id")));
                visitor.setName(rs.getString("name"));
                visitor.setAge(rs.getInt("age"));
                visitors.add(visitor);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return visitors;
    }

    // Retrieve a visitor by their identifier
    public Visitor getVisitorById(UUID id) {
        String sql = "SELECT * FROM Visitor WHERE id = ?";
        Visitor visitor = null;

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");

                visitor = new Visitor(id, name, age);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return visitor;
    }

    // Update a visitor
    public void updateVisitor(Visitor visitor) {
        String sql = "UPDATE Visitor SET name = ?, age = ? WHERE id = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, visitor.getName());
            pstmt.setInt(2, visitor.getAge());
            pstmt.setString(3, visitor.getId().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete a visitor
    public void deleteVisitor(UUID id) {
        String sql = "DELETE FROM Visitor WHERE id = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}