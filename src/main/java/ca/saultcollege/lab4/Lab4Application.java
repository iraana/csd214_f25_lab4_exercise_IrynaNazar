package ca.saultcollege.lab4;

import ca.saultcollege.lab4.entities.*;
import ca.saultcollege.lab4.repositories.*;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Lab4Application implements CommandLineRunner {

    private final BookEntityRepository bookRepo;
    private final MagazineEntityRepository magazineRepo;
    private final DiscMagEntityRepository discMagRepo;
    private final TicketEntityRepository ticketRepo;

    public Lab4Application(BookEntityRepository bookRepo, MagazineEntityRepository magazineRepo, DiscMagEntityRepository discMagRepo, TicketEntityRepository ticketRepo) {
        this.bookRepo = bookRepo;
        this.magazineRepo = magazineRepo;
        this.discMagRepo = discMagRepo;
        this.ticketRepo = ticketRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(Lab4Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
// 1. Create an instance of the Faker class
        Faker faker = new Faker();

        // 2. Generate fake data for the Book entity
        // The Book class from the previous example has a constructor:
        // public Book(String name, double price, String author, String title)

        // Generate a book title and use it for the product name as well
        String bookTitle = faker.book().title();

        // Generate other book-related data
        String author = faker.book().author();
        int copies = 100;
        double price = Double.parseDouble(faker.commerce().price(10, 100)); // Generate a price between 10.00 and 100.00

        // 3. Create a new Book instance with the generated data
        BookEntity randomBook = new BookEntity(author, bookTitle, price, copies);

        // 4. Print the created Book object to the console
        System.out.println("--- Generated Book Entity ---");
        System.out.println(randomBook.toString());

        // --- You can also set the values using setters ---
        System.out.println("\n--- Another Example Using Setters ---");

        BookEntity anotherRandomBook = new BookEntity();
        anotherRandomBook.setTitle(faker.book().title());
        anotherRandomBook.setAuthor(faker.book().author());
        anotherRandomBook.setPrice(Double.parseDouble(faker.commerce().price(5, 50)));
        anotherRandomBook.setName(anotherRandomBook.getTitle()); // Often the name and title are the same for a book product

        BookEntity book1 = new BookEntity();
        book1.setTitle(faker.book().title());
        book1.setAuthor("Julius Caesar");
        book1.setPrice(Double.parseDouble(faker.commerce().price(5, 50)));
        book1.setName(book1.getTitle()); // Often the name and title are the same for a book product

        BookEntity book2 = new BookEntity();
        book2.setTitle(faker.book().title());
        book2.setAuthor("Julius Caesar");
        book2.setPrice(Double.parseDouble(faker.commerce().price(5, 50)));
        book2.setName(book2.getTitle()); // Often the name and title are the same for a book product



        System.out.println(anotherRandomBook.toString());
        bookRepo.save(randomBook);
        bookRepo.save(anotherRandomBook);
        bookRepo.save(book1);
        bookRepo.save(book2);

        List<BookEntity> allBooks = bookRepo.findAll();
        if (allBooks.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        System.out.println("--- Found Books ---");
        // Loop through the list and print each book's details
        for (BookEntity book : allBooks) {
            System.out.println("ID: " + book.getId() +
                    ", Title: " + book.getTitle() +
                    ", Author: " + book.getAuthor() +
                    ", Price: $" + String.format("%.2f", book.getPrice()));
        }

        System.out.println("--- Julius Caesars Books ---");
        List<BookEntity> caesarsBooks = bookRepo.findByAuthor("Julius Caesar");
        // Loop through the list and print each book's details
        for (BookEntity book : caesarsBooks) {
            System.out.println("ID: " + book.getId() +
                    ", Title: " + book.getTitle() +
                    ", Author: " + book.getAuthor() +
                    ", Price: $" + String.format("%.2f", book.getPrice()));
        }

        System.out.println("----------------------------------------");
    }
}
