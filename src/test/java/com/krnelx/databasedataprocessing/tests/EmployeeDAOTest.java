package test.java.com.krnelx.databasedataprocessing.tests;

import main.java.com.krnelx.databaseprocessing.dao.EmployeeDAO;
import main.java.com.krnelx.databaseprocessing.model.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class EmployeeDAOTest {

    private EmployeeDAO employeeDAO;

    @Before
    public void setUp() {
        employeeDAO = new EmployeeDAO();
    }

    @After
    public void tearDown() {
        // Optionally clean up any database changes made during tests
    }

    @Test
    public void testAddEmployee() {
        // Test adding an employee to the database
        UUID id = UUID.randomUUID(); // Generate a random UUID for the employee
        Employee employee = new Employee(id, "John Doe", "Manager", 50000.0);
        employeeDAO.addEmployee(employee);

        Employee retrievedEmployee = employeeDAO.getEmployeeById(id);
        assertNotNull(retrievedEmployee);
        assertEquals("John Doe", retrievedEmployee.getName());
        assertEquals("Manager", retrievedEmployee.getPosition());
        assertEquals(50000.0, retrievedEmployee.getSalary(), 0.01);
    }

    @Test
    public void testGetAllEmployees() {
        // Test retrieving all employees from the database
        List<Employee> employees = employeeDAO.getAllEmployees();
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
    }

    @Test
    public void testGetEmployeeById() {
        // Create a test employee
        UUID id = UUID.randomUUID();
        String name = "Test Employee";
        String position = "Test Position";
        double salary = 1000.0;
        Employee testEmployee = new Employee(id, name, position, salary);

        // Add the employee to the database
        employeeDAO.addEmployee(testEmployee);

        // Retrieve the employee by its id from the database
        Employee retrievedEmployee = employeeDAO.getEmployeeById(id);

        // Check if the retrieved employee has the same id as the test employee
        assertEquals(testEmployee.getId(), retrievedEmployee.getId());
    }

    @Test
    public void testUpdateEmployee() {
        // Create a new test employee
        UUID id = UUID.randomUUID();
        String initialName = "Initial Name";
        String position = "Position";
        double salary = 50000.0;
        Employee testEmployee = new Employee(id, initialName, position, salary);

        // Add the test employee to the database
        employeeDAO.addEmployee(testEmployee);

        // Retrieve the test employee from the database
        Employee retrievedEmployee = employeeDAO.getEmployeeById(id);

        // Update the name of the test employee
        String updatedName = "Updated Name";
        retrievedEmployee.setName(updatedName);
        employeeDAO.updateEmployee(retrievedEmployee);

        // Retrieve the updated employee from the database
        Employee updatedEmployee = employeeDAO.getEmployeeById(id);

        // Check if the name of the employee has been updated
        assertEquals(updatedName, updatedEmployee.getName());
    }

    @Test
    public void testDeleteEmployee() {
        // Create a test employee
        UUID id = UUID.randomUUID();
        String name = "Test Employee";
        String position = "Test Position";
        double salary = 1000.0; // Just an example value
        Employee testEmployee = new Employee(id, name, position, salary);

        // Add the employee to the database
        employeeDAO.addEmployee(testEmployee);

        // Retrieve the UUID of the added employee
        UUID employeeId = testEmployee.getId();

        // Delete the employee from the database
        employeeDAO.deleteEmployee(employeeId);

        // Attempt to retrieve the deleted employee
        Employee deletedEmployee = employeeDAO.getEmployeeById(employeeId);

        // Check if the employee has been successfully deleted
        assertNull(deletedEmployee);
    }
}