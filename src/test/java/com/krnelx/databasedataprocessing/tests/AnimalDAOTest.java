package test.java.com.krnelx.databasedataprocessing.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import main.java.com.krnelx.databaseprocessing.dao.AnimalDAO;
import main.java.com.krnelx.databaseprocessing.model.Animal;

public class AnimalDAOTest {

    private AnimalDAO animalDAO;

    @Before
    public void setUp() {
        // Initialize the AnimalDAO object before each test
        animalDAO = new AnimalDAO();
    }

    @Test
    public void testAddAnimal() {
        // Create a test animal
        UUID id = UUID.randomUUID();
        String name = "Test Animal";
        String species = "Test Species";
        int age = 5;
        int enclosureId = 1;
        Animal testAnimal = new Animal(id, name, species, age, enclosureId);

        // Add the animal to the database
        animalDAO.addAnimal(testAnimal);

        // Retrieve the animal from the database and check if it is added correctly
        Animal addedAnimal = animalDAO.getAnimalById(id);
        assertEquals(testAnimal, addedAnimal);
    }

    @Test
    public void testGetAllAnimals() {
        // Retrieve all animals from the database
        List<Animal> animals = animalDAO.getAllAnimals();

        // Check if the returned list of animals is not empty
        assertFalse(animals.isEmpty());
    }

    @Test
    public void testGetAnimalById() {
        // Create a test animal
        UUID id = UUID.randomUUID();
        String name = "Test Animal";
        String species = "Test Species";
        int age = 5;
        int enclosureId = 1;
        Animal testAnimal = new Animal(id, name, species, age, enclosureId);

        // Add the animal to the database
        animalDAO.addAnimal(testAnimal);

        // Retrieve the animal by its id from the database
        Animal retrievedAnimal = animalDAO.getAnimalById(id);

        // Check if the retrieved animal has the same id as the test animal
        assertEquals(testAnimal.getId(), retrievedAnimal.getId());
    }

    @Test
    public void testUpdateAnimal() {
        // Create a test animal
        UUID id = UUID.randomUUID();
        String name = "Test Animal";
        String species = "Test Species";
        int age = 5;
        int enclosureId = 1;
        Animal testAnimal = new Animal(id, name, species, age, enclosureId);

        // Add the animal to the database
        animalDAO.addAnimal(testAnimal);

        // Update the information of the animal
        String updatedName = "Updated Animal";
        testAnimal.setName(updatedName);
        animalDAO.updateAnimal(testAnimal);

        // Retrieve the animal by its id from the database
        Animal retrievedAnimal = animalDAO.getAnimalById(id);

        // Check if the name of the animal has been updated
        assertEquals(updatedName, retrievedAnimal.getName());
    }

    @Test
    public void testDeleteAnimal() {
        // Create a test animal
        UUID id = UUID.randomUUID();
        String name = "Test Animal";
        String species = "Test Species";
        int age = 5;
        int enclosureId = 1;
        Animal testAnimal = new Animal(id, name, species, age, enclosureId);

        // Add the animal to the database
        animalDAO.addAnimal(testAnimal);

        // Delete the animal from the database
        animalDAO.deleteAnimal(id);

        // Retrieve the animal by its id from the database
        Animal deletedAnimal = animalDAO.getAnimalById(id);

        // Check if the animal has been successfully deleted
        assertNull(deletedAnimal);
    }
}