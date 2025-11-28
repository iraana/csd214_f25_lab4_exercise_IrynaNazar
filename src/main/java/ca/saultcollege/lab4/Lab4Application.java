package ca.saultcollege.lab4;

import ca.saultcollege.lab4.entities.*;
import ca.saultcollege.lab4.repositories.*;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneId;
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

        Faker faker = new Faker();
        System.out.println("===== LAB 4 CRUD DEMO =====");


        // -------------------- BOOK CRUD --------------------
        System.out.println("\n--- BOOKS ---");
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
        System.out.println("\n--- Generated Book Entity ---");
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

        // READ all books
        List<BookEntity> allBooks = bookRepo.findAll();
        if (allBooks.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        System.out.println("\n--- Found Books ---");
        // Loop through the list and print each book's details
        for (BookEntity book : allBooks) {
            System.out.println("ID: " + book.getId() +
                    ", Title: " + book.getTitle() +
                    ", Author: " + book.getAuthor() +
                    ", Price: $" + String.format("%.2f", book.getPrice()));
        }

        // READ Julius Caesar books
        System.out.println("\n--- Julius Caesars Books ---");
        List<BookEntity> caesarsBooks = bookRepo.findByAuthor("Julius Caesar");
        // Loop through the list and print each book's details
        for (BookEntity book : caesarsBooks) {
            System.out.println("ID: " + book.getId() +
                    ", Title: " + book.getTitle() +
                    ", Author: " + book.getAuthor() +
                    ", Price: $" + String.format("%.2f", book.getPrice()));
        }


        // -------------------- MAGAZINES CRUD --------------------
        System.out.println("\n--- MAGAZINES CRUD ---");
        MagazineEntity mag = new MagazineEntity();
        mag.setTitle(faker.book().title() + " Magazine");
        mag.setPrice(Double.parseDouble(faker.commerce().price(5, 50)));
        mag.setCurrentIssue(faker.date().future(30, java.util.concurrent.TimeUnit.DAYS)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        mag.setOrderQty(faker.number().numberBetween(10, 200));
        mag.setName(mag.getTitle());

        mag = magazineRepo.save(mag);

        System.out.println("\n---- Created Magazine: " + mag);

        System.out.println("\n--- All Magazines ---");
        List<MagazineEntity> allMags = magazineRepo.findAll();
        for (MagazineEntity m : allMags) {
            System.out.println("ID: " + m.getId() +
                    ", Name: " + m.getName() +
                    ", Price: $" + String.format("%.2f", m.getPrice()));
        }

        mag.setPrice(mag.getPrice() + 5);
        mag = magazineRepo.save(mag);
        System.out.println("\n---- Updated Magazine: " + mag);

        magazineRepo.deleteById(mag.getId());
        System.out.println("\n---- Remaining Magazines: " + magazineRepo.findAll().size());

        // -------------------- DISC MAG CRUD --------------------
        System.out.println("\n--- DISC MAG CRUD ---");
        DiscMagEntity discMag = new DiscMagEntity();
        discMag.setTitle(faker.book().title() + " DiscMag");
        discMag.setPrice(Double.parseDouble(faker.commerce().price(5, 50)));
        discMag.setCopies(30);
        discMag.setHasDisc(true);
        discMag.setName(discMag.getTitle());

        discMagRepo.save(discMag);

        System.out.println("\n---- Saved DiscMag:");
        discMagRepo.findAll().forEach(System.out::println);

        System.out.println("\n--- All DiscMags ---");
        List<DiscMagEntity> allDiscMags = discMagRepo.findAll();
        for (DiscMagEntity d : allDiscMags) {
            System.out.println("ID: " + d.getId() +
                    ", Name: " + d.getName() +
                    ", Copies: " + d.getCopies() +
                    ", Has Disc: " + d.isHasDisc() +
                    ", Price: $" + String.format("%.2f", d.getPrice()));

            discMag.setPrice(discMag.getPrice() + 5);
            discMagRepo.save(discMag);
            System.out.println("\n---- Updated DiscMag:");
            System.out.println(discMagRepo.findById(discMag.getId()).orElse(null));

            discMagRepo.deleteById(discMag.getId());
            System.out.println("\n---- Remaining DiscMags:");
            discMagRepo.findAll().forEach(System.out::println);

            // -------------------- TICKET CRUD --------------------

            System.out.println("\n--- TICKET CRUD ---");
            TicketEntity ticket = new TicketEntity();
            ticket.setDescription("Ticket: " + faker.lorem().word());
            ticket.setPrice(Double.parseDouble(faker.commerce().price(20, 200)));

            ticket.setName(ticket.getDescription());

            ticketRepo.save(ticket);
            System.out.println("\n---- Saved Ticket:");
            ticketRepo.findAll().forEach(System.out::println);

            System.out.println("\n--- All Tickets ---");
            List<TicketEntity> allTickets = ticketRepo.findAll();
            for (TicketEntity t : allTickets) {
                System.out.println("ID: " + t.getId() +
                        ", Name: " + t.getName() +
                        ", Description: " + t.getDescription() +
                        ", Price: $" + String.format("%.2f", t.getPrice()));
            }

            ticket.setPrice(ticket.getPrice() + 10);
            ticketRepo.save(ticket);
            System.out.println("\n---- Updated Ticket:");
            System.out.println(ticketRepo.findById(ticket.getId()).orElse(null));

            ticketRepo.deleteById(ticket.getId());
            System.out.println("\n---- Remaining Tickets after deletion: " + ticketRepo.findAll().size());


            System.out.println("----------------------------------------");
        }
    }


}
