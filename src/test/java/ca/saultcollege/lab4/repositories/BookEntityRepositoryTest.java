package ca.saultcollege.lab4.repositories;

import ca.saultcollege.lab4.entities.BookEntity;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BookEntityRepositoryTest {

    @Autowired
    private BookEntityRepository bookRepository;
    /**
     * This method will run before each test, ensuring the table is empty.
     * The CommandLineRunner in the main application runs populating the database so you
     * have to delete all records first.
     *
     * Test Context Starts: Spring Boot begins to load the test context.
     * Schema is Created: spring.jpa.hibernate.ddl-auto=create drops and recreates your database tables. The products table is now empty.
     * CommandLineRunner Executes: The run() method in your Lab4Application is triggered. It connects to the now-empty database and inserts its four books (including the two by "Julius Caesar").
     * @BeforeEach Executes: Now, just before your test method runs, your setUp() method is called. It executes bookRepository.deleteAll(), which wipes the four books that the CommandLineRunner just added.
     * @Test Method Executes: Your testSaveAndRetrieveByAuthor() method finally runs. It is now operating against a completely empty products table.
     * It creates and saves its own two books with the author "Julius Caesar".
     * It calls bookRepository.findByAuthor("Julius Caesar").
     * The repository finds exactly the two books that were just inserted by the test itself.
     * Assertion Passes: assertEquals(2, foundBooks.size()) is now true (2 == 2), and the test succeeds.
     */
    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void testSaveAndRetrieveByAuthor() {
        Faker faker = new Faker();

        // 1. CREATE the entity objects
        BookEntity book1 = new BookEntity();
        book1.setTitle(faker.book().title());
        book1.setAuthor("Julius Caesar");
        book1.setPrice(Double.parseDouble(faker.commerce().price(5, 50)));
        book1.setName(book1.getTitle());

        BookEntity book2 = new BookEntity();
        book2.setTitle(faker.book().title());
        book2.setAuthor("Julius Caesar");
        book2.setPrice(Double.parseDouble(faker.commerce().price(5, 50)));
        book2.setName(book2.getTitle());

        // 2. SAVE the entities to the database
        // Use saveAll for efficiency, which returns the saved entities with their generated IDs
        bookRepository.saveAll(Arrays.asList(book1, book2));


        // 3. VERIFY they were saved and can be retrieved

        // Assert that the IDs are no longer null after saving
        assertNotNull(book1.getId());
        assertNotNull(book2.getId());

        // Now, retrieve the books from the database
        List<BookEntity> foundBooks = bookRepository.findByAuthor("Julius Caesar");

        // Assert that the correct number of books were found
        assertEquals(2, foundBooks.size());

        // Optional: A more detailed check on one of the books
        BookEntity retrievedBook = bookRepository.findById(book1.getId()).orElseThrow();
        assertEquals("Julius Caesar", retrievedBook.getAuthor());
        assertEquals(book1.getTitle(), retrievedBook.getTitle());

        // ------------------ UPDATE ------------------
        retrievedBook.setPrice(retrievedBook.getPrice() + 5);
        bookRepository.save(retrievedBook);

        BookEntity updatedBook = bookRepository.findById(book1.getId()).orElseThrow();
        assertEquals(book1.getPrice() + 5, updatedBook.getPrice());

        // ------------------ DELETE ------------------
        bookRepository.deleteById(book1.getId());
        assertFalse(bookRepository.findById(book1.getId()).isPresent());

        List<BookEntity> remainingBooks = bookRepository.findByAuthor("Julius Caesar");
        assertEquals(1, remainingBooks.size()); // book2 should still exist
    }
}
